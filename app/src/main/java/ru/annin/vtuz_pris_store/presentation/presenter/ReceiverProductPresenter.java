package ru.annin.vtuz_pris_store.presentation.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.annin.vtuz_pris_store.domain.model.ReceiverProductModel;
import ru.annin.vtuz_pris_store.domain.repository.ReceiverProductRepository;
import ru.annin.vtuz_pris_store.domain.repository.SettingsRepository;
import ru.annin.vtuz_pris_store.presentation.common.BasePresenter;
import ru.annin.vtuz_pris_store.presentation.ui.view.ReceiverProductView;
import ru.annin.vtuz_pris_store.presentation.ui.viewholder.ReceiverProductViewHolder;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

import static ru.annin.vtuz_pris_store.presentation.ui.viewholder.ReceiverProductViewHolder.OnInteractionListener;

/**
 * <p>Presenter экрана "Прием товара".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class ReceiverProductPresenter extends BasePresenter<ReceiverProductViewHolder, ReceiverProductView> {

    private final CompositeSubscription subscription;

    // Repository's
    private final ReceiverProductRepository receiverProductRepository;
    private final SettingsRepository settingsRepository;

    public ReceiverProductPresenter(@NonNull ReceiverProductRepository receiverProductRepository,
                                    @NonNull SettingsRepository settingsRepository) {
        subscription = new CompositeSubscription();
        this.receiverProductRepository = receiverProductRepository;
        this.settingsRepository = settingsRepository;
    }

    public void onInitialization() {
        Subscription sub = receiverProductRepository
                .listReceiverProductsByStore(settingsRepository.loadSelectStoreId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(models -> {if (viewHolder != null) viewHolder.showReceiveProducts(models);});
        subscription.add(sub);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }

    @Override
    public BasePresenter setViewHolder(@Nullable ReceiverProductViewHolder vh) {
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

        @Override
        public void onAddClick() {
            if (view != null) {
                view.onReceiverProductCreate();
            }
        }

        @Override
        public void onItemClick(ReceiverProductModel model) {
            if (view != null) {
                view.onReceiverProductOpen(model.getId());
            }
        }

        @Override
        public void onRemoveReceiverClick(String receiverId) {
            receiverProductRepository.asyncRemoveReceiver(receiverId);
        }
    };
}
