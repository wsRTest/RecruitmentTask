package pl.codeprime.request;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepositoryDetailsTO implements Serializable{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -9196689562213333863L;

	@SerializedName("full_name")
	@Expose(serialize = true, deserialize = false)
    private String fullName;
    
    private String description;
    
    @SerializedName("clone_url")
    @Expose(serialize = true, deserialize = false)
    private String colneUrl;
    
    @SerializedName(value="stargazers_count", alternate={"starts"})
    private Long stars;
    
    @SerializedName("created_at")
    @Expose(serialize = true, deserialize = false)
    private String createdAt;

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

}
