package com.corvesta.keyspring.blueprint;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Base64;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Venkatesh on 31-Jan-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BlueprintApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ItemAuthIntegrationTests {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MockMvc mvc;

	@Autowired
	private TestRestTemplate testRestTemplate;

	private String clientBasicAuthCredentials;

	@Value("${oauth.test.url}")
	private String authUrl;

	@Before
	public void setup() {
	 this.clientBasicAuthCredentials = Base64.getEncoder().encodeToString("blueprint_client_id:blueprint_client_secret".getBytes());

	}
	@Test
	public void getItemsUnAuth() throws Exception {
		mvc.perform(get("/api/items"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void getItemsAuth() throws Exception {
		String token = obtainAccessToken("reader", "reader");
		MvcResult mvcResult = mvc.perform(get("/api/items")
				.header("Authorization", "Bearer " + token))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		logger.debug("Status : "+status);
	}

	private String obtainAccessToken(String username, String password) throws Exception {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(authUrl+"oauth/token")
				.queryParam("grant_type","password")
				.queryParam("username","reader")
				.queryParam("password","reader");
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization" ,"Basic "+clientBasicAuthCredentials);
		HttpEntity<String> entity = new HttpEntity<>(headers);
		try {
			ResponseEntity<HashMap> responseEntity = testRestTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, entity, HashMap.class);
			HashMap response = responseEntity.getBody();
			return (String) response.get("access_token");
		}catch (Exception e){
			return "Dummy Token";
		}
	}

}
