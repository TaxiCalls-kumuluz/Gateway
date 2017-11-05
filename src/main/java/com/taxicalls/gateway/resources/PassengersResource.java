package com.taxicalls.gateway.resources;

import com.taxicalls.gateway.model.Passenger;
import com.taxicalls.gateway.model.Trip;
import com.taxicalls.gateway.services.NotificationService;
import com.taxicalls.gateway.services.PassengerService;
import com.taxicalls.gateway.services.TripService;
import com.taxicalls.protocol.Response;
import com.taxicalls.protocol.Status;

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

    @Inject
    private TripService tripService;

    @Inject
    private NotificationService notificationService;

    @POST
    @Path("/authenticate")
    public Response authenticatePassenger(Passenger passenger) {
        Response response = passengerService.authenticatePassenger(passenger);
        if (response.getStatus().equals(Status.NOT_FOUND)) {
            return passengerService.createPassenger(passenger);
        }
        return response;
    }

    @POST
    @Path("/trips/drivers/available")
    public Response getAvailableDrivers(Trip trip) {
        LOGGER.log(Level.INFO, "getAvailableDrivers() invoked");
        AvailableDriversRequest availableDriversRequest = new AvailableDriversRequest();
        availableDriversRequest.setCoordinate(trip.getAddressFrom().getCoordinate());
        availableDriversRequest.setRatio(5);
        return tripService.getAvailableDrivers(availableDriversRequest);
    }

    @POST
    @Path("/trips/drivers/choose")
    public Response chooseDriver(ChooseDriverRequest chooseDriverRequest) {
        LOGGER.log(Level.INFO, "chooseDriver() invoked");
        return passengerService.chooseDriver(chooseDriverRequest);
    }

    @POST
    @Path("/notifications/check")
    public Response checkNotifications(Passenger passenger) {
        LOGGER.log(Level.INFO, "checkNotifications() invoked");
        CheckNotificationsRequest checkNotificationsRequest = new CheckNotificationsRequest();
        checkNotificationsRequest.setEntity(passenger.getClass().getSimpleName());
        checkNotificationsRequest.setId(passenger.getId());
        return notificationService.checkNotifications(checkNotificationsRequest);
    }

}
