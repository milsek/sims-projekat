package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.model.dto.SelectedBookDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select * from book b where to_char(b.book_id) like ?1 limit 5", nativeQuery = true)
    public Set<Book> findByIdStartingWith(String id);


    @Query(
            value =
                "select b.* from book b inner join edition e on b.edition_id = e.id inner join book_title bt on e.title_id = bt.id " +
                "where (b.book_id = ?1 or ?1 is null) and (upper(bt.title) = upper(?2) or ?2 is null) and " +
                "(upper(?3) = upper((select c.name from contributor c inner join contribution ct on" +
                " c.id = ct.contributor_id where c.id = " +
                "(select btc.contributions_id from book_title_contributions btc where btc.book_title_id = bt.id order by btc.contributions_id asc limit 1))) or ?3 is null)"
            ,
            nativeQuery = true
    )
    List<Book> searchBook(String id, String title, String author);
}
