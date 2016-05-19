package ru.annin.vtuz_pris_store.data.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.UUID;

import io.realm.Realm;
import io.realm.exceptions.RealmException;
import ru.annin.vtuz_pris_store.data.util.RealmUtil;
import ru.annin.vtuz_pris_store.domain.model.NomenclatureModel;
import ru.annin.vtuz_pris_store.domain.model.ProductModel;
import ru.annin.vtuz_pris_store.domain.repository.ProductRepository;
import rx.Observable;

/**
 * <p>Реализация репозитория {@link ProductRepository}.</p>
 *
 * @author Pavel Annin, 2016.
 */
public class ProductRepositoryImpl implements ProductRepository {

    @NonNull
    @Override
    public Observable<ProductModel> getProductById(String id) {
        return RealmUtil.getRealm().where(ProductModel.class)
                .equalTo(ProductModel.FIELD_ID, id)
                .findFirst()
                .asObservable();
    }

    @Nullable
    @Override
    public ProductModel createProduct(String nomenclatureId, float amount) {
        Realm realm = RealmUtil.getRealm();
        ProductModel product = null;
        try {
            realm.beginTransaction();
            product = realm.createObject(ProductModel.class);
            product.setId(UUID.randomUUID().toString());
            product.setAmount(amount);
            product.setNomenclature(realm.where(NomenclatureModel.class)
                    .equalTo(NomenclatureModel.FIELD_ID, nomenclatureId)
                    .findFirst());
            realm.commitTransaction();
        } catch (RealmException ignore) {
            realm.cancelTransaction();
        }
        return product;
    }

    @Override
    public void asyncEditProduct(String productId, String nomenclatureId, float amount) {
        RealmUtil.getRealm().executeTransactionAsync(realm -> {
            ProductModel product = realm.where(ProductModel.class)
                    .equalTo(ProductModel.FIELD_ID, productId)
                    .findFirst();
            NomenclatureModel nomenclature = realm.where(NomenclatureModel.class)
                    .equalTo(NomenclatureModel.FIELD_ID, nomenclatureId)
                    .findFirst();
            if (product != null && nomenclature != null) {
                product.setNomenclature(nomenclature);
                product.setAmount(amount);
            }
        });
    }

    @Override
    public void asyncRemoveProduct(String productId) {
        RealmUtil.getRealm().executeTransactionAsync(realm -> {
            ProductModel product = realm.where(ProductModel.class)
                    .equalTo(ProductModel.FIELD_ID, productId)
                    .findFirst();
            if (product != null) {
                product.deleteFromRealm();
            }
        });
    }
}
