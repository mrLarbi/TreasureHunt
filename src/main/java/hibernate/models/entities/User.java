package hibernate.models.entities;


import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.sql.Date;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_id_seq")
	@SequenceGenerator(name="user_id_seq", sequenceName="user_id_seq", allocationSize=1)
	@Column(name = "id")
	private int id;
	
	@Column(name = "username", unique = true)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "avatar")
	private String avatar;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "gender")
	private char gender;
	
	@Column(name = "postalcode")
	private int postalcode;
	
	@Column(name = "phone")
	private String phone;

	@Column(name = "remember", unique = true)
	private String remember;

	@Column(name = "created")
	private Date created;

	@OneToMany(mappedBy = "friends.agent",
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER)
	private Set<Friend> myFollowers;
	
	
	@OneToMany(mappedBy = "friends.follower",
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER)
	private Set<Friend> listOFfollowed;
	
	@OneToMany(fetch = FetchType.EAGER,
			mappedBy = "users.sender", 
			cascade = CascadeType.ALL)
	private Set<Message> sentMessages;

	@OneToMany(fetch = FetchType.EAGER,
			mappedBy = "users.receiver", 
			cascade = CascadeType.ALL)
	private Set<Message> receivedMessages;

	@OneToMany(mappedBy="creator", 
			cascade=CascadeType.ALL,
			fetch = FetchType.EAGER)
	private Set<Hunt> createdhunts;
	
	@OneToMany(mappedBy = "hunting.hunter", 
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER)
	@Where(clause = "finished= 'false'")
	private Set<Hunter> currentHunts;
	
	@OneToMany(mappedBy = "hunting.hunter", 
				cascade = CascadeType.ALL,
				fetch = FetchType.EAGER)
	@Where(clause = "finished ='true'")
	private Set<Hunter> finishedhunts;
    

	public User() {}
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.avatar = "defaultAvatar.png";
		this.gender = 'N'; //NONE
		this.createdhunts = new HashSet<>();
		this.createdhunts = new HashSet<>();
		this.finishedhunts = new HashSet<>();
		this.sentMessages = new HashSet<>();
		this.receivedMessages = new HashSet<>();
		this.myFollowers = new HashSet<>();
		this.listOFfollowed = new HashSet<>();
	}

	public Set<Friend> getFriends() {
		return myFollowers;
	}

	public void setFriends(Set<Friend> friends) {
		this.myFollowers = friends;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(int postalcode) {
		this.postalcode = postalcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Set<Hunt> getCreatedhunts() {
		return createdhunts;
	}

	public void setCreatedhunts(Set<Hunt> createdhunts) {
		this.createdhunts = createdhunts;
	}

	public void addHunt(Hunt hunt) {
		this.createdhunts.add(hunt);
	}
	
	public Set<Hunter> getCurrenthunts() {
		return currentHunts;
	}

	public void setCurrenthunts(Set<Hunter> currenthunts) {
		this.currentHunts = currenthunts;
	}

	public Set<Hunter> getFinishedhunts() {
		return finishedhunts;
	}

	public void setFinishedhunts(Set<Hunter> finishedhunts) {
		this.finishedhunts = finishedhunts;
	}
	
	public String getRemember() {
		return remember;
	}

	public void setRemember(String remember) {
		this.remember = Base64.getEncoder().encodeToString(remember.getBytes());
	}
	
	public Set<Message> getSentMessages() {
		return sentMessages;
	}
	public void setSentMessages(Set<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}
	
	public Set<Message> getReceivedMessages() {
		return receivedMessages;
	}
	
	public void setReceivedMessages(Set<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}
	
	public void addToReceivedMessage (Message message) {
		receivedMessages.add(message);
	}
	
	public void addToSentMessages(Message sentMessage) {
		sentMessages.add(sentMessage);
	}
	
	public Set<Friend> getMyFollowers() {
		return myFollowers;
	}
	public void setMyFollowers(Set<Friend> myFollowers) {
		this.myFollowers = myFollowers;
	}

	public Set<Friend> getListOFfollowed() {
		return listOFfollowed;
	}
	public void setListOFfollowed(Set<Friend> listOFfollowed) {
		this.listOFfollowed = listOFfollowed;
	}

	public Set<Hunter> getCurrentHunts() {
		return currentHunts;
	}

	public void setCurrentHunts(Set<Hunter> currentHunts) {
		this.currentHunts = currentHunts;
	}


}