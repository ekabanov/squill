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
public class VisitData  extends org.springframework.samples.petclinic.Visit implements squill.WritableDataObject {

	private java.util.Date visitDate;
	private java.lang.Integer petId;
	public VisitData() {
		super();
	}

	public VisitData(org.springframework.samples.petclinic.Visit model) {
		this.setDescription(model.getDescription());
		this.setId(model.getId());
	} 

	public static List<org.springframework.samples.petclinic.Visit> fromDataList(Collection<VisitData> col) {
		List<org.springframework.samples.petclinic.Visit> result = new ArrayList<org.springframework.samples.petclinic.Visit>();
		for (VisitData item : col) {
			result.add(item);
		}
		return result;
	}

	public static List<VisitData> toDataList(Collection<org.springframework.samples.petclinic.Visit> col) {
		List<VisitData> result = new ArrayList<VisitData>();
		for (org.springframework.samples.petclinic.Visit item : col) {
			result.add(new VisitData(item));
		}
		return result;
	}

	public java.util.Date getVisitDate() { 
		return visitDate; 
	}
	
	public void setVisitDate(java.util.Date visitDate) { 
		this.visitDate = visitDate; 
	}
	
	public java.lang.Integer getPetId() { 
		return petId; 
	}
	
	public void setPetId(java.lang.Integer petId) { 
		this.petId = petId; 
	}
	

	public static VisitData get(Squill squill, java.lang.Integer id) {
		VisitTable t = new VisitTable();
		return squill.from(t).where(eq(t.id, id)).select(t);
	}   
  
	public void update(Squill squill) {
		VisitTable t = new VisitTable();
		squill
		.update(t)
		.where(eq(t.id, getId()))
		.set(
			updateElement(t.visitDate, getVisitDate()), 
			updateElement(t.description, getDescription()), 
			updateElement(t.petId, getPetId()));
	}  
	
	public void insert(Squill squill) {
		VisitTable t = new VisitTable();
		squill
		.insert(t)
		.values(
			insertElement(t.visitDate, getVisitDate()), 
			insertElement(t.description, getDescription()), 
			insertElement(t.id, unchecked(java.lang.Integer.class, "null")), 
			insertElement(t.petId, getPetId()));
	}  
	
	public void delete(Squill squill) {
		VisitTable t = new VisitTable();
		squill
		.delete(t)
		.where(eq(t.id, getId()));
	}  
	
	public static class VisitTable extends WritableTable<VisitData> {
	    @Override
	    public String getTableName() { return "visits"; }
	
	    @Override
	    public Class<VisitData> getTableType() { return VisitData.class; }
	
	    public VisitTable() { }
	
	    public VisitTable(String alias) {
	        super(alias);
	    }
	
	    public final Column<java.util.Date, VisitData> visitDate =
	        new Column<java.util.Date, VisitData>("VISIT_DATE", java.util.Date.class, "visitDate", this);
	    public final Column<java.lang.String, VisitData> description =
	        new Column<java.lang.String, VisitData>("DESCRIPTION", java.lang.String.class, "description", this);
	    public final Column<java.lang.Integer, VisitData> id =
	        new Column<java.lang.Integer, VisitData>("ID", java.lang.Integer.class, "id", this);
	    public final Column<java.lang.Integer, VisitData> petId =
	        new Column<java.lang.Integer, VisitData>("PET_ID", java.lang.Integer.class, "petId", this);
    
		private class PetTableByPetId extends PetData.PetTable implements OrmJoin {
			public ReadableTable getTable() {
		  		return PetTableByPetId.this;
			}

			public Column<?, ?> getSource() {
				return VisitTable.this.petId;
			}
		  
	  		public Column<?, ?> getTarget() {
	  			return PetTableByPetId.this.id;
	  		}
  		
	  		public JoinType getJoinType() {
	  			return JoinType.INNER;
	  		} 
		  
			public boolean isJoin() {
				return true;
			}
		};
		      
		private PetData.PetTable pet;
		
		public PetData.PetTable pet() {
			if (pet == null){
				pet = new PetTableByPetId();
			}
			return pet;
		}
		
	}
}