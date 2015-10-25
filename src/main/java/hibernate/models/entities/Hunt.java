package hibernate.models.entities;

import org.hibernate.annotations.Where;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "hunts")
public class Hunt {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hunt_id_seq")
    @SequenceGenerator(name="hunt_id_seq", sequenceName="hunt_id_seq", allocationSize=1)
    @Column(name = "id")
    private int id;
    
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "creator", nullable = false)
    private User creator;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "createdAt")
    private Date createdAt;
    
    public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "hunt_coord",
            joinColumns = @JoinColumn(name = "hunt_id"),
            inverseJoinColumns = @JoinColumn(name = "coord_id")
    )
    private Set<Coordinate> coordinates;
    
	@OneToMany(mappedBy = "hunting.hunt", 
    		cascade = CascadeType.ALL,
    		fetch = FetchType.EAGER)
    @Where(clause = "finished = 'false'")
    private Set<Hunter> usersCurrentHunts = new HashSet<>();

	@OneToMany(mappedBy = "hunting.hunt", 
    		cascade = CascadeType.ALL,
    		fetch = FetchType.EAGER)
    @Where(clause = "finished = 'true'")
    private Set<Hunter> usersFinishedHunts = new HashSet<>();
    
    
    public Hunt() {
    	this.coordinates = new HashSet<>();
    }

    public Hunt(User creator, String name) {
        this.creator = creator;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
        this.creator.addHunt(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public Set<Hunter> getUsersCurrentHunts() {
		return usersCurrentHunts;
	}

	public void setUsersCurrentHunts(Set<Hunter> usersCurrentHunts) {
		this.usersCurrentHunts = usersCurrentHunts;
	}

	public Set<Hunter> getUsersFinishedHunts() {
		return usersFinishedHunts;
	}

	public void setUsersFinishedHunts(Set<Hunter> usersFinishedHunts) {
		this.usersFinishedHunts = usersFinishedHunts;
	}

	 public Set<Coordinate> getCoordinates() {
			return coordinates;
		}

		public void setCoordinates(Set<Coordinate> coordinates) {
			this.coordinates = coordinates;
		}

    public void addCoordinate(Coordinate coordinate) {
        this.coordinates.add(coordinate);
    }
}