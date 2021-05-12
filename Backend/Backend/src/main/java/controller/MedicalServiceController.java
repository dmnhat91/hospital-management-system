package controller;

import model.MedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.MedicalServiceService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
@CrossOrigin
public class MedicalServiceController {

    @Autowired
    private MedicalServiceService medicalServiceService;

    //Add medical service
    @RequestMapping(path= "medservices", method = RequestMethod.POST)
    public void addMedService(@RequestBody MedicalService medicalService){
        this.medicalServiceService.addMedService(medicalService);
    }

    //Get all medical services
    @RequestMapping(path = "medservices", method = RequestMethod.GET)
    public List<MedicalService> getAllMedServices(){
        return medicalServiceService.getAllMedServices();
    }

    //Get all medical services by page
    @RequestMapping(path = "medservices", method = RequestMethod.GET, params = "page")
    public List<MedicalService> getAllMedServicesByPage(@RequestParam int page){
        return medicalServiceService.getAllMedServicesByPage(page);
    }

    //UPDATE
    @RequestMapping(path = "medservices", method = RequestMethod.PUT)
    public void updateMedService(@RequestBody MedicalService medicalService){
        medicalServiceService.addMedService(medicalService);
    }

    //DELETE
    @RequestMapping(path="medservices/{id}", method = RequestMethod.DELETE)
    public void deleteMedService(@PathVariable int id){
        medicalServiceService.deleteMedService(id);
    }

    //API to find medical services by name
    @RequestMapping(path = "medservices/search", method = RequestMethod.GET, params = "name")
    public List<MedicalService> getMedicalServicesByName(@RequestParam String name){
        return medicalServiceService.findMedServicesbyName(name);
    }

    //API to find medical services by name paging
    @RequestMapping(path = "medservices/search", method = RequestMethod.GET, params = {"name","page"})
    public List<MedicalService> getMedicalServicesByNamePaging(@RequestParam String name, @RequestParam int page){
        return medicalServiceService.findMedServicesbyNamePaging(name,page);
    }

    //API to find medical services by id
    @RequestMapping(path = "medservices/search", method = RequestMethod.GET, params = "id")
    public List<MedicalService> getMedicalServicesById(@RequestParam int id){
        return medicalServiceService.findMedicalServicesById(id);
    }

    //API to find medical services
    @RequestMapping(path = "medservices/search", method = RequestMethod.GET, params = "type")
    public List<MedicalService> getMedServicesByType(@RequestParam String type){
        return medicalServiceService.findMedServicesByType(type);
    }
}
