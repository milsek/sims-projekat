package com.example.library.config;

import com.example.library.model.BookState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BookStateConverter implements AttributeConverter<BookState, String> {

    @Override
    public String convertToDatabaseColumn(BookState attribute) {
        return attribute.name().toUpperCase();
    }

    @Override
    public BookState convertToEntityAttribute(String dbData) {
        return BookState.valueOf(dbData.toUpperCase());
    }
}