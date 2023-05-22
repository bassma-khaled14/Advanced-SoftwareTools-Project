package alakeel.restaurant;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
@Entity
public class Restaurant implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int resid;
	@NotNull
	private String name;
	private int ownerid;                       
	@OneToMany(mappedBy = "meal")
    private List<Meal> mealList;
    @NotNull
    private String address;
    EntityManager entityManager;
    public Restaurant(int resid, String name, int ownerid, List<Meal> mealList, String address) {
        this.resid = resid;
        this.name = name;
        this.ownerid = ownerid;
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

    public int getOwnerId() {
        return ownerid;
    }

    public void setownerid(int ownerid) {
        this.ownerid = ownerid;
    }
    public List<Meal> getMealList() {
    	Query query=entityManager.createQuery(address);
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
    

