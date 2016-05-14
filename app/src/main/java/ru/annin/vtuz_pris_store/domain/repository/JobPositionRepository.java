package ru.annin.vtuz_pris_store.domain.repository;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import ru.annin.vtuz_pris_store.domain.model.JobPositionModel;
import rx.Observable;

/**
 * <p>Интерфейс репозитория "Должность сотрудника".</p>
 *
 * @author Pavel Annin, 2016.
 */
public interface JobPositionRepository {

    /**
     * Возвращает коллекцию "Должностей сотрудников". Отсортированы по названию.
     * @return Коллекцию "Должностей сотрудников".
     */
    @NonNull
    Observable<RealmResults<JobPositionModel>> listJobPosition();

}
