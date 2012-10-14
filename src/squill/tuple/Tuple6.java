package squill.tuple;

import squill.tuple.Tuple;

public class   Tuple6 < T1,T2,T3,T4,T5,T6 >  implements Tuple {
    public final T1 v1;
    public final T2 v2;
    public final T3 v3;
    public final T4 v4;
    public final T5 v5;
    public final T6 v6;
    public  Tuple6 (  T1 v1, T2 v2, T3 v3, T4 v4, T5 v5, T6 v6 ) {
      this.v1 = v1;
      this.v2 = v2;
      this.v3 = v3;
      this.v4 = v4;
      this.v5 = v5;
      this.v6 = v6;
    }

  public static < P1,P2,P3,P4,P5,P6 > Tuple6< P1,P2,P3,P4,P5,P6 > _( P1 v1,P2 v2,P3 v3,P4 v4,P5 v5,P6 v6 ) {
    return new Tuple6< P1,P2,P3,P4,P5,P6 >( v1,v2,v3,v4,v5,v6 );
  }

  @Override
  public String toString() {
    return "(" +  v1 +","+ v2 +","+ v3 +","+ v4 +","+ v5 +","+ v6  + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tuple6 tuple = (Tuple6) o;
        if (v1 == null ? tuple.v1 != null : !v1.equals(tuple.v1)) return false;
        if (v2 == null ? tuple.v2 != null : !v2.equals(tuple.v2)) return false;
        if (v3 == null ? tuple.v3 != null : !v3.equals(tuple.v3)) return false;
        if (v4 == null ? tuple.v4 != null : !v4.equals(tuple.v4)) return false;
        if (v5 == null ? tuple.v5 != null : !v5.equals(tuple.v5)) return false;
        if (v6 == null ? tuple.v6 != null : !v6.equals(tuple.v6)) return false;
        return true;
  }

  @Override
  public int hashCode() {
    int result = 0;
        result = 31 * result + (v1 != null ? v1.hashCode() : 0);
        result = 31 * result + (v2 != null ? v2.hashCode() : 0);
        result = 31 * result + (v3 != null ? v3.hashCode() : 0);
        result = 31 * result + (v4 != null ? v4.hashCode() : 0);
        result = 31 * result + (v5 != null ? v5.hashCode() : 0);
        result = 31 * result + (v6 != null ? v6.hashCode() : 0);
        return result;
  }
}