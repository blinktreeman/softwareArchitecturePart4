package ticketservice;

import java.util.UUID;

public class PaymentProvider {
    public boolean buy(UUID orderId, String cardNo, double amount) {
        return true;
    }
}
