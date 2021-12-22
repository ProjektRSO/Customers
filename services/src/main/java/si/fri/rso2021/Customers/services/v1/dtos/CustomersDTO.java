package si.fri.rso2021.Customers.services.v1.dtos;

import javax.enterprise.context.RequestScoped;
import java.util.Date;

@RequestScoped
public class CustomersDTO implements java.io.Serializable {

    private String id;
    private String firstName;
    private String lastName;
    private String streetAddress;
    private Integer postcode;
    private Date birthDate;
    private String IBAN;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getStreetAddress() { return streetAddress; }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public Integer getPostocode() { return postcode; }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getIBAN() {
        return IBAN;
    }
}
