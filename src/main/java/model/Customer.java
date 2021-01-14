package model;

public class Customer {
    protected int id;
    protected String room;
    protected String name;
    protected String cmnd;
    protected String checkin;
    protected String checkout;

    public Customer(){}

    public Customer(String room, String name, String cmnd, String checkin, String checkout){
        super();
        this.room = room;
        this.name = name;
        this.cmnd = cmnd;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Customer(int id, String room, String name, String cmnd, String checkin, String checkout){
        super();
        this.id = id;
        this.room = room;
        this.name = name;
        this.cmnd = cmnd;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getRoom() {
        return room;
    }

    public String getCmnd() {
        return cmnd;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
