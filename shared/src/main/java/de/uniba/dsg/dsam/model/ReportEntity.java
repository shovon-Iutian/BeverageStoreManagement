package de.uniba.dsg.dsam.model;

public class ReportEntity {
    int customer_order_id;
    double sum;
    String dtype;

    public ReportEntity() {
    }

    public ReportEntity(int customer_order_id, double sum) {
        this.customer_order_id = customer_order_id;
        this.sum = sum;
    }

    public int getCustomer_order_id() {
        return customer_order_id;
    }

    public void setCustomer_order_id(int customer_order_id) {
        this.customer_order_id = customer_order_id;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    @Override
    public String toString() {
        return "SummaryEntity{" +
                "customer_order_id=" + customer_order_id +
                ", sum=" + sum +
                '}';
    }
}
