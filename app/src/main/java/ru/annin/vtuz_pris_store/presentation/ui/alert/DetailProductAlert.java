package ru.annin.vtuz_pris_store.presentation.ui.alert;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.data.repository.NomenclatureRepositoryImpl;
import ru.annin.vtuz_pris_store.data.repository.ProductRepositoryImpl;
import ru.annin.vtuz_pris_store.domain.model.NomenclatureModel;
import ru.annin.vtuz_pris_store.domain.repository.NomenclatureRepository;
import ru.annin.vtuz_pris_store.domain.repository.ProductRepository;
import ru.annin.vtuz_pris_store.presentation.ui.adapter.NomenclatureSelectAdapter;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * <p>Диалог "Товар".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class DetailProductAlert extends DialogFragment {

    public static final String TAG = DetailProductAlert.class.getSimpleName();

    private static final String BUNDLE_PRODUCT_ID = "ru.annin.vtuz_pris_store.bundle.product_id";

    private CompositeSubscription subscription;

    // View's
    private Spinner spNomenclature;
    private TextInputLayout tilAmount;
    private EditText edyAmount;

    // Adapter's
    private NomenclatureSelectAdapter adapter;

    // Listener's
    private OnInteractionListener listener;

    // Data's
    private boolean isCreate;
    private String productId;
    private String nomenclatureId;
    private float amount;

    // Repository'es
    private NomenclatureRepository nomenclatureRepository;
    private ProductRepository productRepository;

    public static DetailProductAlert newInstance(OnInteractionListener listener) {
        DetailProductAlert fragment = new DetailProductAlert();
        fragment.listener = listener;
        return fragment;
    }

    public static DetailProductAlert newInstance(String productId, OnInteractionListener listener) {
        Bundle args = new Bundle();
        args.putString(BUNDLE_PRODUCT_ID, productId);
        DetailProductAlert fragment = new DetailProductAlert();
        fragment.setArguments(args);
        fragment.listener = listener;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscription = new CompositeSubscription();
        nomenclatureRepository = new NomenclatureRepositoryImpl();
        productRepository = new ProductRepositoryImpl();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View vRoot = LayoutInflater.from(getActivity()).inflate(R.layout.alert_detail_product, null);

        spNomenclature = (Spinner) vRoot.findViewById(R.id.sp_nomenclature);
        tilAmount = (TextInputLayout) vRoot.findViewById(R.id.til_amount_product);
        edyAmount = (EditText) vRoot.findViewById(R.id.edt_amount_product);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.title_alert_product)
                .setView(vRoot)
                .setPositiveButton(R.string.action_alert_product_positive, null)
                .setNegativeButton(R.string.action_alert_product_negative, null)
                .create();
        // Data
        final Bundle args = getArguments();
        if (args != null && args.containsKey(BUNDLE_PRODUCT_ID)) {
            isCreate = false;
            productId = args.getString(BUNDLE_PRODUCT_ID);
        } else {
            isCreate = true;
        }

        // Setup
        adapter = new NomenclatureSelectAdapter(getActivity());
        spNomenclature.setAdapter(adapter);
        tilAmount.setHintAnimationEnabled(false);

        Subscription subNomenclature = nomenclatureRepository.listNomenclature()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(models -> {
                    adapter.updateData(models);
                    if (!TextUtils.isEmpty(nomenclatureId)) {
                        spNomenclature.setSelection(adapter.getPosition(nomenclatureId));
                    }
                });
        subscription.add(subNomenclature);

        if (!isCreate) {
            Subscription subProduct = productRepository.getProductById(productId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(model -> {
                        if (model != null && model.isLoaded() && model.isValid()) {
                            productId = model.getId();
                            amount = model.getAmount();
                            edyAmount.setText(Float.toString(amount));
                            final NomenclatureModel nomenclature = model.getNomenclature();
                            if (nomenclature != null && nomenclature.isValid()) {
                                nomenclatureId = nomenclature.getId();
                                spNomenclature.setSelection(adapter.getPosition(nomenclatureId));
                            }
                        }
                    });
            subscription.add(subProduct);
        }

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_POSITIVE)
                .setOnClickListener(v -> {
                    String nomenclatureId = adapter.getId(spNomenclature.getSelectedItemPosition());
                    String amount = edyAmount.getText().toString();
                    if (isValidation(nomenclatureId, amount)) {
                        if (listener != null) {
                            float fAmount = Float.valueOf(amount);
                            if (isCreate) {
                                listener.onCreateProduct(nomenclatureId, fAmount);
                            } else {
                                listener.onSaveProduct(productId, nomenclatureId, fAmount);
                            }
                        }
                        getDialog().dismiss();
                    }
                });
        ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_NEGATIVE)
                .setOnClickListener(v -> getDialog().dismiss());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        subscription.unsubscribe();
    }

    private boolean isValidation(String nomenclatureId, String amount) {
        boolean valid = true;

        if (TextUtils.isEmpty(nomenclatureId)) {
            valid = false;
        }
        if (TextUtils.isEmpty(amount)) {
            valid = false;
        }
        return valid;
    }

    public interface OnInteractionListener {
        void onSaveProduct(String productId, String nomenclatureId, float amount);
        void onCreateProduct(String nomenclatureId, float amount);
    }
}
