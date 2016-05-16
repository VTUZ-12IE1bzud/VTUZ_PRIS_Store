package ru.annin.vtuz_pris_store.data.repository;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import ru.annin.vtuz_pris_store.data.util.RealmUtil;
import ru.annin.vtuz_pris_store.domain.model.ReceiverProductModel;
import ru.annin.vtuz_pris_store.domain.repository.ReceiverProductRepository;
import rx.Observable;

/**
 * <p>Реализация репозитория {@link ReceiverProductRepository}.</p>
 *
 * @author Pavel Annin, 2016.
 */
public class ReceiverProductRepositoryImpl implements ReceiverProductRepository {

    @NonNull
    @Override
    public Observable<RealmResults<ReceiverProductModel>> listReceiverProducts() {
        return RealmUtil.getRealm().where(ReceiverProductModel.class)
                .findAllSortedAsync(ReceiverProductModel.FIELD_DATE)
                .asObservable()
                .filter(RealmResults::isLoaded);
    }
}