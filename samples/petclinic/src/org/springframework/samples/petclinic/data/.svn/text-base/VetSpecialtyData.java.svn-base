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
public class VetSpecialtyData implements squill.ReadableDataObject{

	private java.lang.Integer vetId;
	private java.lang.Integer specialtyId;
	public VetSpecialtyData() {
		super();
	}

	public java.lang.Integer getVetId() { 
		return vetId; 
	}
	
	public void setVetId(java.lang.Integer vetId) { 
		this.vetId = vetId; 
	}
	
	public java.lang.Integer getSpecialtyId() { 
		return specialtyId; 
	}
	
	public void setSpecialtyId(java.lang.Integer specialtyId) { 
		this.specialtyId = specialtyId; 
	}
	
	public static class VetSpecialtyTable extends WritableTable<VetSpecialtyData> {
	    @Override
	    public String getTableName() { return "vet_specialties"; }
	
	    @Override
	    public Class<VetSpecialtyData> getTableType() { return VetSpecialtyData.class; }
	
	    public VetSpecialtyTable() { }
	
	    public VetSpecialtyTable(String alias) {
	        super(alias);
	    }
	
	    public final Column<java.lang.Integer, VetSpecialtyData> vetId =
	        new Column<java.lang.Integer, VetSpecialtyData>("VET_ID", java.lang.Integer.class, "vetId", this);
	    public final Column<java.lang.Integer, VetSpecialtyData> specialtyId =
	        new Column<java.lang.Integer, VetSpecialtyData>("SPECIALTY_ID", java.lang.Integer.class, "specialtyId", this);
    
		private class VetTableByVetId extends VetData.VetTable implements OrmJoin {
			public ReadableTable getTable() {
		  		return VetTableByVetId.this;
			}

			public Column<?, ?> getSource() {
				return VetSpecialtyTable.this.vetId;
			}
		  
	  		public Column<?, ?> getTarget() {
	  			return VetTableByVetId.this.id;
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
				vet = new VetTableByVetId();
			}
			return vet;
		}
		
		private class SpecialtyTableBySpecialtyId extends SpecialtyData.SpecialtyTable implements OrmJoin {
			public ReadableTable getTable() {
		  		return SpecialtyTableBySpecialtyId.this;
			}

			public Column<?, ?> getSource() {
				return VetSpecialtyTable.this.specialtyId;
			}
		  
	  		public Column<?, ?> getTarget() {
	  			return SpecialtyTableBySpecialtyId.this.id;
	  		}
  		
	  		public JoinType getJoinType() {
	  			return JoinType.INNER;
	  		} 
		  
			public boolean isJoin() {
				return true;
			}
		};
		      
		private SpecialtyData.SpecialtyTable specialty;
		
		public SpecialtyData.SpecialtyTable specialty() {
			if (specialty == null){
				specialty = new SpecialtyTableBySpecialtyId();
			}
			return specialty;
		}
		
	}
}