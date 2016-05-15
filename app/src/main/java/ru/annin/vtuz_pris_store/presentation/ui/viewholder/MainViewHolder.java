package ru.annin.vtuz_pris_store.presentation.ui.viewholder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.jakewharton.rxbinding.support.design.widget.RxNavigationView;

import io.realm.RealmResults;
import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.domain.model.EmployeeModel;
import ru.annin.vtuz_pris_store.domain.model.OrganizationUnitModel;
import ru.annin.vtuz_pris_store.presentation.common.BaseViewHolder;
import ru.annin.vtuz_pris_store.presentation.ui.adapter.EmployeeSelectAdapter;
import ru.annin.vtuz_pris_store.presentation.ui.adapter.StoreSelectAdapter;

/**
 * <p>ViewHolder главного экрана.</p>
 *
 * @author Pavel Annin, 2016.
 */
public class MainViewHolder extends BaseViewHolder {

    // View's
    private final Toolbar vToolbar;
    private final DrawerLayout vDrawerLayout;
    private final NavigationView vNavigation;
    private final ActionBarDrawerToggle drawerToggle;
    private final AppCompatSpinner spStore;
    private final AppCompatSpinner spEmployee;

    // Adapter's
    private final StoreSelectAdapter storeAdapter;
    private final EmployeeSelectAdapter employeeAdapter;

    // Listener's
    private OnInteractionListener listener;

    public MainViewHolder(@NonNull Activity activity, @NonNull View view) {
        super(view);
        vToolbar = (Toolbar) vRoot.findViewById(R.id.toolbar);
        vDrawerLayout = (DrawerLayout) vRoot.findViewById(R.id.drawer);
        vNavigation = (NavigationView) vRoot.findViewById(R.id.nav);
        spStore = (AppCompatSpinner) vNavigation.getHeaderView(0).findViewById(R.id.sp_store);
        spEmployee = (AppCompatSpinner) vNavigation.getHeaderView(0).findViewById(R.id.sp_employee);

        // Setup
        drawerToggle = new ActionBarDrawerToggle(activity, vDrawerLayout, vToolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        vDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        storeAdapter = new StoreSelectAdapter(vRoot.getContext());
        employeeAdapter = new EmployeeSelectAdapter(vRoot.getContext());

        RxNavigationView.itemSelections(vNavigation).subscribe(this::onNavItemClick);
        spEmployee.setAdapter(employeeAdapter);
        spStore.setOnItemSelectedListener(onStoreListener);
        spStore.setAdapter(storeAdapter);
    }

    public MainViewHolder showStore(RealmResults<OrganizationUnitModel> data) {
        storeAdapter.updateData(data);
        return this;
    }

    public MainViewHolder showEmployee(RealmResults<EmployeeModel> data) {
        employeeAdapter.updateData(data);
        return this;
    }

    public void setOnInteractionListener(OnInteractionListener listener) {
        this.listener = listener;
    }

    private void onNavItemClick(@NonNull MenuItem item) {
        vDrawerLayout.closeDrawer(GravityCompat.START);
        if (listener != null) {
            switch (item.getItemId()) {
                case R.id.action_nav_units:
                    listener.onNavUnitClick();
                    break;
                case R.id.action_nav_job_positions:
                    listener.onNavJobPositionClick();
                    break;
                case R.id.action_nav_type_organization_units:
                    listener.onNavTypeOrganizationUnitClick();
                    break;
                case R.id.action_nav_organization_units:
                    listener.onNavOrganizationUnitClick();
                    break;
                case R.id.action_nav_employees:
                    listener.onNavEmployeeClick();
                    break;
                case R.id.action_nav_nomenclature:
                    listener.onNavNomenclatureClick();
                    break;
                default: break;
            }
        }
    }

    private final AdapterView.OnItemSelectedListener onStoreListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String storeId = storeAdapter.getId(position);
            if (listener != null) {
                listener.onStoreSelect(storeId);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public interface OnInteractionListener {
        void onNavUnitClick();
        void onNavJobPositionClick();
        void onNavTypeOrganizationUnitClick();
        void onNavOrganizationUnitClick();
        void onNavEmployeeClick();
        void onNavNomenclatureClick();
        void onStoreSelect(@Nullable String id);
    }
}
