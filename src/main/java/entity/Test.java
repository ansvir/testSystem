package entity;

import java.sql.Time;

public class Test {
    private Long id;
    private String name;
    private String description;
    private Time time;

    public Test(Long id, String name, String description, Time time) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.time = time;
    }

    public Test(String name, String description, Time time) {
        this.name = name;
        this.description = description;
        this.time = time;
    }

    public Test() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", time=" + time +
                '}';
    }
}
