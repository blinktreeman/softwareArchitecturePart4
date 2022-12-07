package ticketservice;

import java.util.UUID;

public class CustomerProvider implements CustomerCRUD {
    private final Database database = Database.getInstance();

    public CustomerProvider() {
    }

    @Override
    public UUID createCustomer(Customer customer, String password) throws RuntimeException {
        database.addCustomer(customer);
        return database.setCustomerPassword(customer, password);
    }

    @Override
    public UUID readCustomer(Customer customer, String password) {
        return database.authenticateCustomer(customer, password);
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        database.removeCustomerByID(customerId);
    }
}
