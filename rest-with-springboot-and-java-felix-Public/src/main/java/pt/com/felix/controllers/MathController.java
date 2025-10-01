package pt.com.felix.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double Sum(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo")   String numberTwo
    ) throws Exception {

        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new IllegalArgumentException();
        return  1D;
    }

    private boolean isNumeric(String numberOne){
     return false;

    }


}
