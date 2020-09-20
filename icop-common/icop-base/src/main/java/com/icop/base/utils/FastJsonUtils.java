package com.icop.base.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.icop.base.enums.ExceptionCode;
import com.icop.base.exception.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: liukj_mci
 * @date: 2019/6/28
 * @description： 对象的String和对象的互转
 */
public class FastJsonUtils {

    private  static Logger logger = LoggerFactory.getLogger(FastJsonUtils.class);

    /**json根样式*/
    private static final String ROOT="$.";
    public static final String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YMD = "yyyy-MM-dd";

    /**
    * @Desc： 对象的String转Obj
    */
    public static Object string2Object(String objStr,Class objClass){
        return JSON.parseObject(objStr,objClass);
    }

    /**
    * @Desc： object转String
    */
    public static String object2String(Object object){
        if(null != object){
            return JSON.toJSONString(object);
        }
        return null;
    }

    /**
    * @Desc： Json String 转JsonObject对象
    */
    public static JSONObject string2JsonObj (String jsonStr){
        try{
            return JSON.parseObject(jsonStr);
        }catch (Exception e){
            throw new MyException(ExceptionCode.JSON_PARSE_ERROR);
        }
    }

    /**
    * @Desc： 获取json String串中指定路径节点下的值
    * @Param: jsonStr json格式字符串
    * @Param: path json格式字符串中的路径
    */
    public static Object getJsonValue(String jsonStr,String path){
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        JSONArray jsonArray = (JSONArray) JSONPath.eval(jsonObject,ROOT.concat(path));
        if(null == jsonArray){
            return null;
        }
        return jsonArray.get(0);
    }

    /**
     * 按照指定的日期格式，转化为Json串
     * @param object 带转化对象
     * @return
     */
    public static String toJsonStringWithDateFormat (Object object , String format){
        return JSON.toJSONStringWithDateFormat(object, format);
    }

}
