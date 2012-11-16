/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.holiday.presentation;

import de.holiday.model.Flight;
import de.holiday.model.Voyage;
import de.holiday.rest.HolidayService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author Andi
 */

@Stateless
@Named
public class VoyageManager {
    
   @EJB
   HolidayService holidayService;
   
   public List<Voyage> getAllVoyages() {
       return holidayService.getAllVoyages();
   }
   
   public Voyage getVoyage(int voyageId) {
       return holidayService.getVoyage(voyageId);
   }
   

    
}
