package ru.annin.vtuz_pris_store.domain.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.annin.vtuz_pris_store.domain.model.ProductModel;
import rx.Observable;

/**
 * <p>ИНтерфейс репозитория "Товар".</p>
 *
 * @author Pavel Annin, 2016.
 */
public interface ProductRepository {

    @NonNull
    Observable<ProductModel> getProductById(String id);

    @Nullable
    ProductModel createProduct(String nomenclatureId, float amount);

    void asyncEditProduct(String productId, String nomenclatureId, float amount);

    void asyncRemoveProduct(String productId);

}
