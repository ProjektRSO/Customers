package si.fri.rso2021.Customers.api.v1;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import com.kumuluz.ee.discovery.annotations.RegisterService;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "Customers API", version = "v1",
        contact = @Contact(email = "jf6340@student.uni-lj.si"),
        license = @License(name = "dev"), description = "API for managing customers."),
        servers = @Server(url = "http://localhost:8080/"))
@RegisterService(value = "customers", ttl = 20, pingInterval = 15, environment = "test", version = "1.0.0", singleton = false)
@ApplicationPath("/v1")
public class CustomersApplication extends Application {
}
