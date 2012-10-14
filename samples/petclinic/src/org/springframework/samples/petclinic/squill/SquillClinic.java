package org.springframework.samples.petclinic.squill;

import static squill.functions.Operations.asc;
import static squill.functions.Operations.eq;
import static squill.functions.Operations.like;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.samples.petclinic.*;
import org.springframework.samples.petclinic.data.*;
import org.springframework.samples.petclinic.data.OwnerData.OwnerTable;
import org.springframework.samples.petclinic.data.PetData.PetTable;
import org.springframework.samples.petclinic.data.PetTypeData.PetTypeTable;
import org.springframework.samples.petclinic.data.SpecialtyData.SpecialtyTable;
import org.springframework.samples.petclinic.data.VetData.VetTable;
import org.springframework.samples.petclinic.data.VetSpecialtyData.VetSpecialtyTable;
import org.springframework.samples.petclinic.data.VisitData.VisitTable;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import squill.Squill;
import squill.tuple.Tuple2;

/**
 * Communication with database implemented using Squill framework.
 */
@Service
@ManagedResource("petclinic:type=Clinic")
public class SquillClinic implements Clinic {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	/** You need an instance of this class to query the database */
	private Squill squill;
	
	private final List<Vet> vets = new ArrayList<Vet>();
	
	@Autowired
	public void init(DataSource dataSource) {
		squill = new Squill(dataSource);
	}
	
	@ManagedOperation
	@Transactional(readOnly = true)
	public void refreshVetsCache() throws DataAccessException {
		synchronized (this.vets) {
			this.logger.info("Refreshing vets cache");
			
			/**
			 * This is the Java class that has information about VETS table in
			 * database i.e. fields (fields carry their datatype) and if table
			 * is a read-only or writable. We use v to refer to this table in
			 * the query below.
			 */
			VetTable v = new VetTable();
			
			// Retrieve the list of all vets.
			this.vets.clear();
			
			/**
			 * Query results in SELECT * FROM vets ORDER BY last_name ASC,
			 * first_name ASC Notice that as a result we get list of objects of
			 * Vet (actually we get its supertype VetData (that has Squill
			 * convenice methods))
			 */
			this.vets.addAll(squill.from(v).orderBy(asc(v.lastName), asc(v.firstName)).selectList(v));
			
			// Retrieve the list of all possible specialties.
			
			/**
			 * Again a Java class to represent database table named specialities
			 */
			SpecialtyTable s = new SpecialtyTable();
			
			/**
			 * This results in SELECT * FROM specialties
			 * 
			 * Due to the fact that Squill returns list of SpecialtyData (that
			 * extends Speciality) not Specialty, it means that there is need
			 * for conversion.
			 */
			final List<SpecialtyData> specialtyDataList = squill.from(s).selectList(s);
			final List<Specialty> specialties = SpecialtyData.fromDataList(specialtyDataList);
			
			// Build each vet's list of specialties and bosses.
			for (Vet vet : this.vets) {
				VetTable vBoss = new VetTable();
				VetData vetData = (VetData) vet;
				
				// Here Squill shall return null, if vetData.getBossId() == null
				VetData vetBossData = squill.from(vBoss).where(eq(vBoss.id, vetData.getBossId())).select(vBoss);
				vet.setBoss(vetBossData);
				
				VetSpecialtyTable vs = new VetSpecialtyTable();
				/**
				 * This results in SELECT specialty_id FROM vet_specialties
				 * WHERE id=? We only select a specific field and receive list
				 * of that type.
				 */
				final List<Integer> vetSpecialtiesIds = 
					squill.from(vs).where(eq(vs.vetId, vet.getId())).selectList(vs.specialtyId);
						
				// This code came from PetClinic, not from Squill
				for (int specialtyId : vetSpecialtiesIds) {
					Specialty specialty = EntityUtils.getById(specialties, Specialty.class, specialtyId);
					vet.addSpecialty(specialty);
				}
			}
		}
	}
	
	@Transactional(readOnly = true)
	public Collection<Vet> getVets() throws DataAccessException {
		synchronized (this.vets) {
			if (this.vets.isEmpty()) {
				refreshVetsCache();
			}
			return this.vets;
		}
	}
	
	@Transactional(readOnly = true)
	public Collection<PetType> getPetTypes() throws DataAccessException {
		PetTypeTable pt = new PetTypeTable();
		/**
		 * Due to the fact that all columns are selected (whole object) squill
		 * query returns in List<PetTypeData> that must be converted int
		 * List<Pet>
		 */
		return PetTypeData.fromDataList(squill.from(pt).selectList(pt));
	}
	
