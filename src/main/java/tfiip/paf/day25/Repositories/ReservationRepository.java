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

import tfiip.paf.day25.Models.Reservation;

@Repository
public class ReservationRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SQL_SELECT = "select * from reservation";
    private final String SQL_INSERT = "insert to reservation (reservation_date, full_name) values (?,?)";

    public List<Reservation> findAll() {
        return jdbcTemplate.query(SQL_SELECT,BeanPropertyRowMapper.newInstance(Reservation.class));
    }

    public int create(Reservation reservation) {
        // Create GeneratedKeyHolder object
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[] {"id}"});
                ps.setDate(1,reservation.getReservationDate());
                ps.setString(2,reservation.getFullName());
                return ps;
            }
        };
        jdbcTemplate.update(psc,generatedKeyHolder);

        //Get auto-incremented ID
        Integer returnedId = generatedKeyHolder.getKey().intValue();
        return returnedId;
    }

}
