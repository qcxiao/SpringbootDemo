//package accumulate.hotswap.agentmain;
//
//import com.sun.tools.attach.*;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.IOException;
//import java.util.List;
//
///**
// * @Author: yaodao
// * @Date: 2018/10/17 11:55
// */
//@Slf4j
//public class JVMTIThread {
//    public static void main(String[] args) throws Exception {
//        List<VirtualMachineDescriptor> linked_list = VirtualMachine.linked_list();
//        for (VirtualMachineDescriptor vmd : linked_list) {
//            if (vmd.displayName().endsWith("Consumer")) {
//                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
//                virtualMachine.loadAgent("/Users/qcxiao/like/springbootdemo/src/main/java/accumulate/hotswap/agentmain/jar/agentmain.jar", "cxs");
//                log.info("loaded success");
//                virtualMachine.detach();
//            }
//        }
//    }
//}
