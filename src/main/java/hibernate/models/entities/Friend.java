package hibernate.models.entities;

import java.sql.Date;

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

import hibernate.models.embeddable.R_Friendship;

@Entity
@Table(name = "friends")
@AssociationOverrides({
	@AssociationOverride(name="friends.agent",
						joinColumns = @ JoinColumn(name= "agent")),
	@AssociationOverride( name = "friends.follower",
						joinColumns = @JoinColumn(name = "follower"))
})

public class Friend {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="friend_id_seq")
    @SequenceGenerator(name="friend_id_seq", sequenceName="friend_id_seq", allocationSize=1)
    @Column(name = "id")
    private int id;
    
	@Embedded
	private R_Friendship friends;

	@Column(name = "created")
	private Date friendsSince;
	
	public Friend() {
		
	}
	
	public Friend(User agent, User follower) {
		friends = new R_Friendship();
		friends.setAgent(agent);
		friends.setFollower(follower);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public R_Friendship getFriends() {
		return friends;
	}

	public void setFriends(R_Friendship friends) {
		this.friends = friends;
	}

	public Date getCreated() {
		return friendsSince;
	}

	public void setCreated(Date created) {
		this.friendsSince = created;
	}

}
