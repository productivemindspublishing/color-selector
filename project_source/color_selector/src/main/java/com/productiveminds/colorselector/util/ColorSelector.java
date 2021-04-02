package com.productiveminds.colorselector.util;

import android.graphics.Color;

import androidx.annotation.ColorInt;

public class ColorSelector extends Color {
    @ColorInt
    private int a;
    @ColorInt
    private int r;
    @ColorInt
    private int g;
    @ColorInt
    private int b;
    @ColorInt
    public static int COLOR;
    @ColorInt
    public static int COLOR_WITH_A;
    @ColorInt
    public static int COLOR_ENCODED;

    /**
     * defaults to black
     */
    public ColorSelector() {
        this.a = 255;
        this.r = 0;
        this.g = 0;
        this.b = 0;
        COLOR = Color.rgb(r,g,b);
        COLOR_WITH_A = Color.argb(a,r,g,b);
        COLOR_ENCODED = encodeColor(a, r, g, b);
    }

    /**
     * @param a alpha value
     * @param r red value
     * @param g green value
     * @param b blue value
     */
    public ColorSelector(int a, int r, int g, int b) {
        this.a = a;
        this.r = r;
        this.g = g;
        this.b = b;
        COLOR = Color.rgb(r,g,b);
        COLOR_WITH_A = Color.argb(a,r,g,b);
        COLOR_ENCODED = encodeColor(a, r, g, b);
    }

    /**
     * Method to get ARGB color when class is instantiated
     * @return
     */
    public int getARGBColor() {
        return Color.argb(a,r,g,b);
    }

    /**
     * Method to get rgb color when class is instantiated
     * @return
     */
    public int getRGBColor() {
        return Color.rgb(r,g,b);
    }

    /**
     * @param a alpha value
     * @param r red value
     * @param g green value
     * @param b blue value
     * @return encoded Color
     */
    @ColorInt
    public static int get_ARGB_Color(int a, int r, int g, int b) {
        COLOR = Color.rgb(r,g,b);
        COLOR_WITH_A = Color.argb(a,r,g,b);
        COLOR_ENCODED = encodeColor(a, r, g, b);
        return Color.argb(a,r,g,b);
    }

    /**
     * @param a alpha value
     * @param r red value
     * @param g green value
     * @param b blue value
     * @return encoded Color
     */
    @ColorInt
    public static int encodeColor(int a, int r, int g, int b) {
        int c = (a & 0xff) << 24 | (r & 0xff) << 16 | (g & 0xff) << 8 | (b & 0xff);
        return c;
    }

    /**
     * returns the encoded Hex value of color
     * @return encoded Color
     */
    public static String getHex(int color) {
        String aa = Integer.toHexString((int) ((float)color/16f));
        aa += Integer.toHexString(color % 16);
        return aa;
    }

}
