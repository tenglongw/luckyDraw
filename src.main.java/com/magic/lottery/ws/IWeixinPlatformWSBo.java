package com.magic.lottery.ws;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * 所有方法的调用结果以XML格式返回
 * 
 * @see WebService接口文档.doc
 * 
 * 
 * @author sunxf
 * 
 */
@WebService
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE, use = SOAPBinding.Use.ENCODED, style = SOAPBinding.Style.RPC)
public interface IWeixinPlatformWSBo {

	/**
	 * 1.获取通讯密钥
	 * 
	 * @param id
	 *            渠道ID
	 * @param sign
	 *            签名（对signKey+id进行SHA1加密）
	 * @return
	 */
	public String getSecuretKeyByID(@WebParam(name = "id") String id,
			@WebParam(name = "sign") String sign);

	/**
	 * 2.获取零钱彩票
	 * 
	 * @param id
	 *            渠道ID
	 * @param mpCode
	 *            微信公众号
	 * @param openID
	 *            微信openID
	 * @param amount
	 *            赠送金额（单位：分）
	 * @param num
	 *            赠送数量
	 * @param orderID
	 *            订单号
	 * @param usePrize
	 *            是否使用用户奖金赠彩
	 * @param sign
	 *            签名（对SecuretKey+mpCode+openID+id+orderID+amount+
	 *            num组成的字串进行SHA1加密）
	 * @return
	 */
	public String getLotteryEx(@WebParam(name = "id") String id,
			@WebParam(name = "mpCode") String mpCode,
			@WebParam(name = "openID") String openID,
			@WebParam(name = "amount") int amount,
			@WebParam(name = "num") int num,
			@WebParam(name = "orderID") String orderID,
			@WebParam(name = "usePrize") boolean usePrize,
			@WebParam(name = "sign") String sign);

	/**
	 * 3.设置或修改用户手机号码
	 * 
	 * @param id
	 *            渠道ID
	 * @param mpCode
	 *            微信公众号
	 * @param openID
	 *            微信openID
	 * @param mobile
	 *            用户新手机号码
	 * @param sign
	 *            签名 （对SecuretKey+mpCode+openID+id+mobile组成的字串进行SHA1加密）
	 * @return
	 */
	public String updateMobile(@WebParam(name = "id") String id,
			@WebParam(name = "mpCode") String mpCode,
			@WebParam(name = "openID") String openID,
			@WebParam(name = "mobile") String mobile,
			@WebParam(name = "sign") String sign);

	/**
	 * 4.更新用户身份证号
	 * 
	 * @param id
	 *            渠道ID
	 * @param mpCode
	 *            微信公众号
	 * @param openID
	 *            微信openID
	 * @param cardID
	 *            用户身份证号
	 * @param sign
	 *            签名（对SecuretKey+mpCode+openID+id+cardID组成的字串进行SHA1加密）
	 * @return
	 */
	public String updateUserCardID(@WebParam(name = "id") String id,
			@WebParam(name = "mpCode") String mpCode,
			@WebParam(name = "openID") String openID,
			@WebParam(name = "cardID") String cardID,
			@WebParam(name = "sign") String sign);

	/**
	 * 5.销售对账
	 * 
	 * @param id
	 *            渠道ID
	 * @param fromSeqNo
	 *            开始循序号
	 * @param toSeqNo
	 *            结束顺序号
	 * @param amount
	 *            销售总额
	 * @param sign
	 *            签名（对SecuretKey+amount+id+fromSeqNo+toSeqNo组成的字串进行SHA1加密）
	 * @return
	 */
	public String sellChecking(@WebParam(name = "id") String id,
			@WebParam(name = "fromSeqNo") int fromSeqNo,
			@WebParam(name = "toSeqNo") int toSeqNo,
			@WebParam(name = "amount") int amount,
			@WebParam(name = "sign") String sign);

	/**
	 * 6.获取中奖记录
	 * 
	 * @param id
	 *            渠道ID
	 * @param issue
	 *            期号，为""时查询所有期，不能为空对象
	 * @param sign
	 *            签名 （对SecuretKey+issue + id组成的字串进行SHA1加密）
	 * @return
	 */
	public String getAwardInfo(@WebParam(name = "id") String id,
			@WebParam(name = "issue") String issue,
			@WebParam(name = "sign") String sign);

