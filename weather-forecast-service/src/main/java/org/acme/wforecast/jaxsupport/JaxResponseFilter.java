package org.acme.wforecast.jaxsupport;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

@Provider
public class JaxResponseFilter implements ContainerResponseFilter {

    private static final Logger LOGGER = Logger.getLogger(JaxResponseFilter.class);
    private static final String X_REQUESTID = "X_REQUESTID";

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
        String xRequestIdHeaderMessage = requestContext.getHeaderString(X_REQUESTID).toString();
        LOGGER.info("xRequestIdHeaderMessage: " + xRequestIdHeaderMessage);
        responseContext.getHeaders().add(X_REQUESTID, xRequestIdHeaderMessage);
    }
}
