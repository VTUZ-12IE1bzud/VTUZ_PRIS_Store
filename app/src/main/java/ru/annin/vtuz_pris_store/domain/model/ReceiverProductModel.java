package ru.annin.vtuz_pris_store.domain.model;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * <p>Модель данных "Прием товара".</p>
 *
 * @author Pavel Annin, 2016.
 */
@RealmClass
public class ReceiverProductModel extends RealmObject {

    public static final String FIELD_ID = "id";
    public static final String FIELD_INVOICE = "invoice";
    public static final String FIELD_DATE = "date";
    public static final String FIELD_STORY = "story";
    public static final String FIELD_EMPLOYEE = "employee";
    public static final String FIELD_PRODUCTS = "products";

    @PrimaryKey
    private String id;
    private String invoice;
    private Date date;
    private OrganizationUnitModel story;
    private EmployeeModel employee;
    private RealmList<ProductModel> products;

    public ReceiverProductModel() {
        // Empty
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrganizationUnitModel getStory() {
        return story;
    }

    public void setStory(OrganizationUnitModel story) {
        this.story = story;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
    }

    public RealmList<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(RealmList<ProductModel> products) {
        this.products = products;
    }
}
