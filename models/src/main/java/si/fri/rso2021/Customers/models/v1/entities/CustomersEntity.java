package si.fri.rso2021.Customers.models.v1.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customers")
@NamedQueries(value =
        {
                @NamedQuery(name = "CustomersEntity.getAll",
                        query = "SELECT im FROM CustomersEntity im")
        })
public class CustomersEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private static Integer customerId;

        @Column(name = "firstName")
        private String firstName;

        @Column(name = "lastName")
        private String lastName;

        @Column(name = "streetAddress")
        private String streetAddress;

        @Column(name = "postcode")
        private Integer postcode;

        @Column(name = "birthDate")
        private Date birthDate;

        public static Integer getCustomerId() { return customerId; }

        public void setCustomerId(Integer customerId){ this.customerId = customerId;}

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

        public Integer getPostcode() {
                return postcode;
        }

        public void setPostcode(Integer postcode) {
                this.postcode = postcode;
        }

        public Date getBirthDate() {
                return birthDate;
        }

        public void setBirthDate(Date birthDate) { this.birthDate = birthDate;}

}
