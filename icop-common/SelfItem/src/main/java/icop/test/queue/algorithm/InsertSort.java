package icop.test.queue.algorithm;

import java.util.Arrays;

/**
 * @author: liukj
 * @date: 2020/7/31
 * @description： 插入排序
 */
public class InsertSort {


    /**
     * 选择排序
     * 算法思想：
     * 两层循环，
     * 内层循环：每循环一次，确定一个最大或者最小值。
     * 内层循环：在剩余位置中寻找最大或者最小值的位置，每次循环结束后，更新最大或者最小值
     *
     * 下面案例为升序排序
     * @param src
     */
    public static void selectSort( int[] src){
        if(null==src || src.length<=1){
            return;
        }
        for(int i = 0; i<src.length -1;i++){
            // 记录最小的位置
            int min = i;
            for (int j = i+1 ; j < src.length; j++) {
                // 记录最小的值位置
                if(src[j] < src[min]){
                    min = j;
                }
            }
            // 表明i位置不是最小的，和最小的位置交换数据
            if(min != i){
                int temp = src[min];
                src[min] = src[i];
                src[i]= temp;
            }
        }

    }

    /**
     * 冒泡排序
     *
     * 思想：
     * 两层循环
     * 外层循环：每次循环，都可以确定一个最大值或者最小值
     * 内层循环：每次每次循环都在剩余的数据内进行，依次把相邻的元素比较，根据大小进行互换位置，每次循环完后，都能把最大或者最小的移动到两端
     *
     * 算法优化：
     * 增加一个标志位，如果内层循环结束，标志位未发生变化，说明数据已经是有序的了，直接跳出循环即可
     *
     * 下面案例为升序排序
     * @param src
     */
    public static void bubblingSort(int[] src){
        if(null==src || src.length<=1){
            return;
        }

        for(int i=0; i<src.length-1;i++){
            boolean flag = true;
            for(int j = 0; j<src.length-1-i;j++){
                //把较大的元素往后放
                if(src[j] > src[j+1]){
                    flag = false;
                    src[j] = src[j] ^ src[j+1];
                    src[j+1] = src[j] ^ src[j+1];
                    src[j] = src[j] ^ src[j+1];
                }
            }
            if(flag){
                break;
            }
        }

    }

    /**
     * 插入排序：
     *
     * 两层循环
     * 在开始的时候会通过 哨兵 来保存当前位置的值，后面再向前插入的时候，可以依次往后覆盖，最后将哨兵插入即可
     * 外层循环：用来控制待插入数据i的位置
     * 内存循环: 将待插入的数据i和其之前的数据依次进行大小比较，找到合适的位置后插入数据
     *
     * 下面是从大到小排序
     * @param src
     */
    public static void insertSort(int[] src){

        if(src == null || src.length < 1){
            return;
        }

        for (int i = 1; i < src.length; i++) {
            int j = i;
            int temp = src[j];
            for (; j >0; j--) {
                // 把小的数据往后移
                if(temp > src[j-1]){
                    src[j] = src[j-1];
                }else {
                    break;
                }
            }
            src[j] = temp;
        }
    }


    /**
     * 快速排序
     *
     * 每次随机选择一个基准（piovt）,将其作为参考值
     * 根据遍历比较左右指针，比基准小的放到左边，比基准大的放到右边，同时移动对应的指针。
     * 当左右指针相同的时候，此时数组会被分割为左右两部分，对左右两部分进行递归调用。知道长度为1时返回。
     *
     * 从小到大排序
     *
     * @param src
     * @param l 左指针（头元素位置）
     * @param r 右指针（尾元素位置）
     */
    public static void quicklySort(int[] src, int l, int r){

        if(l<r){
            int nodeL = l;
            int nodeR = r;
            // 每次都取最右边的一个作为基准（也可以随机取出）
            int base = src[r];

            while(nodeL < nodeR){

                // 遍历左指针(小于基准的数据，指针后移，知道有个大于基准的指针后，将其移到nodeR的位置)
                while(nodeL<nodeR && src[nodeL] <= base){
                    nodeL ++;
                }
                if(nodeL<nodeR){
                    src[nodeR--] = src[nodeL];
                }

                // 遍历右指针(大于基准的数据，指针前移，知道有个小于基准的指针后，将其移到nodeL的位置)
                while(nodeL<nodeR && src[nodeR] >= base){
                    nodeR --;
                }
                if(nodeL<nodeR){
                    src[nodeL++] = src[nodeR];
                }
            }
            src[nodeL] = base;
            //递归左子树
            quicklySort(src,l, nodeL-1);
            //递归左子树
            quicklySort(src,nodeL+1, r);
        }
    }

    /**
     *
     */
    public static int[] mergerSort(int src[]){
        if(null == src || src.length <1 ){
            return src;
        }

        int length = src.length;
        int[] result = new int[length];

        // block用来控制每次比较块的大小,即块的长度
        // start用来记录每次开始的位置
        int block,start;

        for(block = 1;block < length<<1; block=block << 1){

            for(start =0;start<length;start = start+(block <<1)){
                // low记录的时result中的位置
                int low = start;
                // middle为两个块大小的中间位置
                int middle = Math.min((start + block), length);
                int high = Math.min((start+block<<1),length);

                //记录两个块的起始下标和结束下表
                int start1 = low,end1=middle;
                int start2 = middle,end2=high;

                //分别对block进行归并
                while (start1<end1 && start2<end2){
                    // 分别按照顺序比较两个块中的数据，把最小的数据放大新的数组中
                    result[low++] = src[start1] < src[start2] ? src[start1++] : src[start2++];
                }
                //（注意，两个block间的数据时无序的，但相同block内的数据有序，因此，直接添加到新数组即可）
                while(start1<end1){
                    result[low++] = src[start1++];
                }

                while(start2<end2){
                    result[low++] = src[start2++];
                }
            }
            // 保存原始数组，并更新已经排序后的组给原始数组，即交换
            int [] temp = src;
            src = result;
            result = temp;
        }
        result = src;
        return result;
    }

    public static void main(String[] args) {
        //int [] src = {};
        int [] src = {11,7,2,3,7,99,81,1,0,0,9,1};
        //int[] ints = mergerSort(src);
        //quicklySort(src,0,src.length-1);
        //selectSort(src);
        //System.out.println(13&17);
        //System.out.println(Arrays.toString(ints));
        //int [] src = {1,2,4,32,8,9,17,20,39,100};

        /*int i = 1,j=6;
        System.out.println(i+"==="+j);
        System.out.println(src[i++]+"==="+src[j--]);
        System.out.println(i+"==="+j);


        System.out.println(-5 >>> 1);
        System.out.println(5 >> 1);*/
        // quickSorts(src,0,src.length-1);
       /* System.out.println(Arrays.toString(src));

        int i =6,j=9;

        System.out.println(i+"+"+j);
        int s = Math.min(i++,j++);
        System.out.println(i+"+"+j+"s"+s);*/
       // System.out.println(Arrays.toString(insertSort(src)));
        // System.out.println(Arrays.toString(selectSort(src)));
        //System.out.println(Arrays.toString(maopao(src)));
    }
}
