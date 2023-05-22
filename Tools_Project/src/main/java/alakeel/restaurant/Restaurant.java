package alakeel.restaurant;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
@Entity
@NamedQuery(name = "listallmeals", query = "Select m from Meal m")
public class Restaurant implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int resid;
	@NotNull
	private String name;
	private RestaurantOwner resownerid;                       
	@OneToMany(mappedBy = "meal")
    private List<Meal> mealList;
    @NotNull
    private String address;
    EntityManager entityManager;
    public Restaurant(int resid, String name, RestaurantOwner resownerid, List<Meal> mealList, String address) {
        this.resid = resid;
        this.name = name;
        this.resownerid=resownerid;
        this.mealList = mealList;
        this.address=address;
    }
    public Restaurant() {}

    // Getters and setters for the fields

    public int getresid() {
        return resid;
    }

    public void setresid(int resid) {
        this.resid = resid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RestaurantOwner getRestaurantOwner() {
        return resownerid;
    }

    public void setRestaurantOwner(RestaurantOwner resownerid) {
        this.resownerid = resownerid;
    }
    
    public List<Meal> getMealList(String Meal) {
    	Query query=entityManager.createQuery("listallmeals");
    	List<Meal>MealList=query.getResultList();
    			
        return mealList;
    }

    public void setMealList(List<Meal> mealList) {
        this.mealList = mealList;
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
    

