package ru.annin.vtuz_pris_store.presentation.common;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;

/**
 * <p>Базовый ViewHolder.</p>
 *
 * @author Pavel Annin, 2016.
 */
public abstract class BaseViewHolder {

    @NonNull
    protected final View vRoot;

    public BaseViewHolder(@NonNull View view) {
        vRoot = view;
    }

    /** @see android.content.Context#getString(int) */
    @NonNull
    public final String getString(@StringRes int resId) {
        return vRoot.getContext().getString(resId);
    }

    /** @see android.content.Context#getString(int, Object...) */
    @NonNull
    public final String getString(@StringRes int resId, Object... formatArgs) {
        return vRoot.getContext().getString(resId, formatArgs);
    }
}
