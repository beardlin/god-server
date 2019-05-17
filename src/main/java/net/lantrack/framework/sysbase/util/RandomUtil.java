package net.lantrack.framework.sysbase.util;

import java.util.Random;

/**
 * 生成随机数和字符等
 *       
 * @date 2019年1月21日
 */
public class RandomUtil {

	/**
	 * 生成指定位数的数字 n>0
	 * @return
	 * @date 2019年1月21日
	 */
	public static String randomNumber(int n) {
		if(n<=0||n>10000000) {
			return "0";
		}
		StringBuilder maxStr = new StringBuilder();
		StringBuilder minStr = new StringBuilder("1");
		for(int i=0;i<n;i++) {
			maxStr.append("9");
		}
		for(int i=1;i<n;i++) {
			minStr.append("0");
		}
		int max = Integer.parseInt(maxStr.toString());
		int min = Integer.parseInt(minStr.toString());
		Random random = new Random();
		int nextInt = random.nextInt(max);
		if(nextInt<min) {
			nextInt+=min;
		}
		return nextInt+"";
	}
	
	public static void main(String[] args) {
		System.out.println(randomNumber(6));
	}
}
