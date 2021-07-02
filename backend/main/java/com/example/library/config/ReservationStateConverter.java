package com.example.library.config;

import com.example.library.model.ReservationState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Locale;

@Converter(autoApply = true)
public class ReservationStateConverter implements AttributeConverter<ReservationState, String> {

    @Override
    public String convertToDatabaseColumn(ReservationState attribute) {
        return attribute.name().toUpperCase();
    }

    @Override
    public ReservationState convertToEntityAttribute(String dbData) {
        return ReservationState.valueOf(dbData.toUpperCase());
    }
}