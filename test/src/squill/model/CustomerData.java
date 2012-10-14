package squill.model;

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
 * This class was generated automatically by SqullGen. 
 * Do not edit anything in this class. Your changes will be lost after the SqullGen runs again.
 */
public class CustomerData implements squill.WritableDataObject {

	private java.util.Date birthdate;
	private java.lang.String lastName;
	private java.lang.String firstName;
	private java.lang.Integer isActive;
	private java.lang.Integer parentCustomerId;
	private java.lang.Integer code;
	private java.lang.Integer discount;
	private java.lang.Integer id;

	public CustomerData() {
		super();
	}

	public java.util.Date getBirthdate() { 
		return birthdate; 
	}
	
	public void setBirthdate(java.util.Date birthdate) { 
		this.birthdate = birthdate; 
	}
	
	public java.lang.String getLastName() { 
		return lastName; 
	}
	
	public void setLastName(java.lang.String lastName) { 
		this.lastName = lastName; 
	}
	
	public java.lang.String getFirstName() { 
		return firstName; 
	}
	
	public void setFirstName(java.lang.String firstName) { 
		this.firstName = firstName; 
	}
	
	public java.lang.Integer getIsActive() { 
		return isActive; 
	}
	
	public void setIsActive(java.lang.Integer isActive) { 
		this.isActive = isActive; 
	}
	
	public java.lang.Integer getParentCustomerId() { 
		return parentCustomerId; 
	}
	
	public void setParentCustomerId(java.lang.Integer parentCustomerId) { 
		this.parentCustomerId = parentCustomerId; 
	}
	
	public java.lang.Integer getCode() { 
		return code; 
	}
	
	public void setCode(java.lang.Integer code) { 
		this.code = code; 
	}
	
	public java.lang.Integer getDiscount() { 
		return discount; 
	}
	
	public void setDiscount(java.lang.Integer discount) { 
		this.discount = discount; 
	}
	
	public java.lang.Integer getId() { 
		return id; 
	}
	
	public void setId(java.lang.Integer id) { 
		this.id = id; 
	}
	
  
	public static CustomerData get(Squill squill, java.lang.Integer id) {
		CustomerTable t = new CustomerTable();
		return squill.from(t).where(eq(t.id, id)).select(t);
	}   
  
	public void update(Squill squill) {
		CustomerTable t = new CustomerTable();
		squill
		.update(t)
		.where(eq(t.id, getId()))
		.set(
			updateElement(t.birthdate, getBirthdate()), 
			updateElement(t.lastName, getLastName()), 
			updateElement(t.firstName, getFirstName()), 
			updateElement(t.isActive, getIsActive()), 
			updateElement(t.parentCustomerId, getParentCustomerId()), 
			updateElement(t.code, getCode()), 
			updateElement(t.discount, getDiscount()));
	}  
	
	public void insert(Squill squill) {
		CustomerTable t = new CustomerTable();
		squill
		.insert(t)
		.values(
			insertElement(t.birthdate, getBirthdate()), 
			insertElement(t.lastName, getLastName()), 
			insertElement(t.firstName, getFirstName()), 
			insertElement(t.isActive, getIsActive()), 
			insertElement(t.parentCustomerId, getParentCustomerId()), 
			insertElement(t.code, getCode()), 
			insertElement(t.discount, getDiscount()), 
			insertElement(t.id, unchecked(java.lang.Integer.class, "null")));
	}  
	
	public void delete(Squill squill) {
		CustomerTable t = new CustomerTable();
		squill
		.delete(t)
		.where(eq(t.id, getId()));
	}  

	public static class CustomerTable extends WritableTable<CustomerData> {
	    @Override
	    public String getTableName() { return "customer"; }
	
	    @Override
	    public Class<CustomerData> getTableType() { return CustomerData.class; }
	
	    public CustomerTable() { }
	
	    public CustomerTable(String alias) {
	        super(alias);
	    }
	
	    public final Column<java.util.Date, CustomerData> birthdate =
	        new Column<java.util.Date, CustomerData>("BIRTHDATE", java.util.Date.class, "birthdate", this);
	    public final Column<java.lang.String, CustomerData> lastName =
	        new Column<java.lang.String, CustomerData>("LAST_NAME", java.lang.String.class, "lastName", this);
	    public final Column<java.lang.String, CustomerData> firstName =
	        new Column<java.lang.String, CustomerData>("FIRST_NAME", java.lang.String.class, "firstName", this);
	    public final Column<java.lang.Integer, CustomerData> isActive =
	        new Column<java.lang.Integer, CustomerData>("IS_ACTIVE", java.lang.Integer.class, "isActive", this);
	    public final Column<java.lang.Integer, CustomerData> parentCustomerId =
	        new Column<java.lang.Integer, CustomerData>("PARENT_CUSTOMER_ID", java.lang.Integer.class, "parentCustomerId", this);
	    public final Column<java.lang.Integer, CustomerData> code =
	        new Column<java.lang.Integer, CustomerData>("CODE", java.lang.Integer.class, "code", this);
	    public final Column<java.lang.Integer, CustomerData> discount =
	        new Column<java.lang.Integer, CustomerData>("DISCOUNT", java.lang.Integer.class, "discount", this);
	    public final Column<java.lang.Integer, CustomerData> id =
	        new Column<java.lang.Integer, CustomerData>("ID", java.lang.Integer.class, "id", this);
    
	}
}