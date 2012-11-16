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
public class Voyage {

    // This attributes are always available in each entity
    private int id;
    private String created_at;
    private String updated_at;
    //class specific fields
    private String title;
    private String country;
    private String code;
    private String startdate;
    private String enddate;
    private String traveltype;
    private int contingent;
    private String description;
    private int hotel_id;
    private int flight_id;
    private int agency_id;

    public int getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(int agency_id) {
        this.agency_id = agency_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getContingent() {
        return contingent;
    }

    public void setContingent(int contingent) {
        this.contingent = contingent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEnddate() {
         return (this.enddate == null) ? null : this.convertDate(this.enddate, "yyyy-MM-dd");
        
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartdate() {
        return (this.startdate == null) ? null : this.convertDate(this.startdate, "yyyy-MM-dd");        
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTraveltype() {
        return traveltype;
    }

    public void setTraveltype(String traveltype) {
        this.traveltype = traveltype;
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
