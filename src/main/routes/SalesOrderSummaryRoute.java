package main.routes;

import com.google.gson.Gson;
import main.domain.salesOrder.reading.ReadSalesOrdersSummaryUseCase;
import main.domain.salesOrder.reading.SalesOrderSummary;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.Collection;

public class SalesOrderSummaryRoute implements Route {
    private Dependencies dependencies;
    private Gson converter = new Gson();

    public SalesOrderSummaryRoute(Dependencies dependencies) {
        this.dependencies = dependencies;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Collection<SalesOrderSummary> orderList = new ArrayList<>();

        new ReadSalesOrdersSummaryUseCase(dependencies.getSalesOrderRepository(), orderList).execute();

        return converter.toJson(orderList);
    }
}
