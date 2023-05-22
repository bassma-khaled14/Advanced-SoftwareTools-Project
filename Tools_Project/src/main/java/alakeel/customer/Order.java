package alakeel.customer;

import java.io.Serializable;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;

import alakeel.restaurant.Restaurant;
import alakeel.runner.Runner;

@Entity
@NamedQuery(name = "itemarray", query = "Select item from itemarr itemarray")
@RolesAllowed({"runner"})

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
    private EntityManager entityManager;
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
    	Query query=entityManager.createQuery("itemarray");
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

    public void addMoneyToRes(double totalPrice,String orderStatus,Restaurant resid)
    {
    	if(orderStatus=="Delivered")
    	{
    		resid.settotalincome(resid.gettotalincome()+totalPrice);
    		
    	}
    	
    }
   	
   	public void NumberAllCancelled(Restaurant resid,String orderStatus)
   	{
   		if((orderStatus=="Cancelled"))
   		{
   			resid.setCancelledOrder(resid.getCancelledOrder()+1);
   		}
   	}
	public void NumberAllCompleted(Restaurant resid,String orderStatus)
   	{
   		if((orderStatus=="Delivered"))
   		{
   			resid.setCompletedOrder(resid.getCompletedOrder()+1);   		}
   	}

}
