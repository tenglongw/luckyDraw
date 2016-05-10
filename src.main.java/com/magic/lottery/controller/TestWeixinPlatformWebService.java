package com.magic.lottery.controller;

import net.sf.json.JSONObject;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.magic.lottery.ws.IWeixinPlatformWSBo;
import com.magic.util.EncoderHandler;
import com.magic.util.StringUtil;


public class TestWeixinPlatformWebService {

	private static String key = null;

	static String channelCode = "500110010003";// "500128010001";
	static String mpCode = "yajiancheshi";
	static String openID = "op2kltzAho5gh1GJenKNb2UWhxEM";

	public static void main(String[] args) {

		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();

		factoryBean.setAddress("http://busi.yipaibaiwan.com:8000/busi/ws/wp");

		factoryBean.setServiceClass(IWeixinPlatformWSBo.class);

		IWeixinPlatformWSBo ws = (IWeixinPlatformWSBo) factoryBean.create();

		String initSecret = "txzw4321";// "testqd001";

		int gameCode = 201;

		boolean useXML = false;

		String mobile = "13800138111";
		String orderID = StringUtil.getGUID();

		try {
			String sign = null;
			sign = EncoderHandler.encode("SHA1", initSecret + channelCode);
			System.out.println("sign:" + sign);
			String result = ws.getSecuretKeyByID(channelCode, sign);
			System.out.println("result:" + result);
			if (useXML) {
				SAXReader reader = new SAXReader();
				Document doc = DocumentHelper.parseText(result);
				Element root = doc.getRootElement();// 鑾峰彇鏍瑰厓绱�
				Element keyElement = root.element("key");
				key = keyElement.getText().trim();
			} else {
				JSONObject json = JSONObject.fromObject(result);
				key = json.getString("key");
			}
			// key = "7dcbdef0906a456aabe190f8dca62942";
			System.out.println("key:" + key);
			String issue = "2013073";
			int type = -1;
			int page = 1;
			int pageSize = 10;

			int amount = 20;
			int num = 1;

			String body = null;
			String commSign = null;

			System.out.println("------------------------------------");
			long curTime = System.currentTimeMillis();
			body = key + mpCode + openID + channelCode + orderID + amount + num;
			System.out.println("body:" + body + ",sKey:" + key);
			commSign = EncoderHandler.encode("SHA1", body);
			String getLottery = ws.getLotteryEx(channelCode, mpCode, openID,
					amount, num, orderID, false, commSign);
			System.out.println("getLottery result:" + getLottery + ",spend:"
					+ (System.currentTimeMillis() - curTime));
			curTime = System.currentTimeMillis();

			// System.out.println("------------------------------------");
			// int totalSellAmount = 60;
			// int fromSeqNo = 9121;
			// int toSeqNo = 9126;
			// body = key + totalSellAmount + channelCode + fromSeqNo + toSeqNo;
			// commSign = EncoderHandler.encode("SHA1", body);
			// System.out.println("sellChecking body:" + body + ",commSign:" +
			// commSign);
			// String sellChecking = ws.sellChecking(channelCode, fromSeqNo,
			// toSeqNo, totalSellAmount, commSign);
			// System.out.println("sellChecking result:" + sellChecking);
			//
			// System.out.println("------------------------------------");
			// issue = "";
			// body = key + issue + channelCode + page + pageSize;
			// commSign = EncoderHandler.encode("SHA1", body);
			// System.out.println("body:" + body + ",commSign:" + commSign);
			// String getAwardInfo = ws.getAwardInfoEx(channelCode, issue, page,
			// pageSize, commSign);
			// System.out.println("getAwardInfo:" + getAwardInfo);

			// System.out.println("------------------------------------");
			// body = key + mpCode + openID + channelCode + type + page +
			// pageSize;
			// commSign = EncoderHandler.encode("SHA1", body);
			// System.out.println("body:" + body + ",commSign:" + commSign);
			// String getLotteryListByUserID = ws
			// .getLotteryListByUserID(channelCode, mpCode, openID, type,
			// page, pageSize, commSign);
			// System.out.println("getLotteryListByUserID:"
			// + getLotteryListByUserID);

			// System.out.println("------------------------------------");
			// body = key + issue + channelCode + page + pageSize;
			// commSign = EncoderHandler.encode("SHA1", body);
			// System.out.println("body:" + body + ",commSign:" + commSign);
			// String getLotteryResult = ws.getLotteryResult(channelCode, issue,
			// page, pageSize, commSign);
			// System.out.println("getLotteryResult:" + getLotteryResult);

			// System.out.println("------------------------------------");
			// body = key + mpCode + openID + channelCode + mobile;
			// commSign = EncoderHandler.encode("SHA1", body);
			// System.out.println("body:" + body + ",commSign:" + commSign);
			// String modifyMobile = ws.updateMobile(channelCode, mpCode,
			// openID,
			// mobile, commSign);
			// System.out.println("modifyMobile:" + modifyMobile);

			// System.out.println("------------------------------------");
			// String cardID = "43042619880729003X";
			// body = key + mpCode + openID + channelCode + cardID;
			// commSign = EncoderHandler.encode("SHA1", body);
			// System.out.println("body:" + body + ",commSign:" + commSign);
			// String updateUserCardID = ws.updateUserCardID(channelCode,
			// mpCode, openID, cardID, commSign);
			// System.out.println("updateUserCardID:" + updateUserCardID);

			// System.out.println("------------------------------------");
			// String encashOrderID = "096aafbe-b83a-4779-8f7b-a232ea7a46f0";
			// // StringUtil.getGUID();
			// int encashAmount = 100;
			// body = key + mpCode + openID + channelCode + encashOrderID +
			// encashAmount;
			// commSign = EncoderHandler.encode("SHA1", body);
			// System.out.println("body:" + body + ",commSign:" + commSign);
			// String encash = ws.encash(channelCode, mpCode, openID,
			// encashOrderID, encashAmount, commSign);
			// System.out.println("encash:" + encash);

			// System.out.println("------------------------------------");
			// int dayLimit = 1000 * 1000;
			// body = key + dayLimit + channelCode;
			// commSign = EncoderHandler.encode("SHA1", body);
			// System.out.println("body:" + body + ",commSign:" + commSign);
			// String setParams = ws.setParams(channelCode, dayLimit, commSign);
			// System.out.println("setParams:" + setParams);
			//
			// System.out.println("------------------------------------");
			// body = key + channelCode;
			// commSign = EncoderHandler.encode("SHA1", body);
			// System.out.println("body:" + body + ",commSign:" + commSign);
			// String getParams = ws.getParams(channelCode, commSign);
			// System.out.println("getParams:" + getParams);

			// System.out.println("------------------------------------");
			// body = key + mpCode + openID + channelCode;
			// commSign = EncoderHandler.encode("SHA1", body);
			// System.out.println("body:" + body + ",commSign:" + commSign);
			// String getAccountInfo = ws.getAccountInfo(channelCode, mpCode,
			// openID, commSign);
			// System.out.println("getAccountInfo:" + getAccountInfo);

			// body = key + channelCode;
			// commSign = EncoderHandler.encode("SHA1", body);
			// String queryChannelBalance = ws.getChannelBalance(channelCode,
			// commSign);
			// System.out.println("queryChannelBalance:" + queryChannelBalance);
			//
			// orderID = "141209120549809170728ef92b9537c9";
			// body = key + orderID + channelCode;
			// commSign = EncoderHandler.encode("SHA1", body);
			// String getEncashToBankStatus = ws.getEncashToBankStatus(
			// channelCode, orderID, commSign);
			// System.out
			// .println("getEncashToBankStatus:" + getEncashToBankStatus);
			// System.out.println("------------------------------------");
			// String name = "娴嬭瘯";
			// String bankCardNo = "6226036551105678";
			// String bankName = "鎷涘晢閾惰";
			// String bankBranchName = "杞﹀叕搴欐敮琛�";
			// orderID = StringUtil.getGUID();
			// amount = 1000;
			// body = key + mpCode + openID + mobile + channelCode + bankCardNo
			// + amount + orderID;
			// commSign = EncoderHandler.encode("SHA1", body);
			// System.out.println("body:" + body + ",commSign:" + commSign);
			// String encashToBankCard = ws.encashToBankCardEx(channelCode,
			// mpCode, openID, mobile, name, bankCardNo, bankName,
			// bankBranchName, amount, orderID, commSign);
			// System.out.println("encashToBankCard:" + encashToBankCard);
			//
			// System.out.println("------------------------------------");
			// body = key + mpCode + openID + channelCode + page + pageSize;
			// commSign = EncoderHandler.encode("SHA1", body);
			// System.out.println("body:" + body + ",commSign:" + commSign);
			// String getAccountTransLog = ws.getAccountTransLogByOpenID(
			// channelCode, mpCode, openID, page, pageSize, commSign);
			// System.out.println("getAccountTransLog:" + getAccountTransLog);

			// System.out.println("------------------------------------");
			// body = key + channelCode + gameCode;
			// System.out.println("body:" + body + ",sKey:" + key);
			// commSign = EncoderHandler.encode("SHA1", body);
			// String getCurrentIssue = ws.getCurrentIssue(channelCode, 201,
			// commSign);
			// System.out.println("getCurrentIssue:" + getCurrentIssue);

			// System.out.println("------------------------------------");
			// body = key + channelCode + gameCode + issue;
			// System.out.println("body:" + body + ",sKey:" + key);
			// commSign = EncoderHandler.encode("SHA1", body);
			// String getIssueStatus = ws.getIssueStatus(channelCode, 201,
			// issue,
			// commSign);
			// System.out.println("getIssueStatus:" + getIssueStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
