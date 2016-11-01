package com.axwave.housework.transvoicesrv;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
       	ServerSocket ss = new ServerSocket(13900);
       	while(true) {
       		try {
				Socket accept = ss.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       	}
    }
}
