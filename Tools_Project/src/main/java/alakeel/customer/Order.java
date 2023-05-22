package alakeel.customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import alakeel.runner.Runner;

@Entity
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private int orderid;
	@JoinColumn(name="runnerid")
	private Runner runner;

	@NotNull
	private boolean delivered;
	public void setDelivered(boolean delivered) {
		this.delivered=delivered;
		// TODO Auto-generated method stub
		
	}
}
