package ru.annin.vtuz_pris_store.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.data.repository.EmployeeRepositoryImpl;
import ru.annin.vtuz_pris_store.presentation.common.BaseActivity;
import ru.annin.vtuz_pris_store.presentation.presenter.EmployeePresenter;
import ru.annin.vtuz_pris_store.presentation.ui.view.EmployeeView;
import ru.annin.vtuz_pris_store.presentation.ui.viewholder.EmployeeViewHolder;

/**
 * <p>Экран "Сотрудники".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class EmployeeActivity extends BaseActivity<EmployeePresenter> implements EmployeeView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        EmployeeViewHolder viewHolder = new EmployeeViewHolder(findViewById(R.id.main));
        presenter = new EmployeePresenter(new EmployeeRepositoryImpl());
        presenter.setViewHolder(viewHolder);
        presenter.setView(this);
        presenter.onInitialization();
    }

    @Override
    public void onFinish() {
        finish();
    }
}
