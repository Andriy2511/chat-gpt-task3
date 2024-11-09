package org.example.healthcaremanagementapplication.controller;

import org.example.healthcaremanagementapplication.service.MapboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mapbox")
public class MapboxController {

    private final MapboxService mapboxService;

    @Autowired
    public MapboxController(MapboxService mapboxService) {
        this.mapboxService = mapboxService;
    }

    @GetMapping("/route")
    public String getRoute(@RequestParam String start, @RequestParam String end) {
        String coordinates = start + ";" + end;
        return mapboxService.getRoute(coordinates);
    }
}