package homework.eight.hospital;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("Therapist")
@Scope("prototype")
public class Therapist implements IDoctor {

    @Override
    public void acceptPatient(Patient patient) {
        System.out.println(String.format("%s принял пациента", this.getClass().getSimpleName()));
    }
}
