package ru.annin.vtuz_pris_store.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.data.repository.UnitRepositoryImpl;
import ru.annin.vtuz_pris_store.presentation.common.BaseActivity;
import ru.annin.vtuz_pris_store.presentation.presenter.UnitPresenter;
import ru.annin.vtuz_pris_store.presentation.ui.view.UnitView;
import ru.annin.vtuz_pris_store.presentation.ui.viewholder.UnitViewHolder;

/**
 * <p>Экран "Единиц измерения".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class UnitActivity extends BaseActivity<UnitPresenter> implements UnitView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);
        UnitViewHolder viewHolder = new UnitViewHolder(findViewById(R.id.main));
        presenter = new UnitPresenter(new UnitRepositoryImpl());
        presenter.setViewHolder(viewHolder);
        presenter.setView(this);
        presenter.onInitialization();
    }

    @Override
    public void onFinish() {
        finish();
    }
}
