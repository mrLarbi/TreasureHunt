package hibernate.models.entities;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import hibernate.models.embeddable.R_Hunting;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="hunters")
@AssociationOverrides({
	@AssociationOverride(name="hunting.hunter",
						joinColumns = @ JoinColumn(name= "hunter_id")),
	@AssociationOverride( name = "hunting.hunt",
						joinColumns = @JoinColumn(name = "hunt_id")),
	@AssociationOverride(name = "hunting.huntedPoint",
						joinColumns = @JoinColumn(name = "coord_id"))
})
public class Hunter implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hunter_id_seq")
    @SequenceGenerator(name="hunter_id_seq", sequenceName="hunter_id_seq", allocationSize=1)
    @Column(name = "id")
    private int id;
    
	@Embedded
	private R_Hunting hunting;

	@Column(name = "finished")
	private boolean finished;
	
	public Hunter(User user, Hunt hunt) {
		hunting = new R_Hunting();
		hunting.setHunter(user);
		hunting.setHunt(hunt);
	}
	
	public Hunter () {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public R_Hunting getHunting() {
		return hunting;
	}

	public void setHunting(R_Hunting hunting) {
		this.hunting = hunting;
	}
	
	public boolean getFinished() {
		return finished;
	}

	public void setFinished(boolean status) {
		this.finished = status;
	}

	public Coordinate getHuntedPoint() {
		return hunting.getHuntedPoint();
	}

	public void setHuntedPoint(Coordinate coord) {
		hunting.setHuntedPoint(coord);
	}

}
