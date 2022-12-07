package ticketservice;

import java.util.Date;

public class Order {
    private Date date;
    private double amount;
    private boolean orderHasBeenPaid = false;

    public Order(Date date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isOrderHasBeenPaid() {
        return orderHasBeenPaid;
    }

    public void setOrderHasBeenPaid(boolean orderHasBeenPaid) {
        this.orderHasBeenPaid = orderHasBeenPaid;
    }
}
