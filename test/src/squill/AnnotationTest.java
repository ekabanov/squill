package squill;

import squill.model.CustomerData;
import squill.tuple.Tuple1;
import static squill.functions.Operations.eq;
import squill.api.Query;

import javax.sql.DataSource;

public class AnnotationTest {

    static interface TestService {
        class CustomerSelect implements Executable {
            public Result<Tuple1<String>> query(final DataSource dataSource, final Object... params) {
                CustomerData.CustomerTable customer = new CustomerData.CustomerTable();
                return Squill.squill(dataSource)
                        .from(customer)
                        .where(eq(customer.id, 1)) // todo param
                        .selectAs(customer.lastName);
            }
        }

        @Query(value = CustomerSelect.class)
        CustomerData.CustomerTable loadCustomer(int id);
    }
}
