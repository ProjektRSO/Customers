package si.fri.rso2021.Customers.models.v1.entities;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "Customer")
@NamedQueries(value =
        {
                @NamedQuery(name = "CuostmersEntity.getAll",
                        query = "SELECT im FROM CuostmersEntity im")
        })
public class CustomersEntitiy {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private static Integer id;

        @Column(name = "firstName")
        private String firstName;

        @Column(name = "lastName")
        private String lastName;

        @Column(name = "address")
        private String address;

        @Column(name = "birthDate")
        private Date birthDate;

        public static Integer getId() { return id; }

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
