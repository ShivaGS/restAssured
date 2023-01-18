package com.test;

import java.io.IOException;

import org.json.JSONException;


import com.test.databinding.JsonUtil;

public class Executable {
	
	public static void main(String[] args) throws JSONException, IOException {
		JsonUtil jsonUtil = new JsonUtil();
		jsonUtil.getSuiteDetails("temp_setting");
	}

}
