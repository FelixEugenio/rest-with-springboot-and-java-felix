package pt.com.felix.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestLogController {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TestLogController.class);

    @GetMapping("/test")
    public String testLog(){
        logger.info("this is a test log");
        logger.debug("this is a debug log");
        logger.error("this is an error log");
        logger.warn("this is a warn log");
        logger.trace("this is a trace log");
        return "test log generated successfully";
    }
}
