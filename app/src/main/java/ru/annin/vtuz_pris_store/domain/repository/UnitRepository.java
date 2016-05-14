package ru.annin.vtuz_pris_store.domain.repository;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import ru.annin.vtuz_pris_store.domain.model.UnitModel;
import rx.Observable;

/**
 * <p>Интерфейс репозитория "Единиц измерения".</p>
 *
 * @author Pavel Annin, 2016.
 */
public interface UnitRepository {

    /**
     * Возвращает коллекцию "Единиц измерения". Отсортированы по названию.
     * @return Коллекцию "Единиц измерения".
     */
    @NonNull
    Observable<RealmResults<UnitModel>> listUnits();

}
