package io.garden.project.model.util;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationRequest implements Serializable {

	private static final long serialVersionUID = -4070766724193556083L;
	private String username;
	private String password;
	
}
