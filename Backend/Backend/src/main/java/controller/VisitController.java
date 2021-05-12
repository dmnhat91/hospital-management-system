package controller;

import model.Patient;
import model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.VisitService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
@CrossOrigin
public class VisitController {
    @Autowired
    private VisitService visitService;

    //API for visits
    //READ
    @RequestMapping(path = "visits", method = RequestMethod.GET)
    public List<Visit> getAllVisits(){
        return visitService.getAllVisits();
    }

    //Get all visits by page
    @RequestMapping(path = "visits", method = RequestMethod.GET, params = "page")
    public List<Visit> getAllVisitsByPage(@RequestParam int page){
        return visitService.getAllVisitsByPage(page);
    }

    //UPDATE
    @RequestMapping(path = "visits", method = RequestMethod.PUT)
    public void updateVisit(@RequestBody Visit visit){
        visitService.addVisit(visit);
    }

    //DELETE
    @RequestMapping(path="visits/{id}", method = RequestMethod.DELETE)
    public void deleteVisit(@PathVariable int id){
        visitService.deleteVisit(id);
    }

    //API to add a visit for new/return patient
    @RequestMapping(path= "visits", method = RequestMethod.POST)
    public void addVisit(@RequestBody Visit visit){
        visitService.addVisit(visit);
    }

    //API to find visit by id
    @RequestMapping(path = "visits/search", method = RequestMethod.GET, params = "id")
    public List<Visit> getVisitById(@RequestParam int id){
        return visitService.findVisitsById(id);
    }

    //API to find visits by patient id
    @RequestMapping(path = "visits/search", method = RequestMethod.GET, params = "pid")
    public List<Visit> getVisitsByPatientId(@RequestParam int pid){
        return visitService.findVisitsByPatientId(pid);
    }

    //API to find visits by patient id paging
    @RequestMapping(path = "visits/search", method = RequestMethod.GET, params = {"pid", "page"})
    public List<Visit> getVisitsByPatientIdPaging(@RequestParam int pid, @RequestParam int page){
        return visitService.findVisitsByPatientIdPaging(pid, page);
    }

    //API to find visits by patient name
    @RequestMapping(path = "visits/search", method = RequestMethod.GET, params = "pname")
    public List<Visit> getVisitsByPatientName(@RequestParam String pname){
        return visitService.findVisitsByPatientName(pname);
    }

    //API to find visits by patient name paging
    @RequestMapping(path = "visits/search", method = RequestMethod.GET, params = {"pname", "page"})
    public List<Visit> getVisitsByPatientNamePaging(@RequestParam String pname, @RequestParam int page){
        return visitService.findVisitsByPatientNamePaging(pname, page);
    }

    //API to find visits by days
    @RequestMapping(path = "visits/search", method = RequestMethod.GET, params = {"startDate","endDate"})
    public List<Visit> getVisitsByDates(@RequestParam String startDate, @RequestParam String endDate) throws Exception{
        return visitService.findVisitsByDays(startDate,endDate);
    }

    //API to find visits by days paging
    @RequestMapping(path = "visits/search", method = RequestMethod.GET, params = {"startDate","endDate","page"})
    public List<Visit> getVisitsByDatesPaging(@RequestParam String startDate, @RequestParam String endDate, @RequestParam int page) throws Exception{
        return visitService.findVisitsByDaysPaging(startDate,endDate,page);
    }

    //API to find visits by date
    @RequestMapping(path = "visits/search", method = RequestMethod.GET, params = "date")
    public List<Visit> getVisitsByDate(@RequestParam String date) throws Exception{
        return visitService.findVisitsByDate(date);
    }


    //API to find visits by date paging
    @RequestMapping(path = "visits/search", method = RequestMethod.GET, params = {"date","page"})
    public List<Visit> getVisitsByDatePaging(@RequestParam String date, @RequestParam int page) throws Exception{
        return visitService.findVisitsByDatePaging(date,page);
    }


    //Under Construction:

    /*
    //API to find visits by date
    @RequestMapping(path = "visits/search", method = RequestMethod.GET, params = "date")
    public List<Visit> getVisitsByDate(@RequestParam("date") List<String> date) throws Exception{
        return visitService.findVisitsByDate(date);
    }

    //API to find visits by date with pages
    @RequestMapping(path = "visits/search", method = RequestMethod.GET, params = {"page","date"})
    public List<Visit> getVisitsByDatePaging(@RequestParam("date") List<String> date, @RequestParam int page) throws Exception{
        return visitService.findVisitsByDatePaging(date,page);
    }
    */

}
