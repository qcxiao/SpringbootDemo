//package accumulate.hotswap.agentmain.utils;
//
//import jdk.internal.org.objectweb.asm.MethodVisitor;
//import jdk.internal.org.objectweb.asm.Opcodes;
//import static jdk.internal.org.objectweb.asm.Opcodes.GETSTATIC;
//import static jdk.internal.org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
//
///**
// * 自定义MethodVisitor
// * @Author: yaodao
// * @Date: 2018/10/17 20:03
// */
//public class MyMethodVisitor extends MethodVisitor {
//
//    public MyMethodVisitor(MethodVisitor mv) {
//        super(Opcodes.ASM5, mv);
//    }
//
//    // 进入方法体内调用此方法
//    @Override
//    public void visitCode() {
//        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//        mv.visitLdcInsn("========start=========");
//        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
//        super.visitCode();
//    }
//
//    // 每执行一个指令都会调用此方法
//    @Override
//    public void visitInsn(int opcode) {
//        if (opcode == Opcodes.RETURN) {
//            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//            mv.visitLdcInsn("========end=========");
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
//        }
//        super.visitInsn(opcode);
//    }
//}