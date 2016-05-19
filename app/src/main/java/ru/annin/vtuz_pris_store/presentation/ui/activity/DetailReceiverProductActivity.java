package ru.annin.vtuz_pris_store.presentation.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.data.repository.EmployeeRepositoryImpl;
import ru.annin.vtuz_pris_store.data.repository.OrganizationUnitRepositoryImpl;
import ru.annin.vtuz_pris_store.data.repository.ProductRepositoryImpl;
import ru.annin.vtuz_pris_store.data.repository.ReceiverProductRepositoryImpl;
import ru.annin.vtuz_pris_store.data.repository.SettingRepositoryImpl;
import ru.annin.vtuz_pris_store.presentation.common.BaseActivity;
import ru.annin.vtuz_pris_store.presentation.presenter.DetailReceiverProductPresenter;
import ru.annin.vtuz_pris_store.presentation.ui.alert.DetailProductAlert;
import ru.annin.vtuz_pris_store.presentation.ui.view.DetailReceiverProductView;
import ru.annin.vtuz_pris_store.presentation.ui.viewholder.DetailReceiverProductViewHolder;

/**
 * <p>Экран "Приходной накладной".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class DetailReceiverProductActivity extends BaseActivity<DetailReceiverProductPresenter>
        implements DetailReceiverProductView {

    public static final String EXTRA_RECEIVER_PRODUCT_ID = "ru.annin.vtuz_pris_store.extra.receiver_product_id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_receiver_product);
        DetailReceiverProductViewHolder viewHolder = new DetailReceiverProductViewHolder(findViewById(R.id.main));
        presenter = new DetailReceiverProductPresenter(new ReceiverProductRepositoryImpl(),
                new OrganizationUnitRepositoryImpl(),
                new EmployeeRepositoryImpl(),
                new ProductRepositoryImpl(),
                new SettingRepositoryImpl(this));
        presenter.setViewHolder(viewHolder);
        presenter.setView(this);

        Intent intent = getIntent();
        if (intent != null) {
            String action = intent.getAction();
            Bundle bundle = intent.getExtras();
            if (TextUtils.equals(Intent.ACTION_EDIT, action) && bundle != null
                    && bundle.containsKey(EXTRA_RECEIVER_PRODUCT_ID)) {
                String receiverProductId = bundle.getString(EXTRA_RECEIVER_PRODUCT_ID);
                presenter.onInitialization(receiverProductId);
            } else {
                presenter.onInitialization();
            }
        } else {
            presenter.onInitialization();
        }
    }

    @Override
    public void onFinish() {
        finish();
    }

    @Override
    public void onProductCreateOpen(DetailProductAlert.OnInteractionListener listener) {
       DetailProductAlert.newInstance(listener)
                .show(getFragmentManager(), DetailProductAlert.TAG);
    }

    @Override
    public void onProductOpen(String productId, DetailProductAlert.OnInteractionListener listener) {
        DetailProductAlert.newInstance(productId, listener)
                .show(getFragmentManager(), DetailProductAlert.TAG);
    }
}
