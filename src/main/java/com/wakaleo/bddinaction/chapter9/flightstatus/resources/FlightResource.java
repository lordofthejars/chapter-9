package com.wakaleo.bddinaction.chapter9.flightstatus.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.wakaleo.bddinaction.chapter9.flightstatus.model.Flight;
import com.wakaleo.bddinaction.chapter9.flightstatus.model.FlightType;

@Path("flights")
public interface FlightResource {

    @Path("/status")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getStatus();

    @GET
    @Path("/{flightNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    Flight findByFlightNumber(@PathParam("flightNumber") String flightNumber);

    @GET
    @Path("/from/{departure}")
    @Produces(MediaType.APPLICATION_JSON)
    List<Flight> findFlights(@PathParam("departure") String departure,
            @QueryParam("flightType") FlightType flightType);

}