	/**
	 * 6.获取中奖记录
	 * 
	 * @param id
	 *            渠道ID
	 * @param issue
	 *            期号，为""时查询所有期，不能为空对象
	 * @param sign
	 *            签名 （对SecuretKey+issue + id+page+pageSize组成的字串进行SHA1加密）
	 * @return
	 */
	public String getAwardInfoEx(@WebParam(name = "id") String id,
			@WebParam(name = "issue") String issue,
			@WebParam(name = "page") int page,
			@WebParam(name = "pageSize") int pageSize,
			@WebParam(name = "sign") String sign);

	/**
	 * 7.查询用户赠彩记录
	 * 
	 * @param id
	 *            渠道ID
	 * @param mpCode
	 *            微信公众号
	 * @param openID
	 *            微信openID
	 * @param type
	 *            彩票状态类型，（-1：所有，0：等待开奖，1：未中奖，2：已中奖）
	 * @param page
	 *            页码，从1开始
	 * @param pageSize
	 *            每页条目数
	 * @param sign
	 *            签名 （对SecuretKey+mpCode+openID + id + type + page +
	 *            pageSize组成的字串进行SHA1加密）
	 * @return
	 */
	public String getLotteryListByUserID(@WebParam(name = "id") String id,
			@WebParam(name = "mpCode") String mpCode,
			@WebParam(name = "openID") String openID,
			@WebParam(name = "type") int type,
			@WebParam(name = "page") int page,
			@WebParam(name = "pageSize") int pageSize,
			@WebParam(name = "sign") String sign);

	/**
	 * 8.查询开奖号码
	 * 
	 * @param id
	 *            渠道ID
	 * @param issue
	 *            期号，为""时查询所有期，不能为null
	 * @param page
	 *            页码，从1开始
	 * @param pageSize
	 *            每页条目数
	 * @param sign
	 *            签名 （对SecuretKey+issue + id + page + pageSize组成的字串进行SHA1加密）
	 * @return
	 */
	public String getLotteryResult(@WebParam(name = "id") String id,
			@WebParam(name = "issue") String issue,
			@WebParam(name = "page") int page,
			@WebParam(name = "pageSize") int pageSize,
			@WebParam(name = "sign") String sign);

	/**
	 * 9.获取用户彩金账户信息
	 * 
	 * @param id
	 *            渠道ID
	 * @param mpCode
	 *            微信公众号
	 * @param openID
	 *            微信openID
	 * @param sign
	 *            签名（对SecuretKey+mpCode+openID+id组成的字串进行SHA1加密）
	 * @return
	 */
	public String getAccountInfo(@WebParam(name = "id") String id,
			@WebParam(name = "mpCode") String mpCode,
			@WebParam(name = "openID") String openID,
			@WebParam(name = "sign") String sign);

	/**
	 * 10.用户彩金账户奖金兑现
	 * 
	 * @param id
	 *            渠道ID
	 * @param mpCode
	 *            微信公众号
	 * @param openID
	 *            微信openID
	 * @param orderID
	 *            订单号
	 * @param amount
	 *            兑现金额（单位：分）
	 * @param sign
	 *            签名（对SecuretKey+mpCode+openID+id+orderID+ amount组成的字串进行SHA1加密）
	 * @return
	 */
	public String encash(@WebParam(name = "id") String id,
			@WebParam(name = "mpCode") String mpCode,
			@WebParam(name = "openID") String openID,
			@WebParam(name = "orderID") String orderID,
			@WebParam(name = "amount") int amount,
			@WebParam(name = "sign") String sign);

	/**
	 * 11.参数设定
	 * 
	 * @param id
	 *            渠道ID
	 * @param dayLimit
	 *            日赠送限制额度
	 * @param sign
	 *            （使用签名密钥对SecuretKey+dayLimit+id进行SHA1加密）
	 * @return
	 */
	public String setParams(@WebParam(name = "id") String id,
			@WebParam(name = "dayLimit") int dayLimit,
			@WebParam(name = "sign") String sign);

