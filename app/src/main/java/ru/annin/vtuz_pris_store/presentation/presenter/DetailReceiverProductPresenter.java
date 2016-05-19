package ru.annin.vtuz_pris_store.presentation.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.Date;

import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.domain.model.EmployeeModel;
import ru.annin.vtuz_pris_store.domain.model.OrganizationUnitModel;
import ru.annin.vtuz_pris_store.domain.model.ProductModel;
import ru.annin.vtuz_pris_store.domain.repository.EmployeeRepository;
import ru.annin.vtuz_pris_store.domain.repository.OrganizationUnitRepository;
import ru.annin.vtuz_pris_store.domain.repository.ProductRepository;
import ru.annin.vtuz_pris_store.domain.repository.ReceiverProductRepository;
import ru.annin.vtuz_pris_store.domain.repository.SettingsRepository;
import ru.annin.vtuz_pris_store.presentation.common.BasePresenter;
import ru.annin.vtuz_pris_store.presentation.ui.alert.DetailProductAlert;
import ru.annin.vtuz_pris_store.presentation.ui.view.DetailReceiverProductView;
import ru.annin.vtuz_pris_store.presentation.ui.viewholder.DetailReceiverProductViewHolder;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

import static ru.annin.vtuz_pris_store.presentation.ui.viewholder.DetailReceiverProductViewHolder.OnInteractionListener;

/**
 * <p>Presenter экрана "Приходная накладная".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class DetailReceiverProductPresenter extends BasePresenter<DetailReceiverProductViewHolder,
        DetailReceiverProductView> {

    private final CompositeSubscription subscription;

    // Repository's
    private final ReceiverProductRepository receiverProductRepository;
    private final OrganizationUnitRepository organizationUnitRepository;
    private final EmployeeRepository employeeRepository;
    private final SettingsRepository settingsRepository;
    private final ProductRepository productRepository;

    // Data's
    private boolean isCreate;
    private Date date;
    private String receiverProductId;
    private String organizationUnitId;
    private String employeeId;

    public DetailReceiverProductPresenter(@NonNull ReceiverProductRepository receiverProductRepository,
                                          @NonNull OrganizationUnitRepository organizationUnitRepository,
                                          @NonNull EmployeeRepository employeeRepository,
                                          @NonNull ProductRepository productRepository,
                                          @NonNull SettingsRepository settingsRepository) {
        subscription = new CompositeSubscription();
        this.receiverProductRepository = receiverProductRepository;
        this.organizationUnitRepository = organizationUnitRepository;
        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
        this.settingsRepository = settingsRepository;
        date = new Date();
        isCreate = true;
    }

    public void onInitialization() {
        isCreate = true;
        if (viewHolder != null) {
            viewHolder.enableAnimation(false)
                    .showDate(date);
        }
        Subscription subOrganizationUnit = organizationUnitRepository
                .getOrganizationUnitById(settingsRepository.loadSelectStoreId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(model -> {
                    if (viewHolder != null && model != null && model.isLoaded() && model.isValid()) {
                        organizationUnitId = model.getId();
                        viewHolder.showMovementTo(model.getName());
                    }
                });
        subscription.add(subOrganizationUnit);

        Subscription subEmployee = employeeRepository
                .getEmployeeById(settingsRepository.loadSelectEmployeeId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(model -> {
                    if (viewHolder != null && model != null && model.isLoaded() && model.isValid()) {
                        employeeId = model.getId();
                        viewHolder.showNameEmployee(model.getSurname(), model.getName(), model.getPatronymic());
                    }
                });
        subscription.add(subEmployee);

        Subscription subReceiverTemp = receiverProductRepository
                .getReceiverProductById(ReceiverProductRepository.TEMP_RECEIVER_PRODUCT_ID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(model -> {
                    if (viewHolder != null && model != null && model.isLoaded() && model.isValid()) {
                        viewHolder.showNameInvoice(model.getInvoice())
                                .showProducts(model.getProducts().where().findAll());
                    }
                });
        subscription.add(subReceiverTemp);
    }

    public void onInitialization(@NonNull String id) {
        isCreate = false;
        Subscription sub = receiverProductRepository.getReceiverProductById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(model -> {
                    if (viewHolder != null && model != null && model.isLoaded() && model.isValid()) {
                        date = model.getDate();
                        receiverProductId = model.getId();
                        viewHolder.enableAnimation(false)
                                .showNameInvoice(model.getInvoice())
                                .showDate(date)
                                .showProducts(model.getProducts().where().findAll());
                        final OrganizationUnitModel organizationUnit = model.getStory();
                        if (organizationUnit != null && organizationUnit.isValid()) {
                            organizationUnitId = organizationUnit.getId();
                            viewHolder.showMovementTo(organizationUnit.getName());
                        }
                        final EmployeeModel employee = model.getEmployee();
                        if (employee != null && employee.isValid()) {
                            employeeId = employee.getId();
                            viewHolder.showNameEmployee(employee.getSurname(), employee.getName(), employee.getPatronymic());
                        }
                    }
                });
        subscription.add(sub);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subscription.unsubscribe();
    }

    @Override
    public BasePresenter setViewHolder(@Nullable DetailReceiverProductViewHolder vh) {
        super.setViewHolder(vh);
        if (viewHolder != null) {
            viewHolder.setOnInteractionListener(onViewHolderListener);
        }
        return this;
    }

    private boolean isValidation(String invoice) {
        boolean valid = true;

        if (viewHolder != null) {
            viewHolder.errorNameInvoice(null);
        }

        if (TextUtils.isEmpty(invoice)) {
            valid = false;
            if (viewHolder != null && view != null) {
                viewHolder.errorNameInvoice(view.getString(R.string.error_field_empty));
            }
        }
        return valid;
    }

    private final OnInteractionListener onViewHolderListener = new OnInteractionListener() {
        @Override
        public void onBackClick() {
            if (view != null) {
                view.onFinish();
            }
        }

        @Override
        public void onSaveClick(String invoice) {
            if (isValidation(invoice)) {
                if (isCreate) {
                    receiverProductRepository
                            .asyncCreateReceiverFromTemp(invoice, organizationUnitId, employeeId, date);
                } else {
                    receiverProductRepository.asyncEditReceiver(receiverProductId, invoice, date);
                }
                onBackClick();
            }
        }

        @Override
        public void onAddClick() {
            if (view != null) {
                view.onProductCreateOpen(onDetailProductAlertListener);
            }
        }

        @Override
        public void onEditProduct(String productId) {
            if (view != null) {
                view.onProductOpen(productId, onDetailProductAlertListener);
            }
        }

        @Override
        public void onRemoveProduct(String productId) {
            productRepository.asyncRemoveProduct(productId);
        }

        @Override
        public void onDateClick() {
            viewHolder.showDatePicker(date);
        }

        @Override
        public void onDateSelect(Date d) {
            date = d;
            viewHolder.showDate(date);
        }
    };

    private final DetailProductAlert.OnInteractionListener onDetailProductAlertListener = new DetailProductAlert.OnInteractionListener() {
        @Override
        public void onSaveProduct(String productId, String nomenclatureId, float amount) {
            productRepository.asyncEditProduct(productId, nomenclatureId, amount);
        }

        @Override
        public void onCreateProduct(String nomenclatureId, float amount) {
            ProductModel product = productRepository.createProduct(nomenclatureId, amount);
            if (product != null && product.isValid()) {
                receiverProductRepository.asyncAddProduct(isCreate
                        ? ReceiverProductRepository.TEMP_RECEIVER_PRODUCT_ID
                        : receiverProductId, product.getId());
            }
        }
    };
}
