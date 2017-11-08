package pl.codeprime.webservices.rest;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeControler {
	
	@Autowired
	private Environment environment;
	
	@RequestMapping(value = "/", produces = MediaType.TEXT_HTML)
    public String home() {
		
		String prefixFormat = "<a href='http://localhost:8080/application/%s'> %s </a>";
		List<String> activeProfiles = Arrays.asList(environment.getActiveProfiles());
		
		StringBuilder sb = new StringBuilder("Nothing here.").append("Go to ->");
		if(!activeProfiles.contains("prod")) {
			
			sb.append("<br> mapping ").append(String.format(prefixFormat, "mappings", "mappings"));
			sb.append("<br> env ").append(String.format(prefixFormat, "env", "env"));
			sb.append("<br> metrics ").append(String.format(prefixFormat, "metrics", "metrics"));
			sb.append("<br> trace ").append(String.format(prefixFormat, "trace", "trace"));
		}
		
		sb.append("<br> info ").append(String.format(prefixFormat, "info","info"));
		sb.append("<br> health ").append(String.format(prefixFormat, "health","health"));
		sb.append("<br> status ").append(String.format(prefixFormat, "status","status"));
		
        return sb.toString();
    }

}
