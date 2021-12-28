package si.fri.rso2021.Customers.graphql;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import com.kumuluz.ee.graphql.classes.Filter;
import com.kumuluz.ee.graphql.classes.Pagination;
import com.kumuluz.ee.graphql.classes.PaginationWrapper;
import com.kumuluz.ee.graphql.classes.Sort;
import com.kumuluz.ee.graphql.utils.GraphQLUtils;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import si.fri.rso2021.Customers.services.v1.beans.CustomersBean;
import si.fri.rso2021.Customers.models.v1.objects.Customers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


@GraphQLClass
@ApplicationScoped
public class CustomersQueries {

    @Inject
    private CustomersBean customersBean;

    @GraphQLQuery
    public PaginationWrapper<Customers> allCustomers(@GraphQLArgument(name = "pagination") Pagination pagination,
                                                             @GraphQLArgument(name = "sort") Sort sort,
                                                             @GraphQLArgument(name = "filter") Filter filter) {

        return GraphQLUtils.process(customersBean.getCustomers(), pagination, sort, filter);
    }

    @GraphQLQuery
    public Customers getCustomers(@GraphQLArgument(name = "id") Integer id) {
        return customersBean.getCustomer_byId(id);
    }
}
