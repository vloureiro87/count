package com.vloureiro.process;

import org.json.simple.JSONObject;

public class ProcessCountValue {

	private static Integer count = 0;

	public Integer returnCount() {
		return count++;
	}
	
	public Integer returnAtualCountValue() {
		return count;
	}

	@SuppressWarnings("unchecked")
	public String toJsonString(Integer countValue) {

		JSONObject obj = new JSONObject();
		obj.put("countValue", countValue);
		return obj.toJSONString();
	}

}
