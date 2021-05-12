package controller;

import model.ICDList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ICDListService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
@CrossOrigin
public class ICDListController {
    @Autowired
    private ICDListService icdListService;

    //Add icd list
    @RequestMapping(path= "icdlists", method = RequestMethod.POST)
    public void addICDList(@RequestBody ICDList icdList){
        this.icdListService.addICDList(icdList);
    }

    //Get all icd lists
    @RequestMapping(path = "icdlists", method = RequestMethod.GET)
    public List<ICDList> getAllICDLists(){
        return icdListService.getAllICDLists();
    }

    //Get all icd lists by page
    @RequestMapping(path = "icdlists", method = RequestMethod.GET, params = "page")
    public List<ICDList> getAllICDListsByPage(int page){
        return icdListService.getAllICDListsByPage(page);
    }

    //UPDATE
    @RequestMapping(path = "icdlists", method = RequestMethod.PUT)
    public void updateICDList(@RequestBody ICDList icdList){
        icdListService.addICDList(icdList);
    }

    //DELETE
    @RequestMapping(path="icdlists/{id}", method = RequestMethod.DELETE)
    public void deleteICDList(@PathVariable int id){
        icdListService.deleteICDList(id);
    }
}
