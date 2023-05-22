package Users;

import java.awt.PageAttributes.MediaType;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import alakeel.customer.CustomerOwner;
import alakeel.restaurant.Restaurant;
import alakeel.restaurant.RestaurantOwner;
import alakeel.runner.Runner;

public class AuthenticationService {

    @PersistenceContext(unitName = "myPersistenceUnit")
    private EntityManager em;
    @RolesAllowed({"RestaurantOwner"})
    @POST
    @Path("/restaurantowner/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean signUpRestaurantOwner(String username, int pass) {
    	RestaurantOwner owner = new RestaurantOwner() ;
    	owner.setUserName(username);
    	owner.setpass(pass);
    	em.persist(owner);
        return true;
    }
    @POST
    @Path("/customer/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"CustomerOwner"})

    public boolean signUpCustomer(String username, int pass) {
    	CustomerOwner customer = new CustomerOwner();
    	customer.setUserName(username);
    	customer.setpass(pass);
    	em.persist(customer);
    	return true;
    }
    @POST
    @Path("/runner/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"runner"})
    public boolean signUpRunner(String username, int pass) {
        Runner runner=new Runner() ;
        runner.setpass(pass);
        runner.setpass(pass);
        em.persist(runner);
        return true;
    }
    @POST
    @Path("/restaurantowner/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"RestaurantOwner"})

    public boolean loginRestaurantOwner(String username, int pass) {
        RestaurantOwner owner = em.createQuery("SELECT o FROM RestaurantOwner o WHERE o.username = :username", RestaurantOwner.class)
                .setParameter("username", username)
                .getSingleResult();
        return owner != null && owner 
    }
    @POST
    @Path("/customer/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"CustomerOwner"})

   
    public boolean loginCustomer(String username, int pass) {
    	CustomerOwner customer = em.createQuery("SELECT c FROM Customer c WHERE c.username = :username", CustomerOwner.class)
                .setParameter("username", username)
                .getSingleResult();
        return customer != null && customer.getpass().
    }
    @POST
    @Path("/runner/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"runner"})

    public boolean loginRunner(String username, int pass) {
        Runner runner = em.createQuery("SELECT r FROM Runner r WHERE r.username = :username", Runner.class)
                .setParameter("username", username)
                .getSingleResult();
        return( runner != null && runner.getpass().equals(pass));

    }
}
