/*
 * Copyright (c) 2010-2014 lijunlin All Rights Reserved.
 * The software source code all copyright belongs to the author, 
 * without permission shall not be any reproduction and transmission.
 */
package org.walkerljl.toolkit.lang;

import java.text.DateFormatSymbols;
import java.util.Locale;

/**
 * Simple holder for <code>DateFormatSymbols</code> that doesn't create new array on each call.
 * This improves performance by avoiding duplication of returned arrays.
 * <p>
 * Use this class from {@link LocaleUtils} or cache it manually.
 */
public class DateFormatSymbolsEx {

    protected final String[] months;
    protected final String[] shortMonths;
    protected final String[] weekdays;
    protected final String[] shortWeekdays;
    protected final String[] eras;
    protected final String[] ampms;

    public DateFormatSymbolsEx(Locale locale) {
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);

        months = dateFormatSymbols.getMonths();
        shortMonths = dateFormatSymbols.getShortMonths();
        weekdays = dateFormatSymbols.getWeekdays();
        shortWeekdays = dateFormatSymbols.getShortWeekdays();
        eras = dateFormatSymbols.getEras();
        ampms = dateFormatSymbols.getAmPmStrings();
    }

    // ---------------------------------------------------------------- getters

    /**
     * Returns month string.
     */
    public String getMonth(int i) {
        return this.months[i];
    }

    /**
     * Returns short months.
     */
    public String getShortMonth(int i) {
        return this.shortMonths[i];
    }

    /**
     * Returns weekday.
     */
    public String getWeekday(int i) {
        return this.weekdays[i];
    }

    /**
     * Returns short weekday.
     */
    public String getShortWeekday(int i) {
        return this.shortWeekdays[i];
    }

    /**
     * Returns BC era.
     */
    public String getBcEra() {
        return this.eras[0];
    }

    /**
     * Returns AD era.
     */
    public String getAdEra() {
        return this.eras[1];
    }

    /**
     * Returns AM.
     */
    public String getAM() {
        return this.ampms[0];
    }

    /**
     * Returns PM.
     */
    public String getPM() {
        return this.ampms[1];
    }

}