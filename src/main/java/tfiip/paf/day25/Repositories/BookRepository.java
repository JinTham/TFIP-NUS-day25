package tfiip.paf.day25.Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tfiip.paf.day25.Models.Book;

@Repository
public class BookRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SQL_SELECT = "select * from book";
    private final String SQL_INSERT = "insert to book (title, quantity) values (?,?)";
    private final String SQL_UPDATE_QUANTITY = "update book set quantity = quantity-1 where id = ?";
    private final String SQL_FINDBYID = "select * from book where id = ?";

    public List<Book> findAll() {
        return jdbcTemplate.query(SQL_SELECT,BeanPropertyRowMapper.newInstance(Book.class));
    }

    public Book findById(Integer id) {
        return jdbcTemplate.queryForObject(SQL_FINDBYID,BeanPropertyRowMapper.newInstance(Book.class), id);
    }

    public int update(Integer id) {
        return jdbcTemplate.update(SQL_UPDATE_QUANTITY, id);
    }

    public int create(Book book) {
        // Create GeneratedKeyHolder object
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[] {"id}"});
                ps.setString(1,book.getTitle());
                ps.setInt(2,book.getQuantity());
                return ps;
            }
        };
        jdbcTemplate.update(psc,generatedKeyHolder);

        //Get auto-incremented ID
        Integer returnedId = generatedKeyHolder.getKey().intValue();
        return returnedId;
    }

}
