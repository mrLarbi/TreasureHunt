package hibernate.models.embeddable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import hibernate.models.entities.Coordinate;
import hibernate.models.entities.Hunt;
import hibernate.models.entities.User;

import java.io.Serializable;

@Embeddable
public class R_Hunting implements Serializable {
	@ManyToOne
	private User hunter;
	
	@ManyToOne
	private Hunt hunt;

	@ManyToOne
	private Coordinate huntedPoint;

	public R_Hunting(){}

	public R_Hunting(User hunter, Hunt hunt, Coordinate huntedPoint) {
		setHunter(hunter);
		setHunt(hunt);
		setHuntedPoint(huntedPoint);
	}

	public Hunt getHunt() {
		return hunt;
	}
	
	public void setHunt(Hunt hunt) {
		this.hunt = hunt;
	}
	
	public User getHunter() {
		return hunter;
	}
	public void setHunter(User hunter) {
		this.hunter = hunter;
	}

	public Coordinate getHuntedPoint() {
		return huntedPoint;
	}

	public void setHuntedPoint(Coordinate huntedPoint) {
		this.huntedPoint = huntedPoint;
	}
}
