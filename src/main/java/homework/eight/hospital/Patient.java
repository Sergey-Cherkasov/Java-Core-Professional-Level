package homework.eight.hospital;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Patient {

    public static void main(String[] args) {

        Patient patient = new Patient();

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        IHospital hospital = context.getBean("Hospital", IHospital.class);
        hospital.goInHospital(patient);

    }

}
