package co.vinod.productservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.stereotype.Component;

@Component
public class JwtConverter extends DefaultAccessTokenConverter implements JwtAccessTokenConverterConfigurer {

	@Override
	public void configure(JwtAccessTokenConverter converter) {
		converter.setAccessTokenConverter(this);
	}

	@Override
	public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
		OAuth2Authentication auth = super.extractAuthentication(map);
		AccessTokenMapper details = new AccessTokenMapper();
		if (map.get("id") != null)
			details.setId((Integer) map.get("id"));
		if (map.get("firstName") != null)
			details.setFirstName((String) map.get("firstName"));
		if (map.get("lastName") != null)
			details.setLastName((String) map.get("lastName"));
		if (map.get("country") != null)
			details.setCountry((String) map.get("country"));
		if (map.get("mobile") != null)
			details.setMobile((String) map.get("mobile"));
		if (map.get("email") != null)
			details.setEmail((String) map.get("email"));
		if (map.get("userType") != null)
			details.setUserType((String) map.get("userType"));

		if (auth.getAuthorities() != null && auth.getAuthorities().isEmpty() == false) {
			List<String> authorities = new ArrayList<String>();
			for (GrantedAuthority authority : auth.getAuthorities()) {
				authorities.add(authority.getAuthority());
			}
			details.setAuthorities(authorities);
		}
		auth.setDetails(details);
		return auth;
	}

}

