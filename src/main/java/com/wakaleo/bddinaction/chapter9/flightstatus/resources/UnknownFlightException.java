package com.wakaleo.bddinaction.chapter9.flightstatus.resources;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


public class UnknownFlightException extends WebApplicationException {
    public UnknownFlightException() {
        Response.status(Response.Status.NOT_FOUND).build();
    }

    public UnknownFlightException(String message) {
        super(Response.status(Response.Status.NOT_FOUND).
                entity(message).type("text/plain").build());
    }
}
