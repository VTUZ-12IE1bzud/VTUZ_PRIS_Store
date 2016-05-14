package ru.annin.vtuz_pris_store.presentation.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * <p>Recycler View адаптер, для коллекций {@link io.realm.RealmResults}.</p>
 *
 * @author Pavel Annin, 2016.
 */
public abstract class RealmRecyclerAdapter <TRealm extends RealmObject,
        VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    // View's
    private View vEmpty;

    // Data's
    protected RealmResults<TRealm> realmResults;

    public RealmRecyclerAdapter() {
        notifyEmptyChanged();
    }

    @Override
    public int getItemCount() {
        if (realmResults != null) {
            return realmResults.size();
        }
        return 0;
    }

    public void setViewEmpty(@Nullable final View view) {
        vEmpty = view;
        notifyEmptyChanged();
    }

    public void updateRealmResults(@Nullable RealmResults<TRealm> queryResults) {
        realmResults = queryResults;
        notifyDataSetChanged();
        notifyEmptyChanged();
    }

    public boolean isEmpty() {
        return (realmResults != null ? realmResults.size() : 0) == 0;
    }

    protected void notifyEmptyChanged() {
        if (vEmpty != null) {
            if (realmResults != null && realmResults.size() >= 1) {
                vEmpty.setVisibility(View.GONE);
            } else {
                vEmpty.setVisibility(View.VISIBLE);
            }
        }
    }
}
