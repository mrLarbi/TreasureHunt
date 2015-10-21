package hibernate.objects;

import javax.persistence.*;

public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="coords_id_seq")
    @SequenceGenerator(name="coords_id_seq", sequenceName="coords_id_seq", allocationSize=1)
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

    public Coordinates() {

    }

    public Coordinates(String name, String latitude, String longitude, String image) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.image = image;
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
}
