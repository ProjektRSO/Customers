package si.fri.rso2021.Customers.api.v1.resources;

import com.kumuluz.ee.logs.cdi.Log;
import si.fri.rso2021.Customers.models.v1.objects.Customers;
import si.fri.rso2021.Customers.services.v1.beans.CustomersBean;
import com.kumuluz.ee.cors.annotations.CrossOrigin;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import org.eclipse.microprofile.metrics.annotation.Metered;
import com.kumuluz.ee.discovery.annotations.DiscoverService;
import si.fri.rso2021.Customers.services.v1.dtos.CustomersDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
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
@CrossOrigin(supportedMethods = "GET, POST, HEAD, DELETE, OPTIONS")
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


    @Operation(description = "Get all customers data.", summary = "Get all data")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "List of customers data",
                    content = @Content(schema = @Schema(implementation = Customers.class, type = SchemaType.ARRAY)),
                    headers = {@Header(name = "X-Total-Count", description = "Number of objects in list")}
            )})
    @GET
    @Metered(name = "getCustomer")
    public Response getCustomer() {
        List<Customers> customers = customersBean.getCustomersFilter(uriInfo);
        return Response.status(Response.Status.OK).entity(customers).build();
    }

    @Operation(description = "Get data for a customer.", summary = "Get data for a customer")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "Customer data",
                    content = @Content(
                            schema = @Schema(implementation = Customers.class))
            )})
    @GET
    @Metered(name = "getCustomer_byId")
    @Path("/{id}")
    public Response getCustomer_byId(@Parameter(description = "id.", required = true)
                                     @PathParam("id") Integer id) {

        Customers c = customersBean.getCustomer_byId(id);
        if (c == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(c).build();
    }

    @Operation(description = "Add customer data.", summary = "Add data")
    @APIResponses({
            @APIResponse(responseCode = "201",
                    description = "Data successfully added."
            ),
            @APIResponse(responseCode = "405", description = "Validation error .")
    })
    @POST
    public Response createCustomer(@RequestBody(
            description = "DTO object with customer data.",
            required = true, content = @Content(
            schema = @Schema(implementation = Customers.class))) Customers c) {
        if ((c.getId() == null || c.getFirstName() == null || c.getLastName() == null)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            c = customersBean.createCustomer(c);
        }
        return Response.status(Response.Status.CONFLICT).entity(c).build();
    }

    @Operation(description = "Update data for a customer.", summary = "Update data")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Data successfully updated."
            )
    })
    @PUT
    @Path("{id}")
    public Response putCustomer(@Parameter(description = "id", required = true)
                                     @PathParam("id") Integer id,
                                     @RequestBody(
                                             description = "DTO object with customer data.",
                                             required = true, content = @Content(
                                             schema = @Schema(implementation = Customers.class)))
                                             Customers c){
        c = customersBean.putCustomers(id, c);
        if (c == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.NOT_MODIFIED).build();
    }


    @Operation(description = "Delete data for customer.", summary = "Delete data")
    @APIResponses({
            @APIResponse(
                    responseCode = "200",
                    description = "Data successfully deleted."
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "Not found."
            )
    })
    @DELETE
    @Path("{id}")
    public Response deleteCustomers(@Parameter(description = "id", required = true)
                                        @PathParam("id") Integer id){

        boolean deleted = customersBean.deleteCustomers(id);
        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
