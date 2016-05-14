package ru.annin.vtuz_pris_store.data.util;

import android.content.Context;
import android.support.annotation.NonNull;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;
import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.domain.model.UnitModel;

/**
 * <p>Утилитарный класс, взаимодействия с {@link io.realm.Realm}.</p>
 *
 * @author Pavel Annin, 2016.
 */
public class RealmUtil {

    // Version's
    public static final int VERSION_1 = 0x01;

    public static final int CURRENT_VERSION = VERSION_1;

    private static final String DB_NAME = "store.realm";

    public static void initialize(@NonNull Context ctx) {
        final RealmConfiguration configuration = new RealmConfiguration.Builder(ctx)
                .name(DB_NAME)
                .schemaVersion(CURRENT_VERSION)
                .migration(new Migration())
                .build();
        Realm.setDefaultConfiguration(configuration);
    }

    @NonNull
    public static Realm getRealm() {
        return Realm.getDefaultInstance();
    }

    public static void defaultData(@NonNull Context ctx) {
        final String unitJson = ctx.getString(R.string.default_units);
        getRealm().executeTransactionAsync(realm -> {realm.createOrUpdateAllFromJson(UnitModel.class, unitJson);});
    }

    private static class Migration implements RealmMigration {

        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            final RealmSchema schema = realm.getSchema();
            // Ignore
        }
    }

}
