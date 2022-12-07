package ticketservice;

import java.util.UUID;

public interface CustomerCRUD {
    /**
     *
     * @param customer Customer
     * @param password Customer password
     * @return new customer UUID
     * @exception RuntimeException Customer create failed
     */
    public UUID createCustomer(Customer customer, String password);

    /**
     *
     * @param customer Customer
     * @param password Customer password
     * @return return customer UUID
     * @exception RuntimeException Customer authentication failed
     */
    public UUID readCustomer(Customer customer, String password);

    /**
     *
     * @param customerId Customer UUID
     */
    public void deleteCustomer(UUID customerId);
}
