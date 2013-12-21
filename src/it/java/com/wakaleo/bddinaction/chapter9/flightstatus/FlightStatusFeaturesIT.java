package com.wakaleo.bddinaction.chapter9.flightstatus;

import com.wakaleo.bddinaction.chapter9.flightstatus.steps.FlightDetailsSteps;
import com.wakaleo.bddinaction.chapter9.flightstatus.steps.ScheduledFlightsSteps;
import cucumber.api.junit.Cucumber;
import cucumber.runtime.arquillian.ArquillianCucumber;
import cucumber.runtime.arquillian.api.Features;
import cucumber.runtime.arquillian.api.Glues;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.runner.RunWith;

import java.io.File;

@Glues({FlightDetailsSteps.class, ScheduledFlightsSteps.class})
@Features({"com/wakaleo/bddinaction/chapter9/flightstatus/flight_details.feature", "com/wakaleo/bddinaction/chapter9/flightstatus/scheduled_flights.feature"})
@RunWith(ArquillianCucumber.class)
@Cucumber.Options(format = { "pretty", "html:target/cucumber-html-report", "json:target/report.json" })
public class FlightStatusFeaturesIT {

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

}
