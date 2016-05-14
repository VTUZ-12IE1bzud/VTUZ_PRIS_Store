package ru.annin.vtuz_pris_store.presentation.ui.viewholder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.jakewharton.rxbinding.support.design.widget.RxNavigationView;

import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.presentation.common.BaseViewHolder;

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

    // Listener's
    private OnInteractionListener listener;

    public MainViewHolder(@NonNull Activity activity, @NonNull View view) {
        super(view);
        vToolbar = (Toolbar) vRoot.findViewById(R.id.toolbar);
        vDrawerLayout = (DrawerLayout) vRoot.findViewById(R.id.drawer);
        vNavigation = (NavigationView) vRoot.findViewById(R.id.nav);

        // Setup
        drawerToggle = new ActionBarDrawerToggle(activity, vDrawerLayout, vToolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        vDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        RxNavigationView.itemSelections(vNavigation).subscribe(this::onNavItemClick);
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
                case R.id.action_nav_job_posotions:
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
                default: break;
            }
        }
    }

    public interface OnInteractionListener {
        void onNavUnitClick();
        void onNavJobPositionClick();
        void onNavTypeOrganizationUnitClick();
        void onNavOrganizationUnitClick();
        void onNavEmployeeClick();
    }
}
