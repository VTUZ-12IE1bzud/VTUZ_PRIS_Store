package ru.annin.vtuz_pris_store.data.repository;

import android.support.annotation.NonNull;

import io.realm.Realm;
import io.realm.RealmResults;
import ru.annin.vtuz_pris_store.data.util.RealmUtil;
import ru.annin.vtuz_pris_store.domain.model.UnitModel;
import ru.annin.vtuz_pris_store.domain.repository.UnitRepository;
import rx.Observable;

/**
 * <p>Реализация репозитория {@link UnitRepository}.</p>
 *
 * @author Pavel Annin, 2016.
 */
public class UnitRepositoryImpl implements UnitRepository {

    @NonNull
    @Override
    public Observable<RealmResults<UnitModel>> listUnits() {
        final Realm realm = RealmUtil.getRealm();
        return realm.where(UnitModel.class)
                .findAllSortedAsync(UnitModel.FIELD_NAME)
                .asObservable()
                .filter(RealmResults::isLoaded);
    }
}
