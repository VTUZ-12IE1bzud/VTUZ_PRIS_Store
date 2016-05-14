package ru.annin.vtuz_pris_store.presentation.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.annin.vtuz_pris_store.domain.repository.JobPositionRepository;
import ru.annin.vtuz_pris_store.presentation.common.BasePresenter;
import ru.annin.vtuz_pris_store.presentation.ui.view.JobPositionView;
import ru.annin.vtuz_pris_store.presentation.ui.viewholder.JobPositionViewHolder;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

import static ru.annin.vtuz_pris_store.presentation.ui.viewholder.JobPositionViewHolder.OnInteractionListener;

/**
 * <p>Presenter экрана "Должности сотрудников".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class JobPositionPresenter extends BasePresenter<JobPositionViewHolder, JobPositionView> {

    private final CompositeSubscription subscription;

    // Repository's
    private final JobPositionRepository jobPositionRepository;

    public JobPositionPresenter(@NonNull JobPositionRepository jobPositionRepository) {
        subscription = new CompositeSubscription();
        this.jobPositionRepository = jobPositionRepository;
    }

    public void onInitialization() {
        Subscription sub = jobPositionRepository.listJobPosition()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(models -> {if (viewHolder != null) viewHolder.showJobPositions(models);});
        subscription.add(sub);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }

    @Override
    public BasePresenter setViewHolder(@Nullable JobPositionViewHolder vh) {
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
