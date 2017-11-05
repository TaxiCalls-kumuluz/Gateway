package com.taxicalls.gateway.resources;

import com.taxicalls.gateway.model.Driver;
import com.taxicalls.gateway.model.Trip;
import com.taxicalls.gateway.services.DriverService;
import com.taxicalls.gateway.services.NotificationService;
import com.taxicalls.gateway.services.TripService;
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

@Path("/drivers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class DriversResource {

    @Inject
    private DriverService driverService;

    @Inject
    private TripService tripService;

    @Inject
    private NotificationService notificationService;

    private static final Logger LOGGER = Logger.getLogger(DriversResource.class.getName());

    @POST
    @Path("/authenticate")
    public Response authenticateDriver(Driver driver) {
        LOGGER.log(Level.INFO, "authenticateDriver() invoked");
        return driverService.authenticateDriver(driver);
    }

    @POST
    @Path("/trips")
    public Response acceptTrip(Trip trip) {
        LOGGER.log(Level.INFO, "acceptTrip() invoked");
        return driverService.acceptTrip(trip);
    }

    @POST
    @Path("/update")
    public Response updateDriver(Driver driver) {
        LOGGER.log(Level.INFO, "updateDriver() invoked");
        return tripService.updateDriver(driver);
    }

    @POST
    @Path("/notifications/check")
    public Response checkNotifications(Driver driver) {
        LOGGER.log(Level.INFO, "checkNotifications() invoked");
        CheckNotificationsRequest checkNotificationsRequest = new CheckNotificationsRequest();
        checkNotificationsRequest.setEntity(driver.getClass().getSimpleName());
        checkNotificationsRequest.setId(driver.getId());
        return notificationService.checkNotifications(checkNotificationsRequest);
    }
}
