package accumulate.encode;

/**
 * @Author: yaodao
 * @Date: 2018/7/29 15:40
 *
 * GBK:
 * 1. 中文占用二个字节
 * 2. 英文占用一个字节
 * UTF-8:
 * 1. 中文占用三个字节
 * 2. 英文占用一个字节
 * UTF-16be（双字节编码）:
 * 1. 中文占用二个字节
 * 2. 英文占用二个字节
 */
public class EncodeDemo {
    public static void main(String[] args) throws Exception{
        // 文本文件可以是任意编码的字节序列
        String str = "哈哈ABC";

        byte [] bytes1 = str.getBytes("GBK");
        for (byte b : bytes1){
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }

        System.out.println();
        byte [] bytes2 = str.getBytes("UTF-8");
        for (byte b : bytes2){
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }

        System.out.println();
        byte [] bytes3 = str.getBytes("UTF-16be");
        for (byte b : bytes3){
            System.out.print(Integer.toHexString(b & 0xff) + " ");
        }

        System.out.println();
        String str1 = new String(bytes3, "UTF-16be"); // 必须带上编码
        System.out.println(str1);
    }
}
