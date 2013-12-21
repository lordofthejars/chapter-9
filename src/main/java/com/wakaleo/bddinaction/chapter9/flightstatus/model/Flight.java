package com.wakaleo.bddinaction.chapter9.flightstatus.model;

//import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.ImmutableList;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Flight {

    private String flightNumber;
    private String departure;
    private String destination;
    private String time;

    public Flight() {
    }

    public Flight(String flightNumber, String departure, String destination, String time) {
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.destination = destination;
        this.time = time;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getTime() { return time; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (departure != null ? !departure.equals(flight.departure) : flight.departure != null) return false;
        if (destination != null ? !destination.equals(flight.destination) : flight.destination != null) return false;
        if (flightNumber != null ? !flightNumber.equals(flight.flightNumber) : flight.flightNumber != null)
            return false;
        if (time != null ? !time.equals(flight.time) : flight.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = flightNumber != null ? flightNumber.hashCode() : 0;
        result = 31 * result + (departure != null ? departure.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    public static FlightBuilder number(String flightNumber) {
        return new FlightBuilder(flightNumber);
    }

    public static class FlightBuilder {
        String departure;
        String destination;
        String flightNumber;
        List<DayOfTheWeek> departureDays;

        public FlightBuilder(String flightNumber) {
            this.flightNumber = flightNumber;
        }

        public FlightBuilder from(String departure) {
            this.departure = departure;
            return this;
        }

        public FlightBuilder to(String destination) {
            this.destination = destination;
            return this;
        }
        public Flight at(String departureTime) {
            return new Flight(flightNumber, departure, destination, departureTime);
        }
    }

}