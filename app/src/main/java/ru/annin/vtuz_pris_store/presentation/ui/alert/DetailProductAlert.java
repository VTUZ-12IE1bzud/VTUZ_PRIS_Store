package ru.annin.vtuz_pris_store.presentation.ui.alert;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.Spinner;

import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.data.repository.NomenclatureRepositoryImpl;
import ru.annin.vtuz_pris_store.domain.repository.NomenclatureRepository;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * <p>Диалог "Товар".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class DetailProductAlert extends DialogFragment {

    private static final String BUNDLE_PRODUCT_ID = "ru.annin.vtuz_pris_store.bundle.product_id";

    private CompositeSubscription subscription;

    // View's
    private Spinner spNomenclature;
    private TextInputLayout tilAmount;
    private EditText edyAmount;

    // Adapter's

    // Listener's
    private OnInteractionListener listener;

    // Data's
    private boolean isCreate;
    private String productId;
    private String nomenclatureId;
    private float amount;

    // Repository'es
    private NomenclatureRepository nomenclatureRepository;

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
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.title_alert_product)
                .setView(R.layout.alert_detail_product)
                .setPositiveButton(R.string.action_alert_product_positive, null)
                .setNegativeButton(R.string.action_alert_product_negative, null)
                .create();
        spNomenclature = (Spinner) dialog.findViewById(R.id.sp_nomenclature);
        tilAmount = (TextInputLayout) dialog.findViewById(R.id.til_amount_product);
        edyAmount = (EditText) dialog.findViewById(R.id.edt_amount_product);

        // Data
        final Bundle args = getArguments();
        if (args != null && args.containsKey(BUNDLE_PRODUCT_ID)) {
            isCreate = false;
            productId = args.getString(BUNDLE_PRODUCT_ID);
        } else {
            isCreate = true;
        }


        Subscription subNomenclature = nomenclatureRepository.listNomenclature()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(models -> {});
        subscription.add(subNomenclature);

        // Setup

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_POSITIVE)
                .setOnClickListener(v -> {

                });
        ((AlertDialog) getDialog()).getButton(DialogInterface.BUTTON_NEGATIVE)
                .setOnClickListener(v -> getDialog().dismiss());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        subscription.unsubscribe();
    }

    public interface OnInteractionListener {
        void onSaveProduct(String productId, String nomenclatureId, float amount);
        void onCreateProduct(String nomenclatureId, float amount);
    }
}
