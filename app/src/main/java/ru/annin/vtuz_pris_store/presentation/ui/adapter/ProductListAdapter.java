package ru.annin.vtuz_pris_store.presentation.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.domain.model.NomenclatureModel;
import ru.annin.vtuz_pris_store.domain.model.ProductModel;
import ru.annin.vtuz_pris_store.domain.model.UnitModel;

/**
 * <p>Адаптер "Товаров".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class ProductListAdapter extends RealmRecyclerAdapter<ProductModel, ProductListAdapter.ItemViewHolder> {

    // Listener's
    private OnClickListener listener;

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View vItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list, parent, false);
        return new ItemViewHolder(vItem);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        final ProductModel model = realmResults.get(position);
        if (model != null && model.isValid()) {
            final NomenclatureModel nomenclature = model.getNomenclature();
            if (nomenclature != null && nomenclature.isValid()) {
                holder.showNameProduct(nomenclature.getName());
                final UnitModel unit = nomenclature.getUnit();
                if (unit != null && unit.isValid()) {
                    holder.showAmountProduct(model.getAmount(), unit.getSymbol());
                }
            }
        }
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        // View's
        private final TextView txtNameProduct;
        private final TextView txtAmountProduct;

        public ItemViewHolder(View itemView) {
            super(itemView);
            txtNameProduct = (TextView) itemView.findViewById(R.id.txt_name_product);
            txtAmountProduct = (TextView) itemView.findViewById(R.id.txt_amount_product);
            RxView.clicks(itemView).subscribe(aVoid -> {
                if (listener != null) listener.onClickListener(realmResults.get(getAdapterPosition()));});
        }

        public ItemViewHolder showNameProduct(String text) {
            txtNameProduct.setText(text);
            return this;
        }

        public ItemViewHolder showAmountProduct(float amount, String symbolUnit) {
            String format = txtAmountProduct.getResources()
                    .getString(R.string.item_amount_product_list_format, amount, symbolUnit);
            txtAmountProduct.setText(format);
            return this;
        }
    }

    public interface OnClickListener {
        void onClickListener(ProductModel model);
    }
}
