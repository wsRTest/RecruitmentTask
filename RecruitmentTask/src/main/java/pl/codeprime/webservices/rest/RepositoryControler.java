package pl.codeprime.webservices.rest;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.codeprime.response.GitHubRepositoryResponse;
import pl.codeprime.response.ResponseEnum;
import pl.codeprime.services.RepositoryService;


/**
 * 
 * @author MOwsians
 *
 */
@RestController
@RequestMapping("/repositories/{owner}/{repositoryName}")
public class RepositoryControler {
	
	private static final Logger LOGGER = Logger.getLogger(RepositoryControler.class);
	
	@Autowired
	private RepositoryService repositoryService;
	
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	String getRepositoryDetailsByUserAndRepositoryName(@PathVariable String owner, 
   													   @PathVariable String repositoryName){
		
		String result = null;
			
			try {

				GitHubRepositoryResponse repoResponse = repositoryService
															.getGitHubRepository(owner, repositoryName);
				
				if (!ResponseEnum.OK.equals(repoResponse.getResponseEnum())) {
					LOGGER.warn(String.format("Response Status %s [user name %s, repo name %s]", 
												repoResponse.getResponseEnum(),owner, repositoryName));
				}

				result = repoResponse.getEntityAsJson();

			} catch (Exception e) {

				LOGGER.error(e);
				result = ResponseEnum.BAD_REQUEST.buildResponse().toString();
			}
		
   		return result;
   	}

}
