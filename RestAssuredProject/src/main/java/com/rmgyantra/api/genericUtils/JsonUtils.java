package com.rmgyantra.api.genericUtils;

import io.restassured.response.Response;

public class JsonUtils {
	/**
	 * 
	 * @param resp
	 * @param jsonXpath
	 * @return
	 */
	public Object getJSonData(Response resp , String jsonXpath) {
		return resp.jsonPath().get(jsonXpath);
	}

}
