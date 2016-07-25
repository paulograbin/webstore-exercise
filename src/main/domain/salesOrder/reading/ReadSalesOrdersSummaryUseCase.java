package main.domain.salesOrder.reading;

import main.domain.salesOrder.SalesOrder;
import main.domain.salesOrder.SalesOrderRepository;

import java.util.Collection;

public class ReadSalesOrdersSummaryUseCase {
    private SalesOrderRepository repository;
    private Collection<SalesOrderSummary> response;

    public ReadSalesOrdersSummaryUseCase(SalesOrderRepository repository, Collection<SalesOrderSummary> output) {
        this.repository = repository;
        this.response = output;
    }

    public void execute() {
        repository.getAll().forEach(s ->
            response.add(summarise(s))
        );
    }

    private SalesOrderSummary summarise(SalesOrder s) {
        SalesOrderSummary summary = new SalesOrderSummary();

        summary.id = s.getId();
        summary.productId = s.getProductId();
        summary.quantity = s.getQuantity();
        summary.customerName = s.getCustomerName();

        return summary;
    }
}