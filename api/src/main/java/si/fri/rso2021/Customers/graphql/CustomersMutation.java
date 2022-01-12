package si.fri.rso2021.Customers.graphql;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import si.fri.rso2021.Customers.models.v1.objects.Customers;
import si.fri.rso2021.Customers.services.v1.beans.CustomersBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;



@GraphQLClass
@ApplicationScoped
public class CustomersMutation {
    @Inject
    private CustomersBean customersBean;

    @GraphQLMutation
    public Customers addCustomers(@GraphQLArgument(name = "customer") Customers customer) {
        customersBean.createCustomer(customer);
        return customer;
    }

    @GraphQLMutation
    public DeleteResponse deleteCustomer(@GraphQLArgument(name = "id") Integer id) {
        return new DeleteResponse(customersBean.deleteCustomers(id));
    }
}