	/**
	 * 12.获取参数
	 * 
	 * @param id
	 *            渠道ID
	 * @param sign
	 *            （使用签名密钥对SecuretKey+id进行SHA1加密）
	 * @return
	 */
	public String getParams(@WebParam(name = "id") String id,
			@WebParam(name = "sign") String sign);

	/**
	 * 查询当前销售期
	 * 
	 * @param id
	 *            渠道ID
	 * @param gameCode
	 *            游戏代码
	 * @param sign
	 *            （使用签名密钥对SecuretKey+id+gameCode进行SHA1加密）
	 * @return
	 */
	public String getCurrentIssue(@WebParam(name = "id") String id,
			@WebParam(name = "gameCode") int gameCode,
			@WebParam(name = "sign") String sign);

	/**
	 * 查询期状态
	 * 
	 * @param id
	 *            渠道ID
	 * @param gameCode
	 *            游戏代码
	 * @param issue
	 *            期号
	 * @param sign
	 *            （使用签名密钥对SecuretKey+id+gameCode+issue进行SHA1加密）
	 * @return
	 */
	public String getIssueStatus(@WebParam(name = "id") String id,
			@WebParam(name = "gameCode") int gameCode,
			@WebParam(name = "issue") String issue,
			@WebParam(name = "sign") String sign);

	/**
	 * 获取零钱彩票(自选号码)
	 * 
	 * @param id
	 *            渠道ID
	 * @param mpCode
	 *            微信公众号
	 * @param openID
	 *            微信openID
	 * @param lotteryData
	 *            用户自选彩票号码
	 * @param gameCode
	 *            游戏代码（双色球为201）
	 * @param amount
	 *            赠送金额（单位：分）
	 * @param orderID
	 *            订单号
	 * @param usePrize
	 *            是否使用用户奖金赠彩
	 * @param sign
	 *            签名（对SecuretKey+mpCode+openID+id+lotteryData+gameCode+ orderID+
	 *            amount组成的字串进行SHA1加密）
	 * @return
	 */
	public String getLotteryCustom(@WebParam(name = "id") String id,
			@WebParam(name = "mpCode") String mpCode,
			@WebParam(name = "openID") String openID,
			@WebParam(name = "lotteryData") String lotteryData,
			@WebParam(name = "gameCode") int gameCode,
			@WebParam(name = "amount") int amount,
			@WebParam(name = "orderID") String orderID,
			@WebParam(name = "usePrize") boolean usePrize,
			@WebParam(name = "sign") String sign);

	/**
	 * 提现到银行卡
	 * 
	 * @param id
	 *            渠道ID
	 * @param mobile
	 *            用户手机号码
	 * @param name
	 *            用户姓名
	 * @param bankCardNo
	 *            用户银行卡号
	 * @param bankName
	 *            开户行名称
	 * @param bankBranchName
	 *            开户行支行名称
	 * @param amount
	 *            提现金额（单位：分）
	 * @param sign
	 *            签名（对SecuretKey+mpCode+openID+mobile+id+bankCardNo+
	 *            amount组成的字串进行SHA1加密）
	 * @return
	 */
	public String encashToBankCard(@WebParam(name = "id") String id,
			@WebParam(name = "mpCode") String mpCode,
			@WebParam(name = "openID") String openID,
			@WebParam(name = "mobile") String mobile,
			@WebParam(name = "name") String name,
			@WebParam(name = "bankCardNo") String bankCardNo,
			@WebParam(name = "bankName") String bankName,
			@WebParam(name = "bankBranchName") String bankBranchName,
			@WebParam(name = "amount") int amount,
			@WebParam(name = "sign") String sign);

