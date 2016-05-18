package ru.annin.vtuz_pris_store.domain.repository;

import android.support.annotation.NonNull;

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

    void asyncCreateProduct(String nomenclatureId, float amount);

}
