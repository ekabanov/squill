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
public class PetTypeData  extends org.springframework.samples.petclinic.PetType implements squill.WritableDataObject {

	public PetTypeData() {
		super();
	}

	public PetTypeData(org.springframework.samples.petclinic.PetType model) {
		this.setName(model.getName());
		this.setId(model.getId());
	} 

	public static List<org.springframework.samples.petclinic.PetType> fromDataList(Collection<PetTypeData> col) {
		List<org.springframework.samples.petclinic.PetType> result = new ArrayList<org.springframework.samples.petclinic.PetType>();
		for (PetTypeData item : col) {
			result.add(item);
		}
		return result;
	}

	public static List<PetTypeData> toDataList(Collection<org.springframework.samples.petclinic.PetType> col) {
		List<PetTypeData> result = new ArrayList<PetTypeData>();
		for (org.springframework.samples.petclinic.PetType item : col) {
			result.add(new PetTypeData(item));
		}
		return result;
	}


	public static PetTypeData get(Squill squill, java.lang.Integer id) {
		PetTypeTable t = new PetTypeTable();
		return squill.from(t).where(eq(t.id, id)).select(t);
	}   
  
	public void update(Squill squill) {
		PetTypeTable t = new PetTypeTable();
		squill
		.update(t)
		.where(eq(t.id, getId()))
		.set(
			updateElement(t.name, getName()));
	}  
	
	public void insert(Squill squill) {
		PetTypeTable t = new PetTypeTable();
		squill
		.insert(t)
		.values(
			insertElement(t.name, getName()), 
			insertElement(t.id, unchecked(java.lang.Integer.class, "null")));
	}  
	
	public void delete(Squill squill) {
		PetTypeTable t = new PetTypeTable();
		squill
		.delete(t)
		.where(eq(t.id, getId()));
	}  
	
	public static class PetTypeTable extends WritableTable<PetTypeData> {
	    @Override
	    public String getTableName() { return "types"; }
	
	    @Override
	    public Class<PetTypeData> getTableType() { return PetTypeData.class; }
	
	    public PetTypeTable() { }
	
	    public PetTypeTable(String alias) {
	        super(alias);
	    }
	
	    public final Column<java.lang.String, PetTypeData> name =
	        new Column<java.lang.String, PetTypeData>("NAME", java.lang.String.class, "name", this);
	    public final Column<java.lang.Integer, PetTypeData> id =
	        new Column<java.lang.Integer, PetTypeData>("ID", java.lang.Integer.class, "id", this);
    
	}
}