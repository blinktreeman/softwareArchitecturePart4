package ticketservice;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.UUID;

public class Ticket {
    private final UUID customerId;
    private double amount;
    private final Date date;
    private BufferedImage qrCode;
    private boolean enable = true;

    public Ticket(UUID customerId, Date date) {
        this.customerId = customerId;
        this.date = date;
    }

    public void setDisable() {
        this.enable = false;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public Date getDate() {
        return date;
    }

    public BufferedImage getQrCode() {
        return qrCode;
    }

    public void setQrCode(BufferedImage qrCode) {
        this.qrCode = qrCode;
    }

    public boolean isEnable() {
        return enable;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
