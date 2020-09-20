package transform;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liukj
 * @date: 2020/9/9
 * @description：
 */
public class TestMain {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("9");
        list.add("8");
        CarPo carPo = CarPo.builder().id(1)
                .brand("BMW")
                .name("测试不一样字段")
                .size("99")
                .list(list)
                .build();
        CarVo carVo = CarCovertBasic.INSTANCE.toConvertVo(carPo);
        System.out.println(carVo);
    }
}
