package com.sports.limitsport.model;import android.text.TextUtils;import java.util.HashMap;import java.util.Map;public class PayResult {	private String resultStatus;	private String result;	private String memo;	private Map<String, String> rawResult;	public PayResult(String resultStatus, String result, String memo) {		this.resultStatus = resultStatus;		this.result = result;		this.memo = memo;	}	public PayResult(Map<String, String> rawResult) {		if (rawResult == null) {			return;		}		this.rawResult = rawResult;		for (String key : rawResult.keySet()) {			if (TextUtils.equals(key, "resultStatus")) {				resultStatus = rawResult.get(key);			} else if (TextUtils.equals(key, "result")) {				result = rawResult.get(key);			} else if (TextUtils.equals(key, "memo")) {				memo = rawResult.get(key);			}		}	}	@Override	public String toString() {		return "resultStatus={" + resultStatus + "};memo={" + memo				+ "};result={" + result + "}";	}	/**	 * @return the resultStatus	 */	public String getResultStatus() {		return resultStatus;	}	/**	 * @return the memo	 */	public String getMemo() {		return memo;	}	/**	 * @return the result	 */	public String getResult() {		return result;	}	public Map<String, String> getRawResult() {		return  rawResult;	}	public Map<String,String> getMap(){		Map<String,String> map = new HashMap<String,String>();		map.put("resultStatus",resultStatus);		map.put("result",result);		map.put("memo",memo);		return map;	}}