package alakeel.customer;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;

import alakeel.restaurant.Restaurant;

@NamedQuery(name = "ListRestaurants", query = "Select r from  Runners")
@Entity
public class CustomerOwner {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int custid;
	@NotNull
	private String name;
    @NotNull
    private String address;
    private EntityManager entityManager;
	private List <Restaurant> ListRestaurants;

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
    @RolesAllowed({"CustomerOwner"})

    public  List <Restaurant> getListRestaurants () {
        return ListRestaurants;
    }

    public void setListRestaurants (List<Restaurant> ListRestaurants) {
    	Query query=entityManager.createQuery("ListRestaurants");
        this.ListRestaurants = ListRestaurants;
    }
   
	
	



}
