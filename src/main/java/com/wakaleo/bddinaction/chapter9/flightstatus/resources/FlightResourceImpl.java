package com.wakaleo.bddinaction.chapter9.flightstatus.resources;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import com.google.common.base.Optional;
import com.wakaleo.bddinaction.chapter9.flightstatus.model.Flight;
import com.wakaleo.bddinaction.chapter9.flightstatus.model.FlightType;
import com.wakaleo.bddinaction.chapter9.flightstatus.service.FlightService;
import com.wakaleo.bddinaction.chapter9.flightstatus.service.InMemoryFlightService;

public class FlightResourceImpl implements FlightResource {

    // TODO: Add dependency injection here
    FlightService flightService = new InMemoryFlightService();

    public FlightResourceImpl() {}

    public String getStatus()
    {
        return "{'status':'OK'}";
    }

    public Flight findByFlightNumber(@PathParam("flightNumber") String flightNumber) {
        Optional<Flight> flight = flightService.findByFlightNumber(flightNumber);
        if (!flight.isPresent()) {
            throw new UnknownFlightException("No flight with number " + flightNumber + " found");
        }
        return flight.get();
    }

    public List<Flight> findFlights(@PathParam("departure") String departure,
                                    @QueryParam("flightType") FlightType flightType) {
        return  flightService.findFlightsFrom(departure, flightType);
    }
}