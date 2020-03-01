package co.vinod.productservice;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AccessTokenMapper {
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private String country;
	private String userType;
	private List<String> authorities;
}