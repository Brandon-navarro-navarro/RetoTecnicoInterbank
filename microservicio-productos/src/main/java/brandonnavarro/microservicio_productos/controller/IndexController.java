package brandonnavarro.microservicio_productos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
        @GetMapping("/")
        public String redirectToSwagger() {
            return "redirect:/swagger-ui/index.html";
        }
}
