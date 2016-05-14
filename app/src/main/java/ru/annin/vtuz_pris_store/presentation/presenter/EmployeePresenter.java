package ru.annin.vtuz_pris_store.presentation.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import ru.annin.vtuz_pris_store.domain.repository.EmployeeRepository;
import ru.annin.vtuz_pris_store.presentation.common.BasePresenter;
import ru.annin.vtuz_pris_store.presentation.ui.view.EmployeeView;
import ru.annin.vtuz_pris_store.presentation.ui.viewholder.EmployeeViewHolder;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

import static ru.annin.vtuz_pris_store.presentation.ui.viewholder.EmployeeViewHolder.OnInteractionListener;

/**
 * <p>Presenter экрана "Сотрудники".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class EmployeePresenter extends BasePresenter<EmployeeViewHolder, EmployeeView> {

    private final CompositeSubscription subscription;

    // Repository's
    private final EmployeeRepository employeeRepository;

    public EmployeePresenter(@NonNull EmployeeRepository employeeRepository) {
        subscription = new CompositeSubscription();
        this.employeeRepository = employeeRepository;
    }

    public void onInitialization() {
        Subscription sub = employeeRepository.listEmployees()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(models -> {if (viewHolder != null) viewHolder.showEmployee(models);});
        subscription.add(sub);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }

    @Override
    public BasePresenter setViewHolder(@Nullable EmployeeViewHolder vh) {
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
