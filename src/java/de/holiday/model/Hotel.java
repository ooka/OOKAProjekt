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
public class Hotel {

    // This attributes are always available in each entity
    private int id;
    private String created_at;
    private String updated_at;
    
    //class specific fields 
    private String name;
    private String city;
    private String country;
    private int stars;
    private int singlerooms;
    private int doublerooms;
    private double singleroomprice;
    private double doubleroomprice;
    private boolean pool;
    private boolean beach;
    private boolean animation;
    private boolean spa;
    private int agency_id;
    
    
    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
    
    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(int agency_id) {
        this.agency_id = agency_id;
    }

    public boolean isAnimation() {
        return animation;
    }

    public void setAnimation(boolean animation) {
        this.animation = animation;
    }

    public boolean isBeach() {
        return beach;
    }

    public void setBeach(boolean beach) {
        this.beach = beach;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getDoubleroomprice() {
        return doubleroomprice;
    }

    public void setDoubleroomprice(double doubleroomprice) {
        this.doubleroomprice = doubleroomprice;
    }

    public int getDoublerooms() {
        return doublerooms;
    }

    public void setDoublerooms(int doublerooms) {
        this.doublerooms = doublerooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public double getSingleroomprice() {
        return singleroomprice;
    }

    public void setSingleroomprice(double singleroomprice) {
        this.singleroomprice = singleroomprice;
    }

    public int getSinglerooms() {
        return singlerooms;
    }

    public void setSinglerooms(int singlerooms) {
        this.singlerooms = singlerooms;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
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
