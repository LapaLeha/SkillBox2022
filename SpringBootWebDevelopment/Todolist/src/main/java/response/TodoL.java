package response;

import java.util.Date;

public class TodoL {

    private int id;
    private String name;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate() {
        this.date = new Date();
    }



}
