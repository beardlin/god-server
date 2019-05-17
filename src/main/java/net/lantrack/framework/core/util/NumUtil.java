package net.lantrack.framework.core.util;

/**
 * 随机生成4，5，6位数字
 *
 * @author coolcjava
 */
public class NumUtil {
    /**
     * 随机生成4位数字
     *
     * @return
     */
    public static int randomFour() {
        return (int) ((Math.random() * 9 + 1) * 1000);
    }

    /**
     * 随机生成5位数字
     *
     * @return
     */
    public static int randomFive() {
        return (int) ((Math.random() * 9 + 1) * 10000);
    }

    /**
     * 随机生成6位数字
     *
     * @return
     */
    public static int randomSix() {
        return (int) ((Math.random() * 9 + 1) * 100000);
    }

}
