package ru.annin.vtuz_pris_store.domain.repository;

import android.support.annotation.NonNull;

/**
 * <p>Интерфейс репозитория "Настроек".</p>
 *
 * @author Pavel Annin, 2016.
 */
public interface SettingsRepository {

    @NonNull
    String loadSelectStoreId();

    @NonNull
    String loadSelectEmployeeId();

    void saveStoreId(@NonNull String id);

    void saveEmployeeId(@NonNull String id);
}
