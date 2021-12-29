package si.fri.rso2021.Customers.api.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
import si.fri.rso2021.Customers.models.v1.objects.Customers;
import si.fri.rso2021.Customers.services.v1.beans.CustomersBean;

import org.eclipse.microprofile.metrics.annotation.Metered;
import com.kumuluz.ee.discovery.annotations.DiscoverService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

@Log
@ApplicationScoped
@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomersResources {

    private final Logger log = Logger.getLogger(CustomersResources.class.getName());

    @Inject
    private CustomersBean customersBean;

    @Context
    protected UriInfo uriInfo;

    @Inject
    @DiscoverService(value = "customers", environment = "test", version = "1.0.0")
    private URL customerServiceUrl;

    private String url = customerServiceUrl.toString();


    @GET
    @Metered(name = "getCustomer")
    public Response getCustomer() {
        List<Customers> customers = customersBean.getCustomersFilter(uriInfo);
        return Response.status(Response.Status.OK).entity(customers).build();
    }

    @GET
    @Metered(name = "getCustomerId")
    @Path("/{id}")
    public Response getCustomerId(@PathParam("id") Integer id) {
        Customers c = customersBean.getCustomer_byId(id);
        if (c == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(c).build();
    }

    @POST
    public Response createCustomer(Customers c) {
        if ((c.getId() == null || c.getFirstName() == null || c.getLastName() == null)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            c = customersBean.createCustomer(c);
        }
        return Response.status(Response.Status.CONFLICT).entity(c).build();
    }

    @PUT
    @Path("{id}")
    public Response putCustomers(@PathParam("id") Integer id, Customers c) {
        c = customersBean.putCustomers(id, c);
        if (c == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.NOT_MODIFIED).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteCustomers(@PathParam("id") Integer id) {

        boolean deleted = customersBean.deleteCustomers(id);
        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
