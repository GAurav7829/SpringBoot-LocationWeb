package com.grv.location.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grv.location.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {
	
	@Query("SELECT type, count(type) FROM Location GROUP BY type")
	public List<Object[]> findTypeAndTypeCount();
}
