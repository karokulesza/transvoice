package com.axwave.housework.transvoicesrv;

public class Header {
	public static final byte[] MAGIC_NUMBER = {0x12, 0x34};
	
	private byte[] header;
	
	public Header(byte[] header) {
		this.header = header;
	}
}
