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
public class VetData  extends org.springframework.samples.petclinic.Vet implements squill.WritableDataObject {

	private java.lang.Integer bossId;
	public VetData() {
		super();
	}

	public VetData(org.springframework.samples.petclinic.Vet model) {
		this.setFirstName(model.getFirstName());
		this.setId(model.getId());
		this.setLastName(model.getLastName());
	} 

	public static List<org.springframework.samples.petclinic.Vet> fromDataList(Collection<VetData> col) {
		List<org.springframework.samples.petclinic.Vet> result = new ArrayList<org.springframework.samples.petclinic.Vet>();
		for (VetData item : col) {
			result.add(item);
		}
		return result;
	}

	public static List<VetData> toDataList(Collection<org.springframework.samples.petclinic.Vet> col) {
		List<VetData> result = new ArrayList<VetData>();
		for (org.springframework.samples.petclinic.Vet item : col) {
			result.add(new VetData(item));
		}
		return result;
	}

	public java.lang.Integer getBossId() { 
		return bossId; 
	}
	
	public void setBossId(java.lang.Integer bossId) { 
		this.bossId = bossId; 
	}
	

	public static VetData get(Squill squill, java.lang.Integer id) {
		VetTable t = new VetTable();
		return squill.from(t).where(eq(t.id, id)).select(t);
	}   
  
	public void update(Squill squill) {
		VetTable t = new VetTable();
		squill
		.update(t)
		.where(eq(t.id, getId()))
		.set(
			updateElement(t.firstName, getFirstName()), 
			updateElement(t.bossId, getBossId()), 
			updateElement(t.lastName, getLastName()));
	}  
	
	public void insert(Squill squill) {
		VetTable t = new VetTable();
		squill
		.insert(t)
		.values(
			insertElement(t.firstName, getFirstName()), 
			insertElement(t.bossId, getBossId()), 
			insertElement(t.id, unchecked(java.lang.Integer.class, "null")), 
			insertElement(t.lastName, getLastName()));
	}  
	
	public void delete(Squill squill) {
		VetTable t = new VetTable();
		squill
		.delete(t)
		.where(eq(t.id, getId()));
	}  
	
	public static class VetTable extends WritableTable<VetData> {
	    @Override
	    public String getTableName() { return "vets"; }
	
	    @Override
	    public Class<VetData> getTableType() { return VetData.class; }
	
	    public VetTable() { }
	
	    public VetTable(String alias) {
	        super(alias);
	    }
	
	    public final Column<java.lang.String, VetData> firstName =
	        new Column<java.lang.String, VetData>("FIRST_NAME", java.lang.String.class, "firstName", this);
	    public final Column<java.lang.Integer, VetData> bossId =
	        new Column<java.lang.Integer, VetData>("BOSS_ID", java.lang.Integer.class, "bossId", this);
	    public final Column<java.lang.Integer, VetData> id =
	        new Column<java.lang.Integer, VetData>("ID", java.lang.Integer.class, "id", this);
	    public final Column<java.lang.String, VetData> lastName =
	        new Column<java.lang.String, VetData>("LAST_NAME", java.lang.String.class, "lastName", this);
    
		private class VetTableByBossId extends VetData.VetTable implements OrmJoin {
			public ReadableTable getTable() {
		  		return VetTableByBossId.this;
			}

			public Column<?, ?> getSource() {
				return VetTable.this.bossId;
			}
		  
	  		public Column<?, ?> getTarget() {
	  			return VetTableByBossId.this.id;
	  		}
  		
	  		public JoinType getJoinType() {
	  			return JoinType.INNER;
	  		} 
		  
			public boolean isJoin() {
				return true;
			}
		};
		      
		private VetData.VetTable vet;
		
		public VetData.VetTable vet() {
			if (vet == null){
				vet = new VetTableByBossId();
			}
			return vet;
		}
		
	}
}