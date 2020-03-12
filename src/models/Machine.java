package models;

import java.util.Date;

public class Machine {

    private int id;
    private int room;
    private String state;
    private Date dateBought;
    private int serialNumber;

    public Machine(int id, int room, String state, Date dateBought, int serialNumber) {
        this.id = id;
        this.room = room;
        this.state = state;
        this.dateBought = dateBought;
        this.serialNumber = serialNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getDateBought() {
        return dateBought;
    }

    public void setDateBought(Date dateBought) {
        this.dateBought = dateBought;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}
