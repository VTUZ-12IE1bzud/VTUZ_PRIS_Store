package ru.annin.vtuz_pris_store.presentation.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.annin.vtuz_pris_store.domain.repository.NomenclatureRepository;
import ru.annin.vtuz_pris_store.presentation.common.BasePresenter;
import ru.annin.vtuz_pris_store.presentation.ui.view.NomenclatureView;
import ru.annin.vtuz_pris_store.presentation.ui.viewholder.NomenclatureViewHolder;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

import static ru.annin.vtuz_pris_store.presentation.ui.viewholder.NomenclatureViewHolder.OnInteractionListener;

/**
 * <p>Presenter экрана "Номенклатура".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class NomenclaturePresenter extends BasePresenter<NomenclatureViewHolder, NomenclatureView> {

    private final CompositeSubscription subscription;

    // Repository's
    private final NomenclatureRepository nomenclatureRepository;

    public NomenclaturePresenter(@NonNull NomenclatureRepository nomenclatureRepository) {
        subscription = new CompositeSubscription();
        this.nomenclatureRepository = nomenclatureRepository;
    }

    public void onInitialization() {
        Subscription sub = nomenclatureRepository.listNomenclature()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(models -> {if (viewHolder != null) viewHolder.showNomenclature(models);});
        subscription.add(sub);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }

    @Override
    public BasePresenter setViewHolder(@Nullable NomenclatureViewHolder vh) {
        super.setViewHolder(vh);
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
