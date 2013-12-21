package com.wakaleo.bddinaction.chapter9.flightstatus.steps;

import com.wakaleo.bddinaction.chapter9.flightstatus.client.FlightStatusClient;
import com.wakaleo.bddinaction.chapter9.flightstatus.model.FlightType;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.net.URL;
import java.util.List;

public class ScheduledFlightsSteps {

    public static final String REST_FLIGHTS_URL = "rest/flights";

    @ArquillianResource
    private URL contextPath;

    String departure;
    List retrievedFlights;

    @Given("^the following flights have been scheduled")
    public void setup_scheduled_flights(DataTable testData) {
        // Setup test data
    }

    @Given("^I want to know the flights out of (.*)$")
    public void I_want_to_check_the_flights_out_of(String departure) throws Throwable {
        this.departure = departure;
    }

    @When("^I request the (.*) flights$")
    public void I_request_flights_of_type(FlightType flightType) throws Throwable {
        FlightStatusClient client = new FlightStatusClient();
        client.setBaseUrl(contextPath.toString()+ REST_FLIGHTS_URL);
        retrievedFlights = client.findByDepartureCityAndType(departure, flightType);
    }

    @When("^I request the (.*) flights in JSON form$")
    public void request_flights_of_type_in_json(FlightType flightType) throws Throwable {
        FlightStatusClient client = new FlightStatusClient();
        client.setBaseUrl(contextPath.toString()+ REST_FLIGHTS_URL);
        receivedJsonData = client.findByDepartureCityAndTypeInJson(departure, flightType);
    }

    @Then("^I should see the following flights:$")
    public void I_should_see_the_following_flights(DataTable expectedFlights) throws Throwable {
        expectedFlights.diff(retrievedFlights);
    }

    protected String receivedJsonData;

    @Then("^I should receive the following flights:$")
    public void should_receive_json_data(String expectedJsonData) throws JSONException {
        JSONAssert.assertEquals(expectedJsonData, receivedJsonData, JSONCompareMode.LENIENT);
    }
}
