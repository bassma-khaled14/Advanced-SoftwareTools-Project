package alakeel.runner;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.security.RolesAllowed;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import alakeel.customer.Order;
import alakeel.restaurant.Meal;
@Entity
@RolesAllowed({"runner"})
@NamedQuery(name = "Runner", query = "Select r from Runner Runners")
@Path("/Runner")

public class Runner implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int runnerid;
	@NotNull
	private String name;
	@NotNull
	private String status;
	@NotNull
	private double deliveryFees;
	@NotNull
	private int RandomAvailableRunner;
	@NotNull
	private String username;
	@NotNull
	private String email;
	@NotNull
	private int pass;
	private List <Runner> Runners;
	private int accomplishedtrips;
	EntityManager em;
	public Runner() {}

    public Runner(String name, String status, double deliveryFees) {
        this.name = name;
        this.status = status;
        this.deliveryFees = deliveryFees;
    }

    // getters and setters
    public int getrunnerid() {
        return runnerid;
    }

    public void setrunnerid(int runnerid) {
        this.runnerid = runnerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRunnerStatus() {
        return status;
    }

    public void setRunnerStatus(String status) {
        this.status = status;
    }

    public double getDeliveryFees() {
        return deliveryFees;
    }

    public void setDeliveryFees(double deliveryFees) {
        this.deliveryFees = deliveryFees;
    }
    
    
    public int getaccomplishedtrips() {
        return accomplishedtrips;
    }

    public void setaccomplishedtrips(int accomplishedtrips) {
        this.accomplishedtrips = accomplishedtrips;
    }

    public void setUserName(String username) {
    	this.username=username;
    }
    public String getUserName() {
        return username;
    }

    public void setpass(int pass) {
        this.pass = pass;
    }
    public int getpass() {
        return pass;
    }
    public void setEmail(String email) {
    	this.email=email;
    }
    public String getEmail() {
        return email;
    }
    public List <Runner>  getRunners() {
        return Runners;
    }

    public void setRunner(List <Runner> Runners) {
    	Query query=em.createQuery("itemarray");
        this.Runners = Runners;
    }
    

    @PUT
    @Path("/markorderdeliver")    
    @Produces(MediaType.APPLICATION_JSON)

    public void markorderdelivered(int orderId ,Order order) {
    	order.setOrderStatus("Delivered");
        this.setRunnerStatus("available");
        this.setaccomplishedtrips(this.getaccomplishedtrips() + 1);
    }
    @GET
    @Path("/AvailableRunner")
    @Produces(MediaType.APPLICATION_JSON)

    public Runner availableRunners(int runnerid)
	{
		
		em.find(Runner.class,runnerid);
		if(status!="busy")
		{
			 return em.find(Runner.class, runnerid);
	    }
		return null;
		
	}
}
    
	


