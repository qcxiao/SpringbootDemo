package accumulate.fallibility;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://www.toutiao.com/a6699409617690034700/?tt_from=mobile_qq&utm_campaign=client_share&timestamp=1559917466&app=news_article&utm_source=mobile_qq&utm_medium=toutiao_ios&req_id=20190607222426010023029039238816A&group_id=6699409617690034700
 * @Author: yaodao
 * @Date: 2019/6/8 14:39
 */
public class ArraysDemo {
    public static void main(String[] args) {
        correct_demo1();
    }


    public static void error_demo1(){
        int[] arr = {1,2,3};
        List list = Arrays.asList(arr);
        System.out.println(list.size());
        System.out.println(list.get(0));
        System.out.println(list.stream().collect(Collectors.toList()));
    }

    public static void correct_demo1(){
        int[] arr = {1,2,3};
        List list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        System.out.println(list.toString());
    }

    public static void error_demo2(){
        String[] arr = {"欢迎","关注","Java"};
        List list = Arrays.asList(arr);
        list.add("新增");
        list.remove("关注");
        System.out.println(list.toString());
    }
}
