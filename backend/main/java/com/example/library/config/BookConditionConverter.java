package com.example.library.config;

import com.example.library.model.BookCondition;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BookConditionConverter implements AttributeConverter<BookCondition, String> {

    @Override
    public String convertToDatabaseColumn(BookCondition attribute) {
        return attribute.name().toUpperCase();
    }

    @Override
    public BookCondition convertToEntityAttribute(String dbData) {
        return BookCondition.valueOf(dbData.toUpperCase());
    }
}