package ru.annin.vtuz_pris_store.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.data.repository.TypeOrganizationUnitRepositoryImpl;
import ru.annin.vtuz_pris_store.presentation.common.BaseActivity;
import ru.annin.vtuz_pris_store.presentation.presenter.TypeOrganizationUnitPresenter;
import ru.annin.vtuz_pris_store.presentation.ui.view.TypeOrganizationUnitView;
import ru.annin.vtuz_pris_store.presentation.ui.viewholder.TypeOrganizationUnitViewHolder;

/**
 * <p>Экран "Типы подразделений предприятия".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class TypeOrganizationUnitActivity extends BaseActivity<TypeOrganizationUnitPresenter>
        implements TypeOrganizationUnitView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_organization_unit);
        TypeOrganizationUnitViewHolder viewHolder = new TypeOrganizationUnitViewHolder(findViewById(R.id.main));
        presenter = new TypeOrganizationUnitPresenter(new TypeOrganizationUnitRepositoryImpl());
        presenter.setViewHolder(viewHolder);
        presenter.setView(this);
        presenter.onInitialization();
    }

    @Override
    public void onFinish() {
        finish();
    }
}
