package com.wakaleo.bddinaction.chapter9.flightstatus.rs;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.wakaleo.bddinaction.chapter9.flightstatus.resources.FlightResourceImpl;

public class JaxRsActivator extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(FlightResourceImpl.class);
        return s;
    }

}
