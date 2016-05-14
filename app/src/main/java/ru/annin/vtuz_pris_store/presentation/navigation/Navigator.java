package ru.annin.vtuz_pris_store.presentation.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import ru.annin.vtuz_pris_store.presentation.ui.activity.JobPositionActivity;
import ru.annin.vtuz_pris_store.presentation.ui.activity.MainActivity;
import ru.annin.vtuz_pris_store.presentation.ui.activity.TypeOrganizationUnitActivity;
import ru.annin.vtuz_pris_store.presentation.ui.activity.UnitActivity;

/**
 * <p>Класс, обеспечивающий навигацию по приложению.</p>
 *
 * @author Pavel Annin, 2016.
 */
public class Navigator {

    public void navigate2Main(@NonNull Context ctx) {
        Intent intent = new Intent(ctx, MainActivity.class);
        ctx.startActivity(intent);
    }

    public void navigate2Units(@NonNull Context ctx) {
        Intent intent = new Intent(ctx, UnitActivity.class);
        ctx.startActivity(intent);
    }

    public void navigate2JobPositions(@NonNull Context ctx) {
        Intent intent = new Intent(ctx, JobPositionActivity.class);
        ctx.startActivity(intent);
    }

    public void navigate2TypeOrganizationUnits(@NonNull Context ctx) {
        Intent intent = new Intent(ctx, TypeOrganizationUnitActivity.class);
        ctx.startActivity(intent);
    }
}
