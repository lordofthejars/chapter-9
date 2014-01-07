package com.wakaleo.bddinaction.chapter9.flightstatus;

import static com.wakaleo.bddinaction.chapter9.flightstatus.model.Flight.number;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.wakaleo.bddinaction.chapter9.flightstatus.model.Flight;
import com.wakaleo.bddinaction.chapter9.flightstatus.resources.FlightResource;

/**
 * Created by alexsoto on 11/12/13.
 */
@RunWith(Arquillian.class)
@RunAsClient
public class WhenINeedToKnowTheDetailsOfFlightNumber {

    private static final String WEBAPP_SRC = "src/main/webapp";

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap
                .create(WebArchive.class, "flightstatus.war")
                .addPackages(false, "com.wakaleo.bddinaction.chapter9.flightstatus.model",
                        "com.wakaleo.bddinaction.chapter9.flightstatus.resources",
                        "com.wakaleo.bddinaction.chapter9.flightstatus.rs",
                        "com.wakaleo.bddinaction.chapter9.flightstatus.service")
                .setWebXML(new File(WEBAPP_SRC, "WEB-INF/web.xml"))
                .addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml").importCompileAndRuntimeDependencies().resolve().withTransitivity().asFile())
                ;

    }


    @Test
    public void I_should_receive_the_details_of_flight(@ArquillianResteasyResource FlightResource flightResource) {

        Flight byFlightNumber = flightResource.findByFlightNumber("FH-101");

        assertThat(byFlightNumber, is(number("FH-101").from("MEL").to("SYD").at("06:00")));
    }
}
