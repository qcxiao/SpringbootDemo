package accumulate.hotswap.agentmain;

import accumulate.hotswap.agentmain.jar.Provider;
import utils.RandomUtil;

/**
 * @Author: yaodao
 * @Date: 2018/10/17 12:35
 */
public class Consumer {
    public static void main(String[] args) {
        for (;;) {
            new Provider().operation();
        }
    }
}