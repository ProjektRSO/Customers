package si.fri.rso2021.Customers.api.v1.resources;

import si.fri.rso2021.Customers.lib.v1.Customers;
import si.fri.rso2021.Customers.services.v1.beans.CustomersBean;

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
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/images")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomersResources {

    private Logger log = Logger.getLogger(CustomersResources.class.getName());

    @Inject
    private CustomersBean customersBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getImageMetadata() {

        List<Customers> customers = customersBean.getCustomersFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(customers).build();
    }

    @GET
    @Path("/{customerId}")
    public Response getImageMetadata(@PathParam("customerId") Integer customerId) {

        Customers customers = customersBean.getCustomerId(customerId);

        if (customers == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(customers).build();
    }

    @POST
    public Response createCustomer(Customers customers) {

        if ((customers.getId() == null || customers.getFirstName() == null || customers.getLastName() == null)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            customers = customersBean.createCustomer(customers);
        }

        return Response.status(Response.Status.CONFLICT).entity(customers).build();

    }

    @PUT
    @Path("{cusomerId}")
    public Response putCustomers(@PathParam("customerId") Integer customerId, Customers customers) {

        customers = customersBean.putCustomers(customerId, customers);

        if (customers == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.NOT_MODIFIED).build();

    }

    @DELETE
    @Path("{customerId}")
    public Response deleteCustomers(@PathParam("customerId") Integer customerId) {

        boolean deleted = customersBean.deleteCustomers(customerId);

        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
