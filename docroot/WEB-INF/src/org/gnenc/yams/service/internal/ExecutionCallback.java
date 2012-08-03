package org.gnenc.yams.service.internal;

import javax.xml.bind.ValidationException;

public interface ExecutionCallback<T> {

	public void executeAction(T operation) ;

}