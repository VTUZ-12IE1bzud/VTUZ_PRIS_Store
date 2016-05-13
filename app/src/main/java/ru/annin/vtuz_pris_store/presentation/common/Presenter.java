package ru.annin.vtuz_pris_store.presentation.common;

/**
 * <p>Интерфейс Presenter (MVP).</p>
 *
 * @author Pavel Annin, 2016.
 */
public interface Presenter {

    void onResume();
    void onPause();
    void onDestroy();
}
