package transform;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: liukj
 * @date: 2020/9/9
 * @description：
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarPo {
    private Integer id;
    private String brand;
    private String name;
    private String size;

    private List<String> list;
}