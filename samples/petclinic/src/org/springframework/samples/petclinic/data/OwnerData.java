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
public class OwnerData  extends org.springframework.samples.petclinic.Owner implements squill.WritableDataObject {

	public OwnerData() {
		super();
	}

	public OwnerData(org.springframework.samples.petclinic.Owner model) {
		this.setFirstName(model.getFirstName());
		this.setAddress(model.getAddress());
		this.setId(model.getId());
		this.setCity(model.getCity());
		this.setTelephone(model.getTelephone());
		this.setLastName(model.getLastName());
	} 

	public static List<org.springframework.samples.petclinic.Owner> fromDataList(Collection<OwnerData> col) {
		List<org.springframework.samples.petclinic.Owner> result = new ArrayList<org.springframework.samples.petclinic.Owner>();
		for (OwnerData item : col) {
			result.add(item);
		}
		return result;
	}

	public static List<OwnerData> toDataList(Collection<org.springframework.samples.petclinic.Owner> col) {
		List<OwnerData> result = new ArrayList<OwnerData>();
		for (org.springframework.samples.petclinic.Owner item : col) {
			result.add(new OwnerData(item));
		}
		return result;
	}


	public static OwnerData get(Squill squill, java.lang.Integer id) {
		OwnerTable t = new OwnerTable();
		return squill.from(t).where(eq(t.id, id)).select(t);
	}   
  
	public void update(Squill squill) {
		OwnerTable t = new OwnerTable();
		squill
		.update(t)
		.where(eq(t.id, getId()))
		.set(
			updateElement(t.firstName, getFirstName()), 
			updateElement(t.address, getAddress()), 
			updateElement(t.city, getCity()), 
			updateElement(t.telephone, getTelephone()), 
			updateElement(t.lastName, getLastName()));
	}  
	
	public void insert(Squill squill) {
		OwnerTable t = new OwnerTable();
		squill
		.insert(t)
		.values(
			insertElement(t.firstName, getFirstName()), 
			insertElement(t.address, getAddress()), 
			insertElement(t.id, unchecked(java.lang.Integer.class, "null")), 
			insertElement(t.city, getCity()), 
			insertElement(t.telephone, getTelephone()), 
			insertElement(t.lastName, getLastName()));
	}  
	
	public void delete(Squill squill) {
		OwnerTable t = new OwnerTable();
		squill
		.delete(t)
		.where(eq(t.id, getId()));
	}  
	
	public static class OwnerTable extends WritableTable<OwnerData> {
	    @Override
	    public String getTableName() { return "owners"; }
	
	    @Override
	    public Class<OwnerData> getTableType() { return OwnerData.class; }
	
	    public OwnerTable() { }
	
	    public OwnerTable(String alias) {
	        super(alias);
	    }
	
	    public final Column<java.lang.String, OwnerData> firstName =
	        new Column<java.lang.String, OwnerData>("FIRST_NAME", java.lang.String.class, "firstName", this);
	    public final Column<java.lang.String, OwnerData> address =
	        new Column<java.lang.String, OwnerData>("ADDRESS", java.lang.String.class, "address", this);
	    public final Column<java.lang.Integer, OwnerData> id =
	        new Column<java.lang.Integer, OwnerData>("ID", java.lang.Integer.class, "id", this);
	    public final Column<java.lang.String, OwnerData> city =
	        new Column<java.lang.String, OwnerData>("CITY", java.lang.String.class, "city", this);
	    public final Column<java.lang.String, OwnerData> telephone =
	        new Column<java.lang.String, OwnerData>("TELEPHONE", java.lang.String.class, "telephone", this);
	    public final Column<java.lang.String, OwnerData> lastName =
	        new Column<java.lang.String, OwnerData>("LAST_NAME", java.lang.String.class, "lastName", this);
    
	}
}