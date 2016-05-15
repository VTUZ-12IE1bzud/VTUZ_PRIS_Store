package ru.annin.vtuz_pris_store.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.data.repository.NomenclatureRepositoryImpl;
import ru.annin.vtuz_pris_store.presentation.common.BaseActivity;
import ru.annin.vtuz_pris_store.presentation.presenter.NomenclaturePresenter;
import ru.annin.vtuz_pris_store.presentation.ui.view.NomenclatureView;
import ru.annin.vtuz_pris_store.presentation.ui.viewholder.NomenclatureViewHolder;

/**
 * <p>Экран "Номенклатура".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class NomenclatureActivity extends BaseActivity<NomenclaturePresenter> implements NomenclatureView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nomenclature);
        NomenclatureViewHolder viewHolder = new NomenclatureViewHolder(findViewById(R.id.main));
        presenter = new NomenclaturePresenter(new NomenclatureRepositoryImpl());
        presenter.setViewHolder(viewHolder);
        presenter.setView(this);
        presenter.onInitialization();
    }

    @Override
    public void onFinish() {
        finish();
    }
}
