package ru.annin.vtuz_pris_store.presentation.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.annin.vtuz_pris_store.domain.repository.UnitRepository;
import ru.annin.vtuz_pris_store.presentation.common.BasePresenter;
import ru.annin.vtuz_pris_store.presentation.ui.view.UnitView;
import ru.annin.vtuz_pris_store.presentation.ui.viewholder.UnitViewHolder;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

import static ru.annin.vtuz_pris_store.presentation.ui.viewholder.UnitViewHolder.*;

/**
 * <p>Presenter экрана "Единицы измерения".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class UnitPresenter extends BasePresenter<UnitViewHolder, UnitView> {

    private final CompositeSubscription subscription;

    // Repository's
    private final UnitRepository unitRepository;

    public UnitPresenter(@NonNull UnitRepository unitRepository) {
        subscription = new CompositeSubscription();
        this.unitRepository = unitRepository;
    }

    public void onInitialization() {
        Subscription sub = unitRepository.listUnits()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(models -> {if (viewHolder != null) viewHolder.showUnits(models);});
        subscription.add(sub);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }

    @Override
    public BasePresenter setViewHolder(@Nullable UnitViewHolder unitViewHolder) {
        super.setViewHolder(unitViewHolder);
        if (viewHolder != null) {
            viewHolder.setOnInteractionListener(onViewHolderListener);
        }
        return this;
    }

    private final OnInteractionListener onViewHolderListener = new OnInteractionListener() {
        @Override
        public void onBackClick() {
            if (view != null) {
                view.onFinish();
            }
        }
    };
}
