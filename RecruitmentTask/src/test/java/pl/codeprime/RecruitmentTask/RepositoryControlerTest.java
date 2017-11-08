package pl.codeprime.RecruitmentTask;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.stream.Stream;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.codeprime.response.GitHubRepositoryResponse;
import pl.codeprime.response.RepositoryDetailsResponseDTO;
import pl.codeprime.services.RepositoryService;
import pl.codeprime.webservices.rest.RepositoryControler;

@RunWith(SpringRunner.class)
@RestClientTest(RepositoryControler.class)
@SpringBootTest
public class RepositoryControlerTest {

	@InjectMocks
	RepositoryControler controller;

	@Autowired
	WebApplicationContext context;
	
	@Autowired
	RepositoryService repoService;

	private MockMvc mvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
    public void shouldHaveCorrectResponse() throws Exception {
    	
    	String owner ="jsflive";
    	String repoName = "jsf22-examples";
    	
    	String obtainedUrl = TestHelper.obtainedPathUrl(owner, repoName);
        ResultActions resultActions = mvc.perform(get(obtainedUrl)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        
        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        GitHubRepositoryResponse gitHubRepository = repoService.getGitHubRepository(owner, repoName);
        
        RepositoryDetailsResponseDTO requestGitHub = TestHelper.getResponseRequestGitHub(owner, repoName);
        RepositoryDetailsResponseDTO expectedDTO = gitHubRepository.getRepoDetailsResponseDTO();
        TestHelper.assertEqualsDTOs(expectedDTO, requestGitHub);
        
        RepositoryDetailsResponseDTO obtainedDTO = 
        		TestHelper.getRepositoryDetailsResponseDTO(response.getContentAsString());
        
        TestHelper.assertEqualsDTOs(expectedDTO, obtainedDTO);
    }
	
	@Test
    public void testNullOwnerAndRepoNameShouldBeEmptyResponse() throws Exception {
        ResultActions resultActions = mvc.perform(get("/repositories/null/null")
                						 .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        
        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        TestHelper.assertEqualsEmptyJSON(response.getContentAsString());
    }
	
   // @Test
    public void requests20OneThread() throws Exception {

		Stream.of("program", "creek", "example", "name", "java", 
					"web", "response","content","soft","medium",
					"wsProgram", "wsCreek", "wsExample", "wsName", "wsJava", 
					"wsWeb", "wsResponse","wsContent","wsSoft","wsMedium")
			  .forEach(this::requestRepoWithWrongOwner);
    }

	private ResultActions requestRepoWithWrongOwner(String repoName){
		
		ResultActions resultActions  = null;
		try {
			resultActions = mvc.perform(get(String.format("/repositories/test/%s",repoName))
								.accept(MediaType.APPLICATION_JSON))
						        .andExpect(status().isOk());

			MockHttpServletResponse response = resultActions.andReturn().getResponse();
			TestHelper.assertEqualsEmptyJSON(response.getContentAsString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultActions;
	}

}
