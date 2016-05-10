package com.magic.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * 
 * 瀛楃涓插伐鍏峰寘
 * 
 * @author Semon
 * 
 */
public class StringUtil {

	private static String MECHINE_TAG = "1";
	static {
//		ResourceBundle dbRes = ResourceBundle.getBundle("magiclottery");
//		MECHINE_TAG = dbRes.getString("sys.mechine_tag");
		if (MECHINE_TAG.length() < 3) {
			MECHINE_TAG = "000".substring(MECHINE_TAG.length()) + MECHINE_TAG;
		}
	}

	/**
	 * 鍘嬬缉瀛楃涓诧細濡傛灉瀛楃涓茶繃闀匡紝鍒欐埅鍙栧墠闈㈤儴鍒嗗瓧绗︼紝骞朵娇鐢ㄧ壒瀹氱殑绗﹀彿濉厖鎴彇鍚庣殑灏鹃儴銆�
	 * 
	 * 渚嬪锛歛bcdefg鎴彇鎴愪负浜哸bc...
	 * 
	 * @param str
	 *            闇�瑕佸帇缂╃殑瀛楃涓�
	 * @param length
	 *            闀垮害闄愬埗
	 * @param padding
	 *            濉厖瀛楃涓�
	 * @return 鍘嬬缉缁撴灉
	 */
	public static String shortenString(String str, int length, String padding) {
		if (str.length() - padding.length() <= length)
			return str;
		else if (str.length() < padding.length())
			return str;
		else
			return str.substring(0, length - padding.length()) + padding;
	}

	public static boolean stringEquals(String src, String dest) {
		if (src != null && dest != null) {
			return src.equalsIgnoreCase(dest);
		}
		return false;
	}

	public static String getGUID() {
		String guid = UUID.randomUUID().toString().replaceAll("-", "");
		String tmp = MD5Util.md5(guid).substring(8, 24);
		String data = System.currentTimeMillis() + MECHINE_TAG + tmp;
		return data.substring(0, 32);
	}

	public static String rerverse(String str) {
		StringBuffer buffer = new StringBuffer();
		if (str == null || str.equals(""))
			return "";
		char[] tmp = str.toCharArray();
		int len = tmp.length;
		for (int i = 1; i <= len; i++) {
			buffer.append(tmp[len - i]);
		}

		return buffer.toString();
	}

	public static String getFmtString(String str, int length, char chr, boolean isFillAtTail) {
		StringBuffer sb = new StringBuffer(str + "");
		for (int i = 0; i < (length - str.length()); i++) {
			if (isFillAtTail) {
				sb.append(chr);
			} else {
				sb.insert(0, chr);
			}
		}
		return sb.toString();
	}

	public static String str2Readable(String str) {
		int i = 0;
		StringBuilder sb = new StringBuilder();
		while (i < str.length() - 4) {
			sb.append(str.substring(i, i + 4));
			sb.append(" ");
			i += 4;
		}
		sb.append(str.substring(i));
		return sb.toString();
	}

	public static int str2Int(String str, int defalutValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return defalutValue;
		}
	}

	public static boolean str2Boolean(String str, boolean defaultValue) {
		try {
			return Boolean.parseBoolean(str);
		} catch (Exception e) {
			return false;
		}
	}

	public static float str2Float(String str, float defaultValue) {
		try {
			return Float.parseFloat(str);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static String int2Amount(int amount) {
		return amount / 100 + "." + ((amount % 100) / 10) + amount % 10;
	}

	public static byte str2Byte(String str, byte defalutValue) {
		try {
			return Byte.parseByte(str);
		} catch (Exception e) {
			return defalutValue;
		}
	}

	public static boolean isNullOrEmpty(String str) {
		return str == null || "".equals(str.trim());
	}

	public static boolean isNumeric(String str) {
		if (isNullOrEmpty(str)) {
			return false;
		}
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 灏嗗紓甯稿爢鏍堜俊鎭浆鎹㈡垚瀛楃涓�;
	 * 
	 * @param t
	 *            Throwable 寮傚父瀵硅薄
	 * @return String
	 */
	public static String stackTrace2String(Throwable t) {
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw));
		String msg = sw.toString();
		try {
			sw.flush();
			sw.close();
		} catch (IOException e) {
			sw = null;
		}
		return msg;
	}

	public static String getJsonStringDatas(List<HashMap<String, String>> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		int i = 0;
		Iterator<HashMap<String, String>> itor = list.iterator();
		while (itor.hasNext()) {
			if (i > 0) {
				sb.append(",");
			}
			HashMap<String, String> hm = itor.next();
			String key = hm.keySet().iterator().next();
			String value = hm.get(key);
			sb.append("\t\n");
			sb.append("{");
			sb.append("key_id:'" + key + "'");
			sb.append(",key_name:'" + value + "'");
			sb.append("}");
			i++;
		}
		sb.append("]");
		return sb.toString();
	}

	/*public static String encapeErrorMsg(String msgCode, String msg) {
		return msgCode + SystemConstants.propSpacer + msg + SystemConstants.propSpacer;
	}

	public static String encapeErrorMsgWithFormat(String msgCode, String msg, OUT_FORMAT of) {
		if (of.equals(OUT_FORMAT.XML)) {
			return encapeErrorMsgWithXML(msgCode, msg);
		} else if (of.equals(OUT_FORMAT.JSON)) {
			return encapeErrorMsgWithJSON(msgCode, msg);
		}
		return null;
	}

	private static String encapeErrorMsgWithJSON(String msgCode, String msg) {
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.put(SystemConstants.KEY_ERROR_CODE, msgCode);
		ht.put(SystemConstants.KEY_ERROR_MSG, msg);
		return JSON.toFormatString(ht, false);
	}

	private static String encapeErrorMsgWithXML(String msgCode, String msg) {
		Hashtable<String, String> ht = new Hashtable<String, String>();
		ht.put(SystemConstants.KEY_ERROR_CODE, msgCode);
		ht.put(SystemConstants.KEY_ERROR_MSG, msg);
		return SmartXmlBuilder.toFormatString(ht, false);
	}

	public static String executeGet(String url) {
		HttpClient htpc = new HttpClient();
		GetMethod getMethod = new GetMethod(url); // 閾炬帴鐨勮矾寰勫锛歨ttp://www.baidu.com
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		try {
			// 鎵цgetMethod
			int statusCode = htpc.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				// 璇诲彇鍐呭
				byte[] responseBody = getMethod.getResponseBody();
				String result = new String(responseBody, "GBK");
				return result.trim();
			}
		} catch (Exception e) {
		} finally {
			// 閲婃斁杩炴帴
			getMethod.releaseConnection();
		}
		return null;
	}*/

	public static void main(String[] args) {

	}
}
