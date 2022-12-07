package ticketservice;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;

import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * Разработать контракты и компоненты системы "Покупка онлайн билетов на автобус в час пик".
 *
 * 5,6,7,8 - необязательные, опциональные задания.
 *
 * 1.  Предусловия.
 * 2.  Постусловия.
 * 3.  Инвариант.
 * 4.  Определить абстрактные и конкретные классы.
 * 5.  Определить интерфейсы.
 * 6.  Реализовать наследование.
 * 7.  Выявить компоненты.
 * 8.  Разработать Диаграмму компонент использую нотацию UML 2.0. Общая без деталей.
 */

public class App {
    public static void main(String[] args) {

        MobileApplication mobileApplication = new MobileApplication();
        mobileApplication.createCustomer("Ivan", "Ivanov", "secretPassword");
        mobileApplication.buyTicket("323113123131");
        BufferedImage image = mobileApplication.getTicket().getQrCode();

        BusStation busStation = new BusStation();
        System.out.println("Ivanov wants to get on the bus");
        busStation.letPassengerOnTheBus(image);

        MobileApplication mobileApplication1 = new MobileApplication();
        mobileApplication1.createCustomer("Pyotr", "Petrov", "qwerty");
        System.out.println("Petrov wants to get on the bus");
        busStation.letPassengerOnTheBus(image);

    }
}
