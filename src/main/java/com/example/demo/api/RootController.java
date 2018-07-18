package com.example.demo.api;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sun.deploy.net.HttpResponse;

/**
 * @author eguzman (2018.07.11 4:29 PM)
 */
@RestController
public class RootController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void redirectToApiDocumentation(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");

    }
}
