package ru.annin.vtuz_pris_store.data.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.domain.repository.SettingsRepository;

/**
 * <p>Реализация репозитория {@link SettingsRepository}.</p>
 *
 * @author Pavel Annin, 2016.
 */
public class SettingRepositoryImpl implements SettingsRepository {

    private static final String PREFS_NAME = "SETTINGS";

    private static final String PREFS_STORE_ID = "PREFS_STORE_ID";
    private static final String PREFS_EMPLOYEE_ID = "PREFS_EMPLOYEE_ID";

    private final Context ctx;

    public SettingRepositoryImpl(Context context) {
        ctx = context;
    }

    @NonNull
    @Override
    public String loadSelectStoreId() {
        SharedPreferences settings= ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(PREFS_STORE_ID, ctx.getString(R.string.default_store_id));
    }

    @NonNull
    @Override
    public String loadSelectEmployeeId() {
        SharedPreferences settings= ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return settings.getString(PREFS_EMPLOYEE_ID, ctx.getString(R.string.default_employee_id));
    }

    @Override
    public void saveStoreId(@NonNull String id) {
        SharedPreferences settings= ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        settings.edit()
                .putString(PREFS_STORE_ID, id)
                .apply();
    }

    @Override
    public void saveEmployeeId(@NonNull String id) {
        SharedPreferences settings= ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        settings.edit()
                .putString(PREFS_EMPLOYEE_ID, id)
                .apply();
    }
}
