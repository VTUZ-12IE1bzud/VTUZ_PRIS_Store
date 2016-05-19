package ru.annin.vtuz_pris_store.presentation.ui.view;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import ru.annin.vtuz_pris_store.presentation.ui.alert.DetailProductAlert;

/**
 * <p>Представление экрана "Приходная накладная".</p>
 *
 * @author Pavel Annin, 2016.
 */
public interface DetailReceiverProductView {

    @NonNull
    String getString(@StringRes int resId);
    void onFinish();
    void onProductCreateOpen(DetailProductAlert.OnInteractionListener listener);
    void onProductOpen(String productId, DetailProductAlert.OnInteractionListener listener);
}
