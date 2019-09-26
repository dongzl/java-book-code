import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

@SuppressWarnings("serial")
public class PayVerify extends HttpServlet {

	public static final String devPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxuJTzr44cJQGXT6Y09Y902AoIDhKhJv8/QSZLnG92L8yIhfLDCCjd5Xprnqxu0GirGESywin1qj6QhHbtBxr5hXz4IzhMHfiYV3TdxGcfxY7sxUYvAwOYk8700vRokdZq35lfg+oYcc9u2ztFRvhthHwH5R7bNCX0aiFP3POjhGPriRLzWAcA4p0FfWg1cqTDoGhH89SKRopdr1AKVpjOZxuOcnnN/vejgdGgUTurvypZCFCnUifcGxjtE/tWQmVv9xNQLwDACJm8JjiP2dAm3l4KViSl/j6j65kGT7WxHTxNM1jvLWzPMnPCvn/O9cWmsfAfmwaa6sIot1L5KlgNwIDAQAB";

	/**
	 * Constructor of the object.
	 */
	public PayVerify() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		Map<String, Object> map = null;

		map = getValue(request);
		if (null == map)
			return;

		String sign = (String) map.get("sign");
		
		/**
		 * @result  验签成功给出响应，0表示成功，1表示验签失败。| Return a response after signature verification is completed. 0 indicates verification success, and 1 indicates verification failure.
		 */

		ResultDomain result = new ResultDomain();
		result.setResult(1);

		/**
		 * 调用验签rsaDoCheck函数，验签返回Boolean值。True为验签成功，False为验签失败。
		 * 
		 * @param sign     devPubkey 支付公钥。
		 * @param signType 签名算法。
		 * 
		 */
		/**
		 * Call the rsaDoCheck function for signature verification. A Boolean value is returned. True indicates verification success, and False indicates verification failure.
		 *
		 * @param sign    Payment public key
		 * @param signType Signature algorithm
		 *
		 */

		if (rsaDoCheck(map, sign, devPubKey, (String) map.get("signType"))) {
			result.setResult(0);
			System.out.println("Result : 0!");  
		} else {
			result.setResult(1);
			System.out.println("Result : 1!");
		}

