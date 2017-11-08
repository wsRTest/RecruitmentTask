package pl.codeprime.RecruitmentTask;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import pl.codeprime.response.GitHubRepositoryResponse;
import pl.codeprime.response.RepositoryDetailsResponseDTO;
import pl.codeprime.services.RepositoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryServiceTest {
	
	
	@Autowired
	RepositoryService repoService;

	@Test
    public void shouldBeSameResponses() throws Exception {
    	
    	String owner ="jsflive";
    	String repoName = "jsf22-examples";
        
        GitHubRepositoryResponse gitHubRepository = repoService.getGitHubRepository(owner, repoName);
        
        RepositoryDetailsResponseDTO requestGitHub = TestHelper.getResponseRequestGitHub(owner, repoName);
        RepositoryDetailsResponseDTO expectedDTO = gitHubRepository.getRepoDetailsResponseDTO();
        
        TestHelper.assertEqualsDTOs(expectedDTO, requestGitHub);
    }
	
	@Test(expected = NullPointerException.class) 
    public void shouldBeNPE()  {
    	
    	String owner ="ffay";
    	String repoName = "lanproxy";
        
        GitHubRepositoryResponse gitHubRepository = repoService.getGitHubRepository(owner, repoName);
        RepositoryDetailsResponseDTO requestGitHub = TestHelper.getResponseRequestGitHub(null, null);
        RepositoryDetailsResponseDTO expectedDTO = gitHubRepository.getRepoDetailsResponseDTO();
        
        TestHelper.assertEqualsDTOs(expectedDTO, requestGitHub);
    }
	
	@Test
    public void shouldBeEmptyDTO()  {
    	
        GitHubRepositoryResponse gitHubRepository = repoService.getGitHubRepository(null, null);
        assertNotNull(gitHubRepository);
        
        TestHelper.assertEqualsEmptyJSON(gitHubRepository.getEntityAsJson());
        RepositoryDetailsResponseDTO repoDetailsResponseDTO = gitHubRepository.getRepoDetailsResponseDTO();
        assertNotNull(repoDetailsResponseDTO);
        
        String format = "Value is not empty. Value  %s is %s";
        assertEquals(String.format(format, "colneUrl",repoDetailsResponseDTO.getColneUrl()),null, repoDetailsResponseDTO.getColneUrl());
        assertEquals(String.format(format, "createdAt",repoDetailsResponseDTO.getCreatedAt()),null, repoDetailsResponseDTO.getCreatedAt());
        assertEquals(String.format(format, "Description",repoDetailsResponseDTO.getDescription()),null, repoDetailsResponseDTO.getDescription());
        assertEquals(String.format(format, "FullName",repoDetailsResponseDTO.getFullName()),null, repoDetailsResponseDTO.getFullName());
        
        String reasonStars = String.format("stargazers_count is not empty cuz stas's %d", repoDetailsResponseDTO.getStars());
        assertNull(reasonStars,repoDetailsResponseDTO.getStars());
    }

}
