import entity.Car;
import entity.CarType;

import java.time.LocalDate;

public class App {

    public static void main(String[] args) {
        CarDAO carDAO = new CarDAO();

        Car car1 = new Car();
        car1.setMark("Hyundai");
        car1.setProductionDate(LocalDate.of(2010, 7, 22));
        car1.setCarType(CarType.CABRIOLET);

        Car car2 = new Car();
        car2.setMark("Audi");
        car2.setProductionDate(LocalDate.of(2015, 2, 13));

        carDAO.add(car1);
        carDAO.add(car2);

        carDAO.readManyMank("Audi").forEach(car -> System.out.println(car));
    }
}
