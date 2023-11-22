package ru.hzerr.util;

import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;

@SuppressWarnings("unused")
public class JsonToStringStyle extends ToStringStyle {

    @Serial
    private static final long serialVersionUID = 1L;

    public JsonToStringStyle() {
        setUseClassName(true);
        setUseShortClassName(true);
        setFieldNameValueSeparator(" = ");
        setUseIdentityHashCode(false);
        setContentStart(" {" + System.lineSeparator() + '\t');
        setFieldSeparator(',' + System.lineSeparator() + '\t');
        setFieldSeparatorAtStart(false);
        setArraySeparator(", ");
        setContentEnd(System.lineSeparator() + "}");
    }
}
