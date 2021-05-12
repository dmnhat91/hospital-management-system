package controller;

import model.LabTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.LabTestService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
@CrossOrigin
public class LabTestController {
    @Autowired
    private LabTestService labTestService;

    //Add lab test
    @RequestMapping(path= "labtests", method = RequestMethod.POST)
    public void addLabTest(@RequestBody LabTest labTest){
        this.labTestService.addLabTest(labTest);
    }

    //Get all lab tests
    @RequestMapping(path = "labtests", method = RequestMethod.GET)
    public List<LabTest> getAllLabTests(){
        return labTestService.getAllLabTests();
    }

    //Get all lab tests by page
    @RequestMapping(path = "labtests", method = RequestMethod.GET, params = "page")
    public List<LabTest> getAllLabTestsByPage(int page){
        return labTestService.getAllLabTestByPage(page);
    }

    //UPDATE
    @RequestMapping(path = "labtests", method = RequestMethod.PUT)
    public void updateLabTest(@RequestBody LabTest labTest){
        labTestService.addLabTest(labTest);
    }

    //DELETE
    @RequestMapping(path="labtests/{id}", method = RequestMethod.DELETE)
    public void deleteLabTest(@PathVariable int id){
        labTestService.deleteLabTest(id);
    }
}
