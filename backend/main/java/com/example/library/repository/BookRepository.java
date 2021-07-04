package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.model.dto.SelectedBookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select * from book b where to_char(b.book_id) like ?1 limit 5", nativeQuery = true)
    public Set<Book> findByIdStartingWith(String id);


    @Query(
            value =
                "select b.* from book b inner join edition e on b.edition_id = e.id inner join book_title bt on e.title_id = bt.id " +
                "where (b.book_id = :id or :id is null) and (upper(bt.title) = upper(:title) or :title is null) and (b.state = :status or :status is null) and " +
                "(upper(:author) = upper((select c.name from contributor c inner join contribution ct on" +
                " c.id = ct.contributor_id where c.id = " +
                "(select btc.contributions_id from book_title_contributions btc where btc.book_title_id = bt.id order by btc.contributions_id asc limit 1))) or :author is null) ",
            countQuery = "select count(b.*) from book b inner join edition e on b.edition_id = e.id inner join book_title bt on e.title_id = bt.id  " +
                    "                where (b.book_id = :id or :id is null) and (upper(bt.title) = upper(:title) or :title is null) and (b.state = :status or :status is null) and  \n" +
                    "                (upper(:author) = upper((select c.name from contributor c inner join contribution ct on" +
                    "                 c.id = ct.contributor_id where c.id =  \n" +
                    "                (select btc.contributions_id from book_title_contributions btc where btc.book_title_id = bt.id order by btc.contributions_id asc limit 1))) or :author is null)\n",
            nativeQuery = true
    )
    Page<Book> searchBook(@Param("id") String id,
                          @Param("title") String title,
                          @Param("author") String author,
                          @Param("status") String status,
                          Pageable paging);

    Book findBookById(Long bookId);
}
