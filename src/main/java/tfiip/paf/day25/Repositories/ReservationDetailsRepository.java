package tfiip.paf.day25.Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tfiip.paf.day25.Models.ReservationDetails;

@Repository
public class ReservationDetailsRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SQL_INSERT = "insert into reservation_details (book_id, reservation_id) values (?,?)";

    public int create(ReservationDetails reservationDetails) {
        // Create GeneratedKeyHolder object
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[] {"id}"});
                ps.setInt(1,reservationDetails.getBookId());
                ps.setInt(2,reservationDetails.getReservationId());
                return ps;
            }
        };
        jdbcTemplate.update(psc,generatedKeyHolder);

        //Get auto-incremented ID
        Integer returnedId = generatedKeyHolder.getKey().intValue();
        return returnedId;
    }

}
