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
public class SpecialtyData  extends org.springframework.samples.petclinic.Specialty implements squill.WritableDataObject {

	public SpecialtyData() {
		super();
	}

	public SpecialtyData(org.springframework.samples.petclinic.Specialty model) {
		this.setName(model.getName());
		this.setId(model.getId());
	} 

	public static List<org.springframework.samples.petclinic.Specialty> fromDataList(Collection<SpecialtyData> col) {
		List<org.springframework.samples.petclinic.Specialty> result = new ArrayList<org.springframework.samples.petclinic.Specialty>();
		for (SpecialtyData item : col) {
			result.add(item);
		}
		return result;
	}

	public static List<SpecialtyData> toDataList(Collection<org.springframework.samples.petclinic.Specialty> col) {
		List<SpecialtyData> result = new ArrayList<SpecialtyData>();
		for (org.springframework.samples.petclinic.Specialty item : col) {
			result.add(new SpecialtyData(item));
		}
		return result;
	}


	public static SpecialtyData get(Squill squill, java.lang.Integer id) {
		SpecialtyTable t = new SpecialtyTable();
		return squill.from(t).where(eq(t.id, id)).select(t);
	}   
  
	public void update(Squill squill) {
		SpecialtyTable t = new SpecialtyTable();
		squill
		.update(t)
		.where(eq(t.id, getId()))
		.set(
			updateElement(t.name, getName()));
	}  
	
	public void insert(Squill squill) {
		SpecialtyTable t = new SpecialtyTable();
		squill
		.insert(t)
		.values(
			insertElement(t.name, getName()), 
			insertElement(t.id, unchecked(java.lang.Integer.class, "null")));
	}  
	
	public void delete(Squill squill) {
		SpecialtyTable t = new SpecialtyTable();
		squill
		.delete(t)
		.where(eq(t.id, getId()));
	}  
	
	public static class SpecialtyTable extends WritableTable<SpecialtyData> {
	    @Override
	    public String getTableName() { return "specialties"; }
	
	    @Override
	    public Class<SpecialtyData> getTableType() { return SpecialtyData.class; }
	
	    public SpecialtyTable() { }
	
	    public SpecialtyTable(String alias) {
	        super(alias);
	    }
	
	    public final Column<java.lang.String, SpecialtyData> name =
	        new Column<java.lang.String, SpecialtyData>("NAME", java.lang.String.class, "name", this);
	    public final Column<java.lang.Integer, SpecialtyData> id =
	        new Column<java.lang.Integer, SpecialtyData>("ID", java.lang.Integer.class, "id", this);
    
	}
}