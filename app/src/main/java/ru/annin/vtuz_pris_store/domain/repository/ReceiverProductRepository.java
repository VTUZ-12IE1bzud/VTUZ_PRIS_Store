package ru.annin.vtuz_pris_store.domain.repository;

import android.support.annotation.NonNull;

import java.util.Date;

import io.realm.RealmResults;
import ru.annin.vtuz_pris_store.domain.model.ReceiverProductModel;
import rx.Observable;

/**
 * <p>Итерфейс репозитория "Получение товара".</p>
 *
 * @author Pavel Annin, 2016.
 */
public interface ReceiverProductRepository {

    String TEMP_RECEIVER_PRODUCT_ID = "temp_receiver_product_id";

    /**
     * Возвращает коллекцию "Товарных накладных". Отсортированы по дате.
     * @return Коллекцию "Товарных накладных".
     */
    @NonNull
    Observable<RealmResults<ReceiverProductModel>> listReceiverProductsByStore(String storeId);

    @NonNull
    Observable<ReceiverProductModel> getReceiverProductById(@NonNull String id);

    void asyncAddProduct(@NonNull String receiverId, @NonNull String productId);

    void asyncCreateReceiverFromTemp(@NonNull String invoice, @NonNull String storeId,
                             @NonNull String employeeId, @NonNull Date date);

    void asyncEditReceiver(@NonNull String receiverId, @NonNull String invoice, @NonNull Date date);

    void asyncRemoveReceiver(@NonNull String id);

}
