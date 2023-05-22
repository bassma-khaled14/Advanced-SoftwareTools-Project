package alakeel.restaurant;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Meal implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@ManyToOne(fetch = FetchType.LAZY)
	private int mealid; 
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
	@NotNull
	private double price;
	@NotNull
	private String name;
	public Meal() {}
	public Meal(String name, String description, double price, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

    public int getmealid() {
        return mealid;
    }

    public void setmealid(int mealid) {
        this.mealid = mealid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
	
	
	

}
