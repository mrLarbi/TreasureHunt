package hibernate.objects;

import javax.persistence.*;

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hunt_id_seq")
    @SequenceGenerator(name="hunt_id_seq", sequenceName="hunt_id_seq", allocationSize=1)
    @Column(name = "id")
    private int id;
    @Column(name = "userfrom")
    private int userfrom;
    @Column(name = "userto")
    private int userto;
    @Column(name = "content")
    private String content;

    public Message() {

    }

    public Message(int userfrom, int userto, String content) {
        this.userfrom = userfrom;
        this.userto = userto;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserfrom() {
        return userfrom;
    }

    public void setUserfrom(int userfrom) {
        this.userfrom = userfrom;
    }

    public int getUserto() {
        return userto;
    }

    public void setUserto(int userto) {
        this.userto = userto;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}