package com.gprc.server_service.repository;

import com.gprc.server_service.entity.VehicleDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleDetails,Integer> {

}
