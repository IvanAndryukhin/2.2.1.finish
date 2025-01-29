package hiber.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long cars_id;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;

    public Car() {
    }

    public Car(long cars_id, String model, int series) {
        this.cars_id = cars_id;
        this.model = model;
        this.series = series;
    }

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    public Car(User user) {
        this.user = user;
    }


    public long getCars_id() {
        return cars_id;
    }

    public void setCars_id(long cars_id) {
        this.cars_id = cars_id;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Car{" +
                "cars_id=" + cars_id +
                ", model='" + model + '\'' +
                ", series=" + series +
                '}';
    }

}
