package com.magic.lottery.controller;

import net.sf.json.JSONObject;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.magic.lottery.ws.ISelfServiceMWSBo;
import com.magic.util.EncoderHandler;
import com.magic.util.StringUtil;

public class TestSelfServiceMWebService {

	private static String key = null;

	static String channelCode = "500125551001";

	public static void main(String[] args) {
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();

		factoryBean.setAddress("http://busi.yipaibaiwan.com:8000/busi/ws/ssm");

		factoryBean.setServiceClass(ISelfServiceMWSBo.class);

		ISelfServiceMWSBo ws = (ISelfServiceMWSBo) factoryBean.create();

		String initSecret = "selftest";

		int gameCode = 201;

		boolean useXML = false;

		String userID = "15914149430";
		String orderID = StringUtil.getGUID();

		try {
			String sign = EncoderHandler
					.encode("MD5", initSecret + channelCode);
			System.out.println("sign:" + sign);
			String result = ws.getSecuretKeyByID(channelCode, sign);
			System.out.println("result:" + result);
			if (useXML) {
				Document doc = DocumentHelper.parseText(result);
				Element root = doc.getRootElement();// 获取根元素
				Element keyElement = root.element("key");
				key = keyElement.getText().trim();
			} else {
				JSONObject json = JSONObject.fromObject(result);
				key = json.getString("key");
			}
			System.out.println("key:" + key);
			String issue = "";
			int type = -1;
			int page = 1;
			int pageSize = 50;

			int amount = 200;
			int num = 1;

			String body = null;
			String commSign = null;

			//
			// System.out.println("------------------------------------");
			// body = key + issue + channelCode + page + pageSize;
			// commSign = EncoderHandler.encode("MD5", body);
			// System.out.println("body:" + body + ",commSign:" + commSign);
			// String getAwardInfo = ws.getAwardInfoEx(channelCode, issue, page,
			// pageSize, commSign);
			// System.out.println("getAwardInfo:" + getAwardInfo);
			// //
			// System.out.println("------------------------------------");
			// body = key + userID + channelCode + type + page + pageSize;
			// commSign = EncoderHandler.encode("MD5", body);
			// System.out.println("body:" + body + ",commSign:" + commSign);
			// String getLotteryListByUserID = ws.getLotteryListByUserID(
			// channelCode, userID, type, page, pageSize, commSign);
			// System.out.println("getLotteryListByUserID:"
			// + getLotteryListByUserID);

			int i = 1;
			while (i > 0) {
				orderID = StringUtil.getGUID();
				num = 5;
				System.out.println("------------------------------------");
				System.out.println("User:" + userID);
				body = key + userID + channelCode + orderID + amount + num;
				System.out.println("body:" + body + ",sKey:" + key);
				commSign = EncoderHandler.encode("MD5", body);
				String getLottery = ws.getLotteryEx(channelCode, userID,
						amount, num, orderID, false, commSign);
				System.out.println("getLottery:" + getLottery);
				i--;
				Thread.sleep(1);
			}

			// String queryChannelBalance = ws.getChannelBalance(channelCode,
			// EncoderHandler.encode("MD5", key + channelCode));
			// System.out.println("queryChannelBalance:" + queryChannelBalance);
			//
			// String getEncashToBankStatus = ws
			// .getEncashToBankStatus(
			// channelCode,
			// "141209120549809170728ef92b9537c9",
			// EncoderHandler.encode("MD5", key
			// + "141209120549809170728ef92b9537c9"
			// + channelCode));
			// System.out
			// .println("getEncashToBankStatus:" + getEncashToBankStatus);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
