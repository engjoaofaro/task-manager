package br.eng.joaofaro.taskmanager.exception;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * <p>This class remove some unused fields when an error ocurred in <code>>application</code</p>
 *
 * @see DefaultErrorAttributes
 *
 * @author João Faro    contato@joaofaro.eng.br on 15/11/20
 * @version 1.0.0
 */
public class ExceptionAttributesHandler extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        errorAttributes.remove("timestamp");
        errorAttributes.remove("path");
        return errorAttributes;
    }
}
