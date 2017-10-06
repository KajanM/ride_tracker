package com.pluralsight.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pluralsight.model.Ride;
import com.pluralsight.repository.util.RideRowMapper;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Ride> getRides() {

		List<Ride> rides = jdbcTemplate.query("SELECT * FROM ride", new RideRowMapper());
		return rides;
	}

	@Override
	public Ride createRide(Ride ride) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("name", ride.getName());
		paramMap.put("duration", ride.getDuration());

		namedParameterJdbcTemplate.update("insert into ride (name, duration) values (:name, :duration)", paramMap);

		return null;
	}

	@Override
	public Ride getRide(Integer id) {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		Ride ride = namedParameterJdbcTemplate.queryForObject("select * from ride where id = :id", paramMap, new RideRowMapper());
		return ride;
	}

	@Override
	public Ride updateRide(Ride ride) {
		jdbcTemplate.update("UPDATE ride SET name = ?, duration = ? WHERE id = ?", ride.getName(), ride.getDuration(),
			ride.getId());
		return ride;
	}

	@Override
	public void updateRides(List<Object[]> pairs) {
		jdbcTemplate.batchUpdate("update ride set ride_date = ? where id = ?", pairs);

	}

	@Override
	public void deleteRide(Integer id) {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);

		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		namedParameterJdbcTemplate.update("delete from ride where id = :id", paramMap);
	}

}
