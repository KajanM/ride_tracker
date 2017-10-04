package com.pluralsight.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pluralsight.model.Ride;

import org.junit.Test;

public class RestControllerTest {

	@Test(timeout = 3000)
	public void testCreateRides() {
		RestTemplate restTemplate = new RestTemplate();

		Ride ride = new Ride();

		ride.setName("test ride 6");
		ride.setDuration(35);

		ride = restTemplate.postForObject("http://localhost:8080/ride", ride, Ride.class);

		System.out.println(ride);
	}

	@Test(timeout=3000)
	public void testGetRides() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
				"http://localhost:8080/rides", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Ride>>() {
				});
		List<Ride> rides = ridesResponse.getBody();

		for (Ride ride : rides) {
			System.out.println("Ride name: " + ride.getName());
		}
	}

	@Test(timeout = 3000)
	public void testGetRide() {
		RestTemplate restTemplate = new RestTemplate();

		Ride ride = restTemplate.getForObject("http://localhost:8080/ride/4", Ride.class);

		System.out.println("Ride name : " + ride.getName());
	}

	@Test(timeout = 3000)
	public void testUpdateRide() {
		RestTemplate restTemplate = new RestTemplate();

		Ride ride = restTemplate.getForObject("http://localhost:8080/ride/2", Ride.class);

		System.out.println("old duration : " + ride.getDuration());

		ride.setDuration(ride.getDuration() + 1);

		restTemplate.put("http://localhost:8080/ride", ride);
	}

	@Test(timeout = 3000)
	public void testBatchUpdate() {
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getForObject("http://localhost:8080/batch", Object.class);
	}
}
