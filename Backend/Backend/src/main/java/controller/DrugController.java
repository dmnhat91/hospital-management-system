package controller;

import model.Drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.DrugService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
@CrossOrigin
public class DrugController {

    @Autowired
    private DrugService drugService;

    //Add drugs
    @RequestMapping(path= "drugs", method = RequestMethod.POST)
    public void addDrug(@RequestBody Drug drug){
        this.drugService.addDrug(drug);
    }

    //Get all drugs
    @RequestMapping(path = "drugs", method = RequestMethod.GET)
    public List<Drug> getAllDrugs(){
        return drugService.getAllDrugs();
    }

    //Get all drugs by page
    @RequestMapping(path = "drugs", method = RequestMethod.GET, params = "page")
    public List<Drug> getAllDrugsByPage(@RequestParam int page){
        return drugService.getAllDrugsByPage(page);
    }

    //UPDATE
    @RequestMapping(path = "drugs", method = RequestMethod.PUT)
    public void updateDrug(@RequestBody Drug drug){
        drugService.addDrug(drug);
    }

    //DELETE
    @RequestMapping(path="drugs/{id}", method = RequestMethod.DELETE)
    public void deleteDrug(@PathVariable int id){
        drugService.deleteDrug(id);
    }

    //API to find drugs by name
    @RequestMapping(path = "drugs/search", method = RequestMethod.GET, params = "name")
    public List<Drug> getDrugsByName(@RequestParam String name){
        return drugService.findDrugsbyName(name);
    }

    //API to find drugs by name paging
    @RequestMapping(path = "drugs/search", method = RequestMethod.GET, params = {"name","page"})
    public List<Drug> getDrugsByNamePaging(@RequestParam String name, @RequestParam int page){
        return drugService.findDrugsbyNamePaging(name,page);
    }

    //API to find drugs by id
    @RequestMapping(path = "drugs/search", method = RequestMethod.GET, params = "id")
    public List<Drug> getDrugsById(@RequestParam int id){
        return drugService.findDrugsById(id);
    }

}
