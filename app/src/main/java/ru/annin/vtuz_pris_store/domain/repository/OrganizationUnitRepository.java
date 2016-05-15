package ru.annin.vtuz_pris_store.domain.repository;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import ru.annin.vtuz_pris_store.domain.model.OrganizationUnitModel;
import rx.Observable;

/**
 * <p>Интерфейс репозитория "Подразделение предприятия".</p>
 *
 * @author Pavel Annin, 2016.
 */
public interface OrganizationUnitRepository {

    /**
     * Возвращает коллекцию "Подразделений предприятия". Отсортированы по названию.
     * @return Коллекцию "Подразделений предприятия".
     */
    @NonNull
    Observable<RealmResults<OrganizationUnitModel>> listOrganizationUnit();

    /**
     * Возвращает коллекцию "Складов". Отсортированы по названию.
     * @return Коллекцию "Складов".
     */
    @NonNull
    Observable<RealmResults<OrganizationUnitModel>> listStore();
}
