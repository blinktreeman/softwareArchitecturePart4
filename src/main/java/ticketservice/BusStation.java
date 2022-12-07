package ticketservice;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import java.awt.image.BufferedImage;
import java.util.UUID;

public class BusStation {
    TicketProvider ticketProvider = new TicketProvider();

    public void letPassengerOnTheBus(BufferedImage image) {
        try {
            UUID ticketId = decodeQRCode(image);
            if (ticketProvider.checkTicket(ticketId)) {
                System.out.println("Please, come in");
                ticketProvider.setTicketDisable(ticketId);
            } else {
                System.out.println("Your ticket is not valid");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    private UUID decodeQRCode(BufferedImage image) throws NotFoundException {
        BinaryBitmap bitmap = new BinaryBitmap(
                new HybridBinarizer(
                        new BufferedImageLuminanceSource(image)));
        Result result = new MultiFormatReader().decode(bitmap);
        return UUID.fromString(result.getText());
    }

}
