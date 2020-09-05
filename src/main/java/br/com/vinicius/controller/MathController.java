package br.com.vinicius.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.vinicius.exception.UnsuportedMathOperationException;
import br.com.vinicius.utils.NumberUtils;

@RestController
public class MathController {
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if(!NumberUtils.isNumeric(numberOne) || !NumberUtils.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
		
		Double sum = NumberUtils.convertToDouble(numberOne) + NumberUtils.convertToDouble(numberTwo);
		
		return sum;
	}
	
	@RequestMapping(value = "/sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sub(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if(!NumberUtils.isNumeric(numberOne) || !NumberUtils.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
		
		Double sum = NumberUtils.convertToDouble(numberOne) - NumberUtils.convertToDouble(numberTwo);
		
		return sum;
	}
	
	@RequestMapping(value = "/mult/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double mutl(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if(!NumberUtils.isNumeric(numberOne) || !NumberUtils.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
		
		Double sum = NumberUtils.convertToDouble(numberOne) * NumberUtils.convertToDouble(numberTwo);
		
		return sum;
	}
	
	@RequestMapping(value = "/div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double div(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if(!NumberUtils.isNumeric(numberOne) || !NumberUtils.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
		
		Double sum = NumberUtils.convertToDouble(numberOne) / NumberUtils.convertToDouble(numberTwo);
		
		return sum;
	}
	
	@RequestMapping(value = "/media/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double media(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) throws Exception {
		if(!NumberUtils.isNumeric(numberOne) || !NumberUtils.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
		
		Double sum = (NumberUtils.convertToDouble(numberOne) + NumberUtils.convertToDouble(numberTwo))/2;
		
		return sum;
	}
	
	@RequestMapping(value = "/raiz/{numberOne}", method = RequestMethod.GET)
	public Double raiz(@PathVariable("numberOne") String numberOne) throws Exception {
		if(!NumberUtils.isNumeric(numberOne)) {
			throw new UnsuportedMathOperationException("Please set a numeric value!");
		}
		
		return Math.sqrt(NumberUtils.convertToDouble(numberOne));
	}
}
