package ru.annin.vtuz_pris_store;

import android.app.Application;

import ru.annin.vtuz_pris_store.data.util.RealmUtil;

/**
 * <p>Класс приложения.</p>
 *
 * @author Pavel Annin, 2016.
 */
public class StoreApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmUtil.initialize(this);
        RealmUtil.defaultData(this);
    }
}
