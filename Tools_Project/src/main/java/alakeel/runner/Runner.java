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

import alakeel.customer.Order;
@Entity
@RolesAllowed({"runner"})
@NamedQuery(name = "Runner", query = "Select r from Runner Runners")

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
    public List <Runner>  getRunners() {
        return Runners;
    }

    public void setRunner(List <Runner> Runners) {
    	Query query=em.createQuery("itemarray");
        this.Runners = Runners;
    }
    

                                                   
    public void markorderdelivered(Order order) {
    	order.setOrderStatus("Delivered");
        this.setRunnerStatus("available");
        this.setaccomplishedtrips(this.getaccomplishedtrips() + 1);
    }
    private Runner getRandomAvailableRunner() {
        Query query = em.createQuery("Runners");
        List<Runner> availableRunners = query.getResultList();
        int availableRunnersCount = availableRunners.size();

        if (availableRunnersCount > 0)
        {
        	
            int randomIndex = ThreadLocalRandom.current().nextInt(availableRunnersCount);
            return availableRunners.get(randomIndex);
        }

        // If no available runners, return nullS
        return null;
    }
    
	

}
