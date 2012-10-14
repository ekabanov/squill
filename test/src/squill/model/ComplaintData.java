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
 * This class was generated automatically by SquillGen. 
 * Do not edit anything in this class. Your changes will be lost after the SquillGen runs again.
 */
public class ComplaintData implements squill.WritableDataObject {

	private java.util.Date resolvedDate;
	private java.lang.Integer percentSolved;
	private java.lang.String comments;
	private java.lang.Integer id;
	private java.lang.Integer customerId;
	private java.math.BigDecimal refoundSum;
	private java.lang.String title;
	public ComplaintData() {
		super();
	}

	public java.util.Date getResolvedDate() { 
		return resolvedDate; 
	}
	
	public void setResolvedDate(java.util.Date resolvedDate) { 
		this.resolvedDate = resolvedDate; 
	}
	
	public java.lang.Integer getPercentSolved() { 
		return percentSolved; 
	}
	
	public void setPercentSolved(java.lang.Integer percentSolved) { 
		this.percentSolved = percentSolved; 
	}
	
	public java.lang.String getComments() { 
		return comments; 
	}
	
	public void setComments(java.lang.String comments) { 
		this.comments = comments; 
	}
	
	public java.lang.Integer getId() { 
		return id; 
	}
	
	public void setId(java.lang.Integer id) { 
		this.id = id; 
	}
	
	public java.lang.Integer getCustomerId() { 
		return customerId; 
	}
	
	public void setCustomerId(java.lang.Integer customerId) { 
		this.customerId = customerId; 
	}
	
	public java.math.BigDecimal getRefoundSum() { 
		return refoundSum; 
	}
	
	public void setRefoundSum(java.math.BigDecimal refoundSum) { 
		this.refoundSum = refoundSum; 
	}
	
	public java.lang.String getTitle() { 
		return title; 
	}
	
	public void setTitle(java.lang.String title) { 
		this.title = title; 
	}
	

	public static ComplaintData get(Squill squill, java.lang.Integer id) {
		ComplaintTable t = new ComplaintTable();
		return squill.from(t).where(eq(t.id, id)).select(t);
	}   
  
	public void update(Squill squill) {
		ComplaintTable t = new ComplaintTable();
		squill
		.update(t)
		.where(eq(t.id, getId()))
		.set(
			updateElement(t.resolvedDate, getResolvedDate()), 
			updateElement(t.percentSolved, getPercentSolved()), 
			updateElement(t.comments, getComments()), 
			updateElement(t.customerId, getCustomerId()), 
			updateElement(t.refoundSum, getRefoundSum()), 
			updateElement(t.title, getTitle()));
	}  
	
	public void insert(Squill squill) {
		ComplaintTable t = new ComplaintTable();
		squill
		.insert(t)
		.values(
			insertElement(t.resolvedDate, getResolvedDate()), 
			insertElement(t.percentSolved, getPercentSolved()), 
			insertElement(t.comments, getComments()), 
			insertElement(t.id, unchecked(java.lang.Integer.class, "null")), 
			insertElement(t.customerId, getCustomerId()), 
			insertElement(t.refoundSum, getRefoundSum()), 
			insertElement(t.title, getTitle()));
	}  
	
	public void delete(Squill squill) {
		ComplaintTable t = new ComplaintTable();
		squill
		.delete(t)
		.where(eq(t.id, getId()));
	}  
	
	public static class ComplaintTable extends WritableTable<ComplaintData> {
	    @Override
	    public String getTableName() { return "complaint"; }
	
	    @Override
	    public Class<ComplaintData> getTableType() { return ComplaintData.class; }
	
	    public ComplaintTable() { }
	
	    public ComplaintTable(String alias) {
	        super(alias);
	    }
	
	    public final Column<java.util.Date, ComplaintData> resolvedDate =
	        new Column<java.util.Date, ComplaintData>("RESOLVED_DATE", java.util.Date.class, "resolvedDate", this);
	    public final Column<java.lang.Integer, ComplaintData> percentSolved =
	        new Column<java.lang.Integer, ComplaintData>("PERCENT_SOLVED", java.lang.Integer.class, "percentSolved", this);
	    public final Column<java.lang.String, ComplaintData> comments =
	        new Column<java.lang.String, ComplaintData>("COMMENTS", java.lang.String.class, "comments", this);
	    public final Column<java.lang.Integer, ComplaintData> id =
	        new Column<java.lang.Integer, ComplaintData>("ID", java.lang.Integer.class, "id", this);
	    public final Column<java.lang.Integer, ComplaintData> customerId =
	        new Column<java.lang.Integer, ComplaintData>("CUSTOMER_ID", java.lang.Integer.class, "customerId", this);
	    public final Column<java.math.BigDecimal, ComplaintData> refoundSum =
	        new Column<java.math.BigDecimal, ComplaintData>("REFOUND_SUM", java.math.BigDecimal.class, "refoundSum", this);
	    public final Column<java.lang.String, ComplaintData> title =
	        new Column<java.lang.String, ComplaintData>("TITLE", java.lang.String.class, "title", this);
    
		private class CustomerTableByCustomerId extends CustomerData.CustomerTable implements OrmJoin {
			public ReadableTable getTable() {
		  		return CustomerTableByCustomerId.this;
			}

			public Column<?, ?> getSource() {
				return ComplaintTable.this.customerId;
			}
		  
	  		public Column<?, ?> getTarget() {
	  			return CustomerTableByCustomerId.this.id;
	  		}
  		
	  		public JoinType getJoinType() {
	  			return JoinType.INNER;
	  		} 
		  
			public boolean isJoin() {
				return true;
			}
		};
		      
		private CustomerData.CustomerTable customer;
		
		public CustomerData.CustomerTable customer() {
			if (customer == null){
				customer = new CustomerTableByCustomerId();
			}
			return customer;
		}
		
	}
}