package com.grv.location.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grv.location.entities.Location;
import com.grv.location.repos.LocationRepository;

@RestController
@RequestMapping("/locations")
public class LocationRestController {
	@Autowired
	private LocationRepository repository;
	
	@GetMapping
	public List<Location> getLocations(){
		return repository.findAll(); 
	}
	
	@PostMapping("/createLocation")
	public Location createLocation(@RequestBody Location location) {
		return repository.save(location);
	}
	
	@PutMapping("/updateLocation")
	public Location updateLocation(@RequestBody Location location) {
		return repository.save(location);
	}
	
	@DeleteMapping("/{id}")
	public String deleteLocation(@PathVariable int id) {
		try {
			repository.deleteById(id);
		}catch(Exception ex) {
			return ex.getMessage();
		}
		return "Location Deleted";
	}
	
	@GetMapping("/{id}")
	public Location getLocation(@PathVariable int id) {
		try {
			Location location = repository.findById(id).get();
			return location;
		}catch(Exception ex) {
			return new Location();
		}
	}
	
}
