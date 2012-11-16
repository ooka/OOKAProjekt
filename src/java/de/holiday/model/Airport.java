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
public class Airport {
    
    // This attributes are always available in each entity
    private int id;
    private String created_at;
    private String updated_at;  
    
    //class specific fields
    private String name;
    private String code;
    private String country;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
