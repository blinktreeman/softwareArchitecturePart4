package ticketservice;

import java.util.Map;
import java.util.UUID;

public class MobileApplication {
    private CustomerProvider customerProvider = new CustomerProvider();
    private TicketProvider ticketProvider = new TicketProvider();
    private Map<UUID, Ticket> ticketMap;
    private Customer customer;
    private UUID customerId;
    private Ticket ticket;


    public MobileApplication() {

    }

    public MobileApplication(String name, String surname){
        this.customer = new Customer(name, surname);
    }

    public void createCustomer(String name, String surname, String password) {
        try {
            customer = new Customer(name, surname);
            customerId = customerProvider.createCustomer(customer, password);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void loginCustomer(String password) {
        try {
            customerId = customerProvider.readCustomer(this.customer, password);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void getTickets() {
        this.ticketMap = ticketProvider.getTickets(customerId);
    }

    public void buyTicket(String cardNumber) {
        try {
            this.ticket = ticketProvider.buyTicket(customerId, cardNumber);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Ticket getTicket() {
        return ticket;
    }
}
