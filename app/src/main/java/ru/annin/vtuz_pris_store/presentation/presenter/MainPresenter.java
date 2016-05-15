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

        @Override
        public void onNavJobPositionClick() {
            if (view != null) {
                view.onJobPositionsOpen();
            }
        }

        @Override
        public void onNavTypeOrganizationUnitClick() {
            if (view != null) {
                view.onTypeOrganizationUnitsOpen();
            }
        }

        @Override
        public void onNavOrganizationUnitClick() {
            if (view != null) {
                view.onOrganizationUnitsOpen();
            }
        }

        @Override
        public void onNavEmployeeClick() {
            if (view != null) {
                view.onEmployeesOpen();
            }
        }

        @Override
        public void onNavNomenclatureClick() {
            if (view != null) {
                view.onNomenclatureOpen();
            }
        }
    };
}
