package com.grv.location.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grv.location.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
