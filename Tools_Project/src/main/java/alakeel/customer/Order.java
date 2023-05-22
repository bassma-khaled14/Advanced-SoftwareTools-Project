package alakeel.customer;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import alakeel.restaurant.Restaurant;
import alakeel.runner.Runner;

@Entity
public class Order implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int orderid;
	@ManyToOne
	@JoinColumn(name="runnerid")
	private Runner runner;
	@NotNull
	private boolean delivered;
	private List<String> itemarr;
    private double totalPrice;
    private String orderStatus;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    // constructors
    public Order() {}

    public Order(List<String> itemarr, double totalPrice, Runner runner, Restaurant restaurant) {
        this.itemarr = itemarr;
        this.totalPrice = totalPrice;
        this.runner = runner;
        this.restaurant = restaurant;
        this.orderStatus = "preparing";
    }

    // getters and setters
    public int getorderid() {
        return orderid;
    }

    public void setorderid(int orderid) {
        this.orderid = orderid;
    }

    public List<String> getitemarr() {
        return itemarr;
    }

    public void setitemarr(List<String> itemarr) {
        this.itemarr = itemarr;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
    
        this.orderStatus = orderStatus;
    }    
    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setDelivered(boolean delivered) {
		this.delivered=delivered;			
		}	

}
