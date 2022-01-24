package com.grv.location.service;

import java.util.List;

import com.grv.location.entities.Location;

public interface LocationService {
	public Location saveLocation(Location location);

	public Location updateLocation(Location location);

	public void deleteLocation(Location location);

	public Location getLocationById(int id);

	public List<Location> getAllLocation();
}
