package com.icop.schema.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author: liukj
 * @date: 2020/6/6
 * @description：
 */
public interface CommonMapper<T> extends BaseMapper<T> {
    int insertBatchSomeColumn(List<T> var1);
}
