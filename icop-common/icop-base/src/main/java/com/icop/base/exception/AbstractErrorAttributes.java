package com.icop.base.exception;

import com.icop.base.entities.R;
import com.icop.base.enums.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: liukj
 * @date: 2020/7/3
 * @description：
 */
public abstract class AbstractErrorAttributes{


//    public Map<String, Object> getErrorAttributes(WebRequest webRequest) {
//        RequestAttributes requestAttributes;
//        R.fail(ExceptionCode.)
//    }

    public void getCode(WebRequest webRequest){

    }

    private void getCode(R r ,Map<String, Object> errorAttributes, RequestAttributes requestAttributes) {
        Integer status = getAttribute(requestAttributes, "javax.servlet.error.status_code");
        //r.setCode()
        if (status == null) {
            errorAttributes.put("status", 999);
            errorAttributes.put("error", "None");
            return;
        }
        errorAttributes.put("status", status);
        try {
            errorAttributes.put("error", HttpStatus.valueOf(status).getReasonPhrase());
        }
        catch (Exception ex) {
            // Unable to obtain a reason
            errorAttributes.put("error", "Http Status " + status);
        }
    }

    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }
}
