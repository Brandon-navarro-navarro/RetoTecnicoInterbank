package brandonnavarro.microservicio_productos.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Aspect
@Component
public class AopConfig {
    
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerMethods() {}

    @Before("controllerMethods()")
    public void logHeader(JoinPoint joinPoint) {
        // Obtener el request actual
        ServletRequestAttributes attributes = 
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            
            String trackingId = request.getHeader("Tracking-ID");

            log.info("El Tracking-ID de la solicitud es: {}", trackingId);
        } else {
            log.warn("No se pudo obtener el objeto HttpServletRequest.");
        }
    }
}
