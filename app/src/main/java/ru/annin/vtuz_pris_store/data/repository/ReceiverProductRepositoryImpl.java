package ru.annin.vtuz_pris_store.data.repository;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmResults;
import ru.annin.vtuz_pris_store.data.util.RealmUtil;
import ru.annin.vtuz_pris_store.domain.model.EmployeeModel;
import ru.annin.vtuz_pris_store.domain.model.OrganizationUnitModel;
import ru.annin.vtuz_pris_store.domain.model.ProductModel;
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
    public Observable<RealmResults<ReceiverProductModel>> listReceiverProductsByStore(String storeId) {
        return RealmUtil.getRealm().where(ReceiverProductModel.class)
                .equalTo(ReceiverProductModel.FIELD_STORY + "." + OrganizationUnitModel.FIELD_ID, storeId)
                .notEqualTo(ReceiverProductModel.FIELD_ID, TEMP_RECEIVER_PRODUCT_ID)
                .findAllSortedAsync(ReceiverProductModel.FIELD_DATE)
                .asObservable()
                .filter(RealmResults::isLoaded);
    }

    @NonNull
    @Override
    public Observable<ReceiverProductModel> getReceiverProductById(@NonNull String id) {
        return RealmUtil.getRealm().where(ReceiverProductModel.class)
                .equalTo(ReceiverProductModel.FIELD_ID, id)
                .findFirstAsync()
                .asObservable();
    }

    @Override
    public void asyncAddProduct(String receiverId, String productId) {
        RealmUtil.getRealm().executeTransactionAsync(realm -> {
            ReceiverProductModel receiverProduct = realm.where(ReceiverProductModel.class)
                    .equalTo(ReceiverProductModel.FIELD_ID, receiverId)
                    .findFirst();
            ProductModel product = realm.where(ProductModel.class)
                    .equalTo(ProductModel.FIELD_ID, productId)
                    .findFirst();
            if (receiverProduct != null && product != null) {
                RealmList<ProductModel> products = receiverProduct.getProducts();
                if (products == null) {
                    products = new RealmList<>();
                }
                products.add(product);
            }
        });
    }

    @Override
    public void asyncCreateReceiverFromTemp(@NonNull String invoice, @NonNull String storeId,
                                            @NonNull String employeeId, @NonNull Date date) {
        RealmUtil.getRealm().executeTransactionAsync(realm -> {
            ReceiverProductModel receiverProduct = realm.createObject(ReceiverProductModel.class);
            ReceiverProductModel tempProduct = realm.where(ReceiverProductModel.class)
                    .equalTo(ReceiverProductModel.FIELD_ID, TEMP_RECEIVER_PRODUCT_ID)
                    .findFirst();
            receiverProduct.setId(UUID.randomUUID().toString());
            receiverProduct.setInvoice(invoice);
            receiverProduct.setDate(date);
            receiverProduct.setStory(realm.where(OrganizationUnitModel.class)
                    .equalTo(OrganizationUnitModel.FIELD_ID, storeId)
                    .findFirst());
            receiverProduct.setEmployee(realm.where(EmployeeModel.class)
                    .equalTo(EmployeeModel.FIELD_ID, employeeId)
                    .findFirst());
            receiverProduct.setProducts(tempProduct.getProducts());
            tempProduct.setProducts(null);
        });
    }

    @Override
    public void asyncEditReceiver(@NonNull String receiverId, @NonNull String invoice, @NonNull Date date) {
        RealmUtil.getRealm().executeTransactionAsync(realm -> {
            ReceiverProductModel receiverProduct = realm.where(ReceiverProductModel.class)
                    .equalTo(ReceiverProductModel.FIELD_ID, receiverId)
                    .findFirst();
            if (receiverProduct != null) {
                receiverProduct.setInvoice(invoice);
                receiverProduct.setDate(date);
            }
        });
    }

    @Override
    public void asyncRemoveReceiver(@NonNull String id) {
        RealmUtil.getRealm().executeTransactionAsync(realm -> {
            ReceiverProductModel receiverProduct = realm.where(ReceiverProductModel.class)
                    .equalTo(ReceiverProductModel.FIELD_ID, id)
                    .findFirst();
            if (receiverProduct != null) {
                receiverProduct.deleteFromRealm();
            }
        });
    }
}