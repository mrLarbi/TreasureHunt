package hibernate.models.embeddable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import hibernate.models.entities.User;

@Embeddable
public class R_Friendship {
	
	
	@ManyToOne
	private User agent;
	
	@ManyToOne
	private User follower;

	public R_Friendship(){}

	public R_Friendship(User agent, User follower) {
		setAgent(agent);
		setFollower(follower);
	}

	public User getAgent() {
		return agent;
	}

	public void setAgent(User agent) {
		this.agent = agent;
	}

	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}
	
	
	
}
