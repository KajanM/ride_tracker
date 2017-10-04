package com.pluralsight.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pluralsight.model.Ride;

/**
 * Created by k4j4n on 10/4/17.
 */
public class RideRowMapper implements RowMapper<Ride>{
	@Override
	public Ride mapRow(ResultSet resultSet, int i) throws SQLException {
		Ride ride = new Ride();

		ride.setId(resultSet.getInt("id"));
		ride.setName(resultSet.getString("name"));
		ride.setDuration(resultSet.getInt("duration"));

		return ride;
	}
}
