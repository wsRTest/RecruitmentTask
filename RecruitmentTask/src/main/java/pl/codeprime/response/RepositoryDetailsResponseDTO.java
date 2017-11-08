package pl.codeprime.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.builder.EqualsBuilder;

import pl.codeprime.common.JSONUtils;
import pl.codeprime.common.StringUtilities;
import pl.codeprime.request.RepositoryDetailsTO;

/**
 * 
 * @author MOwsians
 *
 */
public class RepositoryDetailsResponseDTO {

	
	public static RepositoryDetailsResponseDTO create(RepositoryDetailsTO repositoryDetails) {
	
		
		RepositoryDetailsResponseDTO dto = new RepositoryDetailsResponseDTO(repositoryDetails.getFullName(),
																			repositoryDetails.getDescription(), 
																			repositoryDetails.getColneUrl(), 
																			repositoryDetails.getStars(),
																			repositoryDetails.getCreatedAt());
		
		return dto;
	}
	
    private String fullName;
    private String description;
    private String colneUrl;
    private Long stars;
    private String createdAt;
    
    
    
	public RepositoryDetailsResponseDTO(String fullName, String description, String colneUrl, Long stars,
			String createdAt) {
		super();
		this.fullName = fullName;
		this.description = description;
		this.colneUrl = colneUrl;
		this.stars = stars;
		
		if(StringUtilities.isNotEmpty(createdAt)) {
			createdAt = LocalDateTime.parse(createdAt, DateTimeFormatter.ISO_DATE_TIME).toString();
		}
	}
	
	public RepositoryDetailsResponseDTO() {
		super();
	}

	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getColneUrl() {
		return colneUrl;
	}
	public void setColneUrl(String colneUrl) {
		this.colneUrl = colneUrl;
	}
	public Long getStars() {
		return stars;
	}
	public void setStars(Long stars) {
		this.stars = stars;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colneUrl == null) ? 0 : colneUrl.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		result = prime * result + ((stars == null) ? 0 : stars.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		boolean equals = true;
		
		EqualsBuilder eq = new EqualsBuilder();
		if (obj == null) {
			equals = false;
		}
			
		if (getClass() != obj.getClass()) {
			equals = false;
		}
		
		if(!equals || this != obj){
			
			RepositoryDetailsResponseDTO other = (RepositoryDetailsResponseDTO) obj;
			
			eq.append(colneUrl, other.colneUrl);
			eq.append(createdAt, other.createdAt);
			eq.append(description, other.description);
			eq.append(fullName, other.fullName);
			eq.append(stars, other.stars);
			
			equals = eq.isEquals();
		}
		
		return equals;
	}

	@Override
	public String toString() {
		
		StringBuilder toStringBuiulder = new StringBuilder(getClass().getSimpleName());
		
		toStringBuiulder.append("[")
			.append("fullName=").append(fullName)
			.append(", description=").append(description)
			.append(", colneUrl=").append(colneUrl)
			.append(", stars=").append(stars)
			.append(", createdAt=").append(createdAt)
		.append("]");
		
		return toStringBuiulder.toString();
	}
	
	/**
	 * Convert to json.
	 * @return json as string.
	 */
	public String toJSON() {
		
		String json = JSONUtils.toJson(this);
		
		return json;
	}
    
}
