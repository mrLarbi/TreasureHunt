package hibernate.models.entities;

import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "coordinates")
public class Coordinate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="coord_id_seq")
    @SequenceGenerator(name="coord_id_seq", sequenceName="coord_id_seq", allocationSize=1)
    @Column(name = "id")
    private int id;
    
    @Column(name = "latitude")
    private String latitude;
    
    @Column(name = "longitude")
    private String longitude;
    
    @Column(name = "image")
    private String image;
    
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "coordinates")
    private Set<Hunt> hunts;

    @OneToMany(mappedBy = "hunting.huntedPoint",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @Where(clause = "finished = 'false'")
    private Set<Hunter> usersCurrentHunts;

    @OneToMany(mappedBy = "hunting.huntedPoint",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @Where(clause = "finished = 'true'")
    private Set<Hunter> usersFinishedHunts;

	public Coordinate() {
    	hunts = new HashSet<>();
        usersCurrentHunts = new HashSet<>();
        usersFinishedHunts = new HashSet<>();
    }

    public Coordinate(String name, String latitude, String longitude, String image) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
        this.hunts = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Set<Hunt> getHunts() {
		return hunts;
	}

	public void setHunts(Set<Hunt> hunts) {
		this.hunts = hunts;
	}

    public Set<Hunter> getUsersFinishedHunts() {
        return usersFinishedHunts;
    }

    public void setUsersFinishedHunts(Set<Hunter> usersFinishedHunts) {
        this.usersFinishedHunts = usersFinishedHunts;
    }


    public Set<Hunter> getUsersCurrentHunts() {
        return usersCurrentHunts;
    }

    public void setUsersCurrentHunts(Set<Hunter> usersCurrentHunts) {
        this.usersCurrentHunts = usersCurrentHunts;
    }
}
