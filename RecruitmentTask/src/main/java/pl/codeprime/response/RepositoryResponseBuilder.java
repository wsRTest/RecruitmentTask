package pl.codeprime.response;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import pl.codeprime.common.JSONUtils;
import pl.codeprime.request.RepositoryDetailsTO;

/**
 * 
 * @author MOwsians
 *
 */
public class RepositoryResponseBuilder {
	
	private GitHubRepositoryResponse repositoryResponse;
	private String entityAsJson;
	private Status status;
	
	public static RepositoryResponseBuilder create(Response response) {
		
		RepositoryResponseBuilder builder = new RepositoryResponseBuilder(response)
													.addStatusCode()
													.addEntity();
		
		return builder;
	}
	
	private RepositoryResponseBuilder(Response response) {
		
		status = response.getStatusInfo().toEnum();
		entityAsJson = ResponseEnum.isOK(status) ? response.readEntity(String.class) : "";
		
		repositoryResponse = new GitHubRepositoryResponse();
	}

	
	public RepositoryResponseBuilder addStatusCode() {
		
		ResponseEnum responseByStatus = ResponseEnum.getResponseByStatus(status);
		repositoryResponse.setResponseEnum(responseByStatus);
		
		return this;
	}
	
	public RepositoryResponseBuilder addEntity() {

		RepositoryDetailsTO repositoryDetails = null;
		RepositoryDetailsResponseDTO repoDetailsResponseDTO = null;
		if(ResponseEnum.isOK(status)) {
			
			repositoryDetails = (RepositoryDetailsTO) JSONUtils.fromJson(entityAsJson,RepositoryDetailsTO.class);
			repoDetailsResponseDTO = RepositoryDetailsResponseDTO.create(repositoryDetails);
		}
		else {
			
			repositoryDetails = new RepositoryDetailsTO();
			repoDetailsResponseDTO = new RepositoryDetailsResponseDTO();
		}

		repositoryResponse.setRepoDetailsResponseDTO(repoDetailsResponseDTO);
		repositoryResponse.setEntityAsJson(repoDetailsResponseDTO.toJSON());

		return this;
	}
	
	public GitHubRepositoryResponse build() {
		return repositoryResponse;
	}

}
