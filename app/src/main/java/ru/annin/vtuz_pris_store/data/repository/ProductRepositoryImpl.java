package ru.annin.vtuz_pris_store.data.repository;

import android.support.annotation.NonNull;

import ru.annin.vtuz_pris_store.data.util.RealmUtil;
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

    @Override
    public void asyncCreateProduct(String nomenclatureId, float amount) {
        RealmUtil.getRealm().executeTransactionAsync(realm -> {
            Produ
        });
    }
}
