package ru.annin.vtuz_pris_store.presentation.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.annin.vtuz_pris_store.presentation.common.BaseActivity;
import ru.annin.vtuz_pris_store.presentation.common.BasePresenter;

/**
 * <p>Стартовый экран.</p>
 *
 * @author Pavel Annin, 2016.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigator.navigate2Main(this);
        finish();
    }

    @Nullable
    @Override
    public BasePresenter createPresenter() {
        return null;
    }
}