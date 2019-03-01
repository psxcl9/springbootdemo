package com.liaocx.common.convert;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class DateSerializer extends JsonSerializer {
    @Override
    public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
        if(!Objects.isNull(arg0)) {
            Date value=(Date)arg0;
            arg1.writeNumber(value.getTime());
        }

    }
}
