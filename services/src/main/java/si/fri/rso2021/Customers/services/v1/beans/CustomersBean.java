package si.fri.rso2021.Customers.services.v1.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.rso2021.Customers.models.v1.objects.Customers;
import si.fri.rso2021.Customers.models.v1.converters.CustomersConverter;
import si.fri.rso2021.Customers.models.v1.entities.CustomersEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;

@RequestScoped
public class CustomersBean{

    private Logger log = Logger.getLogger(CustomersBean.class.getName());

    @Inject
    private CustomersBean cusutomersBeanProxy;

    @Inject
    private EntityManager em;

    @Timed(name="get all customers")
    public List<Customers> getCustomers() {

        TypedQuery<CustomersEntity> query = em.createNamedQuery(
                "CustomersEntity.getAll", CustomersEntity.class);

        List<CustomersEntity> resultList = query.getResultList();

        return resultList.stream().map(CustomersConverter::toDto).collect(Collectors.toList());

    }

    public List<Customers> getCustomersFilter(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, CustomersEntity.class, queryParameters).stream()
                .map(CustomersConverter::toDto).collect(Collectors.toList());
    }

    @Timeout(value = 2, unit = ChronoUnit.SECONDS)
    public Customers getCustomer_byId(Integer id) {

        log.info("Getting customers by id.");


        CustomersEntity customersEntity = em.find(CustomersEntity.class, id);
        if (customersEntity == null) {
            throw new NotFoundException();
        }
        return CustomersConverter.toDto(customersEntity);
    }

    public Integer getCustomerIdFallback(Integer id) {
        return null;
    }

    public Customers createCustomer(Customers c) {
        CustomersEntity customersEntity = CustomersConverter.toEntity(c);
        try {
            beginTx();
            em.persist(customersEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }
        if (customersEntity.getId() == null) {
            throw new RuntimeException("Customer entity was not persisted");
        }
        return CustomersConverter.toDto(customersEntity);
    }

    public Customers putCustomers(Integer id, Customers c) {
        CustomersEntity customersEntity = em.find(CustomersEntity.class, id);
        if (customersEntity == null) {
            return null;
        }
        CustomersEntity updatedCustomersEntity = CustomersConverter.toEntity(c);
        try {
            beginTx();
            updatedCustomersEntity.setId(id);
            updatedCustomersEntity = em.merge(updatedCustomersEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }
        return CustomersConverter.toDto(updatedCustomersEntity);
    }

    public boolean deleteCustomers(Integer id) {
        CustomersEntity customersEntity = em.find(CustomersEntity.class, id);
        if (customersEntity != null) {
            try {
                beginTx();
                em.remove(customersEntity);
                commitTx();
            }
            catch (Exception e) {
                rollbackTx();
            }
        }
        else {
            return false;
        }
        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }
}