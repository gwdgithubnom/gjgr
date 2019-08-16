package org.ose.javase;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class Format {
    public static void main(String[] args) {
        // Fixed-point number
        System.out.println("===Fixed-point number===");
        final NumberFormat numFormat = NumberFormat.getNumberInstance();
        System.out.println("Instance of: " + numFormat.getClass().getCanonicalName());
        numFormat.setRoundingMode(RoundingMode.HALF_UP);
        numFormat.setMinimumIntegerDigits(1);
        numFormat.setMaximumFractionDigits(2);
        System.out.println("234.234567   -> " + numFormat.format(234.234567));
        System.out.println("1            -> " + numFormat.format(1));
        System.out.println(".234567      -> " + numFormat.format(.234567));
        System.out.println(".349         -> " + numFormat.format(.349));
        System.out.println(".3499        -> " + numFormat.format(.3499));
        System.out.println(".9999        -> " + numFormat.format(0.9999));

        // Integer
        System.out.println("===Integer===");
        final NumberFormat intFormat = NumberFormat.getIntegerInstance(Locale.US);
        System.out.println("Instance of: " + intFormat.getClass().getCanonicalName());
        System.out.println("7.65   -> " + intFormat.format(7.65)); // round
        System.out.println("7.5    -> " + intFormat.format(7.5));
        System.out.println("7.49   -> " + intFormat.format(7.49));
        System.out.println("-23.23 -> " + intFormat.format(-23.23));

        // Percent
        System.out.println("===Percent===");
        final NumberFormat perFormat = NumberFormat.getPercentInstance(Locale.US);
        System.out.println("getMinimumFractionDigits(): " + perFormat.getMinimumFractionDigits());
        System.out.println("Instance of: " + perFormat.getClass().getCanonicalName());
        System.out.println("1        -> " + perFormat.format(1));
        System.out.println(".75      -> " + perFormat.format(.75));
        System.out.println("75.0/100 -> " + perFormat.format(75.0 / 100));
        System.out.println("93/83    -> " + perFormat.format(93 / 83));
        System.out.println(".5       -> " + perFormat.format(.5));
        System.out.println(".912     -> " + perFormat.format(.912));
        System.out.println("---- Setting Minimum Fraction Digits to 1:");
        perFormat.setMinimumFractionDigits(1);
        System.out.println("1        -> " + perFormat.format(1));
        System.out.println(".75      -> " + perFormat.format(.75));
        System.out.println("75.0/100 -> " + perFormat.format(75.0 / 100));
        System.out.println(".912     -> " + perFormat.format(.912));

        // Currency
        System.out.println("===Currency===");
        final NumberFormat currFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);
        System.out.println("Instance of: " + currFormat.getClass().getCanonicalName());
        System.out.println("15.5   -> " + currFormat.format(15.5));
        System.out.println("15.54  -> " + currFormat.format(15.54));
        System.out.println("15.545 -> " + currFormat.format(15.545));
        Currency currency = currFormat.getCurrency();
        System.out.println("getCurrencyCode():" + currency.getCurrencyCode());
        System.out.println("getNumericCode():" + currency.getNumericCode());
        System.out.println("getDisplayName():" + currency.getDisplayName());
        System.out.println("getDisplayName(locale):" + currency.getDisplayName(Locale.CHINA));
        System.out.println("getSymbol():" + currency.getSymbol());
        System.out.println("getSymbol(locale): " + currency.getSymbol(Locale.CHINA));
        System.out.println("getDefaultFractionDigits(): " + currency.getDefaultFractionDigits());
    }
}
