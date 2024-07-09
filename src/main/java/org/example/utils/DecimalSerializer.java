package org.example.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

@Component
public class DecimalSerializer extends StdSerializer<Double> {

    public DecimalSerializer() {
        this(null);
    }

    protected DecimalSerializer(Class<Double> t) {
        super(t);
    }

    @Override
    public void serialize(Double aDouble, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.00", otherSymbols);
        jsonGenerator.writeString(df.format(aDouble));
    }
}
