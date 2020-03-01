package co.vinod.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler
	public String handleException(Exception ex, Model model) {
		log.error("Got an exception at {}", new Date());
		StringWriter so = new StringWriter();
		ex.printStackTrace(new PrintWriter(so));
		
		model.addAttribute("stackTrace", so.toString());
		return "error"; // /WEB-INF/views/error.jsp
	}

}
