package de.holiday.rest;

import de.hbrs.ooka.ejb.restservice.RestServiceInterface;
import de.hbrs.ooka.ejb.restservice.SimpleJsonParser;
import de.holiday.model.Flight;
import de.holiday.model.Voyage;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Micha
 * OOKA RestServiceDemo
 */

@Named @Stateless
public class HolidayService implements Serializable {
  
  @EJB
  private RestServiceInterface RestService;
  
  @EJB
  private SimpleJsonParser SimpleParser;
  
  private String Title = "CourseDemoEJB " + new java.util.Random().nextInt(10000);
  
  //private String service_BaseURL          = "http://localhost:3000/";
  private String service_BaseURL          = "http://ookarestservice.herokuapp.com/";
  
  private String url_getAllCourses        = service_BaseURL + "courses.json";
  private String url_getCourseByID        = service_BaseURL + "courses/#ID#.json";
  private String url_getLecturerByID      = service_BaseURL + "lecturers/#ID#.json";
  
  
  private String url_getAllFlights        = service_BaseURL + "flights.json";
  private String url_getAllVoyages        = service_BaseURL + "voyages.json";
  private String url_getVoyageByID        = service_BaseURL + "voyages/#ID#.json";
  
  // This is a common auth and only valid for this example !
  private String service_Username         = "weld";
  private String service_Password         = "kopa4aGj";

  
  public HolidayService() {
    
  }
  
  
  private void init(Class Entity) {
    
    this.RestService.setCredentials(service_Username, service_Password);
    this.RestService.enableDebugMessages();
    this.SimpleParser.setResultClassType(Entity);
  }
    
  
  public String getHello() {
    return "Hello from" + this.getClass().getSimpleName() + " :: " + System.currentTimeMillis();
  }    

  public String getTitle() {
    return Title;
  }

  public void setTitle(String Title) {
    this.Title = Title;
  }

  public String getVoyage_JSON(int id) {
    
    this.init(Voyage.class);
    this.RestService.initResource(url_getVoyageByID.replace("#ID#", String.valueOf(id)));
    
    String result_SingleVoyage = this.RestService.getResource();    
    return result_SingleVoyage;
  }
  
    
  public Voyage getVoyage(int id) {
    Voyage v = (Voyage) this.SimpleParser.toEntity(this.getVoyage_JSON(id));
    return v;
  }
  
  public String getAllCourses_JSON() {

    this.init(Course.class);
    this.RestService.initResource(url_getAllCourses);
        
    String result_allAiports = this.RestService.getResource();  
    return result_allAiports;
  }  
  
    public String getAllFlights_JSON() {

    this.init(Flight.class);
    this.RestService.initResource(url_getAllFlights);
        
    String result_allFlights = this.RestService.getResource();  
    return result_allFlights;
  }

  
  public List<Flight> getAllFlights() {
    
    List<Flight> flightList = SimpleParser.fetchStringJsonContent(this.getAllFlights_JSON());
    return flightList;
  }
  
   public String getAllVoyages_JSON() {

    this.init(Voyage.class);
    this.RestService.initResource(url_getAllVoyages);
        
    String result_allVoyages = this.RestService.getResource();  
    return result_allVoyages;
  }

  
  public List<Voyage> getAllVoyages() {
    
    List<Voyage> voyagesList = SimpleParser.fetchStringJsonContent(this.getAllVoyages_JSON());
    return voyagesList;
  }
  
  
  
  public List<Course> getAllCourses() {
    
    List<Course> CourseList = SimpleParser.fetchStringJsonContent(this.getAllCourses_JSON());
    return CourseList;
  }
  
  
  public String getCourse_JSON(int id) {
    
    this.init(Course.class);
    this.RestService.initResource(url_getCourseByID.replace("#ID#", String.valueOf(id)));
    
    String result_SingleCourse = this.RestService.getResource();    
    return result_SingleCourse;
  }
  
    
  public Course getCourse(int id) {
    
    Course c = (Course) this.SimpleParser.toEntity(this.getCourse_JSON(id));
    return c;
  }
  
  
  public String getCourseLecturer_JSON(int id) {
    
    this.init(Lecturer.class);
    this.RestService.initResource(url_getLecturerByID.replace("#ID#", String.valueOf(id)));    
    
    String result_SingleLecturer = this.RestService.getResource();    
    return result_SingleLecturer;    
  }
  
  public Lecturer getCourseLecturer(int id) {
    
    Lecturer l = (Lecturer) this.SimpleParser.toEntity(this.getCourseLecturer_JSON(id));
    return l;
  }
  
  
}
