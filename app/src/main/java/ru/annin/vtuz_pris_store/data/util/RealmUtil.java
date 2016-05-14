package ru.annin.vtuz_pris_store.data.util;

import android.content.Context;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;
import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.domain.model.JobPositionModel;
import ru.annin.vtuz_pris_store.domain.model.OrganizationUnitModel;
import ru.annin.vtuz_pris_store.domain.model.TypeOrganizationUnitModel;
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
        final String jobPositionJson = ctx.getString(R.string.default_job_positions);
        final String typeOrganizationUnitJson = ctx.getString(R.string.default_type_organization_unit);
        final String organizationUnitJson = ctx.getString(R.string.default_organization_unit);
        getRealm().executeTransactionAsync(realm -> {
            realm.createOrUpdateAllFromJson(UnitModel.class, unitJson);
            realm.createOrUpdateAllFromJson(JobPositionModel.class, jobPositionJson);
            realm.createOrUpdateAllFromJson(TypeOrganizationUnitModel.class, typeOrganizationUnitJson);
            JSONArray organizationUnitJA = new JSONArray();
            try {
                organizationUnitJA = new JSONArray(organizationUnitJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            createOrUpdateOrganizationUnitModel(realm, organizationUnitJA);
        });
    }

    private static void createOrUpdateOrganizationUnitModel(@NonNull Realm realm, JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            final JSONObject jsonObject = array.optJSONObject(i);
            if (jsonObject != null) {
                final String id = jsonObject.optString(OrganizationUnitModel.FIELD_ID);
                final String name = jsonObject.optString(OrganizationUnitModel.FIELD_NAME);
                final String type = jsonObject.optString(OrganizationUnitModel.FIELD_TYPE);
                final String chief = jsonObject.optString(OrganizationUnitModel.FIELD_CHIEF);
                final String address = jsonObject.optString(OrganizationUnitModel.FIELD_ADDRESS);
                final TypeOrganizationUnitModel typeModel = realm.where(TypeOrganizationUnitModel.class)
                        .equalTo(TypeOrganizationUnitModel.FIELD_ID, type)
                        .findFirst();
                OrganizationUnitModel model = realm.where(OrganizationUnitModel.class)
                        .equalTo(OrganizationUnitModel.FIELD_ID, id)
                        .findFirst();
                if (model == null) {
                    model = realm.createObject(OrganizationUnitModel.class);
                }
                model.setId(id);
                model.setName(name);
                model.setType(typeModel);
                model.setChief(chief);
                model.setAddress(address);
            }
        }
    }

    private static class Migration implements RealmMigration {

        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
            final RealmSchema schema = realm.getSchema();
            // Ignore
        }
    }

}
