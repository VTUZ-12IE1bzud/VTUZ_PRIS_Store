package ru.annin.vtuz_pris_store.data.repository;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import ru.annin.vtuz_pris_store.data.util.RealmUtil;
import ru.annin.vtuz_pris_store.domain.model.JobPositionModel;
import ru.annin.vtuz_pris_store.domain.repository.JobPositionRepository;
import rx.Observable;

/**
 * <p>Реализация репозитория {@link JobPositionRepository}.</p>
 *
 * @author Pavel Annin, 2016.
 */
public class JobPositionRepositoryImpl implements JobPositionRepository {

    @NonNull
    @Override
    public Observable<RealmResults<JobPositionModel>> listJobPosition() {
        return RealmUtil.getRealm().where(JobPositionModel.class)
                .findAllSortedAsync(JobPositionModel.FIELD_NAME)
                .asObservable()
                .filter(RealmResults::isLoaded);
    }
}
