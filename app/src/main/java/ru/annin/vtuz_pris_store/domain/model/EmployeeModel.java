package ru.annin.vtuz_pris_store.domain.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * <p>Модель данны "Сотрудник".</p>
 *
 * @author Pavel Annin, 2016.
 */
@RealmClass
public class EmployeeModel extends RealmObject {

    public static final String FIELD_ID = "id";
    public static final String FIELD_SURNAME = "surname";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_PATRONYMIC = "patronymic";
    public static final String FIELD_JOB_POSITION = "jobPosition";
    public static final String FIELD_ORGANIZATION_UNIT = "organizationUnit";

    @PrimaryKey
    private String id;
    private String surname;
    private String name;
    private String patronymic;
    private JobPositionModel jobPosition;
    private OrganizationUnitModel organizationUnit;

    public EmployeeModel() {
        // Empty.
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public JobPositionModel getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(JobPositionModel jobPosition) {
        this.jobPosition = jobPosition;
    }

    public OrganizationUnitModel getOrganizationUnit() {
        return organizationUnit;
    }

    public void setOrganizationUnit(OrganizationUnitModel organizationUnit) {
        this.organizationUnit = organizationUnit;
    }
}
