package ru.annin.vtuz_pris_store.presentation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.domain.model.EmployeeModel;
import ru.annin.vtuz_pris_store.domain.model.OrganizationUnitModel;
import ru.annin.vtuz_pris_store.domain.model.ReceiverProductModel;

/**
 * <p>Адаптер "Приемов товара".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class ReceiverProductListAdapter extends RealmRecyclerAdapter<ReceiverProductModel, ReceiverProductListAdapter.ItemViewHolder> {

    // Listener's
    private OnClickListener listener;

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View vItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receiver_product_list, parent, false);
        return new ItemViewHolder(vItem);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final ReceiverProductModel model = realmResults.get(position);
        if (model != null && model.isValid()) {
            holder.showDateInvoice(model.getDate())
                    .showNameInvoice(model.getInvoice());
            final OrganizationUnitModel organizationUnitModel = model.getStory();
            if (organizationUnitModel != null && organizationUnitModel.isValid()) {
                holder.showMovementTo(organizationUnitModel.getName());
            }
            final EmployeeModel employeeModel = model.getEmployee();
            if (employeeModel != null && employeeModel.isValid()) {
                holder.showNameEmployee(employeeModel.getSurname(), employeeModel.getName(),
                        employeeModel.getPatronymic());
            }
        }
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        // View's
        private final TextView txtDateInvoice;
        private final TextView txtNameInvoice;
        private final TextView txtMovementTo;
        private final TextView txtNameEmployee;

        public ItemViewHolder(View itemView) {
            super(itemView);
            txtDateInvoice = (TextView) itemView.findViewById(R.id.txt_date_invoice);
            txtNameInvoice = (TextView) itemView.findViewById(R.id.txt_name_invoice);
            txtMovementTo = (TextView) itemView.findViewById(R.id.txt_movement_to);
            txtNameEmployee = (TextView) itemView.findViewById(R.id.txt_name_employee);

            // Setup
            RxView.clicks(itemView).subscribe(aVoid -> {if (listener != null) listener.onClick(realmResults.get(getAdapterPosition()));});
        }

        public ItemViewHolder showDateInvoice(Date date) {
            if (date != null) {
                String format = SimpleDateFormat.getDateInstance(DateFormat.SHORT).format(date);
                txtDateInvoice.setText(format);
            }
            return this;
        }

        public ItemViewHolder showNameInvoice(String text) {
            txtNameInvoice.setText(text);
            return this;
        }

        public ItemViewHolder showMovementTo(String text) {
            txtMovementTo.setText(text);
            return this;
        }

        public ItemViewHolder showNameEmployee(String surname, String name, String patronymic) {
            String format = txtNameEmployee.getResources()
                    .getString(R.string.item_employee_name_list_format, surname, name, patronymic);
            txtNameEmployee.setText(format);
            return this;
        }
    }

    public interface OnClickListener {
        void onClick(ReceiverProductModel model);
    }
}