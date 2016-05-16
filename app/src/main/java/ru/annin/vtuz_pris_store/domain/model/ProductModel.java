package ru.annin.vtuz_pris_store.domain.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * <p>Модель данных "Товар".</p>
 *
 * @author Pavel Annin, 2016.
 */
@RealmClass
public class ProductModel extends RealmObject {

    public static final String FIELD_ID = "id";
    public static final String FIELD_NOMENCLATURE = "nomenclature";
    public static final String FIELD_AMOUNT = "amount";

    @PrimaryKey
    private String id;
    private NomenclatureModel nomenclature;
    private float amount;

    public ProductModel() {
        // Empty
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NomenclatureModel getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(NomenclatureModel nomenclature) {
        this.nomenclature = nomenclature;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
