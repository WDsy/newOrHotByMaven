package com.xyzh.util;
 /**
 * @author 作者：       周卫东
 * @E-mail 邮箱：       zhouweidong@gochinatv.com
 * @version 创建时间：2016年2月18日 下午5:39:34
 * 类说明
*/
public class UniqueStringGenerator {

    public  synchronized String getUniqueString()

    {

        if(generateCount > 99999)

            generateCount = 0;

        String uniqueNumber = Long.toString(System.currentTimeMillis()) + Integer.toString(generateCount);

        generateCount++;

        return uniqueNumber;

    }

    private static final int MAX_GENERATE_COUNT = 99999;

    private static int generateCount = 0;
    public static void main(String[] args) {
		System.out.println(new UniqueStringGenerator().getUniqueString());
	}
}


