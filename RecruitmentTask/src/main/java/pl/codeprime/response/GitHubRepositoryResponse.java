package pl.codeprime.response;

/**
 * 
 * @author MOwsians
 *
 */
public class GitHubRepositoryResponse {
	
	private String entityAsJson;
	private ResponseEnum responseEnum;
	private RepositoryDetailsResponseDTO repoDetailsResponseDTO;
	
	protected GitHubRepositoryResponse() {
		super();
	}

	public String getEntityAsJson() {
		return entityAsJson;
	}

	public void setEntityAsJson(String entityAsJson) {
		this.entityAsJson = entityAsJson;
	}

	public ResponseEnum getResponseEnum() {
		return responseEnum;
	}

	public void setResponseEnum(ResponseEnum responseEnum) {
		this.responseEnum = responseEnum;
	}

	public RepositoryDetailsResponseDTO getRepoDetailsResponseDTO() {
		return repoDetailsResponseDTO;
	}

	public void setRepoDetailsResponseDTO(RepositoryDetailsResponseDTO repoDetailsResponseDTO) {
		this.repoDetailsResponseDTO = repoDetailsResponseDTO;
	}

	@Override
	public String toString() {
		
		StringBuilder toStringBuilder = new StringBuilder(getClass().getSimpleName());
		
		toStringBuilder.append("[")
			.append("entityAsJson=").append(entityAsJson)
			.append(", responseEnum=").append(responseEnum)
			.append(", repoDetailsResponseDTO=").append(repoDetailsResponseDTO)
		.append("]");
		
		return toStringBuilder.toString();
	}

}