	@Transactional(readOnly = true)
	public Collection<Owner> findOwners(String lastName) throws DataAccessException {
		OwnerTable owner = new OwnerTable();
		List<OwnerData> ownerList =
		/**
		 * This result in SELECT * FROM owner WHERE last_name like ?%
		 */
		squill.from(owner).where(like(owner.lastName, lastName + "%")).selectList(owner);
		
		/** Again, we need to cast */
		List<Owner> owners = OwnerData.fromDataList(ownerList);
		
		loadOwnersPetsAndVisits(owners);
		return owners;
	}
	
	/**
	 * Loads the {@link Owner} with the supplied <code>id</code>; also loads the
	 * {@link Pet Pets} and {@link Visit Visits} for the corresponding owner, if
	 * not already loaded.
	 */
	@Transactional(readOnly = true)
	public Owner loadOwner(int id) throws DataAccessException {
		Owner owner = squill.get(OwnerData.class, id);
		loadPetsAndVisits(owner);
		return owner;
	}
	
	@Transactional(readOnly = true)
	public Pet loadPet(int id) throws DataAccessException {
		PetData pet = PetData.get(squill, id);
		Owner owner = loadOwner(pet.getOwnerId());
		owner.addPet(pet);
		pet.setType(EntityUtils.getById(getPetTypes(), PetType.class, pet.getTypeId()));
		loadVisits(pet);
		return pet;
	}
	
	@Transactional
	public void storeOwner(Owner owner) throws DataAccessException {
		OwnerData ownerData = new OwnerData(owner);
		if (owner.isNew()) {
			squill.insertDataObject(ownerData);
			owner.setId(squill.uncheckedQuery("call identity()", Integer.class));
		} else {
			squill.updateDataObject(ownerData);
		}
	}
	
	@Transactional
	public void storePet(Pet pet) throws DataAccessException {
		PetData petData = new PetData(pet);
		petData.setOwnerId(pet.getOwner().getId());
		petData.setTypeId(pet.getType().getId());
		
		if (pet.isNew()) {
			squill.insertDataObject(petData);
			// what was the value for id?
			pet.setId(squill.uncheckedQuery("call identity()", Integer.class));
		} else {
			squill.updateDataObject(petData);
		}
	}
	
	@Transactional
	public void storeVisit(Visit visit) throws DataAccessException {
		VisitData visitData = new VisitData(visit);
		visitData.setPetId(visit.getPet().getId());
		
		if (visit.isNew()) {
			/**
			 * This means that new object is inserted into db.
			 */
			squill.insertDataObject(visitData);
			/**
			 * And now we make a query to see what id did it receive
			 */
			visit.setId(squill.uncheckedQuery("call identity()", Integer.class));
		} else {
			throw new UnsupportedOperationException("Visit update not supported");
		}
	}
	
	/**
	 * Loads the {@link Visit} data for the supplied {@link Pet}.
	 */
	private void loadVisits(Pet pet) {
		
		VisitTable v = new VisitTable();
		
    for (Visit visit : squill
        .from(v)
        .where(eq(v.petId, pet.getId()))
        .selectList(v)) {			
			pet.addVisit(visit);
		}
	}
	
	/**
	 * Loads the {@link Pet} and {@link Visit} data for the supplied
	 * {@link Owner}.
	 */
	private void loadPetsAndVisits(final Owner owner) {
		
		PetTable p = new PetTable();
		
    for (Tuple2<PetData,PetTypeData> petAndType : 
      squill
      .from(p, p.petType())
      .where(eq(p.ownerId, owner.getId()))
      .selectList(p, p.petType())) {	
			
			Pet pet = petAndType.v1;
			owner.addPet(pet);
			pet.setType(petAndType.v2);
			
			loadVisits(pet);
		}
	}
	
	/**
	 * Loads the {@link Pet} and {@link Visit} data for the supplied
	 * {@link List} of {@link Owner Owners}.
	 * 
	 * @param owners the list of owners for whom the pet and visit data should
	 *            be loaded
	 * @see #loadPetsAndVisits(Owner)
	 */
	private void loadOwnersPetsAndVisits(List<Owner> owners) {
		for (Owner owner : owners) {
			loadPetsAndVisits(owner);
		}
	}
}
