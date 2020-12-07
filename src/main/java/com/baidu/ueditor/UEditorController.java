package com.baidu.ueditor;

import com.baidu.ueditor.configuration.UEditorBaseState;
import com.baidu.ueditor.configuration.UEditorDirective;
import com.baidu.ueditor.exception.UEditorException;
import com.baidu.ueditor.hanlder.UEditorContextFactory;
import com.baidu.ueditor.util.UEditorAssert;
import com.baidu.ueditor.util.UEditorMessageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(
        origins = {"*"},
        originPatterns = {"/**"},
        allowCredentials = "false",
        methods = { RequestMethod.GET, RequestMethod.POST },
        allowedHeaders = {"*"},
        maxAge = 3600 * 24
)
@Controller
public class UEditorController {

    private final UEditorContextFactory factory;

    public UEditorController(UEditorContextFactory factory) {
        this.factory = factory;
    }


    @ResponseBody
    @RequestMapping(value = {"/ueditor/handler"}, method = {RequestMethod.GET, RequestMethod.POST})
    public Object doAction(HttpServletRequest request) throws UEditorException {
        String action = request.getParameter("action");
        UEditorDirective directive = UEditorDirective.directiveOf(action)
                .orElseThrow(() -> UEditorException.build(UEditorMessageUtils.get("com.baidu.ueditor.invalid-action.message")));
        UEditorAssert.notDirective(directive);
        return factory.doAction(request, directive);
    }

    @ControllerAdvice
    static class UEditorGlobalExceptionHandler {

        private final static Logger logger = LoggerFactory.getLogger(UEditorGlobalExceptionHandler.class);

        @ResponseBody
        @ExceptionHandler(UEditorException.class)
        public UEditorBaseState ueditorException(UEditorException e) {
            logger.error("UEditorException", e);
            return e.getState();
        }
    }

}
