package com.wakaleo.bddinaction.chapter9.flightstatus.client;


import com.wakaleo.bddinaction.chapter9.flightstatus.model.Flight;
import com.wakaleo.bddinaction.chapter9.flightstatus.model.FlightType;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightStatusClient {

    private static final String DEFAULT_BASE_URL = "http://localhost:8080/rest/flights";
    private String baseUrl = DEFAULT_BASE_URL;

    public Flight findByFlightNumber(String flightNumber) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(baseUrl).path(flightNumber);
        return webTarget.request().buildGet().invoke(Flight.class);
    }

    public String findByFlightNumberInJson(String flightNumber) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(baseUrl).path(flightNumber);
        return webTarget.request().buildGet().invoke(String.class);
    }

    private GenericArrayType listOfFlights() {
        return new GenericArrayType() {
            @Override
            public Type getGenericComponentType() {
                return Flight.class;
            }
        };
    }
    public List<Flight> findByDepartureCityAndType(String departure, FlightType type) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(baseUrl)
                                    .path("from/" + departure)
                                    .queryParam("flightType", type);
        Flight[] flights = ((Flight[]) webTarget.request().buildGet().invoke(new GenericType(listOfFlights())));

        return new ArrayList(Arrays.asList(flights));
    }

    public String findByDepartureCityAndTypeInJson(String departure, FlightType type) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(baseUrl)
                .path("from/" + departure)
                .queryParam("flightType", type);
        return webTarget.request().buildGet().invoke(String.class);
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
