package com.example.library;

import com.example.library.model.Book;
import com.example.library.model.dto.SelectedBookDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mm = new ModelMapper();

        Converter<Book, SelectedBookDto> authorNameConverter = context -> {
            SelectedBookDto dto = new SelectedBookDto();
            dto.setAuthorName(context.getSource().getEdition().getTitle().getContributions().get(0).getContributor().getName());
            return dto;
        };

        mm.createTypeMap(Book.class, SelectedBookDto.class).setPreConverter(authorNameConverter);

        return mm;
    }
}
