package squill.tuple;

import java.util.Arrays;

public class TupleFactory {
    private TupleFactory() {}
    @SuppressWarnings({"unchecked"})
    public static <T extends Tuple> T tupleFrom(final Object...values) {
        switch(values.length) {
            case 1 : return (T) new Tuple1(values[0]);
            case 2 : return (T) new Tuple2(values[0],values[1]);
            case 3 : return (T) new Tuple3(values[0],values[1],values[2]);
            case 4 : return (T) new Tuple4(values[0],values[1],values[2],values[3]);
            case 5 : return (T) new Tuple5(values[0],values[1],values[2],values[3],values[4]);
            case 6 : return (T) new Tuple6(values[0],values[1],values[2],values[3],values[4],values[5]);
            case 7 : return (T) new Tuple7(values[0],values[1],values[2],values[3],values[4],values[5],values[6]);
            case 8 : return (T) new Tuple8(values[0],values[1],values[2],values[3],values[4],values[5],values[6],values[7]);
            case 9 : return (T) new Tuple9(values[0],values[1],values[2],values[3],values[4],values[5],values[6],values[7],values[8]);
            case 10 : return (T) new Tuple10(values[0],values[1],values[2],values[3],values[4],values[5],values[6],values[7],values[8],values[9]);
            default: throw new IllegalArgumentException("No Tuple available for "+ Arrays.asList(values));
        }
    }
}
