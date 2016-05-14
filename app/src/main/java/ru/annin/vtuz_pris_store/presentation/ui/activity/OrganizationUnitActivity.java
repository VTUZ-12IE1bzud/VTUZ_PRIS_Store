package ru.annin.vtuz_pris_store.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.data.repository.OrganizationUnitRepositoryImpl;
import ru.annin.vtuz_pris_store.presentation.common.BaseActivity;
import ru.annin.vtuz_pris_store.presentation.presenter.OrganizationUnitPresenter;
import ru.annin.vtuz_pris_store.presentation.ui.view.OrganizationUnitView;
import ru.annin.vtuz_pris_store.presentation.ui.viewholder.OrganizationUnitViewHolder;

/**
 * <p>Экран "Подразделения предприятия".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class OrganizationUnitActivity extends BaseActivity<OrganizationUnitPresenter>
        implements OrganizationUnitView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_organization_unit);
        OrganizationUnitViewHolder viewHolder = new OrganizationUnitViewHolder(findViewById(R.id.main));
        presenter = new OrganizationUnitPresenter(new OrganizationUnitRepositoryImpl());
        presenter.setViewHolder(viewHolder);
        presenter.setView(this);
        presenter.onInitialization();
    }

    @Override
    public void onFinish() {
        finish();
    }
}