	/**
	 * 提现到银行卡
	 * 
	 * @param id
	 *            渠道ID
	 * @param mobile
	 *            用户手机号码
	 * @param name
	 *            用户姓名
	 * @param bankCardNo
	 *            用户银行卡号
	 * @param bankName
	 *            开户行名称
	 * @param bankBranchName
	 *            开户行支行名称
	 * @param amount
	 *            提现金额（单位：分）
	 * @param orderid
	 *            订单号
	 * @param sign
	 *            签名（对SecuretKey+mpCode+openID+mobile+id+bankCardNo+
	 *            amount+orderid组成的字串进行SHA1加密）
	 * @return
	 */
	public String encashToBankCardEx(@WebParam(name = "id") String id,
			@WebParam(name = "mpCode") String mpCode,
			@WebParam(name = "openID") String openID,
			@WebParam(name = "mobile") String mobile,
			@WebParam(name = "name") String name,
			@WebParam(name = "bankCardNo") String bankCardNo,
			@WebParam(name = "bankName") String bankName,
			@WebParam(name = "bankBranchName") String bankBranchName,
			@WebParam(name = "amount") int amount,
			@WebParam(name = "orderid") String orderid,
			@WebParam(name = "sign") String sign);

	/**
	 * 查询用户提现交易记录
	 * 
	 * @param id
	 *            渠道ID
	 * @param mobile
	 *            用户手机号码
	 * @param page
	 *            页码（从1开始）
	 * @param size
	 *            每页记录数
	 * @param sign
	 *            签名（对SecuretKey+mobile+id+page+size组成的字串进行SHA1加密）
	 * @return
	 */
	public String getAccountTransLog(@WebParam(name = "id") String id,
			@WebParam(name = "mobile") String mobile,
			@WebParam(name = "page") int page,
			@WebParam(name = "size") int size,
			@WebParam(name = "sign") String sign);

	/**
	 * 查询用户提现交易记录
	 * 
	 * @param id
	 *            渠道ID
	 * @param mpCode
	 *            微信公众号
	 * @param openID
	 *            微信openID
	 * @param page
	 *            页码（从1开始）
	 * @param size
	 *            每页记录数
	 * @param sign
	 *            签名（对SecuretKey+mpCode+openID+id+page+size组成的字串进行SHA1加密）
	 * @return
	 */
	public String getAccountTransLogByOpenID(@WebParam(name = "id") String id,
			@WebParam(name = "mpCode") String mpCode,
			@WebParam(name = "openID") String openID,
			@WebParam(name = "page") int page,
			@WebParam(name = "size") int size,
			@WebParam(name = "sign") String sign);

	/**
	 * 根据订单号查询赠彩结果
	 * 
	 * @param id
	 *            渠道ID
	 * @param orderID
	 *            订单号
	 * @param sign签名
	 *            （对SecuretKey+orderID+id组成的字串进行SHA1加密）
	 * @return
	 */
	public String getTicketInfoByOrderID(@WebParam(name = "id") String id,
			@WebParam(name = "orderID") String orderID,
			@WebParam(name = "sign") String sign);

	/**
	 * 查询渠道余额
	 * 
	 * @param id
	 *            渠道ID
	 * @param sign签名
	 *            （对SecuretKey+id组成的字串进行SHA1加密）
	 * @return
	 */
	public String getChannelBalance(@WebParam(name = "id") String id,
			@WebParam(name = "sign") String sign);

	/**
	 * 查询提现到银行卡订单状态
	 * 
	 * @param id
	 *            渠道ID
	 * @param orderID
	 *            订单号
	 * @param sign签名
	 *            （对SecuretKey+orderID+id组成的字串进行SHA1加密）
	 * @return
	 */
	public String getEncashToBankStatus(@WebParam(name = "id") String id,
			@WebParam(name = "orderID") String orderID,
			@WebParam(name = "sign") String sign);

	/**
	 * 微信用户间彩票转赠
	 * 
	 * @param id
	 *            渠道ID
	 * @param mpCode
	 *            微信号
	 * @param fromOpenID
	 *            发起者OpenID
	 * @param toOpenID
	 *            接收者OpenID
	 * @param orderList
	 *            转赠彩票订单列表，逗号分割
	 * @param signsign签名
	 *            （对SecuretKey+mpCode+id+fromOpenID+toOpenID+
	 *            orderList组成的字串进行SHA1加密）
	 * @return
	 */
	public String lotteryPresent(String id, String mpCode, String fromOpenID,
			String toOpenID, String orderList, String sign);
}
