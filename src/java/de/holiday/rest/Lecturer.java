package de.holiday.rest;

/**
 *
 * @author Micha
 */
public class Lecturer {
  
  // This is the definition of the lecturer-entity at remote-service:
  /*
  t.string :firstname
  t.string :lastname
  t.string :degree
  t.string :email
  t.integer :university_id, :default => 1
  t.integer :department_id, :default => 1
  t.boolean :flag, :default => false  
  */
  
  // This attributes are always available in each entity
  private int id;
  private String created_at;
  private String updated_at;  
  
  // This attributes are specific for this entity
  private String firstname;
  private String lastname;
  private String degree;
  private String email;
  private int university_id;
  private int department_id;
  private boolean flag;

  public Lecturer() {
    
  }
  
  @Override
  public String toString() {
    return "Lecturer #" + this.id + ": " + this.degree + " " + this.firstname + " " + this.lastname + " | " + this.email + "";
  }          
 

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getDegree() {
    return degree;
  }

  public void setDegree(String degree) {
    this.degree = degree;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getUniversity_id() {
    return university_id;
  }

  public void setUniversity_id(int university_id) {
    this.university_id = university_id;
  }

  public int getDepartment_id() {
    return department_id;
  }

  public void setDepartment_id(int department_id) {
    this.department_id = department_id;
  }

  public boolean isFlag() {
    return flag;
  }

  public void setFlag(boolean flag) {
    this.flag = flag;
  }
    
}
