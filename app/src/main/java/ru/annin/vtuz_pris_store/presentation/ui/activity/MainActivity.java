package ru.annin.vtuz_pris_store.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.presentation.common.BaseActivity;
import ru.annin.vtuz_pris_store.presentation.presenter.MainPresenter;
import ru.annin.vtuz_pris_store.presentation.ui.view.MainView;
import ru.annin.vtuz_pris_store.presentation.ui.viewholder.MainViewHolder;

/**
 * <p>Главный экран</p>
 *
 * @author Pavel Annin, 2016.
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainViewHolder viewHolder = new MainViewHolder(this, findViewById(R.id.drawer));
        presenter = new MainPresenter();
        presenter.setViewHolder(viewHolder);
        presenter.setView(this);
    }

    @Override
    public void onUnitsOpen() {
        navigator.navigate2Units(this);
    }
}
