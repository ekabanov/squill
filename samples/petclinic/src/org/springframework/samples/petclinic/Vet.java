package org.springframework.samples.petclinic;

import java.util.*;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

/**
 * Simple JavaBean domain object representing a veterinarian.
 * 
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 */
public class Vet extends Person {
	
	private Set<Specialty> specialties;
	
	private Vet boss;
	
	protected void setSpecialtiesInternal(Set<Specialty> specialties) {
		this.specialties = specialties;
	}
	
	protected Set<Specialty> getSpecialtiesInternal() {
		if (this.specialties == null) {
			this.specialties = new HashSet<Specialty>();
		}
		return this.specialties;
	}
	
	public List<Specialty> getSpecialties() {
		List<Specialty> sortedSpecs = new ArrayList<Specialty>(getSpecialtiesInternal());
		PropertyComparator.sort(sortedSpecs, new MutableSortDefinition("name", true, true));
		return Collections.unmodifiableList(sortedSpecs);
	}
	
	public int getNrOfSpecialties() {
		return getSpecialtiesInternal().size();
	}
	
	public void addSpecialty(Specialty specialty) {
		getSpecialtiesInternal().add(specialty);
	}
	
	public void setBoss(Vet boss) {
		this.boss = boss;
	}
	
	public Vet getBoss() {
		return boss;
	}
	
}
