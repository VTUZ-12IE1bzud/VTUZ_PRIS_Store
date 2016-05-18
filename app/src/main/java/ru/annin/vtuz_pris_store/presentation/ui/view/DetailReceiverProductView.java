package ru.annin.vtuz_pris_store.presentation.ui.view;

import ru.annin.vtuz_pris_store.presentation.ui.alert.DetailProductAlert;

/**
 * <p>Представление экрана "Приходная накладная".</p>
 *
 * @author Pavel Annin, 2016.
 */
public interface DetailReceiverProductView {
    void onFinish();
    void onProductCreateOpen(DetailProductAlert.OnInteractionListener listener);
}
