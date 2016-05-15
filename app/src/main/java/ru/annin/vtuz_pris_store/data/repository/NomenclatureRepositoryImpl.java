package ru.annin.vtuz_pris_store.data.repository;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import ru.annin.vtuz_pris_store.data.util.RealmUtil;
import ru.annin.vtuz_pris_store.domain.model.NomenclatureModel;
import ru.annin.vtuz_pris_store.domain.repository.NomenclatureRepository;
import rx.Observable;

/**
 * <p>Реализация репозитория {@link NomenclatureRepository}.</p>
 *
 * @author Pavel Annin, 2016.
 */
public class NomenclatureRepositoryImpl implements NomenclatureRepository {

    @NonNull
    @Override
    public Observable<RealmResults<NomenclatureModel>> listNomenclature() {
        return RealmUtil.getRealm().where(NomenclatureModel.class)
                .findAllSortedAsync(NomenclatureModel.FIELD_NAME)
                .asObservable()
                .filter(RealmResults::isLoaded);
    }
}
