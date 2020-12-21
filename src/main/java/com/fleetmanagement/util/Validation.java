package com.fleetmanagement.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class Validation {
	
	
	public Boolean isEmpty(String input) {
		return StringUtils.isEmpty(input.trim()) ? true : false;
	}
	
	
	
}
