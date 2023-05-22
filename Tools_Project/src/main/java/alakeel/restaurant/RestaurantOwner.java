package alakeel.restaurant;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
@Entity
@RolesAllowed({"RestaurantOwner"})
public class RestaurantOwner {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int resownerid;
	@NotNull
	private String username;
	@NotNull
	private int pass;
    @PersistenceContext(unitName = "myPersistenceUnit")
	private EntityManager em;
	public RestaurantOwner() {}
	
	public RestaurantOwner(int resownerid, String username,int pass) {
        this.resownerid = resownerid;
        this.username = username;
        this.pass=pass;
        
    }
	   public int getresownerid() {
	        return resownerid;
	    }

	    public void setresownerid(int resownerid) {
	        this.resownerid = resownerid;
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
	   
        //Function craete Menu 
	    
	    public void createMenue( List<Meal> mealList) 
	    {
	    	 for (Meal meal : mealList) {
	        em.persist(meal); 
	    }
	    	 
	    }
	    // Function EditMenu
	    public void editMenu( List<Meal> mealList) 
	    {
	    	 for (Meal meal : mealList) {
	        em.merge(meal); 
	    }
	    }
	    
	    public Restaurant GetRestaurantdetailsbyid( int resid)
	   
	    {
	    	
	    	return em.find(Restaurant.class,resid);
	    }
	    @GET
	    @Path("/CreateReport")
	    public void CreateReport(Restaurant resid)
	    {
	    	System.out.println("The total income is " +resid.gettotalincome());
	    	System.out.println("The total cancelled order is " +resid.getCancelledOrder());
	    	System.out.println("The total completed orders is " +resid.getCompletedOrder());
	    	
	    }
	 
	    

	 
}
