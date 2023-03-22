package com.mini.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class IpCheck {
	private static String ipAddr;
	public static String getIpAddr() throws IOException{
		URL ipCheckURL= new URL("https://checkip.amazonaws.com/");
		
		
		BufferedReader br=	new BufferedReader(new InputStreamReader(ipCheckURL.openStream()));
		ipAddr=	br.readLine(); // 한줄식 읽도록
	return ipAddr;
	
	}
}
