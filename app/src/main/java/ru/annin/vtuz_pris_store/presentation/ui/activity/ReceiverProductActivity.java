package ru.annin.vtuz_pris_store.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.data.repository.ReceiverProductRepositoryImpl;
import ru.annin.vtuz_pris_store.data.repository.SettingRepositoryImpl;
import ru.annin.vtuz_pris_store.presentation.common.BaseActivity;
import ru.annin.vtuz_pris_store.presentation.presenter.ReceiverProductPresenter;
import ru.annin.vtuz_pris_store.presentation.ui.view.ReceiverProductView;
import ru.annin.vtuz_pris_store.presentation.ui.viewholder.ReceiverProductViewHolder;

/**
 * <p>Экран "Приход товара".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class ReceiverProductActivity extends BaseActivity<ReceiverProductPresenter>
        implements ReceiverProductView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_product);
        ReceiverProductViewHolder viewHolder = new ReceiverProductViewHolder(findViewById(R.id.main));
        presenter = new ReceiverProductPresenter(new ReceiverProductRepositoryImpl(),
                new SettingRepositoryImpl(this));
        presenter.setViewHolder(viewHolder);
        presenter.setView(this);
        presenter.onInitialization();
    }

    @Override
    public void onFinish() {
        finish();
    }

    @Override
    public void onReceiverProductCreate() {
        navigator.navigate2DetailReceiverProduct(this);
    }

    @Override
    public void onReceiverProductOpen(String id) {
        navigator.navigate2DetailReceiverProduct(this, id);
    }

}
