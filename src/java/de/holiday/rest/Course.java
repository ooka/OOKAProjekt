package de.holiday.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Micha
 */
public class Course {
  
  // This is the definition of the course-entity by remote-service:
  /*
  t.string :name
  t.date :startdate
  t.time :starttime
  t.date :enddate
  t.time :endtime
  t.text :description
  t.decimal :credits, :precision => 3, :scale => 1
  t.datetime :exam
  t.integer :participants
  t.float :rate
  t.integer :lecturer_id, :default => 1
  t.boolean :flag, :default => false
  */
  
  // Notice: Declaring startdate as 'private Date startdate;' causes JsonSyntaxException in GSON
  // [...] com.google.gson.internal.bind.DateTypeAdapter.deserializeToDate(DateTypeAdapter.java:81) [...]
  // Very very simple workaround: declare as String and perform conversion to Date in Getter if necessary
    
  // Notice: Variables must be lowercase, otherwise they do not match with restservice-params
  
  // This attributes are always available in each entity
  private int id;
  private String created_at;
  private String updated_at;  
  
  // This attributes are specific for this entity
  private String name;
  private String startdate;
  private String starttime;
  private String enddate;
  private String endtime;  
  private String description;
  private double credits;
  private String exam;
  private int participants;
  private float rate;
  private int lecturer_id;
  private boolean flag;
  
  // This is a non-existing attribute and will be ignored by remote-service
  private String test_stuff;
  
  
  public Course() {
    
  }
  
  @Override
  public String toString() {
    return "Course #" + this.id + ": " + this.name + " | Starts: " + this.startdate + ", " + this.starttime + " | " +
           "Ends: " + this.enddate + ", " + this.endtime + " | Credits: " + this.credits + " | Exam at: " + this.exam + " | " +
           "Participants:" + this.participants + " | Rate: " + this.rate + " | Lecturer: " + this.lecturer_id + " | " + 
           "<br />Description: " + this.description; 
  }
  
    
  // All Getter and Setters with some type-conversions
  
  public int getId() {
    return id;
  }

  public void setId(int Id) {
    this.id = Id;
  }

  public String getName() {
    return name;
  }

  public void setName(String Name) {
    this.name = Name;
  }

  public Date getStartdate() {
    return (this.startdate == null) ? null : this.convertDate(this.startdate, "yyyy-MM-dd");
  }

  public void setStartdate(String startdate) {
    this.startdate = startdate;
  }

  public String getStarttime() {
    return starttime;
  }

  public void setStarttime(String starttime) {
    this.starttime = starttime;
  }

  public Date getEnddate() {
    return (this.enddate == null) ? null : this.convertDate(this.enddate, "yyyy-MM-dd");
  }

  public void setEnddate(String enddate) {
    this.enddate = enddate;
  }

  public String getEndtime() {
    return endtime;
  }

  public void setEndtime(String endtime) {
    this.endtime = endtime;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getCredits() {
    return credits;
  }

  public void setCredits(double credits) {
    this.credits = credits;
  }

  public Date getExam() {
    // Pragmatic solution :)
    return (this.exam == null) ? null : this.convertDate(this.exam.replace("T", " ").replace("Z", " "), "yyyy-MM-dd HH:mm:ss");
  }

  public void setExam(String exam) {
    this.exam = exam;
  }

  public int getParticipants() {
    return participants;
  }

  public void setParticipants(int participants) {
    this.participants = participants;
  }

  public float getRate() {
    return rate;
  }

  public void setRate(float rate) {
    this.rate = rate;
  }

  public int getLecturer_id() {
    return lecturer_id;
  }

  public void setLecturer_id(int lecturer_id) {
    this.lecturer_id = lecturer_id;
  }

  public boolean isFlag() {
    return flag;
  }

  public void setFlag(boolean flag) {
    this.flag = flag;
  }

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  public String getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(String updated_at) {
    this.updated_at = updated_at;
  }

  public String getTest_stuff() {
    return test_stuff;
  }

  public void setTest_stuff(String test_stuff) {
    this.test_stuff = test_stuff;
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
