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
public class Passenger {

    // This attributes are always available in each entity
    private int id;
    private String created_at;
    private String updated_at;
    
    //class specific fields
    private String firstname;
    private String lastname;
    private int flight_id;
    private int voyage_id;
    private int booking_id;
    private int agency_id;

    public int getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(int agency_id) {
        this.agency_id = agency_id;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getVoyage_id() {
        return voyage_id;
    }

    public void setVoyage_id(int voyage_id) {
        this.voyage_id = voyage_id;
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
