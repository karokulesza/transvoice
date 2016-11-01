package com.axwave.housework.transvoice;

import org.kohsuke.args4j.Option;

/**
 * Entry point to the client app.
 */
public class App {
	@Option(aliases = { "-s",
			"--server" }, usage = "Server to which the app should send its audio content", name = "Server hostname", required = true)
	private String serverAddress;

	@Option(aliases = { "-p",
			"--port" }, usage = "Server to which the app should send its audio content", name = "Server hostname", required = true)
	private String serverPort;

	public static void main(String[] args) {
		DataProvider provider = new MicrophoneDataProvider();
		PeriodicSender sender = new PeriodicSender(provider);
	}
}
