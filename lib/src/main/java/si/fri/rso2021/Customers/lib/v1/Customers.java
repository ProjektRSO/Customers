package si.fri.rso2021.Customers.lib.v1;

import java.util.Date;

public class Customers {

    private Integer CustomerId;
    private String firstName;
    private String lastName;
    private String address;
    private Date birthDate;


    public Integer getId() {
        return CustomerId;
    }

    public void setId(Integer id) {
        this.CustomerId = CustomerId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) { this.birthDate = birthDate;}


}