		String resultinfo = convertJsonStyle(result);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println("Response string: " + resultinfo);
		PrintWriter out = response.getWriter();
		out.print(resultinfo);
		out.close();
	}

	public void writeFile(String filename, String content) throws Throwable {
		File file = new File(filename);
		// 目录如果不存在就创建 | If the directory does not exist, create one.
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		FileOutputStream outSTr = new FileOutputStream(file, true);
		BufferedOutputStream Buff = new BufferedOutputStream(outSTr);
		Buff.write(content.getBytes());
		Buff.flush();
		Buff.close();
		outSTr.close();
	}
	/**
	 * @param request
	 * @return 本接口Content-Type是：application/x-www-form-urlencoded，对所有参数，会自动进行编码，接收端收到消息也会自动根据Content-Type进行解码。
	 *         同时，接口中参数在发送端并没有进行单独的URLEncode
	 *         (sign和extReserved、sysReserved参数除外)，所以，在接收端根据Content-Type解码后，即为原始的参数信息。
	 *         但是HttpServletRequest的getParameter()方法会对指定参数执行隐含的URLDecoder.decode(),所以，相应参数中如果包含比如"%"，就会发生错误。
	 *         因此，我们建议通过如下方法获取原始参数信息。
	 * 
	 *         注：使用如下方法必须在原始ServletRequest未被处理的情况下进行，否则无法获取到信息。比如，在Struts情况，由于struts层已经对参数进行若干处理，
	 *         http中InputStream中其实已经没有信息，因此，本方法不适用。要获取原始信息，必须在原始的，未经处理的ServletRequest中进行。
	 */
	/**
	 * @param request
	 * @return Interface Content-Type: application/x-www-form-urlencoded. All parameters are automatically encoded. After receiving the message, the receive end automatically decodes the parameters based on Content-Type.
	 *         The interface parameters excluding sign, extReserved, and sysReserved are not URL encoded on the sending end.
	 *         Therefore, the receiving end obtains the original parameter information after parameter decoding based on Content-Type.
	 *         However, the getParameter() method of HttpServletRequest implicitly URL decodes specified parameters. Therefore, for example, if a parameter value contains the percent sign (%), an error occurs.
	 *         Therefore, it is recommended that you obtain the original parameter information using the following method.
	 *         Note: The following method must be used before the original ServletRequest is not processed. Otherwise, the original parameter information cannot be obtained.
	 *         For example, when Struts is used, the parameters have been processed at the Struts layer, and there is actually no information in InputStream of HTTP. In this case, this method cannot be used. The original parameters can be obtained only from the original ServletRequest.
	 *
	 */
	public Map<String, Object> getValue(HttpServletRequest request) {

		/*
		 * String path = request.getRealPath("/"); //path = path.substring(0,
		 * path.indexOf("webapps")); //path = request.getContextPath(); if
		 * (path.endsWith(File.separator)) { path = path; }else { path = path +
		 * File.separator; } path = path + "../../logs/my.log";
		 */

		String line = null;
		StringBuffer sb = new StringBuffer();
		try {
			request.setCharacterEncoding("UTF-8");
			InputStream stream = request.getInputStream();
			InputStreamReader isr = new InputStreamReader(stream);
			BufferedReader br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\r\n");
			}
			System.out.println("The original data is : " + sb.toString());
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		String str = sb.toString();
		
    /**
     * @param valueMap  解析获取实际回调中含有的字段值，且sign，extReserved(有值的情况)，sysReserved(有值的情况)需要进行Urldecode处理。 |Parse the parameter values out of the actual callback result, and URL encode sign, extReserved (if a value is assigned), and sysReserved (if a value is assigned).
     */
		Map<String, Object> valueMap = new HashMap<String, Object>();
		if (null == str || "".equals(str)) {
			return valueMap;
		}

		String[] valueKey = str.split("&");
		for (String temp : valueKey) {
			String[] single = temp.split("=");
			valueMap.put(single[0], single[1]);
		}
		System.out.println("The parameters in map are : " + valueMap);

		// 接口中，如下参数sign和extReserved是URLEncode的，所以需要decode，其他参数直接是原始信息发送，不需要decode
		//The sign, extReserved, and sysReserved parameters in the interface have been URL encoded, and therefore need to be decoded. The other parameters are sent in their original forms, and do not need to be decoded.
		try {
			String sign = (String) valueMap.get("sign");
			String extReserved = (String) valueMap.get("extReserved");
			String sysReserved = (String) valueMap.get("sysReserved");

			if (null != sign) {
				sign = URLDecoder.decode(sign, "utf-8");
				valueMap.put("sign", sign);
			}
			if (null != extReserved) {
				extReserved = URLDecoder.decode(extReserved, "utf-8");
				valueMap.put("extReserved", extReserved);
			}

			if (null != sysReserved) {
				sysReserved = URLDecoder.decode(sysReserved, "utf-8");
				valueMap.put("sysReserved", sysReserved);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return valueMap;
	}

	private String convertJsonStyle(Object resultMessage) {
		ObjectMapper mapper = new ObjectMapper();
		Writer writer = new StringWriter();
		try {
			if (null != resultMessage) {
				mapper.writeValue(writer, resultMessage);
			}

		} catch (Exception e) {

		}

		return writer.toString();
	}

	/**
	 * @ResultDomain  支付验签响应类，验签成功返回0，否则返回1.|Payment signature verification response class. 0 is returned upon a verification success, and 1 is returned upon a verification failure.
	 */
	public class ResultDomain {

		private int result;

		public int getResult() {
			return result;
		}

		public void setResult(int result) {
			this.result = result;
		}
	}

	/**
	 * @param params 回调中实际携带的字段参数解析后的map集合
	 * @param sign 回调中签名sign字段
	 * @param  publicKey 支付公钥
	 * @param  signtype  签名算法
	 */
	/**
	 * @param params Map of parameters parsed from the callback result
	 * @param sign field in the callback result
	 * @param  Payment public key
	 * @param  signtype Signature algorithm
	 */
	public static boolean rsaDoCheck(Map<String, Object> params, String sign, String publicKey, String signtype) {

//		content  是去除map中的sign以及signtype字段后的的排序拼接。  | content is a string spliced by sorted parameters in the map excluding sign and signtype.

		String content = getSignData(params);
//数据打印，用于日志输出查看。  | Data printing, used to print logs.
		System.out.println("The content for sign is : " + content.toString());
		System.out.println("The sign is : " + sign);
		System.out.println("The pubkey is : " + publicKey);

		return doCheck(content, sign, publicKey, signtype);
	}

	/**
	 * 
	 * @param  实际回调中的字段map集合   | Map of parameters parsed from the callback result
	 * @return  去除sign和signType字段，以升序排序 key1=value1&key2=value2&.....  |Sort the parameters excluding sign and signtype in ascending order, and splice the sorted parameters into a string in the "key1=value1&key2=value2&..." format.
	 */
	public static String getSignData(Map<String, Object> params) {
		StringBuffer content = new StringBuffer();

		
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			if ("sign".equals(key) || "signType".equals(key)) {
				continue;
			}
			String value = (String) params.get(key);
			if (value != null) {
				content.append((i == 0 ? "" : "&") + key + "=" + value);
			} else {
				content.append((i == 0 ? "" : "&") + key + "=");
			}

		}
		return content.toString();
	}

	public static final String SIGN_ALGORITHMS = "SHA1WithRSA";
	public static final String SIGN_ALGORITHMS256 = "SHA256WithRSA";
	public static final Base64.Decoder decoder = Base64.getDecoder();
	public static final Base64.Encoder encoder = Base64.getEncoder();
	
	/** doCheck函数各个字段参数示例参考：
	 * @param content       验签拼接
	   *                     示例：accessMode=0&amount=10.00&bankId=AliPay&extReserved=11&notifyTime=1554733129506&orderId=A3b6a9dd1d5dc1312dee66b32e085692&orderTime=2019-04-08
	 *                      22:18:48&payType=4&productName=1000元宝&requestId=8328984560238592&result=0&spending=0.00&tradeTime=2019-04-08
	 *                      22:18:49&userName=80086000134870332
	 * @param sigtype       sigtype=rsa256 时：SIGN_ALGORITHMS256 = "SHA256WithRSA", 其他情况都使用：
	 *                      SIGN_ALGORITHMS = "SHA1WithRSA"
	 * @param sign          回调中的sign字段，示例：Xmc+JExSPFr9FUjd4IPKtRAkkkOTm0oXCFNwlvXdQJHbhB0prD8ycpw0Wdr6enKLUgHBKB8JQ9s7VB9wMcgRXOJ6iArn0NqTTTl9Z5gP7zGBGDVH8J22CRObd8JKeQ9YiJ2SUM+r2H+jQdMpj3n3Cp47Z4rGR3kUDDIRCOEhSwHAqwzdeFJg2utEJfuYQIhseM7/hxB/H1ssoe9SaIAmKfjuZgYS+VzsUBjCiTmd9jKdpq0kTCEas1lL8MZVxkZ91g+zd2PH0ajgYkU8wvxRZgLRWw4h28DrKD7ziQKHOt92ZZjvcej+maSSDeQtyp7DhFgH4T+2uHy/c3Kf33kUFw==
	 * @param 联盟后台支付服务下支付公钥 示例：
	 *                      MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxuJTzr44cJQGXT6Y09Y902AoIDhKhJv8/QSZLnG92L8yIhfLDCCjd5Xprnqxu0GirGESywin1qj6QhHbtBxr5hXz4IzhMHfiYV3TdxGcfxY7sxUYvAwOYk8700vRokdZq35lfg+oYcc9u2ztFRvhthHwH5R7bNCX0aiFP3POjhGPriRLzWAcA4p0FfWg1cqTDoGhH89SKRopdr1AKVpjOZxuOcnnN/vejgdGgUTurvypZCFCnUifcGxjtE/tWQmVv9xNQLwDACJm8JjiP2dAm3l4KViSl/j6j65kGT7WxHTxNM1jvLWzPMnPCvn/O9cWmsfAfmwaa6sIot1L5KlgNwIDAQAB
	 */
	/** Examples of parameters in the doCheck function:
	 * @param content       Splice the parameters into a string for verification:
	 *                     Example:
	 *                     accessMode=0&amount=10.00&bankId=AliPay&extReserved=11&notifyTime=1554733129506&orderId=A3b6a9dd1d5dc1312dee66b32e085692&orderTime=2019-04-08
	 *                      22:18:49&userName=80086000134870332
	 * @param sigtype       If signType is set to rsa256, use the value SHA256WithRSA of SIGN_ALGORITHMS256. In other cases,
	 *                      use the value SHA1WithRSA of SIGN_ALGORITHMS.
	 * @param sign          sign field in the callback result. Example: Xmc+JExSPFr9FUjd4IPKtRAkkkOTm0oXCFNwlvXdQJHbhB0prD8ycpw0Wdr6enKLUgHBKB8JQ9s7VB9wMcgRXOJ6iArn0NqTTTl9Z5gP7zGBGDVH8J22CRObd8JKeQ9YiJ2SUM+r2H+jQdMpj3n3Cp47Z4rGR3kUDDIRCOEhSwHAqwzdeFJg2utEJfuYQIhseM7/hxB/H1ssoe9SaIAmKfjuZgYS+VzsUBjCiTmd9jKdpq0kTCEas1lL8MZVxkZ91g+zd2PH0ajgYkU8wvxRZgLRWw4h28DrKD7ziQKHOt92ZZjvcej+maSSDeQtyp7DhFgH4T+2uHy/c3Kf33kUFw==
	 * @param  Payment public key obtained after the Huawei IAP service is enabled at the background of Huawei Developer. Example:
	 *                      MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxuJTzr44cJQGXT6Y09Y902AoIDhKhJv8/QSZLnG92L8yIhfLDCCjd5Xprnqxu0GirGESywin1qj6QhHbtBxr5hXz4IzhMHfiYV3TdxGcfxY7sxUYvAwOYk8700vRokdZq35lfg+oYcc9u2ztFRvhthHwH5R7bNCX0aiFP3POjhGPriRLzWAcA4p0FfWg1cqTDoGhH89SKRopdr1AKVpjOZxuOcnnN/vejgdGgUTurvypZCFCnUifcGxjtE/tWQmVv9xNQLwDACJm8JjiP2dAm3l4KViSl/j6j65kGT7WxHTxNM1jvLWzPMnPCvn/O9cWmsfAfmwaa6sIot1L5KlgNwIDAQAB
	 */
	public static boolean doCheck(String content, String sign, String publicKey, String signtype) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			byte[] encodedKey = decoder.decode(publicKey);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

			java.security.Signature signature = null;
			if ("RSA256".equals(signtype)) {
				signature = java.security.Signature.getInstance(SIGN_ALGORITHMS256);
			} else {
				signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);
			}
			
			signature.initVerify(pubKey);
			signature.update(content.getBytes("utf-8"));
			boolean bverify = signature.verify(decoder.decode(sign));
			return bverify;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
