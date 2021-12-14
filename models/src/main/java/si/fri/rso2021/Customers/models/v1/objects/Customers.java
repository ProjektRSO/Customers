package si.fri.rso2021.Customers.models.v1.objects;

import java.util.Date;

public class Customers {

    private Integer id;
    private String firstName;
    private String lastName;
    private String streetAddress;
    private Integer postcode;
    private Date birthDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public Integer getPostcode() { return postcode; }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) { this.birthDate = birthDate;}


}

