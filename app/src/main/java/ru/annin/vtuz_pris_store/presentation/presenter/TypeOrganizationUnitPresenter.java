package ru.annin.vtuz_pris_store.presentation.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.annin.vtuz_pris_store.domain.repository.TypeOrganizationUnitRepository;
import ru.annin.vtuz_pris_store.presentation.common.BasePresenter;
import ru.annin.vtuz_pris_store.presentation.ui.view.TypeOrganizationUnitView;
import ru.annin.vtuz_pris_store.presentation.ui.viewholder.TypeOrganizationUnitViewHolder;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

import static ru.annin.vtuz_pris_store.presentation.ui.viewholder.TypeOrganizationUnitViewHolder.OnInteractionListener;

/**
 * <p>Presenter экрана "Типы подразделений предприятия".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class TypeOrganizationUnitPresenter extends BasePresenter<TypeOrganizationUnitViewHolder, TypeOrganizationUnitView> {

    private final CompositeSubscription subscription;

    // Repository's
    private final TypeOrganizationUnitRepository typeOrganizationUnitRepository;

    public TypeOrganizationUnitPresenter(@NonNull TypeOrganizationUnitRepository typeOrganizationUnitRepository) {
        subscription = new CompositeSubscription();
        this.typeOrganizationUnitRepository = typeOrganizationUnitRepository;
    }

    public void onInitialization() {
        Subscription sub = typeOrganizationUnitRepository.listTypesOrganizationUnit()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(models -> {if (viewHolder != null) viewHolder.showTypeOrganizationUnit(models);});
        subscription.add(sub);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }

    @Override
    public BasePresenter setViewHolder(@Nullable TypeOrganizationUnitViewHolder vh) {
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
