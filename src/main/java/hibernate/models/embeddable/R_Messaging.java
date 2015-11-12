package hibernate.models.embeddable;


import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import hibernate.models.entities.User;

import java.io.Serializable;

@Embeddable
public class R_Messaging implements Serializable{
	@ManyToOne
	private User sender;
	
	@ManyToOne
	private User receiver;

	public R_Messaging(){

	}

	public R_Messaging(User sender, User receiver) {
		setSender(sender);
		setReceiver(receiver);
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
}
