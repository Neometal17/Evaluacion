package pe.com.user.administrator.infrastructure.adapter.in;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventConsumer {

    @RabbitListener
    public void receiveMessage(String message){
        System.out.println("Mensaje recibido: " + message);
    }
}
