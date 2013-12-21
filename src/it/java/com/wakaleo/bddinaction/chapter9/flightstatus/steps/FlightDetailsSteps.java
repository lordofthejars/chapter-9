package com.wakaleo.bddinaction.chapter9.flightstatus.steps;

import com.wakaleo.bddinaction.chapter9.flightstatus.client.FlightStatusClient;
import com.wakaleo.bddinaction.chapter9.flightstatus.model.Flight;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.net.URL;

import static com.google.common.collect.Lists.newArrayList;

public class FlightDetailsSteps {

    public static final String REST_FLIGHTS_URL = "rest/flights";

    @ArquillianResource
    private URL contextPath;

    String flightNumber;
    Flight matchingFlight;
    FlightStatusClient client = new FlightStatusClient();

    @Given("^I need to know the details of flight number (.*)$")
    public void the_flight_number(String flightNumber) throws Throwable {
        this.flightNumber = flightNumber;
        this.client.setBaseUrl(contextPath.toString()+ REST_FLIGHTS_URL);
    }

    @When("^I request the details about this flight$")
    public void I_request_the_details_about_this_flight() throws Throwable {
        matchingFlight = client.findByFlightNumber(flightNumber);
    }

    @Then("^I should receive the following:$")
    public void I_should_receive_the_following_details(DataTable flightDetails) throws Throwable {
        flightDetails.diff(newArrayList(matchingFlight));
    }

String receivedJsonData;

@When("^I request the details about this flight in JSON format$")
public void request_details_in_json_format() {
    receivedJsonData = client.findByFlightNumberInJson(flightNumber);
}

@Then("^I should receive:$")
public void should_receive_json_data(String expectedJsonData) throws JSONException {
    JSONAssert.assertEquals(expectedJsonData, receivedJsonData, JSONCompareMode.LENIENT);
}

}
