package com.example.library;

import com.example.library.model.Book;
import com.example.library.model.Edition;
import com.example.library.model.Member;
import com.example.library.model.Review;
import com.example.library.model.dto.EditionDisplayDto;
import com.example.library.model.dto.EditionDto;
import com.example.library.model.dto.ReviewDisplayDto;
import com.example.library.model.dto.SelectedBookDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        Converter<Edition, EditionDisplayDto> authorNameConverterDisplay = context -> {
            EditionDisplayDto dto = new EditionDisplayDto();
            dto.setAuthorName(context.getSource().getTitle().getContributions().get(0).getContributor().getName());
            return dto;
        };

        Converter<Edition, EditionDto> reviewToDtoConverter = context -> {
            EditionDto dto = new EditionDto();
            dto.setReviews(context.getSource().getReviews().stream().map(x -> mm.map(x, ReviewDisplayDto.class)).collect(Collectors.toList()));
            return dto;
        };

        Converter<Review, ReviewDisplayDto> fullNameConverter = context -> {
            ReviewDisplayDto dto = new ReviewDisplayDto();
            Member member = (Member) context.getSource().getBookReservation().getUser();
            dto.setFullName(member.getName() + " " + member.getSurname());
            return dto;
        };

        mm.createTypeMap(Book.class, SelectedBookDto.class).setPreConverter(authorNameConverter);
        mm.createTypeMap(Review.class, ReviewDisplayDto.class).setPreConverter(fullNameConverter);
        mm.createTypeMap(Edition.class, EditionDisplayDto.class).setPreConverter(authorNameConverterDisplay);
        mm.createTypeMap(Edition.class, EditionDto.class).setPreConverter(reviewToDtoConverter);

        return mm;
    }
}

/*
{
  "id": 70,
  "takeOut": false,
  "reads": 572,
  "rating": 1.7,
  "dimensions": "18",
  "pageCount": 252,
  "description": "<p>In this critique of security studies, with insights into the thinking of Heidegger, Foucault, Derrida, Levinas and Arendt, Michael Dillon contributes to the rethinking of some of the fundamentals of international politics developing what might be called a political philosophy of continental thought. Drawing on the work of Martin Heidegger, Politics of Security establishes the relationship between Heidegger's readical hermeneutical phenomenology and politics and the fundamental link between politics, the tragic and the ethical. It breaks new ground by providing an etymology of security, tracing the word back to the Greek asphaleia (not to trip up or fall down), and a unique political reading of Oedipus Rex . Michael Dillon traces the roots of desire for security to the metaphysical desire for certitude, and points out that our way of seeking that security is embedded in 20th century technology, thus resulting in a global crisis.",
  "language": "en",
  "year": "1996-01-01",
  "imageSmall": "http://covers.openlibrary.org/b/isbn/9780415129602-M.jpg",
  "imageLarge": "http://covers.openlibrary.org/b/isbn/9780415129602-L.jpg",
  "titleTitle": "Politics of Security",
  "author": null,
  "reviews": [
    {
      "content": "Integer imperdiet mauris sed ante bibendum, sit amet consectetur tortor imperdiet. Integer gravida erat egestas, porta risus ut, ultrices lorem. Pellentesque tempus sem ut semper malesuada. Etiam eu metus vitae nisl volutpat efficitur. ",
      "rating": 1.7,
      "fullName": null
    }
  ],
  "genres": [],
  "tags": [
    {
      "id": 7,
      "name": "hacking"
    },
    {
      "id": 0,
      "name": "cryptography"
    },
    {
      "id": 4,
      "name": "programming"
    },
    {
      "id": 1,
      "name": "security"
    },
    {
      "id": 2,
      "name": "computers"
    }
  ],
  "publisherName": "Psychology Press"
}

 */