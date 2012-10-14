package org.springframework.samples.petclinic.data;

import squill.Squill;
import squill.query.select.Column;
import squill.query.select.ReadableTable;
import squill.query.select.WritableTable;
import squill.query.from.OrmJoin;
import squill.query.JoinType;
import java.util.Date;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import static java.lang.String.format;
import static squill.functions.Operations.*;

/**
 * This class was generated automatically by SquillGen. 
 * Do not edit anything in this class. Your changes will be lost after the SquillGen runs again.
 */
public class PetData  extends org.springframework.samples.petclinic.Pet implements squill.WritableDataObject {

	private java.lang.Integer ownerId;
	private java.lang.Integer typeId;
	public PetData() {
		super();
	}

	public PetData(org.springframework.samples.petclinic.Pet model) {
		this.setName(model.getName());
		this.setBirthDate(model.getBirthDate());
		this.setId(model.getId());
	} 

	public static List<org.springframework.samples.petclinic.Pet> fromDataList(Collection<PetData> col) {
		List<org.springframework.samples.petclinic.Pet> result = new ArrayList<org.springframework.samples.petclinic.Pet>();
		for (PetData item : col) {
			result.add(item);
		}
		return result;
	}

	public static List<PetData> toDataList(Collection<org.springframework.samples.petclinic.Pet> col) {
		List<PetData> result = new ArrayList<PetData>();
		for (org.springframework.samples.petclinic.Pet item : col) {
			result.add(new PetData(item));
		}
		return result;
	}

	public java.lang.Integer getOwnerId() { 
		return ownerId; 
	}
	
	public void setOwnerId(java.lang.Integer ownerId) { 
		this.ownerId = ownerId; 
	}
	
	public java.lang.Integer getTypeId() { 
		return typeId; 
	}
	
	public void setTypeId(java.lang.Integer typeId) { 
		this.typeId = typeId; 
	}
	

	public static PetData get(Squill squill, java.lang.Integer id) {
		PetTable t = new PetTable();
		return squill.from(t).where(eq(t.id, id)).select(t);
	}   
  
	public void update(Squill squill) {
		PetTable t = new PetTable();
		squill
		.update(t)
		.where(eq(t.id, getId()))
		.set(
			updateElement(t.ownerId, getOwnerId()), 
			updateElement(t.name, getName()), 
			updateElement(t.birthDate, getBirthDate()), 
			updateElement(t.typeId, getTypeId()));
	}  
	
	public void insert(Squill squill) {
		PetTable t = new PetTable();
		squill
		.insert(t)
		.values(
			insertElement(t.ownerId, getOwnerId()), 
			insertElement(t.name, getName()), 
			insertElement(t.birthDate, getBirthDate()), 
			insertElement(t.id, unchecked(java.lang.Integer.class, "null")), 
			insertElement(t.typeId, getTypeId()));
	}  
	
	public void delete(Squill squill) {
		PetTable t = new PetTable();
		squill
		.delete(t)
		.where(eq(t.id, getId()));
	}  
	
	public static class PetTable extends WritableTable<PetData> {
	    @Override
	    public String getTableName() { return "pets"; }
	
	    @Override
	    public Class<PetData> getTableType() { return PetData.class; }
	
	    public PetTable() { }
	
	    public PetTable(String alias) {
	        super(alias);
	    }
	
	    public final Column<java.lang.Integer, PetData> ownerId =
	        new Column<java.lang.Integer, PetData>("OWNER_ID", java.lang.Integer.class, "ownerId", this);
	    public final Column<java.lang.String, PetData> name =
	        new Column<java.lang.String, PetData>("NAME", java.lang.String.class, "name", this);
	    public final Column<java.util.Date, PetData> birthDate =
	        new Column<java.util.Date, PetData>("BIRTH_DATE", java.util.Date.class, "birthDate", this);
	    public final Column<java.lang.Integer, PetData> id =
	        new Column<java.lang.Integer, PetData>("ID", java.lang.Integer.class, "id", this);
	    public final Column<java.lang.Integer, PetData> typeId =
	        new Column<java.lang.Integer, PetData>("TYPE_ID", java.lang.Integer.class, "typeId", this);
    
		private class PetTypeTableByTypeId extends PetTypeData.PetTypeTable implements OrmJoin {
			public ReadableTable getTable() {
		  		return PetTypeTableByTypeId.this;
			}

			public Column<?, ?> getSource() {
				return PetTable.this.typeId;
			}
		  
	  		public Column<?, ?> getTarget() {
	  			return PetTypeTableByTypeId.this.id;
	  		}
  		
	  		public JoinType getJoinType() {
	  			return JoinType.INNER;
	  		} 
		  
			public boolean isJoin() {
				return true;
			}
		};
		      
		private PetTypeData.PetTypeTable petType;
		
		public PetTypeData.PetTypeTable petType() {
			if (petType == null){
				petType = new PetTypeTableByTypeId();
			}
			return petType;
		}
		
		private class OwnerTableByOwnerId extends OwnerData.OwnerTable implements OrmJoin {
			public ReadableTable getTable() {
		  		return OwnerTableByOwnerId.this;
			}

			public Column<?, ?> getSource() {
				return PetTable.this.ownerId;
			}
		  
	  		public Column<?, ?> getTarget() {
	  			return OwnerTableByOwnerId.this.id;
	  		}
  		
	  		public JoinType getJoinType() {
	  			return JoinType.INNER;
	  		} 
		  
			public boolean isJoin() {
				return true;
			}
		};
		      
		private OwnerData.OwnerTable owner;
		
		public OwnerData.OwnerTable owner() {
			if (owner == null){
				owner = new OwnerTableByOwnerId();
			}
			return owner;
		}
		
	}
}