package homework.eight.hospital;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("Oculist")
@Scope("prototype")
public class Oculist implements IDoctor {

    @Override
    public void acceptPatient(Patient patient) {
        System.out.println(String.format("%s принял пациента", this.getClass().getSimpleName()));
    }
}
