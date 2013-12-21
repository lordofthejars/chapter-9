package com.wakaleo.bddinaction.chapter9.flightstatus.service;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.wakaleo.bddinaction.chapter9.flightstatus.model.Flight;
import com.wakaleo.bddinaction.chapter9.flightstatus.model.FlightType;

import java.util.List;

import static ch.lambdaj.Lambda.filter;
import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isOneOf;
import static org.hamcrest.Matchers.not;

public class InMemoryFlightService implements FlightService {

    private static final Optional<Flight> NO_MATCHING_FLIGHT = Optional.absent();

    List<Flight> flights;

    public InMemoryFlightService() {
        this.flights = ImmutableList.of(
                Flight.number("FH-101").from("MEL").to("SYD").at("06:00"),
                Flight.number("FH-102").from("SYD").to("MEL").at("06:15"),
                Flight.number("FH-223").from("SYD").to("LAX").at("06:00"),
                Flight.number("FH-305").from("MEL").to("SFO").at("07:15"),
                Flight.number("FH-234").from("SYD").to("LHR").at("09:25"),
                Flight.number("FH-403").from("SYD").to("DBX").at("14:05")

        );
    }

    @Override
    public Optional<Flight> findByFlightNumber(String flightNumber) {
        List<Flight> matchingFlights = filter(having(on(Flight.class).getFlightNumber(), equalTo(flightNumber)), flights);
        return (matchingFlights.isEmpty()) ? NO_MATCHING_FLIGHT : Optional.of(matchingFlights.get(0));
    }

    @Override
    public List<Flight> findFlightsFrom(String departure, FlightType flightType) {
        switch (flightType) {
            case International:
                return filter(having(on(Flight.class).getDestination(), not(isOneOf("SYD", "MEL"))), flights);
            case Domestic:
                return filter(having(on(Flight.class).getDestination(), isOneOf("SYD","MEL")), flights);
        }
        return ImmutableList.of();
    }

}
