package pl.codeprime.RecruitmentTask;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.codeprime.common.JSONUtils;
import pl.codeprime.response.RepositoryDetailsResponseDTO;
import pl.codeprime.response.RepositoryResponseBuilder;

public class TestHelper {
	
	
	public final static String URL_FORMAT ="/repositories/%s/%s";
	public final static String EMPTY_JSON ="{}";
	
	
	public static RepositoryDetailsResponseDTO getResponseRequestGitHub(String owner, String repositoryName) {
		
		Response response = ClientBuilder.newClient().target("https://api.github.com/repos/")
		 .path(owner) 
		 .path(repositoryName)
		 .queryParam("access_token", "7a7d935e826efb31f7c3c9a490fc7f13dd9f3c3b")
		 .request()
		  .acceptEncoding("UTF8")
		  .accept(MediaType.APPLICATION_JSON)
		   .get();
		
		RepositoryDetailsResponseDTO repoDetailsResponseDTO = RepositoryResponseBuilder.create(response).build().getRepoDetailsResponseDTO();
		
		return repoDetailsResponseDTO;
	}
	
	public static void assertEqualsDTOs(RepositoryDetailsResponseDTO expectedDTO,RepositoryDetailsResponseDTO obtainedDTO ) {
    	
   	 assertEquals(obtainedDTO.getDescription(), obtainedDTO.getDescription());
        assertEquals(obtainedDTO.getCreatedAt(), obtainedDTO.getCreatedAt());
        assertEquals(obtainedDTO.getFullName(), obtainedDTO.getFullName());
        assertEquals(obtainedDTO.getStars(), obtainedDTO.getStars());
   }
	
	
	public static RepositoryDetailsResponseDTO getRepositoryDetailsResponseDTO(String json) {
    	
    	RepositoryDetailsResponseDTO convertedDto = (RepositoryDetailsResponseDTO) JSONUtils
    														.fromJson(json, RepositoryDetailsResponseDTO.class);
    	
    	return convertedDto;
    }
	
	public static void assertEqualsEmptyJSON(String entityAsJson) {
		
		String reason = String.format("Entity as Json is not empty. Value's %s", entityAsJson);
		assertEquals(reason, EMPTY_JSON, entityAsJson);
	}
	
	public static String obtainedPathUrl(String owner, String repoName) {
		return String.format(URL_FORMAT, owner, repoName);
	}

}
