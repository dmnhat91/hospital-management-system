import config.AppConfig;
import model.Drug;
import model.Patient;
import model.Prescription;
import model.Visit;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Time;
import java.util.Date;
//import service.StudentService;
import service.*;

/**
 * Created by CoT on 7/29/18.
 */
public class Main {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);

        context.refresh();

        PatientService patientService = context.getBean(PatientService.class);
        DrugService drugService = context.getBean(DrugService.class);
        VisitService visitService = context.getBean(VisitService.class);
        PrescriptionService prescriptionService = context.getBean(PrescriptionService.class);
        DrugPrescriptionService drugPrescriptionService = context.getBean(DrugPrescriptionService.class);

        //System.out.println(studentService.findStudents("Student"));
        //System.out.println(studentService.getAllStudents());
    }
}
