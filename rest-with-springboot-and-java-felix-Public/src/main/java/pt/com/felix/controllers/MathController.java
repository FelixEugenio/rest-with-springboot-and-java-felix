package pt.com.felix.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.com.felix.exceptions.UnsuportedMathOperationException;

@RestController
@RequestMapping("/math")
public class MathController {

    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double Sum(
            @PathVariable("numberOne") String numberOne,
            @PathVariable("numberTwo")   String numberTwo
    ) throws Exception {

        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsuportedMathOperationException("Please enter a numeric value");
        return  convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    private boolean isNumeric(String strNumber){
     if(strNumber == null || strNumber.isEmpty()) throw new IllegalArgumentException();
     String number = strNumber.replace(",",".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    private Double convertToDouble(String strNumber){
        if(strNumber == null || strNumber.isEmpty()) throw new UnsuportedMathOperationException("Please enter a numeric value");;
        String number = strNumber.replace(",",".");
        return Double.parseDouble(number);
    }

    @RequestMapping("/sub/{numberOne}/{numberTwo}")
    public Double Sub(
            @PathVariable("numberOne")   String numberOne,
            @PathVariable("numberTwo")   String numberTwo
    ) throws Exception {

        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsuportedMathOperationException("Please enter a numeric value");
        return  convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @RequestMapping("/mult/{numberOne}/{numberTwo}")
    public Double Multiply(
            @PathVariable("numberOne")   String numberOne,
            @PathVariable("numberTwo")   String numberTwo
    ) throws Exception {

        if(!isNumeric(numberOne) || !isNumeric(numberTwo)) throw new UnsuportedMathOperationException("Please enter a numeric value");
        return  convertToDouble(numberOne) * convertToDouble(numberTwo);
    }


}
