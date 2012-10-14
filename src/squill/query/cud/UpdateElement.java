package squill.query.cud;

import squill.util.ToString;

public interface UpdateElement<OBJ> extends CUDElement {

    ToString<UpdateElement<?>> GET_SQL = new ToString<UpdateElement<?>>() {
        public String toString(UpdateElement<?> updateElement) {
            return updateElement.getDefaultSql();
        }
    };
}