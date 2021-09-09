package main;

import Processes.ShellProcess;
import Processes.ShellProcessImpl.OS;

public class CommandModel extends ShellProcess{

	private String Command;
	private OS os; 
	
	
	public CommandModel(String command, OS os) {
		super();
		Command = command;
		this.os = os;
	}

	public void setCommand(String command) {
		Command = command;
	}

	public void setOs(OS os) {
		this.os = os;
	}

	@Override
	public String getCommand() {
		return Command;
	}

	@Override
	public OS getOSType() {
		return OS.UNIX;
	}

}
