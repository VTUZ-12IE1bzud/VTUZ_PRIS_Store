package ru.annin.vtuz_pris_store.presentation.ui.viewholder;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding.view.RxView;

import io.realm.RealmResults;
import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.domain.model.ReceiverProductModel;
import ru.annin.vtuz_pris_store.presentation.common.BaseViewHolder;
import ru.annin.vtuz_pris_store.presentation.ui.adapter.ReceiverProductListAdapter;

/**
 * <p>ViewHolder экрана "Прием товара".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class ReceiverProductViewHolder extends BaseViewHolder {

    // View's
    private final Toolbar vToolbar;
    private final RecyclerView rcList;
    private final FloatingActionButton fabAdd;
    private final View vEmpty;

    // Adapter's
    private final ReceiverProductListAdapter adapter;

    // Listener's
    private OnInteractionListener listener;

    public ReceiverProductViewHolder(@NonNull View view) {
        super(view);
        vToolbar = (Toolbar) vRoot.findViewById(R.id.toolbar);
        rcList = (RecyclerView) vRoot.findViewById(android.R.id.list);
        fabAdd = (FloatingActionButton) vRoot.findViewById(R.id.fab_add);
        vEmpty = vRoot.findViewById(android.R.id.empty);

        // Setup
        adapter = new ReceiverProductListAdapter();
        adapter.setViewEmpty(vEmpty);
        adapter.setOnClickListener(model -> {if (listener != null) listener.onItemClick(model);});
        rcList.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(onItemTouchHelper);
        itemTouchHelper.attachToRecyclerView(rcList);
        RxToolbar.navigationClicks(vToolbar).subscribe(aVoid -> {if (listener != null) listener.onBackClick();});
        RxView.clicks(fabAdd).subscribe(aVoid -> {if (listener != null) listener.onAddClick();});
    }

    public ReceiverProductViewHolder showReceiveProducts(RealmResults<ReceiverProductModel> model) {
        adapter.updateRealmResults(model);
        return this;
    }

    public void setOnInteractionListener(OnInteractionListener listener) {
        this.listener = listener;
    }

    private final ItemTouchHelper.SimpleCallback onItemTouchHelper = new ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            adapter.notifyItemRemoved(position);
            if (listener != null) {
                listener.onRemoveReceiverClick(adapter.getItem(position).getId());
            }
        }
    };

    public interface OnInteractionListener {
        void onBackClick();
        void onAddClick();
        void onItemClick(ReceiverProductModel model);
        void onRemoveReceiverClick(String receiverId);
    }
}
