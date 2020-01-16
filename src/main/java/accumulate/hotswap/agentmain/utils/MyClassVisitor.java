//package accumulate.hotswap.agentmain.utils;
//
//import jdk.internal.org.objectweb.asm.ClassVisitor;
//import jdk.internal.org.objectweb.asm.MethodVisitor;
//import jdk.internal.org.objectweb.asm.Opcodes;
//
///**
// * 自定义ClassVisitor
// * @Author: yaodao
// * @Date: 2018/10/17 20:03
// */
//public class MyClassVisitor extends ClassVisitor {
//    public MyClassVisitor(final ClassVisitor cv) {
//        super(Opcodes.ASM5, cv);
//    }
//
//    @Override
//    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
//        if (cv != null) {
//            cv.visit(version, access, name, signature, superName, interfaces);
//        }
//    }
//
//    @Override
//    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
//        //如果methodName是operation，则返回我们自定义的MethodVisitor
//        if ("operation".equals(name)) {
//            MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
//            return new MyMethodVisitor(mv);
//        }
//        if (cv != null) {
//            return cv.visitMethod(access, name, desc, signature, exceptions);
//        }
//
//        return null;
//    }
//}