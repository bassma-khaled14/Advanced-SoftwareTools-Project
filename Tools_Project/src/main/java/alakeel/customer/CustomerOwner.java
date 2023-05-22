package alakeel.customer;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
public class CustomerOwner {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int custid;
	@NotNull
	private String name;
    @NotNull
    private String address;
    public CustomerOwner() {}
    public CustomerOwner(int custid, String name, String address) {
        this.custid = custid;
        this.name = name;
        this.address=address;
    }
    public int getresid() {
        return custid;
    }

    public void setcustid(int custid) {
        this.custid = custid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setaddress(String address)
    {
    	this.address=address;
    }
    public String getadress()
    {
    	return address;
    }

}
