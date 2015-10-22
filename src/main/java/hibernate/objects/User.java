package hibernate.objects;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_id_seq")
	@SequenceGenerator(name="user_id_seq", sequenceName="user_id_seq", allocationSize=1)
	@Column(name = "id")
	private int id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
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
	@Column(name = "friends")
	private int[] friends;
	@Column(name = "messages")
	private int[] messages;
	@Column(name = "createdhunts")
	private int[] createdhunts;
	@Column(name = "currenthunts")
	private int[] currenthunts;
	@Column(name = "finishedhunts")
	private int[] finishedhunts;
	@Column(name = "remember")
	private String remember;


	public User() {}
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.avatar = "default.jpg";
		this.gender = 'N'; //NONE
	}

	public int[] getFriends() {
		return friends;
	}

	public void setFriends(int[] friends) {
		this.friends = friends;
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

	public int[] getMessages() {
		return messages;
	}

	public void setMessages(int[] messages) {
		this.messages = messages;
	}

	public int[] getCreatedhunts() {
		return createdhunts;
	}

	public void setCreatedhunts(int[] createdhunts) {
		this.createdhunts = createdhunts;
	}

	public int[] getCurrenthunts() {
		return currenthunts;
	}

	public void setCurrenthunts(int[] currenthunts) {
		this.currenthunts = currenthunts;
	}

	public int[] getFinishedhunts() {
		return finishedhunts;
	}

	public void setFinishedhunts(int[] finishedhunts) {
		this.finishedhunts = finishedhunts;
	}

	public String getRemember() {
		return remember;
	}

	public void setRemember(String remember) {
		this.remember = remember;
	}
}