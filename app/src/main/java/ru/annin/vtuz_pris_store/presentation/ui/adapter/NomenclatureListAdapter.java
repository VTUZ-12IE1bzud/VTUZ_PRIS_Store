package ru.annin.vtuz_pris_store.presentation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.domain.model.NomenclatureModel;
import ru.annin.vtuz_pris_store.domain.model.UnitModel;

/**
 * <p>Адаптер "Номенклатура".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class NomenclatureListAdapter extends RealmRecyclerAdapter<NomenclatureModel, NomenclatureListAdapter.ItemViewHolder> {

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View vItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nomenclature_list, parent, false);
        return new ItemViewHolder(vItem);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final NomenclatureModel model = realmResults.get(position);
        if (model != null && model.isValid()) {
            holder.showName(model.getName());
            final UnitModel unitModel = model.getUnit();
            if (unitModel != null && unitModel.isValid()) {
                holder.showUnit(unitModel.getName(), unitModel.getSymbol());
            }
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        // View's
        private final TextView txtName;
        private final TextView txtUnit;

        public ItemViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_name_nomenclature);
            txtUnit = (TextView) itemView.findViewById(R.id.txt_name_unit);
        }

        public ItemViewHolder showName(String text) {
            txtName.setText(text);
            return this;
        }

        public ItemViewHolder showUnit(String unit, String symbol) {
            String format = txtName.getResources()
                    .getString(R.string.item_nomenclature_unit_list_format, unit, symbol);
            txtUnit.setText(format);
            return this;
        }
    }
}
