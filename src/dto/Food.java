package dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Walter White
 */
public class Food implements Comparable<Food> {

    private String name, type, place;
    private int weight, id;
    private Date expiredDate;

    public Food() {
        id = 0;
        name = "";
        type = "";
        weight = 0;
        place = "";

    }

    public Food(int id, String name, String type, int weight, String place, Date expiredDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.place = place;
        this.expiredDate = expiredDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return String.format("|%-10s|%-10s|%-10d|%-15s|%-10s|\n", id, name, weight, place, df.format(expiredDate));
    }

    public void showInfo() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        System.out.printf("|%8d|%-15s|%-25s|%-5d|%-17s|%-10s|\n",
                id, name, type, weight, place, df.format(expiredDate));
    }

    @Override
    public int compareTo(Food that) {
        if (this.id < that.getId()) {
            return -1;
        } else if (this.id > that.getId()) {
            return 1;
        }
        return 0;
    }

    boolean contains(String search) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
