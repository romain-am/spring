package main.com.dragonsoft.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketChecker {
	
	public static boolean checkIfReachable(String addr, int openPort, int timeOutMillis) {
	    // Any Open port on other machine
	    // openPort =  22 - ssh, 80 or 443 - webserver, 25 - mailserver etc.
	    try {
	        try (Socket soc = new Socket()) {
	            soc.connect(new InetSocketAddress(addr, openPort), timeOutMillis);
	        }
	        return true;
	    } catch (IOException ex) {
	        return false;
	    }
	}

}
