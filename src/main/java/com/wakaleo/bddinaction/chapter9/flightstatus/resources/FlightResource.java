package com.wakaleo.bddinaction.chapter9.flightstatus.resources;

import com.google.common.base.Optional;
import com.wakaleo.bddinaction.chapter9.flightstatus.model.Flight;
import com.wakaleo.bddinaction.chapter9.flightstatus.model.FlightType;
import com.wakaleo.bddinaction.chapter9.flightstatus.service.FlightService;
import com.wakaleo.bddinaction.chapter9.flightstatus.service.InMemoryFlightService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("flights")
public class FlightResource {

    // TODO: Add dependency injection here
    FlightService flightService = new InMemoryFlightService();

    public FlightResource() {}

    @Path("/status")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getStatus()
    {
        return "{'status':'OK'}";
    }

    @GET
    @Path("/{flightNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Flight findByFlightNumber(@PathParam("flightNumber") String flightNumber) {
        Optional<Flight> flight = flightService.findByFlightNumber(flightNumber);
        if (!flight.isPresent()) {
            throw new UnknownFlightException("No flight with number " + flightNumber + " found");
        }
        return flight.get();
    }

    @GET
    @Path("/from/{departure}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Flight> findFlights(@PathParam("departure") String departure,
                                    @QueryParam("flightType") FlightType flightType) {
        return  flightService.findFlightsFrom(departure, flightType);
    }
}