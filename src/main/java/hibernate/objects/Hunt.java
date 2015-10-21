package hibernate.objects;

import javax.persistence.*;

@Entity
@Table(name = "hunts")
public class Hunt {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hunt_id_seq")
    @SequenceGenerator(name="hunt_id_seq", sequenceName="hunt_id_seq", allocationSize=1)
    @Column(name = "id")
    private int id;
    @Column(name = "creator")
    private int creator;
    @Column(name = "name")
    private String name;
    @Column(name = "points")
    private int[] points;
    @Column(name = "usersfinished")
    private int[] usersfinished;
    @Column(name = "userscurrent")
    private int[] userscurrent;

    public Hunt() {

    }

    public Hunt(int creator, String name) {
        this.creator = creator;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getPoints() {
        return points;
    }

    public void setPoints(int[] points) {
        this.points = points;
    }

    public int[] getUsersfinished() {
        return usersfinished;
    }

    public void setUsersfinished(int[] usersfinished) {
        this.usersfinished = usersfinished;
    }

    public int[] getUserscurrent() {
        return userscurrent;
    }

    public void setUserscurrent(int[] userscurrent) {
        this.userscurrent = userscurrent;
    }
}