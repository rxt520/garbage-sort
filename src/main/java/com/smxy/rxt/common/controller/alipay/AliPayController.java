package com.smxy.rxt.common.controller.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.*;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.response.AlipayFundAuthOrderFreezeResponse;
import com.alipay.api.response.AlipayFundCouponOrderAgreementPayResponse;
import com.alipay.api.response.AlipayTradeCreateResponse;
import com.jpay.alipay.AliPayApi;
import com.jpay.alipay.AliPayApiConfig;
import com.jpay.alipay.AliPayApiConfigKit;
import com.jpay.util.StringUtils;
import com.jpay.vo.AjaxResult;
import com.smxy.rxt.common.entity.AliPayBean;
import com.smxy.rxt.sys.model.orders;
import com.smxy.rxt.sys.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/alipay")
public class AliPayController extends AliPayApiController {
    private static final Logger log = LoggerFactory.getLogger(AliPayController.class);

	@Autowired
	private AliPayBean aliPayBean;

	@Autowired(required = false)
	OrderService orderService;
	private AjaxResult result = new AjaxResult();

	@Override
	public AliPayApiConfig getApiConfig() {
		return AliPayApiConfig.New()
				.setAppId(aliPayBean.getAppId())
				.setAlipayPublicKey(aliPayBean.getPublicKey())
				.setCharset("UTF-8")
				.setPrivateKey(aliPayBean.getPrivateKey())
				.setServiceUrl(aliPayBean.getServerUrl())
				.setSignType("RSA2")
				.build();
	}
	
