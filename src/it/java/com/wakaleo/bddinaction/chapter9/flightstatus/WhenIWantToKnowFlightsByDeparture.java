package com.wakaleo.bddinaction.chapter9.flightstatus;

import static com.wakaleo.bddinaction.chapter9.flightstatus.model.Flight.number;
import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

import ch.lambdaj.Lambda;
import com.wakaleo.bddinaction.chapter9.flightstatus.client.FlightStatusClient;
import com.wakaleo.bddinaction.chapter9.flightstatus.model.Flight;
import com.wakaleo.bddinaction.chapter9.flightstatus.model.FlightType;
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
import java.util.List;

/**
 * Created by alexsoto on 13/12/13.
 */
@RunWith(Arquillian.class)
@RunAsClient
public class WhenIWantToKnowFlightsByDeparture {

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
    public void flights_should_be_returned() {

        FlightStatusClient flightStatusClient = new FlightStatusClient();
        flightStatusClient.setBaseUrl(contextPath.toString()+ REST_FLIGHTS_URL);

        List<Flight> flights = flightStatusClient.findByDepartureCityAndType("SYD", FlightType.Domestic);

        assertThat(flights, containsInAnyOrder(
                number("FH-102").from("SYD").to("MEL").at("06:15"),
                number("FH-101").from("MEL").to("SYD").at("06:00")));

    }

}
