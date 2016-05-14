package ru.annin.vtuz_pris_store.domain.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * <p>Модель данных "Подразделение предприятия".</p>
 *
 * @author Pavel Annin, 2016.
 */
@RealmClass
public class OrganizationUnitModel extends RealmObject {

    public static final String FIELD_ID = "id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_TYPE = "type";
    public static final String FIELD_ADDRESS = "address";
    public static final String FIELD_CHIEF = "chief";

    @PrimaryKey
    private String id;
    private String name;
    private TypeOrganizationUnitModel type;
    private String address;
    private String chief;

    public OrganizationUnitModel() {
        // Empty.
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

    public TypeOrganizationUnitModel getType() {
        return type;
    }

    public void setType(TypeOrganizationUnitModel type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getChief() {
        return chief;
    }

    public void setChief(String chief) {
        this.chief = chief;
    }
}
