package com.saravanan.RestApiClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class upliftQuote {
	
	public static String freeAPI_URL="https://zenquotes.io/api/random";

	public static void fetchQuote() {
	  try {
		URL url=new URL(freeAPI_URL);
		HttpURLConnection con=(HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "application/json");
		
		int resCode=con.getResponseCode();
		StringBuilder resData=new StringBuilder();
		if(resCode==200) {
			InputStreamReader inputReader=new InputStreamReader(con.getInputStream());
			BufferedReader reader=new BufferedReader(inputReader);
			String str=null;

			while((str=reader.readLine())!=null) {
				resData.append(str);
			}
			reader.close();
			con.disconnect();
		}else {
			System.out.println("Error occured while fetching Quote.Error code:"+resCode);
		    return;
		}
		JSONArray jsonArr=new JSONArray(resData.toString());
		JSONObject json=jsonArr.getJSONObject(0);
		String quote=json.getString("q");
		String author=json.getString("a");
		
		StringBuilder line = new StringBuilder();
		for (int i = 0; i < quote.length()+2; i++) line.append("-");
		
		System.out.println("+"+line.toString()+"+");
		System.out.println("\" "+quote+"\"");
		System.out.println("    -"+author);
		System.out.println("+"+line.toString()+"+");
		
	  }catch(Exception e) {
		  e.printStackTrace();
	  }	
	}
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
        String userReq = null;
        
        while(true) {
        	System.out.println("Quote!!!");
        	System.out.println("Press Y or N");
        	userReq=sc.nextLine().trim();
        	
        	if(userReq.equalsIgnoreCase("N")) break;
        	fetchQuote();
        }
        sc.close();
	}

}
