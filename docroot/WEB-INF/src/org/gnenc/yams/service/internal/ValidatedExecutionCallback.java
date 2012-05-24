package org.gnenc.yams.service.internal;

import javax.xml.bind.ValidationException;

public interface ValidatedExecutionCallback<T> extends ExecutionCallback<T> {
	
	public void validateAction(T operation) throws ValidationException;

}
