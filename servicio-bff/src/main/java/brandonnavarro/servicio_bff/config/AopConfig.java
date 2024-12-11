package brandonnavarro.servicio_bff.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.stereotype.Component;


import brandonnavarro.servicio_bff.service.BffService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Aspect
@Component
public class AopConfig {
    
    @After("execution(* brandonnavarro.servicio_bff.service.BffService.*(..)) && !execution(* brandonnavarro.servicio_bff.service.BffService.getTrackingId(..)) && this(service)")
    public void logTrackingId(JoinPoint joinPoint, BffService service) {
        try {
            String trackingId = service.getTrackingId();
        log.info("El Tracking-ID de la solicitud es: {}", trackingId);
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}
