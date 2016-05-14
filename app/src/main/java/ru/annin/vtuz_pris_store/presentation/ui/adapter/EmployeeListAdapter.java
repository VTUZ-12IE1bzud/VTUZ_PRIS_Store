package ru.annin.vtuz_pris_store.presentation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.domain.model.EmployeeModel;
import ru.annin.vtuz_pris_store.domain.model.JobPositionModel;
import ru.annin.vtuz_pris_store.domain.model.OrganizationUnitModel;

/**
 * <p>Адаптер "Сотрудников".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class EmployeeListAdapter extends RealmRecyclerAdapter<EmployeeModel, EmployeeListAdapter.ItemViewHolder> {

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View vItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee_unit_list, parent, false);
        return new ItemViewHolder(vItem);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final EmployeeModel model = realmResults.get(position);
        if (model != null && model.isValid()) {
            holder.showName(model.getSurname(), model.getName(), model.getPatronymic());
            final OrganizationUnitModel organizationUnitModel = model.getOrganizationUnit();
            if (organizationUnitModel != null && organizationUnitModel.isValid()) {
                holder.showOrganization(organizationUnitModel.getName());
            }
            final JobPositionModel jobPositionModel = model.getJobPosition();
            if (jobPositionModel != null && jobPositionModel.isValid()) {
                holder.showJobPosition(jobPositionModel.getName());
            }
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        // View's
        private final TextView txtOrganizationUnit;
        private final TextView txtName;
        private final TextView txtJobPosition;

        public ItemViewHolder(View itemView) {
            super(itemView);
            txtOrganizationUnit = (TextView) itemView.findViewById(R.id.txt_name_organization_unit);
            txtName = (TextView) itemView.findViewById(R.id.txt_name_employee);
            txtJobPosition = (TextView) itemView.findViewById(R.id.txt_name_job_position);
        }

        public ItemViewHolder showOrganization(String text) {
            txtOrganizationUnit.setText(text);
            return this;
        }

        public ItemViewHolder showName(String surname, String name, String patronymic) {
            String format = txtName.getResources()
                    .getString(R.string.item_employee_name_list_format, surname, name, patronymic);
            txtName.setText(format);
            return this;
        }

        public ItemViewHolder showJobPosition(String text) {
            txtJobPosition.setText(text);
            return this;
        }
    }
}
