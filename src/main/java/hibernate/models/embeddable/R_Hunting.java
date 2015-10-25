package hibernate.models.embeddable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import hibernate.models.entities.Hunt;
import hibernate.models.entities.User;

@Embeddable
public class R_Hunting {
	@ManyToOne
	private User hunter;
	
	@ManyToOne
	private Hunt hunt;
	
	public R_Hunting(){}

	public R_Hunting(User hunter, Hunt hunt) {
		setHunter(hunter);
		setHunt(hunt);
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
}
