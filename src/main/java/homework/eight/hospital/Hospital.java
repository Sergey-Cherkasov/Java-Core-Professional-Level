package homework.eight.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("Hospital")
@Scope("prototype")
public class Hospital implements IHospital {

    private IRegistry registry;

    @Autowired
    @Qualifier("Registry")
    public void setRegistry(IRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void goInHospital(Patient patient) {
        System.out.println(String.format("The %s went to the hospital", patient.getClass().getSimpleName()));
        registry.process(patient);
        System.out.println(String.format("The %s left the hospital", patient.getClass().getSimpleName()));
    }

}
