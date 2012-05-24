package org.gnenc.yams.service.internal;

public interface ExecutionCallback<T> {
	
	public void executeAction(T operation);

}
