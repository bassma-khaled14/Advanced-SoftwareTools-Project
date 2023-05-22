package alakeel.runner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import alakeel.customer.Order;
@Entity
public class Runner {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int runnerid;
	@NotNull
	private String name;
	@NotNull
	private String status;
	@NotNull
	private double deliveryFees;
	private int accomplishedtrips;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

                                                    //runner id foreignkey in order
    public void markorderdelivered(Order order) {
        order.setDelivered(true);
        this.setStatus("available");
        this.setaccomplishedtrips(this.getaccomplishedtrips() + 1);
    }
    
	

}
