package ru.annin.vtuz_pris_store.presentation.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import io.realm.RealmResults;
import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.domain.model.EmployeeModel;
import ru.annin.vtuz_pris_store.domain.model.JobPositionModel;

/**
 * <p>Адаптер "Сотрудники".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class EmployeeSelectAdapter extends ArrayAdapter<EmployeeModel> {

    private final LayoutInflater inflater;
    private RealmResults<EmployeeModel> realmResults;

    public EmployeeSelectAdapter(Context context) {
        super(context, R.layout.item_employee_select);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_employee_select, parent, false);
        }
        final EmployeeModel model = realmResults.get(position);
        if (model != null && model.isValid()) {
            final ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.showNameSmall(model.getSurname(), model.getName(), model.getPatronymic());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_employee_select_drop, parent, false);
        }
        final EmployeeModel model = realmResults.get(position);
        if (model != null && model.isValid()) {
            final ViewHolder viewHolder = new ViewHolder(convertView);
            viewHolder.showNameFull(model.getSurname(), model.getName(), model.getPatronymic());
            final JobPositionModel jobPosition = model.getJobPosition();
            if (jobPosition != null && jobPosition.isValid()) {
                viewHolder.showJobPosition(jobPosition.getName());
            }
        }
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        final EmployeeModel model = realmResults.get(position);
        return model != null && model.isValid() ? model.getId().hashCode() : -1;
    }

    @Override
    public int getCount() {
        if (realmResults != null) {
            return realmResults.size();
        }
        return 0;
    }

    public void updateData(RealmResults<EmployeeModel> data) {
        realmResults = data;
        notifyDataSetChanged();
    }

    @Nullable
    public String getId(int position) {
        if (realmResults != null && position >= 0 && realmResults.size() > position) {
            final EmployeeModel model = realmResults.get(position);
            if (model.isValid()) {
                return realmResults.get(position).getId();
            }
        }
        return null;
    }

    public int getPosition(String id) {
        if (realmResults != null) {
            for(int i = 0; i < realmResults.size(); ++i) {
                final String dataId = realmResults.get(i).getId();
                if (TextUtils.equals(dataId, id)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private class ViewHolder {

        private final TextView txtName;
        private final TextView txtJobPosition;

        public ViewHolder(View view) {
            txtName = (TextView) view.findViewById(android.R.id.text1);
            txtJobPosition = (TextView) view.findViewById(android.R.id.text2);
        }

        public ViewHolder showNameSmall(String surname, String name, String patronymic) {
            String format = txtName.getResources()
                    .getString(R.string.item_employee_name_small_list_format, surname, name.charAt(0), patronymic.charAt(0));
            txtName.setText(format);
            return this;
        }

        public ViewHolder showNameFull(String surname, String name, String patronymic) {
            String format = txtName.getResources()
                    .getString(R.string.item_employee_name_list_format, surname, name, patronymic);
            txtName.setText(format);
            return this;
        }

        public ViewHolder showJobPosition(String text) {
            txtJobPosition.setText(text);
            return this;
        }
    }
}
