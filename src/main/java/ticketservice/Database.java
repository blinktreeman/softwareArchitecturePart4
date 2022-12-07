package ticketservice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Database {
    private static Database instance;
    private final Map<UUID, Customer> customerMap = new HashMap<>();
    private final Map<UUID, Ticket> ticketMap = new HashMap<>();
    private final Map<UUID, String> customerPasswordMap = new HashMap<>();
    private final Map<UUID, Order> orderMap = new HashMap<>();

    private final double TICKET_AMOUNT = 40;

    private Database() {
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Map<UUID, Customer> getCustomerMap() {
        return customerMap;
    }

    public Map<UUID, Ticket> getTicketMap() {
        return ticketMap;
    }

    public void addCustomer(Customer customer) {
        UUID customerId = UUID.randomUUID();
        customerMap.put(customerId, customer);
    }

    public void removeCustomerByID(UUID customerId) {
        customerMap.remove(customerId);
        customerPasswordMap.remove(customerId);
    }

    public void addTicket(UUID ticketId, Ticket ticket) {
        ticketMap.put(ticketId, ticket);
    }

    public void updateTicket(UUID ticketId, Ticket ticket) {
        ticketMap.put(ticketId, ticket);
    }

    public void removeTicketByID(UUID ticketId) {
        ticketMap.remove(ticketId);
    }

    public UUID setCustomerPassword(Customer customer, String password) throws RuntimeException {
        UUID customerId = customerMap.entrySet()
                .stream()
                .filter(c -> c.getValue().equals(customer))
                .map(Map.Entry::getKey)
                .findFirst().orElseThrow();
        customerPasswordMap.put(customerId, password);
        return customerId;
    }

    public UUID authenticateCustomer(Customer customer, String password) throws RuntimeException {
        UUID customerId = customerMap.entrySet()
                .stream()
                .filter(c -> c.getValue().equals(customer))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer not exist"));
        String databasePasswordValue = customerPasswordMap.entrySet()
                .stream()
                .filter(p -> p.getKey().equals(customerId))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Customer password not exist"));
        if (password.equals(databasePasswordValue)) {
            return customerId;
        } else {
            throw new RuntimeException("Authentication error");
        }
    }

    public void addTicketOrder(UUID orderId, UUID customerId) {
        Order order = new Order(new Date(), TICKET_AMOUNT);
        orderMap.put(orderId, order);
    }

    public void updateTicketOrder(UUID orderId, Order order) {
        orderMap.put(orderId, order);
    }

    public Order getOrderById(UUID orderId) {
        return orderMap.get(orderId);
    }

}
