package arithmetic.sort;

import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * 冒泡排序
 * 稳定排序算法
 *
 * 参考文章：
 * 1. https://mp.weixin.qq.com/s/nyUumQ6mp2PPqdzpzE6C7Q
 *
 * @Author: yaodao
 * @Date: 2018/8/20 09:48
 */
@Slf4j
public class BubbleSort {

    static List<Integer> list = Arrays.asList(5, 8, 6, 3, 9, 2, 1, 7);

    public static void main(String[] args) {
        int[] array = Ints.toArray(list);
        generalBubbleSort(array);
        log.info("{}", array);
    }

    /**
     * 普通冒泡排序
     *
     * @param obj
     */
    public static void generalBubbleSort(int[] obj) {
        int temp;
        for (int i = 0; i < obj.length; i++) {
            for (int j = 0; j < obj.length - i - 1; j++) {
                if (obj[j] > obj[j + 1]) {
                    temp = obj[j + 1];
                    obj[j + 1] = obj[j];
                    obj[j] = temp;
                }
            }
        }
    }

    /**
     * 优化后的冒泡排序，主要优化点是观察最后几轮排序是否已经有序了，当排序近乎有序的一组数时此优化作用明显
     *
     * @param obj
     */
    public static void optimizeBubbleSort1(int[] obj) {
        int temp;
        for (int i = 0; i < obj.length; i++) {
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            for (int j = 0; j < obj.length - i - 1; j++) {
                if (obj[j] > obj[j + 1]) {
                    temp = obj[j + 1];
                    obj[j + 1] = obj[j];
                    obj[j] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }


    /**
     * 对于部分有序的序列进行排序，如:[2,4,3,1,5,6,7,8]，后面部分已经有序了，
     * 所以只需要比较到无序的边界即可，当排序近乎有序的一组数时此优化作用更明显
     * @param array
     */
    private static void optimizeBubbleSort2(int array[]) {
        int temp = 0;
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        //无序数列的边界，每次比较只需要比到这里为止
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                    //把无序数列的边界更新为最后一次交换元素的位置
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if (isSorted) {
                break;
            }
        }
    }
}