	@RequestMapping("")
	@ResponseBody
	public String index() {
		return "欢迎使用IJPay 中的支付宝支付 -By Javen";
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public String test(){
		String charset = AliPayApiConfigKit.getAliPayApiConfig().getCharset();
		log.info("charset>"+charset);
		return aliPayBean.toString();
	}


	/**
	 * app支付
	 */
	@RequestMapping(value = "/appPay")
	@ResponseBody
	public AjaxResult appPay() {
		try {
			AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
			model.setBody("我是测试数据-By Javen");
			model.setSubject("App支付测试-By Javen");
			model.setOutTradeNo(StringUtils.getOutTradeNo());
			model.setTimeoutExpress("30m");
			model.setTotalAmount("0.01");
			model.setPassbackParams("callback params");
			model.setProductCode("QUICK_MSECURITY_PAY");
			String orderInfo = AliPayApi.startAppPay(model, aliPayBean.getDomain() + "/alipay/notify_url");
			result.success(orderInfo);
		} catch (AlipayApiException e) {
			e.printStackTrace();
			result.addError("system error:"+e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping(value = "/wapPay") 
	@ResponseBody
	public void wapPay(HttpServletResponse response) {
		String body = "我是测试数据-By Javen";
		String subject = "Javen Wap支付测试";
		String totalAmount = "1";
		String passbackParams = "1";
		String returnUrl = aliPayBean.getDomain() + "/alipay/return_url";
		String notifyUrl = aliPayBean.getDomain() + "/alipay/notify_url";

		AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
		model.setBody(body);
		model.setSubject(subject);
		model.setTotalAmount(totalAmount);
		model.setPassbackParams(passbackParams);
		String outTradeNo = StringUtils.getOutTradeNo();
		System.out.println("wap outTradeNo>"+outTradeNo);
		model.setOutTradeNo(outTradeNo);
		model.setProductCode("QUICK_WAP_PAY");

		try {
			AliPayApi.wapPay(response, model, returnUrl, notifyUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * PC支付
	 */
	@RequestMapping(value = "/pcPay") 
	@ResponseBody
	public void pcPay(HttpServletRequest request, HttpServletResponse response,String outTradeNo,String wage){
		try {

			String totalAmount = wage;
			String returnUrl = aliPayBean.getDomain() + "/alipay/return_url";
			String notifyUrl = aliPayBean.getDomain() + "/alipay/notify_url";
			AlipayTradePagePayModel model = new AlipayTradePagePayModel();
			model.setOutTradeNo(outTradeNo);
			model.setProductCode("FAST_INSTANT_TRADE_PAY");
			model.setTotalAmount(totalAmount);
			model.setSubject("阮雄腾的支付测试");
			model.setBody("阮雄腾的PC支付测试");
			model.setPassbackParams("passback_params");
			AliPayApi.tradePage(response,model , notifyUrl, returnUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * pay_order
	 * 订单界面支付
	 * @param request
	 * @param response
	 * @param outTradeNo
	 * @param wage
	 */
	@RequestMapping(value = "/pay_order")
	@ResponseBody
	public void pay_order(HttpServletRequest request, HttpServletResponse response,String outTradeNo,String wage){
		try {
			String totalAmount = wage;
			String returnUrl = aliPayBean.getDomain() + "/alipay/return1_url";
			String notifyUrl = aliPayBean.getDomain() + "/alipay/notify_url";
			AlipayTradePagePayModel model = new AlipayTradePagePayModel();
			model.setOutTradeNo(outTradeNo);
			model.setProductCode("FAST_INSTANT_TRADE_PAY");
			model.setTotalAmount(totalAmount);
			model.setSubject("阮雄腾的支付测试");
			model.setBody("阮雄腾的PC支付测试");
			model.setPassbackParams("passback_params");
			AliPayApi.tradePage(response,model , notifyUrl, returnUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * 回调函数1
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/return1_url")
	public String return1_url(HttpServletRequest request) {
		String OutTradeNo="";
		String TradeNo="";
		try {
			// 获取支付宝GET过来反馈信息
			Map<String, String> map = AliPayApi.toMap(request);
			for (Map.Entry<String, String> entry : map.entrySet()) {
				if (entry.getKey().equals("trade_no")){
					TradeNo=entry.getValue().toString();
				}
				if (entry.getKey().equals("out_trade_no")){
					OutTradeNo =entry.getValue().toString();
				}
				System.out.println(entry.getKey() + " = " + entry.getValue());
			}

			boolean verify_result = AlipaySignature.rsaCheckV1(map, aliPayBean.getPublicKey(), "UTF-8",
					"RSA2");
			if (verify_result) {// 验证成功
				// TODO 请在这里加上商户的业务逻辑程序代码
				orders orders = new orders();
				Integer orderid= orderService.FindOrderIdByOutTranNo(OutTradeNo);
				orders.setOrderid(orderid);
				orders.setState("待接单");
				orders.setTradeno(TradeNo);
				orders.setOuttradeno(OutTradeNo);
				orderService.update(orders);

				System.out.println("return_url 验证成功");
				return "redirect:http://sort.smxy.online:9000/userOrder_list";
			} else {
				System.out.println("return_url 验证失败");
				// TODO
				return "failure";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}

	/**
	 * 回调函数原生
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/return_url")
	public String return_url(HttpServletRequest request) {
		String OutTradeNo="";
		String TradeNo="";
		try {
			// 获取支付宝GET过来反馈信息
			Map<String, String> map = AliPayApi.toMap(request);
			for (Map.Entry<String, String> entry : map.entrySet()) {
				if (entry.getKey().equals("trade_no")){
					TradeNo=entry.getValue().toString();
				}
				if (entry.getKey().equals("out_trade_no")){
					OutTradeNo =entry.getValue().toString();
				}
				System.out.println(entry.getKey() + " = " + entry.getValue());
			}

			boolean verify_result = AlipaySignature.rsaCheckV1(map, aliPayBean.getPublicKey(), "UTF-8",
					"RSA2");
			if (verify_result) {// 验证成功
				// TODO 请在这里加上商户的业务逻辑程序代码
				orders orders = new orders();
				Integer orderid= orderService.FindOrderIdByOutTranNo(OutTradeNo);
				orders.setOrderid(orderid);
				orders.setState("待接单");
				orders.setTradeno(TradeNo);
				orders.setOuttradeno(OutTradeNo);
				orderService.update(orders);

				System.out.println("return_url 验证成功");
				return "redirect:http://sort.smxy.online:9000/index";
			} else {
				System.out.println("return_url 验证失败");
				// TODO
				return "failure";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}

	/**
	 * 条形码支付
	 * https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.Yhpibd&
	 * treeId=194&articleId=105170&docType=1#s4
	 */
	@RequestMapping(value ="/tradePay")
	@ResponseBody
	public String  tradePay(@RequestParam("auth_code") String authCode) {
		String subject = "Javen 支付宝条形码支付测试";
		String totalAmount = "100";
		String notifyUrl = aliPayBean.getDomain() + "/alipay/notify_url";

		AlipayTradePayModel model = new AlipayTradePayModel();
		model.setAuthCode(authCode);
		model.setSubject(subject);
		model.setTotalAmount(totalAmount);
		model.setOutTradeNo(StringUtils.getOutTradeNo());
		model.setScene("bar_code");
		try {
			return AliPayApi.tradePay(model,notifyUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 声波支付
	 * https://doc.open.alipay.com/docs/doc.htm?treeId=194&articleId=105072&docType=1#s2
	 */
	@RequestMapping(value ="/tradeWavePay")
	@ResponseBody
	public String tradeWavePay(@RequestParam("auth_code") String authCode) {
		String subject = "Javen 支付宝声波支付测试";
		String totalAmount = "100";
		String notifyUrl = aliPayBean.getDomain() + "/alipay/notify_url";

		AlipayTradePayModel model = new AlipayTradePayModel();
		model.setAuthCode(authCode);
		model.setSubject(subject);
		model.setTotalAmount(totalAmount);
		model.setOutTradeNo(StringUtils.getOutTradeNo());
		model.setScene("wave_code");
		try {
			return AliPayApi.tradePay(model,notifyUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  null;
	}

	/**
	 * 扫码支付
	 */
	@RequestMapping(value ="/tradePrecreatePay")
	@ResponseBody
	public String tradePrecreatePay() {
		String subject = "Javen 支付宝扫码支付测试";
		String totalAmount = "86";
		String storeId = "123";
		String notifyUrl = aliPayBean.getDomain() + "/alipay/notify_url";

		AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
		model.setSubject(subject);
		model.setTotalAmount(totalAmount);
		model.setStoreId(storeId);
		model.setTimeoutExpress("5m");
		model.setOutTradeNo(StringUtils.getOutTradeNo());
		try {
			String resultStr = AliPayApi.tradePrecreatePay(model, notifyUrl);
			JSONObject jsonObject = JSONObject.parseObject(resultStr);
			return jsonObject.getJSONObject("alipay_trade_precreate_response").getString("qr_code");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 单笔转账到支付宝账户
	 * https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.54Ty29&
	 * treeId=193&articleId=106236&docType=1
	 */
	@RequestMapping(value = "/transfer")
	@ResponseBody
	public boolean transfer() {
		boolean isSuccess = false;
		String total_amount = "66";
		AlipayFundTransToaccountTransferModel model = new AlipayFundTransToaccountTransferModel();
		model.setOutBizNo(StringUtils.getOutTradeNo());
		model.setPayeeType("ALIPAY_LOGONID");
		model.setPayeeAccount("abpkvd0206@sandbox.com");
		model.setAmount(total_amount);
		model.setPayerShowName("测试退款");
		model.setPayerRealName("沙箱环境");
		model.setRemark("javen测试单笔转账到支付宝");

		try {
			isSuccess = AliPayApi.transfer(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	/**
	 * 资金授权冻结接口
	 */
	@RequestMapping(value = "/authOrderFreeze")
	@ResponseBody
	public AlipayFundAuthOrderFreezeResponse authOrderFreeze(@RequestParam("auth_code") String authCode){
		try {
			AlipayFundAuthOrderFreezeModel model = new AlipayFundAuthOrderFreezeModel();
			model.setOutOrderNo(StringUtils.getOutTradeNo());
			model.setOutRequestNo(StringUtils.getOutTradeNo());
			model.setAuthCode(authCode);
			model.setAuthCodeType("bar_code");
			model.setOrderTitle("资金授权冻结-By IJPay");
			model.setAmount("36");
//			model.setPayTimeout("");
			model.setProductCode("PRE_AUTH");
			
			AlipayFundAuthOrderFreezeResponse response = AliPayApi.authOrderFreezeToResponse(model);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 红包协议支付接口
	 * https://docs.open.alipay.com/301/106168/
	 */
	@RequestMapping(value = "/agreementPay")
	@ResponseBody
	public AlipayFundCouponOrderAgreementPayResponse agreementPay(){
		try {
			AlipayFundCouponOrderAgreementPayModel model = new AlipayFundCouponOrderAgreementPayModel();
			model.setOutOrderNo(StringUtils.getOutTradeNo());
			model.setOutRequestNo(StringUtils.getOutTradeNo());
			model.setOrderTitle("红包协议支付接口-By IJPay");
			model.setAmount("36");
			model.setPayerUserId("2088102180432465");
			
			
			AlipayFundCouponOrderAgreementPayResponse response = AliPayApi.fundCouponOrderAgreementPayToResponse(model);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 下载对账单
	 */
	@RequestMapping(value = "/dataDataserviceBill")
	@ResponseBody
	public String dataDataserviceBill(@RequestParam("billDate") String billDate) {
		try {
			AlipayDataDataserviceBillDownloadurlQueryModel model = new AlipayDataDataserviceBillDownloadurlQueryModel();
			model.setBillType("trade");
			model.setBillDate(billDate);
			return AliPayApi.billDownloadurlQuery(model);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 退款
	 */
	@RequestMapping(value = "/tradeRefund")
	@ResponseBody
	public String tradeRefund(Integer orderid,String TradeNo,String RefundAmount) {
		try {
			AlipayTradeRefundModel model = new AlipayTradeRefundModel();
			model.setTradeNo(TradeNo);
			model.setRefundAmount(RefundAmount);
			model.setRefundReason("正常退款");
			orders orders = new orders();
			orders.setOrderid(orderid);
			orders.setState("已退款");
			orderService.update(orders);
			return AliPayApi.tradeRefund(model);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return "1";
	}

	/**
	 * 交易查询
	 */
	@RequestMapping(value = "/tradeQuery")
	@ResponseBody
	public boolean tradeQuery() {
		boolean isSuccess = false;
		try {
			AlipayTradeQueryModel model = new AlipayTradeQueryModel();
			model.setOutTradeNo("081014283315023");
			model.setTradeNo("2017081021001004200200273870");

			isSuccess = AliPayApi.isTradeQuery(model);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	@RequestMapping(value = "/tradeQueryByStr")
	@ResponseBody
	public String  tradeQueryByStr(@RequestParam("out_trade_no") String out_trade_no) {
		AlipayTradeQueryModel model = new AlipayTradeQueryModel();
		model.setOutTradeNo(out_trade_no);

		try {
			return AliPayApi.tradeQueryToResponse(model).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 创建订单
	 * {"alipay_trade_create_response":{"code":"10000","msg":"Success","out_trade_no":"081014283315033","trade_no":"2017081021001004200200274066"},"sign":"ZagfFZntf0loojZzdrBNnHhenhyRrsXwHLBNt1Z/dBbx7cF1o7SZQrzNjRHHmVypHKuCmYifikZIqbNNrFJauSuhT4MQkBJE+YGPDtHqDf4Ajdsv3JEyAM3TR/Xm5gUOpzCY7w+RZzkHevsTd4cjKeGM54GBh0hQH/gSyhs4pEN3lRWopqcKkrkOGZPcmunkbrUAF7+AhKGUpK+AqDw4xmKFuVChDKaRdnhM6/yVsezJFXzlQeVgFjbfiWqULxBXq1gqicntyUxvRygKA+5zDTqE5Jj3XRDjVFIDBeOBAnM+u03fUP489wV5V5apyI449RWeybLg08Wo+jUmeOuXOA=="}
	 */
	@RequestMapping(value = "/tradeCreate")
	@ResponseBody
	public String tradeCreate(@RequestParam("out_trade_no") String outTradeNo){

		String notifyUrl = aliPayBean.getDomain()+ "/alipay/notify_url";

		AlipayTradeCreateModel model = new AlipayTradeCreateModel();
		model.setOutTradeNo(outTradeNo);
		model.setTotalAmount("88.88");
		model.setBody("Body");
		model.setSubject("Javen 测试统一收单交易创建接口");
		model.setBuyerLogonId("abpkvd0206@sandbox.com");//买家支付宝账号，和buyer_id不能同时为空
		try {
			AlipayTradeCreateResponse response = AliPayApi.tradeCreateToResponse(model, notifyUrl);
			return response.getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 撤销订单
	 */
	@RequestMapping(value = "/tradeCancel")
	@ResponseBody
	public boolean tradeCancel() {
		boolean isSuccess = false;
		try {
			AlipayTradeCancelModel model = new AlipayTradeCancelModel();
			model.setOutTradeNo("081014283315033");
			model.setTradeNo("2017081021001004200200274066");

			isSuccess = AliPayApi.isTradeCancel(model);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	/**
	 * 关闭订单
	 */
	@RequestMapping(value = "/tradeClose")
	@ResponseBody
	public String tradeClose(@RequestParam("out_trade_no") String outTradeNo,@RequestParam("trade_no") String tradeNo){
		try {
			AlipayTradeCloseModel model = new AlipayTradeCloseModel();
			model.setOutTradeNo(outTradeNo);

			model.setTradeNo(tradeNo);

			return AliPayApi.tradeCloseToResponse(model).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 结算
	 */
	@RequestMapping(value = "/tradeOrderSettle")
	@ResponseBody
	public String  tradeOrderSettle(@RequestParam("trade_no") String tradeNo){
		try {
			AlipayTradeOrderSettleModel model = new AlipayTradeOrderSettleModel();
			model.setOutRequestNo(StringUtils.getOutTradeNo());
			model.setTradeNo(tradeNo);

			return AliPayApi.tradeOrderSettleToResponse(model ).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取应用授权URL并授权
	 */
	@RequestMapping(value = "/toOauth")
	@ResponseBody
	public void toOauth(HttpServletResponse response) {
		try {
			String redirectUri = aliPayBean.getDomain()+ "/alipay/redirect_uri";
			String oauth2Url = AliPayApi.getOauth2Url(aliPayBean.getAppId(), redirectUri);
			response.sendRedirect(oauth2Url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 应用授权回调
	 */
	@RequestMapping(value = "/redirect_uri")
	@ResponseBody
	public String redirect_uri(@RequestParam("app_id") String app_id,@RequestParam("app_auth_code") String app_auth_code) {
		try {
			System.out.println("app_id:"+app_id);
			System.out.println("app_auth_code:"+app_auth_code);
			//使用app_auth_code换取app_auth_token
			AlipayOpenAuthTokenAppModel model = new AlipayOpenAuthTokenAppModel();
			model.setGrantType("authorization_code");
			model.setCode(app_auth_code);
			return  AliPayApi.openAuthTokenApp(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询授权信息
	 */
	@RequestMapping(value = "/openAuthTokenAppQuery")
	@ResponseBody
	public String openAuthTokenAppQuery(@RequestParam("app_auth_token") String app_auth_token) {
		try {
			AlipayOpenAuthTokenAppQueryModel model = new AlipayOpenAuthTokenAppQueryModel();
			model.setAppAuthToken(app_auth_token);
			return  AliPayApi.openAuthTokenAppQuery(model);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 批量付款到支付宝账户有密接口
	 */
	@RequestMapping(value = "/batchTrans")
	@ResponseBody
	public  void batchTrans(HttpServletResponse response) {
		try {
			String sign_type = "MD5";
			String notifyUrl = aliPayBean.getDomain()+ "/alipay/notify_url";;
			Map<String, String> params = new HashMap<>();
			params.put("partner", "PID");
			params.put("sign_type", sign_type);
			params.put("notify_url", notifyUrl);
			params.put("account_name", "xxx");
			params.put("detail_data", "流水号1^收款方账号1^收款账号姓名1^付款金额1^备注说明1|流水号2^收款方账号2^收款账号姓名2^付款金额2^备注说明2");
			params.put("batch_no",String.valueOf(System.currentTimeMillis()));
			params.put("batch_num", 1+"");
			params.put("batch_fee", 10.00+"");
			params.put("email", "xx@xxx.com");
			
			AliPayApi.batchTrans(params, aliPayBean.getPrivateKey(), sign_type, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 地铁购票核销码发码
	 */
	@RequestMapping(value = "/voucherGenerate")
	@ResponseBody
	public String voucherGenerate(@RequestParam("tradeNo") String tradeNo) {
		try {
			//需要支付成功的订单号
//			String tradeNo = getPara("tradeNo");
			
			AlipayCommerceCityfacilitatorVoucherGenerateModel model = new AlipayCommerceCityfacilitatorVoucherGenerateModel();
			model.setCityCode("440300");
			model.setTradeNo(tradeNo);
			model.setTotalFee("8");
			model.setTicketNum("2");
			model.setTicketType("oneway");
			model.setSiteBegin("001");
			model.setSiteEnd("002");
			model.setTicketPrice("4");
			return AliPayApi.voucherGenerate(model);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/notify_url")
	@ResponseBody
	public String  notify_url(HttpServletRequest request) {
		try {
			// 获取支付宝POST过来反馈信息
			Map<String, String> params = AliPayApi.toMap(request);

			for (Map.Entry<String, String> entry : params.entrySet()) {
				System.out.println(entry.getKey() + " = " + entry.getValue());
			}

			boolean verify_result = AlipaySignature.rsaCheckV1(params, aliPayBean.getPublicKey(), "UTF-8",
					"RSA2");

			if (verify_result) {// 验证成功
				// TODO 请在这里加上商户的业务逻辑程序代码 异步通知可能出现订单重复通知 需要做去重处理
				System.out.println("notify_url 验证成功succcess");
				return "success";
			} else {
				System.out.println("notify_url 验证失败");
				// TODO
				return "failure";
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
			return "failure";
		}
	}
}
