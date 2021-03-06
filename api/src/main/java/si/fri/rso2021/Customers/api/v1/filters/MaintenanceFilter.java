package si.fri.rso2021.Customers.api.v1.filters;

import si.fri.rso2021.Customers.services.v1.config.RestProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.Objects;

@Provider
@ApplicationScoped
public class MaintenanceFilter implements ContainerRequestFilter {

    @Inject
    private RestProperties restProperties;

    @Override
    public void filter(ContainerRequestContext ctx)  {
        if (restProperties.getMaintenanceMode()) {

            ctx.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity("{\"message\" : \"Maintenance mode set to enabled\"}")
                    .build());
        }else if(!Objects.equals(restProperties.getTopicname(), "xb9xnuao-customers")){
            System.out.println("Topic name changed!");
        }
    }
}