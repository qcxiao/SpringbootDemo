package accumulate.file;

import java.io.*;

/**
 * @Author: yaodao
 * @Date: 2018/7/29 19:16
 */
public class IOUtils {

    /**
     * 字节流读取
     * 根据文件名打印此文件的内容，以16进制整形方式打印
     * 一次读一个字节
     *
     * @param fileName
     */
    public static void pringHex(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        int b;
        int i = 1;

        while ((b = fis.read()) != -1) {
            if (b <= 0xf) {
                System.out.print(0);
            }
            System.out.print(Integer.toHexString(b) + " ");

            if (i++ % 10 == 0) {
                System.out.println();
            }
        }
        fis.close();
    }

    /**
     * 字节流读取
     * 一次读取N个字节
     *
     * @param fileName
     * @throws IOException
     */
    public static void pringHexByBytes(String fileName) throws IOException {
        System.out.println();
        FileInputStream fis = new FileInputStream(fileName);
        int b;
        int i = 1;
        byte[] buf = new byte[2 * 1024];

        while ((b = fis.read(buf, 0, buf.length)) != -1) {
            for (int j = 0; j < b; j++) {
                if (b <= 0xf) {
                    System.out.print(0);
                }
                System.out.print(Integer.toHexString(buf[j] & 0xff) + " ");

                if (i++ % 10 == 0) {
                    System.out.println();
                }
            }
        }
        fis.close();
    }

    /**
     * 利用单个字节读取文件，实现文件拷贝
     * @param src
     * @param des
     * @throws IOException
     */
    public static void copyFileByByte(File src, File des) throws IOException {
        if (!src.exists()) {
            throw new IllegalArgumentException("源文件" + src + "不存在");
        }
        if (!src.isFile()) {
            throw new IllegalArgumentException("源文件" + src + "不是文件");
        }
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(des);

        int b;
        while ((b = fis.read()) != -1){
            fos.write(b);
        }
        fis.close();
        fos.close();
    }

    /**
     * 利用批量字节读取文件，实现文件拷贝
     * @param src
     * @param des
     * @throws IOException
     */
    public static void copyFileByBytes(File src, File des) throws IOException {
        if (!src.exists()) {
            throw new IllegalArgumentException("源文件" + src + "不存在");
        }
        if (!src.isFile()) {
            throw new IllegalArgumentException("源文件" + src + "不是文件");
        }
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream(des);

        int b;
        byte[] buf = new byte[2 * 1024];
        while ((b = fis.read(buf, 0, buf.length)) != -1){
            fos.write(buf, 0, b);
        }
        fis.close();
        fos.close();
    }

    /**
     * 利用缓冲读取文件，实现文件拷贝
     * @param src
     * @param des
     * @throws IOException
     */
    public static void copyFileByBuffer(File src, File des) throws IOException {
        if (!src.exists()) {
            throw new IllegalArgumentException("源文件" + src + "不存在");
        }
        if (!src.isFile()) {
            throw new IllegalArgumentException("源文件" + src + "不是文件");
        }
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(des));

        int b;
        while ((b = bis.read()) != -1){
            bos.write(b);
            bos.flush();
        }
        bis.close();
        bos.close();
    }

    public static void main(String[] args) throws Exception {
        IOUtils.pringHex("./src/main/java/top/qcxiao/springbootdemo/accumulate/file/file.txt");
        IOUtils.pringHexByBytes("./src/main/java/top/qcxiao/springbootdemo/accumulate/file/file.txt");
        System.out.println();
        long start3 = System.currentTimeMillis();
        IOUtils.copyFileByByte(new File("./src/main/java/top/qcxiao/springbootdemo/accumulate/file/file.txt"), new File("./src/main/java/top/qcxiao/springbootdemo/accumulate/file/file1.txt"));
        long end3 = System.currentTimeMillis();
        System.out.println(end3 - start3);

        long start1 = System.currentTimeMillis();
        IOUtils.copyFileByBytes(new File("./src/main/java/top/qcxiao/springbootdemo/accumulate/file/file.txt"), new File("./src/main/java/top/qcxiao/springbootdemo/accumulate/file/file2.txt"));
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);

        long start2 = System.currentTimeMillis();
        IOUtils.copyFileByBuffer(new File("./src/main/java/top/qcxiao/springbootdemo/accumulate/file/file.txt"), new File("./src/main/java/top/qcxiao/springbootdemo/accumulate/file/file3.txt"));
        long end2 = System.currentTimeMillis();
        System.out.println(end2 - start2);



    }
}
