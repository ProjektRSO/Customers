package si.fri.rso2021.Customers.services.v1.beans;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.rso2021.Customers.lib.v1.Customers;
import si.fri.rso2021.Customers.models.v1.converters.CustomersConverter;
import si.fri.rso2021.Customers.models.v1.entities.CustomersEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
public class CustomersBean{

    private Logger log = Logger.getLogger(CustomersBean.class.getName());

    @Inject
    private EntityManager em;

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

    public Customers getCustomerId(Integer id) {

        CustomersEntity customersEntity = em.find(CustomersEntity.class, id);

        if (customersEntity == null) {
            throw new NotFoundException();
        }

        Customers customers = CustomersConverter.toDto(customersEntity);

        return customers;
    }

    public Customers createCustomer(Customers customers) {

        CustomersEntity customersEntity = CustomersConverter.toEntity(customers);

        try {
            beginTx();
            em.persist(customersEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        if (CustomersEntity.getCustomerId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return CustomersConverter.toDto(customersEntity);
    }

    public Customers putCustomers(Integer id, Customers customers) {

        CustomersEntity c = em.find(CustomersEntity.class, id);

        if (c == null) {
            return null;
        }

        CustomersEntity updatedCustomersEntity = CustomersConverter.toEntity(customers);

        try {
            beginTx();
            updatedCustomersEntity.setCustomerId(c.getCustomerId());
            updatedCustomersEntity = em.merge(updatedCustomersEntity);
            commitTx();
        }
        catch (Exception e) {
            rollbackTx();
        }

        return CustomersConverter.toDto(updatedCustomersEntity);
    }

    public boolean deleteCustomers(Integer id) {

        CustomersEntity customers = em.find(CustomersEntity.class, id);

        if (customers != null) {
            try {
                beginTx();
                em.remove(customers);
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