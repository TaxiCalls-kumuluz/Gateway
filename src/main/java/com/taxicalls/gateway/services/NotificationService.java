/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taxicalls.gateway.services;

import com.taxicalls.gateway.model.Driver;
import com.taxicalls.protocol.Response;
import com.taxicalls.utils.ServiceRegistry;
import java.util.Collection;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author romulo
 */
@ApplicationScoped
public class NotificationService {

    @Inject
    private ServiceRegistry serviceRegistry;

    @Inject
    public NotificationService() {
    }

    public Response chooseDriver(Driver driver) {
        return ClientBuilder.newClient()
                .target(serviceRegistry.discoverServiceURI(getClass().getSimpleName()))
                .path("notifications")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(driver, MediaType.APPLICATION_JSON), Response.class);
    }

}
