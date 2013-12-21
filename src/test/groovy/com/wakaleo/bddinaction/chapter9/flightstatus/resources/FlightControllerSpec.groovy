package com.wakaleo.bddinaction.chapter9.flightstatus.resources

import spock.lang.Specification

class FlightControllerSpec extends Specification{

    void "testing spock works"(){
        expect:
        true
    }
//
//    @Shared
//    @AutoCleanup
//    ConfigurableApplicationContext context
//
//    void setupSpec() {
//        Future future = Executors
//                .newSingleThreadExecutor().submit(
//                new Callable() {
//                    @Override
//                    public ConfigurableApplicationContext call() throws Exception {
//                        return (ConfigurableApplicationContext) SpringApplication
//                                .run(Application.class)
//                    }
//                })
//        context = future.get(60, TimeUnit.SECONDS)
//    }
//
//    void "should return Greetings from Spring Boot!"() {
//        when:
//            ResponseEntity entity = new RestTemplate().getForEntity("http://localhost:8080/flight/FH-101", String.class)
//
//        then:
//            entity.statusCode == HttpStatus.OK
//            entity.body == '{"id":1,"flightNumber":"FH-203","departure":"Sydney","destination":"Perth"}'
//    }

}