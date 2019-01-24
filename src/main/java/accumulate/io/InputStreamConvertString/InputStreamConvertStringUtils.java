package accumulate.io.InputStreamConvertString;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.google.common.io.ByteStreams;
import com.google.common.io.CharSource;
import com.google.common.io.CharStreams;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.impl.common.ReaderInputStream;

/**
 * InputStream和String之间的转换方法
 * https://blog.csdn.net/lmy86263/article/details/60479350
 *
 * @Author: yaodao
 * @Date: 2019/1/10 15:02
 */
public class InputStreamConvertStringUtils {
    /**
     * JDK原生提供
     */
    public static void util1_1(InputStream inputStream) {
        try {
            byte[] bytes = new byte[0];
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            String str = new String(bytes);
        } catch (Exception e) {

        }
    }

    public static void util1_2(InputStream inputStream) {
        try {
            String result = new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (Exception e) {

        }
    }

    public static void util1_3(InputStream inputStream) {
        try {
            String result = new BufferedReader(new InputStreamReader(inputStream))
                    .lines().parallel().collect(Collectors.joining(System.lineSeparator()));
        } catch (Exception e) {

        }
    }

    public static void util1_4(InputStream inputStream) {
        try {
            Scanner s = new Scanner(inputStream).useDelimiter("\\A");
            String str = s.hasNext() ? s.next() : "";
        } catch (Exception e) {

        }
    }

    public static void util1_5(InputStream inputStream) {
        try {
            String resource = new Scanner(inputStream).useDelimiter("\\Z").next();
        } catch (Exception e) {

        }
    }

    public static void util1_6(InputStream inputStream) {
        try {
            StringBuilder sb = new StringBuilder();
            String line;

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String str = sb.toString();
        } catch (Exception e) {

        }
    }

    public static void util1_7(InputStream inputStream) {
        try {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            String str = result.toString(StandardCharsets.UTF_8.name());
        } catch (Exception e) {

        }
    }

    public static void util1_8(InputStream inputStream) {
        try {
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            int result = bis.read();
            while (result != -1) {
                buf.write((byte) result);
                result = bis.read();
            }
            String str = buf.toString();
        } catch (Exception e) {

        }
    }

    /**
     * Apache Common提供
     */
    public static void util2_1(InputStream inputStream) {
        try {
            StringWriter writer = new StringWriter();
            IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8.name());
            String str = writer.toString();
        } catch (Exception e) {

        }
    }

    public static void util2_2(InputStream inputStream) {
        try {
            String str = IOUtils.toString(inputStream, "utf-8");
        } catch (Exception e) {

        }
    }

    /**
     * Google Guava提供
     */
    public static void util3_1(InputStream inputStream) {
        try {
            String str = CharStreams.toString(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        } catch (Exception e) {

        }
    }

    public static void util3_2(InputStream inputStream) {
        try {
            String str = new String(ByteStreams.toByteArray(inputStream));
        } catch (Exception e) {

        }
    }


    /**
     * String转化为InputStream
     */
    public static void util4_1(String str) {
        try {
            InputStream is = new ByteArrayInputStream(str.getBytes());

        } catch (Exception e) {

        }
    }

    public static void util5_1(String str) {
        try {
            InputStream targetStream = IOUtils.toInputStream(str, StandardCharsets.UTF_8.name());
        } catch (Exception e) {

        }
    }

    public static void util6_1(String str) {
        try {
            InputStream targetStream =
                    new ReaderInputStream(CharSource.wrap(str).openStream(), StandardCharsets.UTF_8.name());
        } catch (Exception e) {

        }
    }
}
