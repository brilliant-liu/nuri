package com.icop.schema.dao;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author: liukj
 * @date: 2020/6/7
 * @description：
 */
public interface CommonServices<T> extends IService<T> {

    default boolean insertList(List<T> entityList){
        if(CollectionUtil.isEmpty(entityList)){
            return false;
        }
        return SqlHelper.retBool(((CommonMapper)this.getBaseMapper()).insertBatchSomeColumn(entityList));
    }

}
