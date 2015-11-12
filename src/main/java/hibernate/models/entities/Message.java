package hibernate.models.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import hibernate.models.embeddable.R_Messaging;

@Entity
@Table(name="messages")
@AssociationOverrides({
	@AssociationOverride(name="users.sender",
						joinColumns = @ JoinColumn(name= "userfrom")),
	@AssociationOverride( name = "users.receiver",
						joinColumns = @JoinColumn(name = "userto"))
})
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="message_id_seq")
    @SequenceGenerator(name="message_id_seq", sequenceName="message_id_seq", allocationSize=1)
    @Column(name = "id")
    private int id;
    
    @Embedded
    private R_Messaging users;
    
    @Column(name = "content")
    private String content;

    @Column(name="sentdate")
    private Date sentdate;
    
	public Message() {

    }

	public Message(User sender, User receiver) {
		users = new R_Messaging();
		users.setSender(sender);
		users.setReceiver(receiver);
	}
	
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public R_Messaging getUsers() {
		return users;
	}

	public void setUsers(R_Messaging users) {
		this.users = users;
	}

	public Date getSentdate() {
		return sentdate;
	}

	public void setSentdate(Date sentdate) {
		this.sentdate = sentdate;
	}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}