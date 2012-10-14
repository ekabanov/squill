package squill.tuple;

import squill.tuple.Tuple;

public class   Tuple1 < T1 >  implements Tuple {
    public final T1 v1;
    public  Tuple1 (  T1 v1 ) {
      this.v1 = v1;
    }

  public static < P1 > Tuple1< P1 > _( P1 v1 ) {
    return new Tuple1< P1 >( v1 );
  }

  @Override
  public String toString() {
    return "(" +  v1  + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tuple1 tuple = (Tuple1) o;
        if (v1 == null ? tuple.v1 != null : !v1.equals(tuple.v1)) return false;
        return true;
  }

  @Override
  public int hashCode() {
    int result = 0;
        result = 31 * result + (v1 != null ? v1.hashCode() : 0);
        return result;
  }
}