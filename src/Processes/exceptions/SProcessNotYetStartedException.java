package Processes.exceptions;

import Processes.ShellProcess;

public class SProcessNotYetStartedException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public SProcessNotYetStartedException(ShellProcess command){
		super("Could not wait on SProcess to terminate because it has not yet been started."
				+ "Command string is: \"" + command.getCommand() + "\"");
	}

}
