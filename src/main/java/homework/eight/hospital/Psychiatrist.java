package homework.eight.hospital;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("Psychiatrist")
@Scope("singleton")
public class Psychiatrist implements IDoctor {

    @Override
    public void acceptPatient(Patient patient) {
        System.out.println(String.format("%s принял пациента", this.getClass().getSimpleName()));
    }
}
