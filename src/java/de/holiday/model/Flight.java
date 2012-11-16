/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.holiday.model;

import de.holiday.rest.Course;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andi
 */
public class Flight {
    
    // This attributes are always available in each entity
    private int id;
    private String created_at;
    private String updated_at;  
    
    //class specific fields
    private String airline;
    private String flightnumber;
    private int seats;
    private double price;
    private String departuredate;
    private String arrivaldate;
    private int departure_id;
    private int arrival_id;
    private int agency_id;

    public int getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(int agency_id) {
        this.agency_id = agency_id;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public int getArrival_id() {
        return arrival_id;
    }

    public void setArrival_id(int arrival_id) {
        this.arrival_id = arrival_id;
    }

    public Date getArrivaldate() {
        return (this.arrivaldate == null) ? null : this.convertDate(this.arrivaldate.replace("T", " ").replace("Z", " "), "yyyy-MM-dd HH:mm:ss");
        
    }

    public void setArrivaldate(String arrivaldate) {
        this.arrivaldate = arrivaldate;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getDeparture_id() {
        return departure_id;
    }

    public void setDeparture_id(int departure_id) {
        this.departure_id = departure_id;
    }

    public Date getDeparturedate() {
        Logger.getLogger(Course.class.getName()).log(Level.SEVERE, "Failed to parse Date from JSON: {0}", departuredate);
        return (this.departuredate == null) ? null : this.convertDate(this.departuredate.replace("T", " ").replace("Z", " "), "yyyy-MM-dd HH:mm:ss");
    }

    public void setDeparturedate(String departuredate) {
        this.departuredate = departuredate;
    }

    public String getFlightnumber() {
        return flightnumber;
    }

    public void setFlightnumber(String flightnumber) {
        this.flightnumber = flightnumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
    
       private Date convertDate(String DateValue, String Format) {
    try {
      return new SimpleDateFormat(Format).parse(DateValue);
    } catch (ParseException | NullPointerException ex) {
      Logger.getLogger(Course.class.getName()).log(Level.SEVERE, "Failed to parse Date from JSON: {0}", DateValue);
    }
    return null;    
  }

   
    
}
