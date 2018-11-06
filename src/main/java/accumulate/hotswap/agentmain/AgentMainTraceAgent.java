package accumulate.hotswap.agentmain;

import accumulate.hotswap.agentmain.jar.Provider;
import accumulate.hotswap.agentmain.utils.MyClassVisitor;
import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * https://www.jianshu.com/p/6096bfe19e41
 * 利用agentmain配合asm，实现热部署和字节码重组
 * @Author: yaodao
 * @Date: 2018/10/17 11:46
 */
@Slf4j
public class AgentMainTraceAgent {

    public static void agentmain(String agentArgs, Instrumentation inst) throws Exception {
        log.info("Agent Main called, agentArgs:" + agentArgs);
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                    ProtectionDomain protectionDomain, byte[] classfileBuffer)
                    throws IllegalClassFormatException {
                log.info("agentmain load Class:" + className);

                log.info("modify bytecode start...");
                /**
                 * 通过asm修改字节码
                 */
                ClassReader cr = null;
                try {
                    cr = new ClassReader(Provider.class.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
                ClassVisitor cv = new MyClassVisitor(cw);
                cr.accept(cv, Opcodes.ASM5);
                log.info("modify bytecode success");

                return cw.toByteArray();
                //return classfileBuffer;
            }
        }, true);
        inst.retransformClasses(Provider.class);
    }
}
