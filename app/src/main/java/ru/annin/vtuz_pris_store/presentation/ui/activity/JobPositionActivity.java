package ru.annin.vtuz_pris_store.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.annin.vtuz_pris_store.R;
import ru.annin.vtuz_pris_store.data.repository.JobPositionRepositoryImpl;
import ru.annin.vtuz_pris_store.presentation.common.BaseActivity;
import ru.annin.vtuz_pris_store.presentation.presenter.JobPositionPresenter;
import ru.annin.vtuz_pris_store.presentation.ui.view.JobPositionView;
import ru.annin.vtuz_pris_store.presentation.ui.viewholder.JobPositionViewHolder;

/**
 * <p>Экран "Дожности сотрудников".</p>
 *
 * @author Pavel Annin, 2016.
 */
public class JobPositionActivity extends BaseActivity<JobPositionPresenter> implements JobPositionView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_position);
        JobPositionViewHolder viewHolder = new JobPositionViewHolder(findViewById(R.id.main));
        presenter = new JobPositionPresenter(new JobPositionRepositoryImpl());
        presenter.setViewHolder(viewHolder);
        presenter.setView(this);
        presenter.onInitialization();
    }

    @Override
    public void onFinish() {
        finish();
    }
}
