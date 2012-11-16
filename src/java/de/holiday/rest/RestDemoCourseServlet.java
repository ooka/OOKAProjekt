package de.holiday.rest;

import de.hbrs.ooka.ejb.restservice.RestServiceInterface;
import de.hbrs.ooka.ejb.restservice.SimpleJsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Micha
 */
@WebServlet(name = "RestDemoCourseServlet", urlPatterns = {"/RestDemoCourseServlet"})
public class RestDemoCourseServlet extends HttpServlet {
  
  // Required Services  
  @EJB
  private RestServiceInterface RestService;

  @EJB
  private SimpleJsonParser SimpleParser;  
 
  // Required Service-URLS
  private String service_BaseURL          = "http://ookarestservice.herokuapp.com/";
  
  private String url_getAllCourses        = service_BaseURL + "courses.json";
  private String url_getFirstCourse       = service_BaseURL + "courses/1.json";
  private String url_getCourseByID        = service_BaseURL + "courses/#ID#.json";
  private String url_createCourse         = service_BaseURL + "courses";
  private String url_updateCourse         = service_BaseURL + "courses/";  
  private String url_deleteCourse         = service_BaseURL + "courses/";
  private String url_getAllLecturers      = service_BaseURL + "lecturers.json";
  
  
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    
    // --------------------------------------------------------------------------------
    // Init and set authentication for services
    // --------------------------------------------------------------------------------
    
    this.SimpleParser.setResultClassType(Course.class);
    this.RestService.enableDebugMessages();
    
    this.SimpleParser.setHttpCredentials("apiuser", "api");
    this.RestService.setCredentials("apiuser", "api");    
    
    
    // --------------------------------------------------------------------------------
    // Get all Courses from REST-API as JSON (using RSI)
    // --------------------------------------------------------------------------------
    
    this.RestService.initResource(url_getAllCourses);
    String jsonresult_allCourses  = this.RestService.getResource();
    List<Course> CourseList   = this.SimpleParser.fetchStringJsonContent(jsonresult_allCourses);    
    
    
    // --------------------------------------------------------------------------------
    // Get first Course from REST-API as JSON (using RSI)
    // --------------------------------------------------------------------------------    
    
    this.RestService.initResource(url_getFirstCourse);
    String jsonresult_FirstCourse = this.RestService.getResource();           

    
    // --------------------------------------------------------------------------------
    // Get a random Course from REST-API as JSON (using RSI)
    // --------------------------------------------------------------------------------
    
    int existing_random_id = 1;
    
    if(! CourseList.isEmpty())  {
      existing_random_id = CourseList.get(new java.util.Random().nextInt(CourseList.size())).getId();
    }
   
    this.RestService.initResource(url_getCourseByID.replace("#ID#", String.valueOf(existing_random_id)));
    String jsonresult_RandomCourse = this.RestService.getResource();    
   
    
    // --------------------------------------------------------------------------------
    // Create a new Course using a POST-Request with some multipart formdata
    // NOTICE: Declare POST-Params using the rails-convention: 
    // '$modelname[$attribute]' => otherwise the request will fail
    // --------------------------------------------------------------------------------    
    
    this.RestService.initResource(url_createCourse);
    
    this.RestService.getPostForm().add("course[name]", "Parallel Algorithms");   
    this.RestService.getPostForm().add("course[startdate]", "2012-10-15");   
    this.RestService.getPostForm().add("course[starttime]", "09:30");
    this.RestService.getPostForm().add("course[enddate]", "2013-02-01");   
    this.RestService.getPostForm().add("course[endtime]", "12:00");    
    this.RestService.getPostForm().add("course[description]", "Some description text for Parallel Algorithms");    
    this.RestService.getPostForm().add("course[credits]", 5.5);    
    this.RestService.getPostForm().add("course[exam]", "2013-03-16 08:00");
    this.RestService.getPostForm().add("course[participants]", 16);
    this.RestService.getPostForm().add("course[rate]", 1.454158);
    this.RestService.getPostForm().add("course[lecturer_id]", 1);
    this.RestService.getPostForm().add("course[flag]", true);
   
    String jsonresult_Course_PostedViaForm = this.RestService.postResourceFormEncoded();        
    
    
    // --------------------------------------------------------------------------------
    // Create a new Course using a POST-Request with a JSON-String build from an Course-Object
    // No special conventions have to be attended
    // --------------------------------------------------------------------------------        
    
    this.RestService.initResource(url_createCourse);
    
    Course ReqEng = new Course();
    ReqEng.setName("Requirements-Engineering");
    ReqEng.setStartdate("2012-10-03");
    ReqEng.setStarttime("11:15");
    ReqEng.setEnddate("2013-01-26");
    ReqEng.setEndtime("15:45");
    ReqEng.setDescription("Some description text for Requirements-Engineering");
    ReqEng.setCredits(6.5);
    ReqEng.setExam("2013-03-11 09:30");
    ReqEng.setParticipants(15);
    ReqEng.setRate(1.5121558F);
    ReqEng.setLecturer_id(2);
    ReqEng.setFlag(false);
    ReqEng.setCreated_at("2012.10.28 01:30"); // Will be ignored by remote-service, cause it's auto-generated
    ReqEng.setUpdated_at("2012.10.29 01:30"); // Will be ignored by remote-service, cause it's auto-generated
    ReqEng.setTest_stuff("abc");              // This attribute does not exist at remote-service, will just be ignored to our rescue !
        
