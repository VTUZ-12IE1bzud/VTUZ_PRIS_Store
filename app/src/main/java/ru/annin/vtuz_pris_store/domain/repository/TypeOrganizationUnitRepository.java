package ru.annin.vtuz_pris_store.domain.repository;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import ru.annin.vtuz_pris_store.domain.model.TypeOrganizationUnitModel;
import rx.Observable;

/**
 * <p>Интерфейс репозитория "Типов подразделения предприятия".</p>
 *
 * @author Pavel Annin, 2016.
 */
public interface TypeOrganizationUnitRepository {

    /**
     * Возвращает коллекцию "Типов подразделения предприятия". Отсортированы по названию.
     * @return Коллекцию "Типов подразделения предприятия".
     */
    @NonNull
    Observable<RealmResults<TypeOrganizationUnitModel>> listTypesOrganizationUnit();

}
