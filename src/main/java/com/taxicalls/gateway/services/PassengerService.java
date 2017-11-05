/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taxicalls.gateway.services;

import com.taxicalls.gateway.model.Passenger;
import com.taxicalls.gateway.model.Trip;
import com.taxicalls.gateway.resources.ChooseDriverRequest;
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

    private Response post(Object object, String path) {
        return ClientBuilder.newClient()
                .target(serviceRegistry.discoverServiceURI(getClass().getSimpleName()))
                .path(path)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(object, MediaType.APPLICATION_JSON), Response.class);
    }

    public Response authenticatePassenger(Passenger passenger) {
        return post(passenger, "authenticate");
    }

    public Response getAvailableDrivers(Trip trip) {
        return post(trip, "trips/drivers/available");
    }

    public Response chooseDriver(ChooseDriverRequest chooseDriverRequest) {
        return post(chooseDriverRequest, "trips/drivers/choose");
    }

    public Response createPassenger(Passenger passenger) {
        return post(passenger, "passengers");
    }

}
