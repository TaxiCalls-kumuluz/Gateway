/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taxicalls.gateway.services;

import com.taxicalls.gateway.model.Driver;
import com.taxicalls.gateway.model.Passenger;
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
public class PassengerService {

    @Inject
    private ServiceRegistry serviceRegistry;

    @Inject
    public PassengerService() {
    }

    public Response authenticatePassenger(Passenger passenger) {
        return ClientBuilder.newClient()
                .target(serviceRegistry.discoverServiceURI(getClass().getSimpleName()))
                .path("authenticate")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(passenger, MediaType.APPLICATION_JSON), Response.class);
    }

    public Response getAvailableDrivers() {
        return ClientBuilder.newClient()
                .target(serviceRegistry.discoverServiceURI(getClass().getSimpleName()))
                .path("trips").path("drivers").path("available")
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class);
    }

    public Response chooseDriver(Driver driver) {
        return ClientBuilder.newClient()
                .target(serviceRegistry.discoverServiceURI(getClass().getSimpleName()))
                .path("trips").path("drivers").path("choose")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(driver, MediaType.APPLICATION_JSON), Response.class);
    }

}
