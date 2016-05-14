package ru.annin.vtuz_pris_store.presentation.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.annin.vtuz_pris_store.domain.repository.OrganizationUnitRepository;
import ru.annin.vtuz_pris_store.presentation.common.BasePresenter;
import ru.annin.vtuz_pris_store.presentation.ui.view.OrganizationUnitView;
import ru.annin.vtuz_pris_store.presentation.ui.viewholder.OrganizationUnitViewHolder;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

import static ru.annin.vtuz_pris_store.presentation.ui.viewholder.OrganizationUnitViewHolder.OnInteractionListener;

/**
 * <p>Presenter экрана "Подразделения предприятия".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class OrganizationUnitPresenter extends BasePresenter<OrganizationUnitViewHolder, OrganizationUnitView> {

    private final CompositeSubscription subscription;

    // Repository's
    private final OrganizationUnitRepository organizationUnitRepository;

    public OrganizationUnitPresenter(@NonNull OrganizationUnitRepository organizationUnitRepository) {
        subscription = new CompositeSubscription();
        this.organizationUnitRepository = organizationUnitRepository;
    }

    public void onInitialization() {
        Subscription sub = organizationUnitRepository.listOrganizationUnit()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(models -> {if (viewHolder != null) viewHolder.showOrganizationUnit(models);});
        subscription.add(sub);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }

    @Override
    public BasePresenter setViewHolder(@Nullable OrganizationUnitViewHolder vh) {
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
