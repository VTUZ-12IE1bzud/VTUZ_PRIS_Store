package ru.annin.vtuz_pris_store.domain.repository;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import ru.annin.vtuz_pris_store.domain.model.NomenclatureModel;
import rx.Observable;

/**
 * <p>Интерфейс репозитория "Номенклатура".</p>
 *
 * @author Pavel Annin, 2016.
 */
public interface NomenclatureRepository {

    /**
     * Возвращает коллекцию "Номенклатуры". Отсортированы по названию.
     * @return Коллекцию "Номенклатуры".
     */
    @NonNull
    Observable<RealmResults<NomenclatureModel>> listNomenclature();

}
