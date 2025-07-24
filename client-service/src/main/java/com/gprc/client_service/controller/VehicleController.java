package com.gprc.client_service.controller;

import com.gprc.client_service.service.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle-details")
public class VehicleController {

    @Autowired
    private VehicleServiceImpl vehicleService;

    @GetMapping
    public void getVehicleDetails(@RequestParam int id){
        vehicleService.subscribeVehicle(id);
    }
}
