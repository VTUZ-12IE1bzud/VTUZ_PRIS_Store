package ru.annin.vtuz_pris_store.presentation.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.annin.vtuz_pris_store.domain.repository.EmployeeRepository;
import ru.annin.vtuz_pris_store.domain.repository.OrganizationUnitRepository;
import ru.annin.vtuz_pris_store.presentation.common.BasePresenter;
import ru.annin.vtuz_pris_store.presentation.ui.view.MainView;
import ru.annin.vtuz_pris_store.presentation.ui.viewholder.MainViewHolder;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

import static ru.annin.vtuz_pris_store.presentation.ui.viewholder.MainViewHolder.OnInteractionListener;

/**
 * <p>Presenter главного экрана.</p>
 *
 * @author Pavel Annin, 2016.
 */
public class MainPresenter extends BasePresenter<MainViewHolder, MainView> {

    private final CompositeSubscription subscription;

    // Repository's
    private final OrganizationUnitRepository organizationUnitRepository;
    private final EmployeeRepository employeeRepository;

    public MainPresenter(@NonNull OrganizationUnitRepository organizationUnitRepository,
                         @NonNull EmployeeRepository employeeRepository) {
        subscription = new CompositeSubscription();
        this.organizationUnitRepository = organizationUnitRepository;
        this.employeeRepository = employeeRepository;
    }

    public void onInitialization() {
        Subscription sub = organizationUnitRepository.listStore()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(models -> {if (viewHolder != null) viewHolder.showStore(models);});
        subscription.add(sub);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }

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

        @Override
        public void onStoreSelect(@Nullable String id) {
            Subscription sub = employeeRepository.listEmployeesByStore(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(viewHolder::showEmployee);
            subscription.add(sub);
        }
    };
}
