package com.productiveminds.colorselector.util;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;

import com.productiveminds.colorselector.helper.ProjectConstants;

public class UtilStyleManager {

    public static int convertToPx(Activity activity, int value) {
        float factor = activity.getApplicationContext().getResources().getDisplayMetrics().density;
        return Math.round(factor*((float)value));
    }

    public static void setDialogSizeColorSelector(Activity activity, View view) {
        FrameLayout.LayoutParams params = ( FrameLayout.LayoutParams)(view).getLayoutParams();

        int width = activity.getResources().getConfiguration().screenWidthDp;
        int height = activity.getResources().getConfiguration().screenHeightDp;

        if ( width > 480 ) {
            float rough;
            if (width > height) {
                if (width > ProjectConstants.MINI_FROM_SCREEN_SIZE) {
                    int weightedWidth = UtilStyleManager.convertToPx(activity, ProjectConstants.MINI_FROM_SCREEN_SIZE);
                    int weightedHeight = UtilStyleManager.convertToPx(activity, ProjectConstants.SCREEN_HEIGHT_FOR_ALL_TABLETS);
                    width = weightedWidth;
                    if (height > weightedHeight) {
                        height = weightedHeight;
                    } else {
                        height = UtilStyleManager.convertToPx(activity, height);
                    }
                } else {
                    width = UtilStyleManager.convertToPx(activity, width);
                    height = UtilStyleManager.convertToPx(activity, height);
                    rough = width - (0.1f * width);
                    width = Math.round(rough);
                    rough = height - (0.01f * height);
                    height = Math.round(rough);
                }
            } else {
                width = UtilStyleManager.convertToPx(activity, width);
                height = UtilStyleManager.convertToPx(activity, height);
                if (height > ProjectConstants.SMALL_PHONE_WIDTH_SIZE) {
                    rough = width - (0.2f * width);
                    width = Math.round(rough);
                    rough = height - (0.1f * height);
                    height = Math.round(rough);
                } else {
                    // do nothing
                }
            }
            //params.height = height;
            params.width = width;
            view.setLayoutParams(params);
        }
    }

}
