package brandonnavarro.servicio_bff.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
        @GetMapping("/")
        public String redirectToSwagger() {
            return "redirect:/swagger-ui/index.html";
        }
}
