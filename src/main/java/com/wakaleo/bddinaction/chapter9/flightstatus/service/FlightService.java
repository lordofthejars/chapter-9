package com.wakaleo.bddinaction.chapter9.flightstatus.service;

import com.google.common.base.Optional;
import com.wakaleo.bddinaction.chapter9.flightstatus.model.Flight;
import com.wakaleo.bddinaction.chapter9.flightstatus.model.FlightType;

import java.util.List;

public interface FlightService {
    Optional<Flight> findByFlightNumber(String flightNumber);
    List<Flight> findFlightsFrom(String departure, FlightType flightType);
}
