package controller;

import model.ICD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ICDService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
@CrossOrigin
public class ICDController {

    @Autowired
    private ICDService icdService;

    //Add icds
    @RequestMapping(path= "icds", method = RequestMethod.POST)
    public void addICD(@RequestBody ICD icd){
        this.icdService.addICD(icd);
    }

    //Get all icds
    @RequestMapping(path = "icds", method = RequestMethod.GET)
    public List<ICD> getAllICDs(){
        return icdService.getAllICDs();
    }

    //Get all icds by page
    @RequestMapping(path = "icds", method = RequestMethod.GET, params = "page")
    public List<ICD> getAllICDsByPage(@RequestParam int page){
        return icdService.getAllICDsByPage(page);
    }

    //UPDATE
    @RequestMapping(path = "icds", method = RequestMethod.PUT)
    public void updateICD(@RequestBody ICD icd){
        icdService.addICD(icd);
    }

    //DELETE
    @RequestMapping(path="icds/{code}", method = RequestMethod.DELETE)
    public void deleteICD(@PathVariable String code){
        icdService.deleteICD(code);
    }

    //API to find icds by description
    @RequestMapping(path = "icds/search", method = RequestMethod.GET, params = "description")
    public List<ICD> getICDsByDescription(@RequestParam String description){
        return icdService.findICDsbyDescription(description);
    }

    //API to find icds by description paging
    @RequestMapping(path = "icds/search", method = RequestMethod.GET, params = {"description","page"})
    public List<ICD> getICDsByDescriptionPaging(@RequestParam String description, @RequestParam int page){
        return icdService.findICDsbyDescriptionPaging(description,page);
    }

    //API to find icds by code
    @RequestMapping(path = "icds/search", method = RequestMethod.GET, params = "code")
    public List<ICD> getICDsByCode(@RequestParam String code){
        return icdService.findICDsByCode(code);
    }

}
