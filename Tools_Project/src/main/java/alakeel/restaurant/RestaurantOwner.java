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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
@Entity
@RolesAllowed({"RestaurantOwner"})
@Path("/RestaurantOwner")

public class RestaurantOwner {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int resownerid;
	@NotNull
	private String username;
	@NotNull
	private int pass;
	@NotNull
	private String email;
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
	    public void setEmail(String email) {
	    	this.email=email;
	    }
	    public String getEmail() {
	        return email;
	    }

	   
        //Function craete Menu 
	    @POST
	    @Path("/createMeue")
	    @Consumes(MediaType.APPLICATION_JSON)
	    public void createMenue( List<Meal> mealList) 
	    {
	    	 for (Meal meal : mealList) {
	        em.persist(meal); 
	    }
	    	 
	    }
	    // Function EditMenu
	    @PUT
	    @Path("/editMenu")
	    @Consumes(MediaType.APPLICATION_JSON)
	    public void editMenu( List<Meal> mealList) 
	    {
	    	 for (Meal meal : mealList) {
	        em.merge(meal); 
	    }
	    }
	    @GET
	    @Path("/GetRestaurantdetails/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Restaurant GetRestaurantdetailsbyid(@PathParam("id") int resid)
	   
	    {
	    	
	    	return em.find(Restaurant.class,resid);
	    }
	    @GET
	    @Path("/CreateReport")
	    @Consumes(MediaType.APPLICATION_JSON)
	    public void CreateReport(Restaurant resid)
	    {
	    	System.out.println("The total income is " +resid.gettotalincome());
	    	System.out.println("The total cancelled order is " +resid.getCancelledOrder());
	    	System.out.println("The total completed orders is " +resid.getCompletedOrder());
	    	
	    }
	 
	    

	 
}
