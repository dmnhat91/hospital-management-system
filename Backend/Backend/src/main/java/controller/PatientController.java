
package controller;

import model.*;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.PatientService;

import java.util.Date;
import java.util.List;

import static java.sql.Types.NULL;

@RestController
@RequestMapping(path = "/")
@CrossOrigin
public class PatientController {

    @Autowired
    private PatientService patientService;

    //API for patients
    //READ: get all patients
    @RequestMapping(path = "patients", method = RequestMethod.GET)
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    //Get all patients paging
    @RequestMapping(path = "patients", method = RequestMethod.GET, params = "page")
    public List<Patient> getAllPatientsByPage(@RequestParam int page){
        return patientService.getAllPatientsByPage(page);
    }

    //CREATE
    @RequestMapping(path= "patients", method = RequestMethod.POST)
    public void addPatient(@RequestBody Patient patient){
        patientService.addPatient(patient);
    }
    //DELETE
    @RequestMapping(path="patients/{id}", method = RequestMethod.DELETE)
    public void deletePatient(@PathVariable int id){
        patientService.deletePatient(id);
    }
    //UPDATE
    @RequestMapping(path = "patients", method = RequestMethod.PUT)
    public void updateStudent(@RequestBody Patient patient){
        patientService.addPatient(patient);
    }

    //API to find patients by name
    @RequestMapping(path = "patients/search", method = RequestMethod.GET, params = "name")
    public List<Patient> getPatientsByName(@RequestParam String name){
        return patientService.findPatientsByName(name);
    }

    //API to find patients by name and by page
    @RequestMapping(path = "patients/search", method = RequestMethod.GET, params = {"name","page"})
    public List<Patient> getPatientsByNamePaging(@RequestParam String name, @RequestParam int page){
        return patientService.findPatientsByNamePaging(name,page);
    }

    //API to find patients by id
    @RequestMapping(path = "patients/search", method = RequestMethod.GET, params = "id")
    public List<Patient> getPatientsById(@RequestParam int id){
        return patientService.findPatientsById(id);
    }

    //API to find patients by birthdate
    @RequestMapping(path = "patients/search", method = RequestMethod.GET, params = "birthdate")
    public List<Patient> getPatientsByBd(@RequestParam String birthdate) throws Exception{
        return patientService.findPatientsByBd(birthdate);
    }

    //API to find patients by birthdate paging
    @RequestMapping(path = "patients/search", method = RequestMethod.GET, params = {"birthdate","page"})
    public List<Patient> getPatientsByBdPaging(@RequestParam String birthdate, @RequestParam int page) throws Exception{
        return patientService.findPatientsByBdPaging(birthdate, page);
    }


}
