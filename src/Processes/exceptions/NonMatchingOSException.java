package Processes.exceptions;

import Processes.ShellProcessImpl;

public class NonMatchingOSException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NonMatchingOSException(ShellProcessImpl.OS executorType, ShellProcessImpl.OS commandType){
		super("Mismatch between executor type (" + executorType + ") and SProcess type (" + commandType + ").");
	}

}
