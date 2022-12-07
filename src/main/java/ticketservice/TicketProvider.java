package ticketservice;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class TicketProvider {
    private final Database database = Database.getInstance();
    private final PaymentProvider paymentProvider = new PaymentProvider();

    public TicketProvider() {
    }

    public Map<UUID, Ticket> getTickets(UUID customerId) {
        // Предусловие
        if (customerId == null) {
            throw new RuntimeException("Invalid customer Id");
        }
        return database.getTicketMap()
                .entrySet()
                .stream()
                .filter(t -> t.getValue().getCustomerId().equals(customerId))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Ticket buyTicket(UUID customerId, String cardNumber) throws RuntimeException, WriterException {
        // Предусловие
        if (customerId == null || cardNumber == null) {
            throw new RuntimeException("Invalid parameters");
        }
        UUID orderId = UUID.randomUUID();
        database.addTicketOrder(orderId, customerId);
        double amount = database.getOrderById(orderId).getAmount();
        if (paymentProvider.buy(orderId, cardNumber, amount)) {

            Order order = database.getOrderById(orderId);
            order.setOrderHasBeenPaid(true);
            database.updateTicketOrder(orderId, order);

            UUID ticketId = UUID.randomUUID();
            Ticket ticket = new Ticket(customerId, new Date());
            ticket.setQrCode(setBarcode(ticketId.toString()));
            database.addTicket(ticketId, ticket);

            return ticket;
        }
        // Постусловие
        else {
            throw new RuntimeException("Buying ticket... Payment failed");
        }
    }

    public boolean checkTicket(UUID ticketId) throws RuntimeException {
        try {
            return database.getTicketMap().get(ticketId).isEnable();
        } catch (Exception e) {
            throw new RuntimeException("No valid tickets");
        }
    }

    public void setTicketDisable(UUID ticketId) {
        Ticket ticket = database.getTicketMap().get(ticketId);
        ticket.setDisable();
        database.updateTicket(ticketId, ticket);
    }

    private BufferedImage setBarcode(String qrCodeText) throws WriterException {
        QRCodeWriter qrCode = new QRCodeWriter();
        BitMatrix matrix = qrCode.encode(qrCodeText, BarcodeFormat.QR_CODE, 200, 200);
        return MatrixToImageWriter.toBufferedImage(matrix);
    }
}
