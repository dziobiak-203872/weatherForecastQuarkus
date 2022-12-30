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

        LOGGER.info("Request: " + requestContext.getMethod() + " "
                + requestContext.getUriInfo().getRequestUri().toString() + " Headers: " + requestContext.getHeaders());

        String xRequestIdHeaderMessage = requestContext.getHeaderString(X_REQUESTID).toString();
        responseContext.getHeaders().add(X_REQUESTID, xRequestIdHeaderMessage);

        LOGGER.info("Response: Status code: " + Integer.toString(responseContext.getStatusInfo().getStatusCode()) + " Reason phrase: "
                + responseContext.getStatusInfo().getReasonPhrase() + " Headers: " + responseContext.getHeaders());
    }
}