    String reqeng_as_json_string            = this.SimpleParser.toJson(ReqEng);
    String jsonresult_Course_PostedViaJSON  = this.RestService.postResource(reqeng_as_json_string);    
    
    
    // --------------------------------------------------------------------------------
    // Update a random selected Course using a PUT-Request with a JSON-String
    // --------------------------------------------------------------------------------        
    
    Course toBeUpdatedCourse   = (Course) this.SimpleParser.toEntity(jsonresult_RandomCourse);
    String response_put_request   = null;
    
    if(toBeUpdatedCourse != null) {
    
      toBeUpdatedCourse.setName("Course XYZ " + System.currentTimeMillis());
      
      String update_json_string = this.SimpleParser.toJson(toBeUpdatedCourse);

      this.RestService.initResource(url_updateCourse + toBeUpdatedCourse.getId());
      response_put_request = this.RestService.putResource(update_json_string);    
    }    
    
    // --------------------------------------------------------------------------------
    // Delete a random selected Course using a DELETE-Request
    // --------------------------------------------------------------------------------
    
    String response_delete_request  = "Sorry, deletion of course not possible";
    Course toBeDeletedCourse        = null; 
    
    if(CourseList.size() > 0 ) {
      
      toBeDeletedCourse = CourseList.get(new java.util.Random().nextInt(CourseList.size()));
      
      this.RestService.initResource(url_deleteCourse + toBeDeletedCourse.getId());
      response_delete_request = this.RestService.deleteResource();
    }

    // --------------------------------------------------------------------------------
    // All courses from REST-API (this time using only SJP)
    // --------------------------------------------------------------------------------

    List<Course> courses  = this.SimpleParser.fetchRemoteJsonContent(url_getAllCourses);        
    
    // --------------------------------------------------------------------------------
    // Now get all Lecturers from REST-API as JSON (using RSI)
    // --------------------------------------------------------------------------------    
    
    // Don't forget to change this, if you are working with another entity
    this.SimpleParser.setResultClassType(Lecturer.class);
    
    this.RestService.initResource(url_getAllLecturers);
    
    String jsonresult_allLecturers  = this.RestService.getResource();
    List<Lecturer> LecturerList     = this.SimpleParser.fetchStringJsonContent(jsonresult_allLecturers);        
   
    
    // --------------------------------------------------------------------------------
    // Output all results
    // --------------------------------------------------------------------------------
    
    try {
      
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet JsonRailsCourseDemoServlet</title>");      
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Servlet JsonRailsCourseDemoServlet at " + request.getContextPath() + "</h1>");
      

      out.println("This Servlet demonstrates you how to operate with CRUD-Methods on a remote REST-API");
      out.println("which is based on Rails with handling Input and Output in JSON-Format using GSON and Jersey");      
      out.println("<hr>");      
      
      
      out.println("<h2>All Courses from REST-API as JSON-String</h2>");
      //out.println(jsonresult_allCourses);
      
      String[] result_allCourses_formated = jsonresult_allCourses.split("\\},\\{");
      
      for(String s : result_allCourses_formated) {
        out.println("<p style='font-size:8pt'>" + s + "</p>");
      }
            
      out.println("<h3>OK make it a bit pretty looking:</h3>");
      if(CourseList.isEmpty()) {
        out.println("<b>Sorry no courses available</b>");
      }

      for( Course c : CourseList ) {
        out.println("<p style='font-size:10pt'>" + c + "</p>");
      }          
      out.println("<br><hr>");
      
      
      out.println("<h2>First Course from REST-API as JSON-String</h2>");
      out.println(jsonresult_FirstCourse);
      out.println("<br><hr>");        
      
      
      out.println("<h2>Random Single Course from REST-API as JSON (using RSI)</h2>");
      out.println(jsonresult_RandomCourse);
      out.println("<br><hr>");                 
      
      
      out.println("<h2>Create a new Course using a POST-Request with multipart formdata</h2>");
      out.println(jsonresult_Course_PostedViaForm);
      out.println("<br><hr>");               
      

      out.println("<h2>Create a new Course using a POST-Request with a JSON-String from a Course-Object</h2>");
      out.println(jsonresult_Course_PostedViaJSON);
      out.println("<br><hr>");              
      
      
      out.println("<h2>Update a random selected Course using a PUT-Request with a JSON-String</h2>");
      out.println(toBeUpdatedCourse);
      out.println("<br>Response: " + response_put_request);
      out.println("<br><hr>");       
      

      out.println("<h2>Delete a random selected Course using a DELETE-Request</h2>");
      out.println(toBeDeletedCourse);
      out.println("<br>Response: " + response_delete_request);
      out.println("<br><hr>");                                    
            
      
      out.println("<h2>All Courses from REST-API (diretcly using SJP)</h2>");
      for( Course c : courses ) {
        out.println(c);
        out.println("<br>");
      }          
      out.println("<br><hr>");      
      
      
      out.println("<h2>Lastly all Lecturers from REST-API as JSON-String and pretty formated</h2>");
      
      out.println("<p style='font-size:8pt'>" + jsonresult_allLecturers + "</p>");
            
      for( Lecturer l : LecturerList ) {
        out.println(l + "<br>");
      }          
      out.println("<br><hr>");            
            
      
      out.println("</body>");
      out.println("</html>");
    } finally {      
      out.close();
    }
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP
   * <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP
   * <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>
}
