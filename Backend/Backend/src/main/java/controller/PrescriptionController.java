package controller;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.PrescriptionService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
@CrossOrigin
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    //API for prescription

    //Add new prescription
    @RequestMapping(path= "prescriptions", method = RequestMethod.POST)
    public void addPrescription(@RequestBody Prescription prescription){
        this.prescriptionService.addPrescription(prescription);
    }

    //READ: Get all prescriptions
    @RequestMapping(path = "prescriptions", method = RequestMethod.GET)
    public List<Prescription> getAllPrescriptions(){
        return prescriptionService.getAllPrescriptions();
    }

    //Get all prescriptions by page
    @RequestMapping(path = "prescriptions", method = RequestMethod.GET, params = "page")
    public List<Prescription> getAllPrescriptionsByPage(@RequestParam int page){
        return prescriptionService.getAllPrescriptionsByPage(page);
    }

    @RequestMapping(path = "prescriptions/search", method = RequestMethod.GET, params = "id")
    public List<Prescription> getPrescriptionsById(@RequestParam int id){
        return prescriptionService.findPrescriptionsById(id);
    }

    //Get prescriptions by visit id
    @RequestMapping(path = "prescriptions/search", method = RequestMethod.GET, params = "visit")
    public List<Prescription> getPrescriptionsByVisitId(@RequestParam int visit){
        return prescriptionService.findPrescriptionsByVisitId(visit);
    }

    //Get Drugs by prescription id
    @RequestMapping(path = "drugs/search", method = RequestMethod.GET, params = "prescription")
    public List<Drug> getDrugsByPrescriptionId(@RequestParam int prescription){
        return prescriptionService.findDrugsByPrescriptionId(prescription);
    }

    //Get ICDs by prescription id
    @RequestMapping(path = "icds/search", method = RequestMethod.GET, params = "prescription")
    public List<ICD> getICDsByPrescriptionId(@RequestParam int prescription){
        return prescriptionService.findICDsByPrescriptionId(prescription);
    }

    //Get med services by prescription id
    @RequestMapping(path = "medservices/search", method = RequestMethod.GET, params = "prescription")
    public List<MedicalService> getMedServicesByPrescriptionId(@RequestParam int prescription){
        return prescriptionService.findMedServicesByPrescriptionId(prescription);
    }

    //Get drug prescriptions by prescription id
    @RequestMapping(path = "drugprescriptions/search", method = RequestMethod.GET, params = "prescription")
    public List<DrugPrescription> getDrugPrescriptionsByPrescriptionId(@RequestParam int prescription){
        return prescriptionService.findDrugPresByPrescriptionId(prescription);
    }

    //Get icd lists by prescription id
    @RequestMapping(path = "icdlists/search", method = RequestMethod.GET, params = "prescription")
    public List<ICDList> getIcdListByPrescriptionId(@RequestParam int prescription){
        return prescriptionService.findIcdListByPrescriptionId(prescription);
    }

    //Get lab test by prescription id
    @RequestMapping(path = "labtests/search", method = RequestMethod.GET, params = "prescription")
    public List<LabTest> getLabTestsByPrescriptionId(@RequestParam int prescription){
        return prescriptionService.findLabTestByPrescriptionId(prescription);
    }


    //UPDATE
    @RequestMapping(path = "prescriptions", method = RequestMethod.PUT)
    public void updateVisit(@RequestBody Prescription prescription){
        prescriptionService.addPrescription(prescription);
    }

    //DELETE
    @RequestMapping(path="prescriptions/{id}", method = RequestMethod.DELETE)
    public void deletePrescription(@PathVariable int id){
        prescriptionService.deletePrescription(id);
    }
}
