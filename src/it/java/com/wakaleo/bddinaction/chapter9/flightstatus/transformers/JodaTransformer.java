package com.wakaleo.bddinaction.chapter9.flightstatus.transformers;

import cucumber.api.Transformer;
import cucumber.runtime.ParameterInfo;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Locale;

public class JodaTransformer extends Transformer<LocalDate> {

    private String format;

    @Override
    public void setParameterInfoAndLocale(ParameterInfo parameterInfo, Locale locale) {
        super.setParameterInfoAndLocale(parameterInfo, locale);
        this.format = parameterInfo.getFormat();
    }

    public LocalDate transform(String value) {
        return  DateTimeFormat.forPattern(format).parseLocalDate(value);
    }
}