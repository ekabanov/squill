package squill;


import javax.sql.DataSource;

import squill.tuple.Tuple;


public interface Executable<T extends Tuple> {
  Result<T> query(final DataSource dataSource, Object... params);
}