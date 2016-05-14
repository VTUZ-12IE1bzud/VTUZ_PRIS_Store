package ru.annin.vtuz_pris_store.data.repository;

import android.support.annotation.NonNull;

import io.realm.RealmResults;
import ru.annin.vtuz_pris_store.data.util.RealmUtil;
import ru.annin.vtuz_pris_store.domain.model.TypeOrganizationUnitModel;
import ru.annin.vtuz_pris_store.domain.repository.TypeOrganizationUnitRepository;
import rx.Observable;

/**
 * <p>Реализация репозитория {@link TypeOrganizationUnitRepository}.</p>
 *
 * @author Pavel Annin, 2016.
 */
public class TypeOrganizationUnitRepositoryImpl implements TypeOrganizationUnitRepository {

    @NonNull
    @Override
    public Observable<RealmResults<TypeOrganizationUnitModel>> listTypesOrganizationUnit() {
        return RealmUtil.getRealm().where(TypeOrganizationUnitModel.class)
                .findAllSortedAsync(TypeOrganizationUnitModel.FIELD_NAME)
                .asObservable()
                .filter(RealmResults::isLoaded);
    }
}
