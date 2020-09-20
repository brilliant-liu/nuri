package com.icop.schema.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icop.schema.dao.CommonMapper;
import com.icop.schema.dao.CommonServices;
/**
 * @author: liukj
 * @date: 2020/6/7
 * @description：
 */
public class CommonServicesImpl<M extends CommonMapper<T>,T> extends ServiceImpl<M,T> implements CommonServices<T> {



}
