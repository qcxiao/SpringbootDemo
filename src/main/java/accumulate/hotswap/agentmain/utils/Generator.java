package accumulate.hotswap.agentmain.utils;

import accumulate.hotswap.agentmain.jar.Provider;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;

/**
 * 手动修改生成字节码,用于调试
 * @Author: yaodao
 * @Date: 2018/10/17 20:23
 */
public class Generator {
    public static void main(String[] args) throws Exception {
        ClassReader cr = new ClassReader(Provider.class.getName());
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        ClassVisitor cv = new MyClassVisitor(cw);
        cr.accept(cv, Opcodes.ASM5);
        // 获取生成的class文件对应的二进制流
        byte[] code = cw.toByteArray();
        //将二进制流写到out/下
        FileOutputStream fos = new FileOutputStream("/Users/qcxiao/like/springbootdemo/src/main/java/accumulate/hotswap/agentmain/jar/Provider$1.class");
        fos.write(code);
        fos.close();
    }
}
