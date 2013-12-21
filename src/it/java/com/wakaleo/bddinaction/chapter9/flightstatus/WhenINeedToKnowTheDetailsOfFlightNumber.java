package com.wakaleo.bddinaction.chapter9.flightstatus;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static com.wakaleo.bddinaction.chapter9.flightstatus.model.Flight.number;

import com.wakaleo.bddinaction.chapter9.flightstatus.client.FlightStatusClient;
import com.wakaleo.bddinaction.chapter9.flightstatus.model.Flight;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.net.URL;

/**
 * Created by alexsoto on 11/12/13.
 */
@RunWith(Arquillian.class)
@RunAsClient
public class WhenINeedToKnowTheDetailsOfFlightNumber {

    private static final String WEBAPP_SRC = "src/main/webapp";
    public static final String REST_FLIGHTS_URL = "rest/flights";

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "flightstatus.war")
                .addPackages(false, "com.wakaleo.bddinaction.chapter9.flightstatus.model",
                        "com.wakaleo.bddinaction.chapter9.flightstatus.resources",
                        "com.wakaleo.bddinaction.chapter9.flightstatus.service")
                //.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .setWebXML(new File(WEBAPP_SRC, "WEB-INF/web.xml"))
                .addAsWebResource(new File(WEBAPP_SRC, "index.html"))
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml").importCompileAndRuntimeDependencies().resolve().withTransitivity().asFile())
                ;

    }

    @ArquillianResource
    URL contextPath;

    @Test
    public void I_should_receive_the_details_of_flight() {

        FlightStatusClient flightStatusClient = new FlightStatusClient();
        flightStatusClient.setBaseUrl(contextPath.toString()+ REST_FLIGHTS_URL);

        Flight byFlightNumber = flightStatusClient.findByFlightNumber("FH-101");

        assertThat(byFlightNumber, is(number("FH-101").from("MEL").to("SYD").at("06:00")));
    }
}
