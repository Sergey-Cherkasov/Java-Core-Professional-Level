package homework.eight.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("Registry")
@Scope("singleton")
public class Registry implements IRegistry {

    List<IDoctor> listDoctors;

    private IDoctor therapist;
    private IDoctor narcology;
    private IDoctor psychiatrist;
    private IDoctor oculist;

    public Registry() {
        this.listDoctors = new ArrayList<>();
//        listDoctors.add(therapist);
//        listDoctors.add(narcology);
//        listDoctors.add(psychiatrist);
//        listDoctors.add(oculist);
    }

    @Autowired
    @Qualifier("Therapist")
    public void setTherapist(IDoctor doctor) {
        this.therapist = doctor;
    }

    @Autowired
    @Qualifier("Narcology")
    public void setNarcology(IDoctor doctor) {
        this.narcology = doctor;
    }

    @Autowired
    @Qualifier("Psychiatrist")
    public void setPsychiatrist(IDoctor doctor) {
        this.psychiatrist = doctor;
    }

    @Autowired
    @Qualifier("Oculist")
    public void setOculist(IDoctor doctor) {
        this.oculist = doctor;
    }

    public void setListDoctors() {
        listDoctors.add(therapist);
        listDoctors.add(narcology);
        listDoctors.add(psychiatrist);
        listDoctors.add(oculist);
    }

    public void process(Patient patient) {
        setListDoctors();
        System.out.println(String.format("%s go to the registry", patient.getClass().getSimpleName()));
        System.out.println(String.format("%s got a list of doctors: %s", patient.getClass().getSimpleName(), listDoctors));
        System.out.println("Patient go to the doctors");
        for (IDoctor doctor : listDoctors) {
            doctor.acceptPatient(patient);
        }
    }

}
