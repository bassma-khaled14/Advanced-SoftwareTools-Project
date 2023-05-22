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
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import alakeel.restaurant.Restaurant;
import alakeel.runner.Runner;

@Entity
@NamedQuery(name = "itemarray", query = "Select item from itemarr itemarray")
@RolesAllowed({"runner"})
@Path("Order")
public class Order implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int orderid;
	@ManyToOne
	@JoinColumn(name="runnerid")
	private Runner runner;
	@NotNull
	private boolean delivered;
	private List<Order> itemarr;
    private double totalPrice;
    private String orderStatus;
    private EntityManager entityManager;
    private CustomerOwner custid;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    // constructors
    public Order() {}

    public Order(List<Order> itemarr, double totalPrice, Runner runner, Restaurant restaurant) {
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
    
    public List<Order> getitemarr() {
        return itemarr;
    }

    public void setitemarr(List<Order> itemarr) {
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
    @POST
    @Path("/calculateTotalReceiptValue")
    @Consumes(MediaType.APPLICATION_JSON)
    public double calculateTotalReceiptValue(List<Order> itemarr, double deliveryFees) {
        double total = 0.0;

        for (Order item : itemarr) {
            total += item.getTotalPrice();
        }

        return total + deliveryFees;
    }
    @PUT
    @Path("/addMoneyToRes")
    @Consumes(MediaType.APPLICATION_JSON)

    public void addMoneyToRes(double totalPrice,String orderStatus,Restaurant resid)
    {
    	if(orderStatus=="Delivered")
    	{
    		resid.settotalincome(resid.gettotalincome()+totalPrice);
    		
    	}
    	
    }
    @PUT
    @Path("/NumberAllCancelled")
    @Consumes(MediaType.APPLICATION_JSON)
   	
   	public void NumberAllCancelled(Restaurant resid,String orderStatus)
   	{
   		if((orderStatus=="Cancelled"))
   		{
   			resid.setCancelledOrder(resid.getCancelledOrder()+1);
   		}
   	}
    @PUT
    @Path("/NumberAllCompleted")
    @Consumes(MediaType.APPLICATION_JSON)
    
	public void NumberAllCompleted(Restaurant resid,String orderStatus)
   	{
   		if((orderStatus=="Delivered"))
   		{
   			resid.setCompletedOrder(resid.getCompletedOrder()+1);   		}
   	}
	@RolesAllowed({"CustomerOwner"})
	@POST
    @Path("/createOrder")
    @Consumes(MediaType.APPLICATION_JSON)
	
    public void createOrder(int custid,  String restname, List<Order> itemarr, double delieveryFees,int runnerid) {
        
        // Update runner's status to "busy"
        runner.setRunnerStatus("busy");
        entityManager.merge(runner);

        // Create the order
        Order order = new Order();
        order.custid.setcustid(custid);
        order.restaurant.setName(restname);
        order.setitemarr(itemarr);
		order.runner.setDeliveryFees(delieveryFees);
        order.runner.setrunnerid(runnerid);
        order.setTotalPrice(calculateTotalReceiptValue(itemarr, delieveryFees));

        entityManager.persist(order);
    }
	@RolesAllowed({"CustomerOwner"})
	@PUT
    @Path("/ChangeOrder")
    @Consumes(MediaType.APPLICATION_JSON)
	public void ChangeOrder(int orderid,String orderStatus,Order order)
	{
		
		entityManager.find(Order.class,orderid);
		if(orderStatus!="Cancelled")
		{
			entityManager.merge(order);
		}
	}

}
