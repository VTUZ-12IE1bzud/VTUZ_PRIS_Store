package ru.annin.vtuz_pris_store.domain.repository;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import ru.annin.vtuz_pris_store.domain.model.ReceiverProductModel;
import rx.Observable;

/**
 * <p>Итерфейс репозитория "Получение товара".</p>
 *
 * @author Pavel Annin, 2016.
 */
public interface ReceiverProductRepository {

    /**
     * Возвращает коллекцию "Товарных накладных". Отсортированы по дате.
     * @return Коллекцию "Товарных накладных".
     */
    @NonNull
    Observable<RealmResults<ReceiverProductModel>> listReceiverProducts();

}
