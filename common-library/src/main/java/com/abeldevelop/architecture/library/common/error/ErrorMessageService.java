package com.abeldevelop.architecture.library.common.error;

import java.util.Optional;

public interface ErrorMessageService {

	public Optional<String> getMessage(String code);
	
}
