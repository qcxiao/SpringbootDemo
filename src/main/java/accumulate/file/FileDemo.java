package accumulate.file;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * @Author: yaodao
 * @Date: 2018/7/29 17:04
 */
@Slf4j
public class FileDemo {
    public static void main(String[] args) throws Exception {
        File file = new File("./src/main/java/top/qcxiao/springbootdemo/accumulate/file/1", "file.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException ioException){
                log.error("创建失败," + ioException.getMessage());
            }
        }
        log.debug("{}", file);
        log.debug("{}", file.getName());
        log.debug("{}", file.getParent());


        log.debug("{}", file.getAbsolutePath());
        log.debug("{}", file.getParentFile().getAbsolutePath());
        randomAccessFile();
    }


    public static void randomAccessFile() throws Exception {
        RandomAccessFile raf = new RandomAccessFile(new File("./src/main/java/top/qcxiao/springbootdemo/accumulate/file", "file.txt"), "rw");
        // write只写一个字节，同时指针移向下一个字节的位置，准备再次写
        raf.write('A');
        log.info("filePointer: {}", raf.getFilePointer());

        int i = 0x7fffffff; // 需要写4次，10进制的127
        raf.write(i >>> 24);
        raf.write(i >>> 16);
        raf.write(i >>> 8);
        raf.write(i);
        log.info("filePointer: {}", raf.getFilePointer());
        // 可以用raf.writeInt(i);代替以上整形的写入

        byte[] str = "中".getBytes("gbk");
        raf.write(str);

        // 读取时需要将指针指到对应的字节前
        raf.seek(0);
        // 从当前指针位置读一个字节
        int b = raf.read();
        log.info("{}", b);

        // 读取时需要将指针指到对应的字节前
        raf.seek(1);
        // 从当前指针位置读一个字节
        b = raf.read();
        log.info("{}", b);


        // 读取时需要将指针指到对应的字节前
        raf.seek(5);
        // 从当前指针位置读一个字节
        b = raf.read();
        log.info("{}", b);


        raf.seek(0);
        byte [] buf = new byte[(int)raf.length()];
        raf.read(buf);
        log.info("{}", Arrays.toString(buf));


        String str1 = new String(buf);
        log.info("{}", str1);
    }
}
