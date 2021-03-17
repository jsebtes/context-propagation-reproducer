package org.acme.context;

import org.eclipse.microprofile.context.spi.ThreadContextProvider;
import org.eclipse.microprofile.context.spi.ThreadContextSnapshot;
import org.jboss.logging.MDC;

import java.util.Map;

public class MyContextThreadContextProvider implements ThreadContextProvider {

    @Override
    public ThreadContextSnapshot currentContext(Map<String, String> props) {
        String myValue = MyContext.getMyValue();
        return () -> {
            MyContext.setMyValue(myValue);
            return MyContext::clear;
        };
    }

    @Override
    public ThreadContextSnapshot clearedContext(Map<String, String> props) {
        return () -> {
            MDC.clear();
            return MyContext::clear;
        };
    }

    @Override
    public String getThreadContextType() {
        return "MyContext";
    }

}
