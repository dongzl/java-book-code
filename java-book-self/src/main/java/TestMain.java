public class TestMain {
	/**
	 * @param  content， sign，signtype ，devPubKey 替换为开发者自己的数据。| Both the test data and payment public key are real data on the live network.
	 */
	static String content = "accessMode=0&amount=10.00&bankId=AliPay&extReserved=11&notifyTime=1554733129506&orderId=A3b6a9dd1d5dc1312dee66b32e085692&orderTime=2019-04-08 22:18:48&payType=4&productName=1000Ԫ��&requestId=8328984560238592&result=0&spending=0.00&tradeTime=2019-04-08 22:18:49&userName=80086000134870332";
	static String sign  =  "Xmc+JExSPFr9FUjd4IPKtRAkkkOTm0oXCFNwlvXdQJHbhB0prD8ycpw0Wdr6enKLUgHBKB8JQ9s7VB9wMcgRXOJ6iArn0NqTTTl9Z5gP7zGBGDVH8J22CRObd8JKeQ9YiJ2SUM+r2H+jQdMpj3n3Cp47Z4rGR3kUDDIRCOEhSwHAqwzdeFJg2utEJfuYQIhseM7/hxB/H1ssoe9SaIAmKfjuZgYS+VzsUBjCiTmd9jKdpq0kTCEas1lL8MZVxkZ91g+zd2PH0ajgYkU8wvxRZgLRWw4h28DrKD7ziQKHOt92ZZjvcej+maSSDeQtyp7DhFgH4T+2uHy/c3Kf33kUFw==";
	public static String signtype  = "RSA256";
	public static final String devPubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxuJTzr44cJQGXT6Y09Y902AoIDhKhJv8/QSZLnG92L8yIhfLDCCjd5Xprnqxu0GirGESywin1qj6QhHbtBxr5hXz4IzhMHfiYV3TdxGcfxY7sxUYvAwOYk8700vRokdZq35lfg+oYcc9u2ztFRvhthHwH5R7bNCX0aiFP3POjhGPriRLzWAcA4p0FfWg1cqTDoGhH89SKRopdr1AKVpjOZxuOcnnN/vejgdGgUTurvypZCFCnUifcGxjtE/tWQmVv9xNQLwDACJm8JjiP2dAm3l4KViSl/j6j65kGT7WxHTxNM1jvLWzPMnPCvn/O9cWmsfAfmwaa6sIot1L5KlgNwIDAQAB";
	public static void main(String[] args) {
		
	Boolean bool = 	PayVerify.doCheck(content, sign, devPubKey, signtype);
	
	if(bool) {
		System.out.println("verification is successful!");
	}
	
	else {
		
		System.out.println("verification is false!");
	}
		
	}
}



