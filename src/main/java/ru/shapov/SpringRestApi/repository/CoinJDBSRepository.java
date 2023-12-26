package ru.shapov.SpringRestApi.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.shapov.SpringRestApi.model.Coin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CoinJDBSRepository {
    private final JdbcTemplate jdbcTemplate;
    public CoinJDBSRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Coin> findAll() {
        return jdbcTemplate.query("select * from coin",
                new StudentMapper());
    }

    public Coin findById(Long id) {
        return jdbcTemplate.query("select * from coin where id=" + id,
                new StudentMapper()).get(0);
    }

    public List<Coin> findAllCountryId(Long id) {
        return jdbcTemplate.query("select * from coin where country_id=" + id,
                new StudentMapper());
    }

    public class StudentMapper implements RowMapper<Coin> {
        @Override
        public Coin mapRow(ResultSet rs, int rowNum) throws SQLException {
            Coin coin = new Coin();
            coin.setId(rs.getInt("id"));
            coin.setCountryId(rs.getInt("country_id"));
            coin.setDenomination(rs.getInt("denomination"));
            coin.setCurrencyId(rs.getInt("currency_id"));
            coin.setMintId(rs.getInt("mint_id"));
            coin.setPicturePath(rs.getString("picture_path"));
            coin.setYearMinting(rs.getInt("year_minting"));
            return coin;
        }
    }
}
