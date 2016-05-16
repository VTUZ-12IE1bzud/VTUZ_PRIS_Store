package ru.annin.vtuz_pris_store.domain.repository;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import ru.annin.vtuz_pris_store.domain.model.EmployeeModel;
import rx.Observable;

/**
 * <p>Итерфейс репозитория "Сотрудник".</p>
 *
 * @author Pavel Annin, 2016.
 */
public interface EmployeeRepository {

    /**
     * Возвращает коллекцию "Сотрудников". Отсортированы по фамилии.
     * @return Коллекцию "Сотрудников".
     */
    @NonNull
    Observable<RealmResults<EmployeeModel>> listEmployees();

    /**
     * Возвращает коллекцию "Сотрудников" по складу. Отсортированы по фамилии.
     * @param id Идентификатор склада.
     * @return Коллекция "Сотрудников".
     */
    @NonNull
    Observable<RealmResults<EmployeeModel>> listEmployeesByStore(String id);

    @NonNull
    Observable<EmployeeModel> getEmployeeById(String id);

}
