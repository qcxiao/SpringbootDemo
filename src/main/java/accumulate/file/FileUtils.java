package accumulate.file;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yaodao
 * @Date: 2018/7/29 17:26
 */
@Slf4j
public class FileUtils {

    private static List<File> fileList = new ArrayList<>();

    /**
     * 列出指定目录包括子目录下的文件
     * @param dir
     * @throws IOException
     */
    public static List<File> listDirectory(File dir) throws IOException {
        if(!dir.exists()){
            throw new IllegalArgumentException(dir + "目录不存在");
        }
        if(!dir.isDirectory()){
            throw new IllegalArgumentException(dir + "不是文件目录");
        }

        File [] files = dir.listFiles();
        if (null != files && files.length > 0){
            for (File file : files){
                if(file.isDirectory()){
                    listDirectory(file);
                }else{
                    fileList.add(file);
                }
            }
        }
        return fileList;
    }

    public static void main(String[] args) throws Exception{
        List<File> fileList = FileUtils.listDirectory(new File("./"));
        for (File file : fileList){
            log.info("file:" + file);
        }
    }
}
