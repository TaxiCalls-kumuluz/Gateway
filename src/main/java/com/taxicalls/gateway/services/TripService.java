/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taxicalls.gateway.services;

import com.taxicalls.gateway.model.Driver;
import com.taxicalls.gateway.model.Trip;
import com.taxicalls.gateway.resources.AvailableDriversRequest;
import com.taxicalls.protocol.Response;
import com.taxicalls.utils.ServiceRegistry;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author romulo
 */
@ApplicationScoped
public class TripService {

    @Inject
    private ServiceRegistry serviceRegistry;

    @Inject
    public TripService() {
    }

    public Response post(Object object, String path) {
        return ClientBuilder.newClient()
                .target(serviceRegistry.discoverServiceURI(getClass().getSimpleName()))
                .path(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(object, MediaType.APPLICATION_JSON), Response.class);
    }

    public Response getAvailableDrivers(AvailableDriversRequest availableDriversRequest) {
        return post(availableDriversRequest, "drivers/available");
    }
    
    public Response requestTrip(Trip trip) {
        return post(trip, "trips/request");
    }

    public Response updateDriver(Driver driver) {
        return post(driver, "drivers/update");
    }

    public Response updateTrip(Trip trip) {
        return post(trip, "trips/update");
    }
}
