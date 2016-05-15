package ru.annin.vtuz_pris_store.domain.value;

/**
 * <p>Тип подразделения предприятия.</p>
 *
 * @author Pavel Annin, 2016.
 */
public enum TypeOrganizationUnit {

    ADMINISTRATION("5376d2da-25ec-43a4-a404-54e14bb323da"),
    ACCOUNTING("13cc72c5-e8f1-456c-bdb7-fb9a60b3142a"),
    STORE("00734d56-e024-4cb9-986c-e42984dad649"),
    SHOP("c642f897-f2d7-4ae9-94a3-de12eca7b96e");

    private final String id;

    TypeOrganizationUnit(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
