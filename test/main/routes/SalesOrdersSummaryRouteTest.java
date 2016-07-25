package main.routes;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import main.domain.salesOrder.SalesOrder;
import main.persistence.mongo.MongoSalesOrderRepository;
import org.junit.Test;

public class SalesOrdersSummaryRouteTest extends RouteTest {
    @Test
    public void integration() throws Exception {
        MongoClientURI uri = new MongoClientURI(System.getenv("MONGOLAB_URI"));
        MongoClient client = new MongoClient(uri);
        MongoDatabase database = client.getDatabase(uri.getDatabase());
        database.getCollection("salesOrders").drop();
        client.close();

        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setId("579634f99901f506930de65a");
        salesOrder.setCustomerName("José Asdrubal");
        salesOrder.setQuantity(5);
        salesOrder.setProductId("555");
        new MongoSalesOrderRepository().save(salesOrder);

        assertRouteResponse("GET", "/sales-orders",
                "[{\"id\":\"579634f99901f506930de65a\"," +
                        "\"productId\":\"555\"," +
                        "\"quantity\":5," +
                        "\"customerName\":\"José Asdrubal\"}]");
    }
}