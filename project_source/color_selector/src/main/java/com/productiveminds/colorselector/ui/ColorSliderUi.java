package com.productiveminds.colorselector.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatSeekBar;

import com.productiveminds.colorselector.R;
import com.productiveminds.colorselector.helper.ProjectConstants;
import com.productiveminds.colorselector.helper.UserPreferencesManager;
import com.productiveminds.colorselector.util.ColorSelector;
import com.productiveminds.colorselector.util.UtilStyleManager;

public class ColorSliderUi {

    private int a = ProjectConstants.C_S_DEFAULT_VALUE_A;
    private int r = ProjectConstants.C_S_DEFAULT_VALUE_R;
    private int g = ProjectConstants.C_S_DEFAULT_VALUE_G;
    private int b = ProjectConstants.C_S_DEFAULT_VALUE_B;

    public void showDialogColorSelector(Activity activity, boolean showHexCode, boolean isCancelable, String heading) {
        Context context = activity.getApplicationContext();
        // init dialog
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_color_picker);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if ( isCancelable ) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }

        TextView dialog_title = dialog.findViewById(R.id.dialog_title);
        if ( !TextUtils.isEmpty(heading) ) {
            dialog_title.setText(heading);
        } else {
            dialog_title.setText(activity.getResources().getString(R.string.color_slider_dialog_title));
        }

        View parent_view_dialog = dialog.findViewById(R.id.parent_view_dialog);
        UtilStyleManager.setDialogSizeColorSelector(activity, parent_view_dialog);

        (dialog.findViewById(R.id.dialog_btn_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        initContent(activity, showHexCode, dialog);

        dialog.show();
    }

    public void initContent(Activity activity, boolean showHexCode, Dialog dialog) {
        Context context = activity.getApplicationContext();
        // init components
        AppCompatSeekBar color_a, color_r, color_g, color_b;
        TextView indicator_a, indicator_r, indicator_g, indicator_b, edt_indicator_hex;
        ImageView indicator_icon, copy_hex_color_code_icon, hex_coloy_apply_icon;
        LinearLayout copy_hex_color_code_container;

        color_a = dialog.findViewById(R.id.color_a);
        color_r = dialog.findViewById(R.id.color_r);
        color_g = dialog.findViewById(R.id.color_g);
        color_b = dialog.findViewById(R.id.color_b);

        indicator_a = dialog.findViewById(R.id.indicator_a);
        indicator_r = dialog.findViewById(R.id.indicator_r);
        indicator_g = dialog.findViewById(R.id.indicator_g);
        indicator_b = dialog.findViewById(R.id.indicator_b);

        indicator_icon = dialog.findViewById(R.id.indicator_icon);

        edt_indicator_hex = dialog.findViewById(R.id.edt_indicator_hex);

        copy_hex_color_code_container = dialog.findViewById(R.id.copy_hex_color_code_container);
        if ( showHexCode ) {
            copy_hex_color_code_container.setVisibility(View.VISIBLE);
        } else {
            copy_hex_color_code_container.setVisibility(View.GONE);
        }

        // set defaults
        indicator_a.setText(String.valueOf(a));
        indicator_r.setText(String.valueOf(r));
        indicator_g.setText(String.valueOf(g));
        indicator_b.setText(String.valueOf(b));

        a = UserPreferencesManager.getIntPrefValue(context,
                ProjectConstants.C_S_CODE_ALPHA, a);
        r = UserPreferencesManager.getIntPrefValue(context,
                ProjectConstants.C_S_CODE_RED, r);
        g = UserPreferencesManager.getIntPrefValue(context,
                ProjectConstants.C_S_CODE_GREEN, g);
        b = UserPreferencesManager.getIntPrefValue(context,
                ProjectConstants.C_S_CODE_BLUE, b);

        String latestHex = UserPreferencesManager.getStringPrefValue(context,
                ProjectConstants.C_S_HEX_LATEST, setColorGetHex(a, r,g,b));

        updateIndicators(latestHex, indicator_icon, edt_indicator_hex);

        // start:: r
        color_r = dialog.findViewById(R.id.color_r);
        color_r.setProgress(r);
        indicator_r.setText(String.valueOf(r));
        color_r.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar s, int progress, boolean fromUser) {
                r = progress;
                indicator_r.setText(String.valueOf(r));

                String hexCode = setColorGetHex(a, r, g, b);
                updateIndicators(hexCode, indicator_icon, edt_indicator_hex);
            }
            @Override
            public void onStartTrackingTouch(SeekBar s) {
                //
            }
            @Override
            public void onStopTrackingTouch(SeekBar s) {
                indicator_r.setText(String.valueOf(r));

                String hexCode = setColorGetHex(a, r, g, b);
                updateIndicators(hexCode, indicator_icon, edt_indicator_hex);

                UserPreferencesManager.setIntPrefValue(context,
                        ProjectConstants.C_S_CODE_RED, r);
                UserPreferencesManager.setStringPrefValue(context,
                        ProjectConstants.C_S_HEX_LATEST, hexCode);
                UserPreferencesManager.setIntPrefValue(context,
                        ProjectConstants.C_S_ARGB_COLOR_LATEST, ColorSelector.COLOR_ENCODED);

                if ( onColorSelectedListener != null ) {
                    onColorSelectedListener.getOnColorAlphaSelectedListener(a);
                    onColorSelectedListener.getOnColorStringSelectedListener(hexCode);
                    onColorSelectedListener.getOnColorIntSelectedListener(ColorSelector.COLOR_ENCODED);
                }
            }
        });
        // end:: r

        // start:: g
        color_g = dialog.findViewById(R.id.color_g);
        color_g.setProgress(g);
        indicator_g.setText(String.valueOf(g));
        color_g.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar s, int progress, boolean fromUser) {
                g = progress;
                indicator_g.setText(String.valueOf(g));

                String hexCode = setColorGetHex(a, r, g, b);
                updateIndicators(hexCode, indicator_icon, edt_indicator_hex);
            }
            @Override
            public void onStartTrackingTouch(SeekBar s) {
                //
            }
            @Override
            public void onStopTrackingTouch(SeekBar s) {
                indicator_g.setText(String.valueOf(g));

                String hexCode = setColorGetHex(a, r, g, b);
                updateIndicators(hexCode, indicator_icon, edt_indicator_hex);

                UserPreferencesManager.setIntPrefValue(context,
                        ProjectConstants.C_S_CODE_GREEN, g);
                UserPreferencesManager.setStringPrefValue(context,
                        ProjectConstants.C_S_HEX_LATEST, hexCode);
                UserPreferencesManager.setIntPrefValue(context,
                        ProjectConstants.C_S_ARGB_COLOR_LATEST, ColorSelector.COLOR_ENCODED);

                if ( onColorSelectedListener != null ) {
                    onColorSelectedListener.getOnColorAlphaSelectedListener(a);
                    onColorSelectedListener.getOnColorStringSelectedListener(hexCode);
                    onColorSelectedListener.getOnColorIntSelectedListener(ColorSelector.COLOR_ENCODED);
                }
            }
        });
        // end:: g

        // start:: b
        color_b = dialog.findViewById(R.id.color_b);
        color_b.setProgress(b);
        indicator_b.setText(String.valueOf(b));
        color_b.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar s, int progress, boolean fromUser) {
                b = progress;
                indicator_b.setText(String.valueOf(b));

                String hexCode = setColorGetHex(a, r, g, b);
                updateIndicators(hexCode, indicator_icon, edt_indicator_hex);
            }
            @Override
            public void onStartTrackingTouch(SeekBar s) {
                //
            }
            @Override
            public void onStopTrackingTouch(SeekBar s) {
                indicator_b.setText(String.valueOf(b));

                String hexCode = setColorGetHex(a, r, g, b);
                updateIndicators(hexCode, indicator_icon, edt_indicator_hex);

                UserPreferencesManager.setIntPrefValue(context,
                        ProjectConstants.C_S_CODE_BLUE, b);
                UserPreferencesManager.setStringPrefValue(context,
                        ProjectConstants.C_S_HEX_LATEST, hexCode);
                UserPreferencesManager.setIntPrefValue(context,
                        ProjectConstants.C_S_ARGB_COLOR_LATEST, ColorSelector.COLOR_ENCODED);

                if ( onColorSelectedListener != null ) {
                    onColorSelectedListener.getOnColorAlphaSelectedListener(a);
                    onColorSelectedListener.getOnColorStringSelectedListener(hexCode);
                    onColorSelectedListener.getOnColorIntSelectedListener(ColorSelector.COLOR_ENCODED);
                }
            }
        });
        // end:: b

        // start:: a
        color_a = dialog.findViewById(R.id.color_a);
        color_a.setProgress(a);
        indicator_a.setText(String.valueOf(a));
        color_a.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar s, int progress, boolean fromUser) {
                a = progress;
                indicator_a.setText(String.valueOf(a));

                String hexCode = setColorGetHex(a, r, g, b);
                updateIndicators(hexCode, indicator_icon, edt_indicator_hex);
            }
            @Override
            public void onStartTrackingTouch(SeekBar s) {
                //
            }
            @Override
            public void onStopTrackingTouch(SeekBar s) {
                indicator_a.setText(String.valueOf(a));

                String hexCode = setColorGetHex(a, r, g, b);
                updateIndicators(hexCode, indicator_icon, edt_indicator_hex);

                UserPreferencesManager.setIntPrefValue(context,
                        ProjectConstants.C_S_CODE_ALPHA, a);
                UserPreferencesManager.setStringPrefValue(context,
                        ProjectConstants.C_S_HEX_LATEST, hexCode);
                UserPreferencesManager.setIntPrefValue(context,
                        ProjectConstants.C_S_ARGB_COLOR_LATEST, ColorSelector.COLOR_ENCODED);

                if ( onColorSelectedListener != null ) {
                    onColorSelectedListener.getOnColorAlphaSelectedListener(a);
                    onColorSelectedListener.getOnColorStringSelectedListener(hexCode);
                    onColorSelectedListener.getOnColorIntSelectedListener(ColorSelector.COLOR_ENCODED);
                }
            }
        });
        // end:: a

        // other defaults
        hex_coloy_apply_icon = dialog.findViewById(R.id.hex_coloy_apply_icon);
        hex_coloy_apply_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hexCode = getHexCode(a, r, g, b);
                if ( onColorSelectedListener != null ) {
                    onColorSelectedListener.getOnColorAlphaSelectedListener(a);
                    onColorSelectedListener.getOnColorStringSelectedListener(hexCode);
                    onColorSelectedListener.getOnColorIntSelectedListener(ColorSelector.COLOR_ENCODED);
                }
                UserPreferencesManager.setIntPrefValue(context,
                        ProjectConstants.C_S_ARGB_COLOR_LATEST, ColorSelector.COLOR_ENCODED);
                dialog.dismiss();
            }
        });

        copy_hex_color_code_icon = dialog.findViewById(R.id.copy_hex_color_code_icon);
        copy_hex_color_code_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copyHexFromEditText(activity, dialog, edt_indicator_hex.getText().toString());
            }
        });
    }

    private String setColorGetHex(int a, int r, int g, int b) {
        ColorSelector.get_ARGB_Color(a, r, g, b);
        return getHexCode(a, r, g, b);
    }

    private String getHexCode(int a, int r, int g, int b) {
        String hexColorCode = "#"+ ColorSelector.getHex(a);
        hexColorCode += ColorSelector.getHex(r);
        hexColorCode += ColorSelector.getHex(g);
        hexColorCode += ColorSelector.getHex(b);
        return hexColorCode.toUpperCase();
    }

    private String getHexCodeShort(int r, int g, int b) {
        String hexColorCode = "#"+ ColorSelector.getHex(r);
        hexColorCode += ColorSelector.getHex(g);
        hexColorCode += ColorSelector.getHex(b);
        return hexColorCode.toUpperCase();
    }

    private void updateIndicators(String hexCode, ImageView indicator_icon, TextView edt_indicator_hex) {
        edt_indicator_hex.setText(hexCode);
        indicator_icon.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(hexCode)));
    }

    public void copyHexFromEditText(Activity activity, Dialog dialog, String hexCode) {
        // copy to clipboard - https://developer.android.com/guide/topics/text/copy-paste
        ClipboardManager clipboard = (ClipboardManager)
                activity.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(ProjectConstants.C_S_HEX_COPIED,
                hexCode);
        clipboard.setPrimaryClip(clip);

        dialog.dismiss();
    }

    /**
     * Interface to listen to, to apply selected color
     */
    private OnColorSelectedListener onColorSelectedListener;
    public interface OnColorSelectedListener {
        public void getOnColorStringSelectedListener(String color);
        public void getOnColorIntSelectedListener(int color);
        public void getOnColorAlphaSelectedListener(int color);
    }
    public void setOnColorSelectedListener(OnColorSelectedListener onColorSelectedListener) {
        this.onColorSelectedListener = onColorSelectedListener;
    }

}