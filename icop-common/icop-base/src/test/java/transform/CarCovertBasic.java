package transform;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author: liukj
 * @date: 2020/9/9
 * @description：
 */
@Mapper
public interface CarCovertBasic {
    CarCovertBasic INSTANCE = Mappers.getMapper(CarCovertBasic.class);

    @Mappings({
            @Mapping(source = "name", target = "carName"),
            @Mapping(source = "size", target = "vo"),
    })
    CarVo toConvertVo(CarPo source);
}
