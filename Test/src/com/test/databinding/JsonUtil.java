package com.test.databinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {


	/*
	 * This method is to retrieve the data from json based on suite name and results 
	 */
	public static List retriveJsonData(String suiteName, String resultFromJson) throws JSONException, IOException{
		String file = System.getProperty("user.dir")+"\\testdata.json";
		JSONObject jsonObject = new JSONObject(new String(Files.readAllBytes(Paths.get(file))));
		List<String> list = new ArrayList<String>();
		JSONArray jsonArray = jsonObject.getJSONArray("test_suites");
		for(int i=0;i<jsonArray.length();i++){
				if(suiteName.contains(jsonArray.getJSONObject(i).getString("suite_name"))){
					JSONObject results = jsonArray.getJSONObject(i);
					JSONArray jsonArray2 = results.getJSONArray("results");
					for (int j=0 ;j<jsonArray2.length();j++)
					{
						list.add(jsonArray2.getJSONObject(j).getString(resultFromJson));
					}
				}
			}
		return list;
	}
	
	/*
	 * this method is to retrieve the data and store it in a list format 
	 */
	
	public static List getSuite(String suiteName, String resultToFetch) throws JSONException, IOException{
		return retriveJsonData(suiteName, resultToFetch);
	}
	
	public static void getcount(List statusList){
		int Pass = 0, Fail = 0, Blocked = 0;
		for(int i=0;i<statusList.size();i++){
			if(statusList.get(i).equals("pass"))
				Pass++;
			else if(statusList.get(i).equals("fail"))
				Fail++;
			else if(statusList.get(i).equals("blocked"))
				Blocked++;
		}
		System.out.println("Pass count : "+ Pass++);
		System.out.println("Fail count : "+ Fail++);
		System.out.println("Blocked count : "+ Blocked++);
		
	}
	
	
	/*
	 * To get the count of time greater  than 10 Seconds 
	 */
	
	public static void getTestExecutedMoreThanSpecifiedTime(List timeList, int time){
		int j=0;
		 timeList.removeIf(s -> s == null || "".equals(s));
		for(int i=0;i<timeList.size();i++){
			if(Math.round(Float.parseFloat(timeList.get(i).toString()))>time ){
				j++;
			}
		}
		System.out.println("Count of Results greater than " + time + " is " +  j);
	}
	
	
	/*
	 * To get the complete details of the suite 
	 */
	
	public void getSuiteDetails(String suiteName) throws JSONException, IOException{
		System.out.println("Suite Name : "+ suiteName);
		getcount(getSuite(suiteName, "status"));
		getTestExecutedMoreThanSpecifiedTime(getSuite(suiteName, "time"), 10);
	}
}