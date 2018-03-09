package com.corvesta.keyspring.blueprint.config.security;

import java.util.Collections;
import java.util.Set;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;


@ConfigurationProperties(prefix = "keycloak-client")
public class KeycloakClientProperties {
	private String id;

	private String secret;

	private String name;

	private Set<String> scopes = Collections.emptySet();

	private String serverUrl;

	private String realm;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getScopes() {
		return scopes;
	}

	public void setScopes(Set<String> scopes) {
		this.scopes = scopes;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	/**
	 * And this is the only interesting part here. The keycloak realm is transformed
	 * to a so called ClientRegistration. ClientRegistrations are used by Spring
	 * Security 5 to define different OAuth providers
	 * 
	 * @return
	 */
	public ClientRegistration asClientRegistration() {
		final String openIdConnectBaseUri = this.serverUrl + "/realms/" + this.realm + "/protocol/openid-connect";
		return ClientRegistration.withRegistrationId(this.realm).clientId(this.id).clientSecret(this.secret)
				.clientName(this.name).clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.redirectUriTemplate("{baseUrl}/login/oauth2/code/{registrationId}")
				.scope(this.scopes.toArray(new String[0])).authorizationUri(openIdConnectBaseUri + "/auth")
				.tokenUri(openIdConnectBaseUri + "/token").jwkSetUri(openIdConnectBaseUri + "/certs")
				.userInfoUri(openIdConnectBaseUri + "/userinfo")
				// Use a sane username from the JWT
				.userNameAttributeName("preferred_username").build();
	}
}



