package cn.edu.gzmu.validate;

import cn.edu.gzmu.constant.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-14 13:59
 */
@RestController
public class ValidateCodeController {

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    @GetMapping(SecurityConstants.VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void creatCode(HttpServletRequest request, HttpServletResponse response,
                          @PathVariable String type) throws Exception {
        validateCodeProcessorHolder.findValidateCodeProcessor(type)
                .create(new ServletWebRequest(request, response));
    }

}
