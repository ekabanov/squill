

package org.springframework.samples.petclinic.data;


import static squill.functions.Operations.eq;
import squill.Squill;
import squill.query.select.Column;
import squill.query.select.WritableTable;

public class TypeData  {

  private java.lang.String name; 
  private java.lang.Integer id; 

public TypeData() {
  super();
} 




public static TypeData get(Squill squill, java.lang.Integer id) {
  TypeTable t = new TypeTable();
	return squill.from(t).where(eq(t.id, id)).select(t);
} 

	  public java.lang.String getName() { return name; }
  public void setName(java.lang.String name) { this.name = name; }
  	  public java.lang.Integer getId() { return id; }
  public void setId(java.lang.Integer id) { this.id = id; }
  
  @Override
  public String toString() {
    StringBuilder sb=new StringBuilder();
        sb.append(", name: ").append(getName()); 		
        sb.append(", id: ").append(getId()); 		
        return sb.substring(2);
  }

  public static class TypeTable extends WritableTable<TypeData> {
    @Override
    public String getTableName() { return "types"; }

    @Override
    public Class<TypeData> getTableType() { return TypeData.class; }

    public TypeTable() { }

    public TypeTable(String alias) {
        super(alias);
    }

        public final Column<java.lang.String, TypeData> name =
        new Column<java.lang.String, TypeData>("NAME", java.lang.String.class, "name", this);
        public final Column<java.lang.Integer, TypeData> id =
        new Column<java.lang.Integer, TypeData>("ID", java.lang.Integer.class, "id", this);
        
      }
}