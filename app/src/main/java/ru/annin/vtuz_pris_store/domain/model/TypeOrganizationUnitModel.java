package ru.annin.vtuz_pris_store.domain.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * <p>Модель данных "Тип подразделения предприятия".</p>
 *
 * @author Pavel Annin, 2016.
 */
@RealmClass
public class TypeOrganizationUnitModel extends RealmObject {

    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";

    @PrimaryKey
    private String id;
    private String name;

    public TypeOrganizationUnitModel() {
        // Empty/
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
