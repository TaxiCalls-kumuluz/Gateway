package com.taxicalls.gateway.resources;

import com.taxicalls.gateway.model.Driver;
import com.taxicalls.gateway.model.Passenger;
import com.taxicalls.gateway.services.PassengerService;
import com.taxicalls.protocol.Response;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/passengers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class PassengersResource {

    private static final Logger LOGGER = Logger.getLogger(PassengersResource.class.getName());

    @Inject
    private PassengerService passengerService;

    @POST
    @Path("/authenticate")
    public Response authenticatePassenger(Passenger passenger) {
        return passengerService.authenticatePassenger(passenger);
    }

    @POST
    @Path("/trips/drivers/available")
    public Response getAvailableDrivers() {
        LOGGER.log(Level.INFO, "getAvailableDrivers() invoked");
        return passengerService.getAvailableDrivers();
    }

    @POST
    @Path("/trips/drivers/choose")
    public Response chooseDriver(Driver driver) {
        LOGGER.log(Level.INFO, "chooseDriver() invoked");
        return passengerService.chooseDriver(driver);
    }

}
