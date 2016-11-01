package com.axwave.housework.transvoicesrv;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ProtocolHandler extends Thread {
	
	private static byte[] MAGIC_NUMBER = {0x12, 0x34};

	private Socket socket;
	
	private boolean shutdown = false;

	public ProtocolHandler(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		byte[] header = new byte[4];
		InputStream inputStream = socket.getInputStream();
		while(!shutdown) {
			Header = parseHeader(inputStream);
			inputStream.read(metadata);
			inputStream.read();
		}
	}
	
	public synchronized void gracefullyShutdown() {
		this.shutdown = true;
	}
}
