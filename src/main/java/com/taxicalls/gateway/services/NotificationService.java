/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taxicalls.gateway.services;

import com.taxicalls.gateway.resources.CheckNotificationsRequest;
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
public class NotificationService {

    @Inject
    private ServiceRegistry serviceRegistry;

    @Inject
    public NotificationService() {
    }

    public Response checkNotifications(CheckNotificationsRequest checkNotificationsRequest) {
        return ClientBuilder.newClient()
                .target(serviceRegistry.discoverServiceURI(getClass().getSimpleName()))
                .path("checks")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(checkNotificationsRequest, MediaType.APPLICATION_JSON), Response.class);
    }

}
