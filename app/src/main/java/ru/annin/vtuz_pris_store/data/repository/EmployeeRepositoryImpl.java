package ru.annin.vtuz_pris_store.data.repository;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import io.realm.Sort;
import ru.annin.vtuz_pris_store.data.util.RealmUtil;
import ru.annin.vtuz_pris_store.domain.model.EmployeeModel;
import ru.annin.vtuz_pris_store.domain.model.OrganizationUnitModel;
import ru.annin.vtuz_pris_store.domain.repository.EmployeeRepository;
import rx.Observable;

/**
 * <p>Реализация интферфейса {@link EmployeeRepository}.</p>
 *
 * @author Pavel Annin, 2016.
 */
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @NonNull
    @Override
    public Observable<RealmResults<EmployeeModel>> listEmployees() {
        return RealmUtil.getRealm().where(EmployeeModel.class)
                .findAllSortedAsync(EmployeeModel.FIELD_SURNAME, Sort.ASCENDING, EmployeeModel.FIELD_NAME, Sort.ASCENDING)
                .asObservable()
                .filter(RealmResults::isLoaded);
    }

    @NonNull
    @Override
    public Observable<RealmResults<EmployeeModel>> listEmployeesByStore(String id) {
        return RealmUtil.getRealm().where(EmployeeModel.class)
                .equalTo(EmployeeModel.FIELD_ORGANIZATION_UNIT + "." + OrganizationUnitModel.FIELD_ID, id)
                .findAllSortedAsync(EmployeeModel.FIELD_SURNAME, Sort.ASCENDING, EmployeeModel.FIELD_NAME, Sort.ASCENDING)
                .asObservable()
                .filter(RealmResults::isLoaded);
    }

    @NonNull
    @Override
    public Observable<EmployeeModel> getEmployeeById(String id) {
        return RealmUtil.getRealm().where(EmployeeModel.class)
                .equalTo(EmployeeModel.FIELD_ID, id)
                .findFirstAsync()
                .asObservable();
    }
}
