package ru.annin.vtuz_pris_store.presentation.ui.behavior;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * <p>FAB Behavior.</p>
 *
 * @author Pavel Annin, 2016.
 */
public class FabFloatBehavior extends FloatingActionButton.Behavior {

    public FabFloatBehavior(@NonNull Context context, AttributeSet attrs) {
        // Empty
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                               View target, int dxConsumed, int dyConsumed, int dxUnconsumed,
                               int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed);
        if (dyConsumed > 0) {
            CoordinatorLayout.LayoutParams layoutParams
                    = (CoordinatorLayout.LayoutParams)child.getLayoutParams();
            int fabBottomMargin = layoutParams.bottomMargin;
            child.animate()
                    .translationY((float)(child.getHeight() + fabBottomMargin))
                    .setInterpolator(new LinearInterpolator()).setDuration(150);
        } else if (dyConsumed < 0) {
            child.animate()
                    .translationY(0.0f)
                    .setInterpolator(new LinearInterpolator())
                    .setDuration(150);
        }
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout,
                                       FloatingActionButton child, View directTargetChild,
                                       View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }
}
