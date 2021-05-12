package controller;

import model.DrugPrescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.DrugPrescriptionService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
@CrossOrigin
public class DrugPrescriptionController {
    @Autowired
    private DrugPrescriptionService drugPrescriptionService;

    //Add drug prescription
    @RequestMapping(path= "drugprescription", method = RequestMethod.POST)
    public void addDrugPres(@RequestBody DrugPrescription drugPrescription){
        this.drugPrescriptionService.addDrugPres(drugPrescription);
    }

    //Get all drugs prescription
    @RequestMapping(path = "drugprescription", method = RequestMethod.GET)
    public List<DrugPrescription> getAllDrugPres(){
        return drugPrescriptionService.getAllDrugPres();
    }

    //Get all drugs prescription by page
    @RequestMapping(path = "drugprescription", method = RequestMethod.GET, params = "page")
    public List<DrugPrescription> getAllDrugPresByPage(int page){
        return drugPrescriptionService.getAllDrugPresByPage(page);
    }

    //UPDATE
    @RequestMapping(path = "drugprescription", method = RequestMethod.PUT)
    public void updateDrugPres(@RequestBody DrugPrescription drugPrescription){
        drugPrescriptionService.addDrugPres(drugPrescription);
    }

    //DELETE
    @RequestMapping(path="drugprescription/{id}", method = RequestMethod.DELETE)
    public void deleteDrugPres(@PathVariable int id){
        drugPrescriptionService.deleteDrugPres(id);
    }
}
