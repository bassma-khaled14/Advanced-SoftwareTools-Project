package alakeel.restaurant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
@Entity
public class RestaurantOwner {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int resownerid;
	@NotNull
	private String name;
	public RestaurantOwner() {}
	
	public RestaurantOwner(int resownerid, String name) {
        this.resownerid = resownerid;
        this.name = name;
    }
	 public int getresownerid() {
	        return resownerid;
	    }

	    public void setresownerid(int resownerid) {
	        this.resownerid = resownerid;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    
		

}
