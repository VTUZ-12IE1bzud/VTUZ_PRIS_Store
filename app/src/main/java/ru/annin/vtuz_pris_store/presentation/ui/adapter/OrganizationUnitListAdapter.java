package ru.annin.vtuz_pris_store.presentation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.domain.model.OrganizationUnitModel;
import ru.annin.vtuz_pris_store.domain.model.TypeOrganizationUnitModel;

/**
 * <p>Адаптер "подразделений предприятия".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class OrganizationUnitListAdapter extends RealmRecyclerAdapter<OrganizationUnitModel,
        OrganizationUnitListAdapter.ItemViewHolder> {

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View vItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_organization_unit_list, parent, false);
        return new ItemViewHolder(vItem);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final OrganizationUnitModel model = realmResults.get(position);
        if (model != null && model.isValid()) {
            holder.showName(model.getName())
                    .showChief(model.getChief())
                    .showAddress(model.getAddress());
            final TypeOrganizationUnitModel typeOrganizationUnitModel = model.getType();
            if (typeOrganizationUnitModel != null && typeOrganizationUnitModel.isValid()) {
                holder.showType(typeOrganizationUnitModel.getName());
            }
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        // View's
        private final TextView txtType;
        private final TextView txtName;
        private final TextView txtChief;
        private final TextView txtAddress;

        public ItemViewHolder(View itemView) {
            super(itemView);
            txtType = (TextView) itemView.findViewById(R.id.txt_type_organization_unit);
            txtName = (TextView) itemView.findViewById(R.id.txt_name_organization_unit);
            txtChief = (TextView) itemView.findViewById(R.id.txt_chief_organization_unit);
            txtAddress = (TextView) itemView.findViewById(R.id.txt_address_organization_unit);
        }

        public ItemViewHolder showType(String text) {
            txtType.setText(text);
            return this;
        }

        public ItemViewHolder showName(String text) {
            txtName.setText(text);
            return this;
        }

        public ItemViewHolder showChief(String text) {
            txtChief.setText(text);
            return this;
        }

        public ItemViewHolder showAddress(String text) {
            txtAddress.setText(text);
            return this;
        }
    }
}
