package io.garden.project.model.util;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationResponse implements Serializable {

	private static final long serialVersionUID = -608103023464153229L;
	private final String jwt;
	
}
