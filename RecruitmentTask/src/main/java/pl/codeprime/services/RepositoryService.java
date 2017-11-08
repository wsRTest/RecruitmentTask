package pl.codeprime.services;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pl.codeprime.common.StringUtilities;
import pl.codeprime.response.GitHubRepositoryResponse;
import pl.codeprime.response.RepositoryResponseBuilder;
import pl.codeprime.response.ResponseEnum;

@Service
public class RepositoryService {

	@Value("${git-hub-repo-api}")
	private String GIT_HUB_REPO_API_URL ;
	
	@Value("${git-hub-access-token}")
	private String GIT_HUB_ACCESS_TOKEN;
	private final Client gitHubClient =  ClientBuilder.newClient();
	
	public GitHubRepositoryResponse getGitHubRepository(String owner, String repositoryName) {
		
		Response response = null;
		
		if(StringUtilities.isNotEmpty(owner, repositoryName)) {
			
			response = gitHubClient.target(GIT_HUB_REPO_API_URL)
									 .path(owner) 
									 .path(repositoryName)
									 .queryParam("access_token", GIT_HUB_ACCESS_TOKEN)
									 .request()
									  .acceptEncoding("UTF8")
									  .accept(MediaType.APPLICATION_JSON)
									   .get();
		}
		else {
			response = ResponseEnum.BAD_REQUEST.buildResponse();
		}
		
		GitHubRepositoryResponse repoResponse = RepositoryResponseBuilder.create(response).build();
		
		return repoResponse;
	}
}
