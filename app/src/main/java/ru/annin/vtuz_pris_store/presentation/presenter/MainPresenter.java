package ru.annin.vtuz_pris_store.presentation.presenter;

import android.support.annotation.Nullable;

import ru.annin.vtuz_pris_store.presentation.common.BasePresenter;
import ru.annin.vtuz_pris_store.presentation.ui.view.MainView;
import ru.annin.vtuz_pris_store.presentation.ui.viewholder.MainViewHolder;

import static ru.annin.vtuz_pris_store.presentation.ui.viewholder.MainViewHolder.*;

/**
 * <p>Presenter главного экрана.</p>
 *
 * @author Pavel Annin, 2016.
 */
public class MainPresenter extends BasePresenter<MainViewHolder, MainView> {

    @Override
    public BasePresenter setViewHolder(@Nullable MainViewHolder mainViewHolder) {
        super.setViewHolder(mainViewHolder);
        if (viewHolder != null) {
            viewHolder.setOnInteractionListener(onViewHolderListener);
        }
        return this;
    }

    private final OnInteractionListener onViewHolderListener = new OnInteractionListener() {
        @Override
        public void onNavUnitClick() {
            if (view != null) {
                view.onUnitsOpen();
            }
        }
    };
}
