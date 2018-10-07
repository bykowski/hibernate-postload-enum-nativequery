package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String mark;

    @Transient
    private long age;

    @Enumerated(EnumType.ORDINAL)
    private CarType carType;

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public long getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private LocalDate productionDate;

    public Car() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @PostLoad
    public void calculateAge() {
        age = ChronoUnit.YEARS.between(productionDate, LocalDate.now());
    }


    @PrePersist
    public void fillInfoAbutCarTyp() {
        if (carType == null)
            setCarType(CarType.BUS);
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(LocalDate productionDate) {
        this.productionDate = productionDate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", age=" + age +
                ", carType=" + carType +
                ", productionDate=" + productionDate +
                '}';
    }
}
