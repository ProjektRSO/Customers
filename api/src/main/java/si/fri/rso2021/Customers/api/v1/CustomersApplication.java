package si.fri.rso2021.Customers.api.v1;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import com.kumuluz.ee.discovery.annotations.RegisterService;


@RegisterService(value = "customers", ttl = 20, pingInterval = 15, environment = "test", version = "1.0.0", singleton = false)
@ApplicationPath("/v1")
public class CustomersApplication extends Application {
}
