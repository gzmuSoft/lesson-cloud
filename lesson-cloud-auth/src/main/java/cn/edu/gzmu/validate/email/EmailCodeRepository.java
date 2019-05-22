package cn.edu.gzmu.validate.email;

import cn.edu.gzmu.constant.SecurityConstants;
import cn.edu.gzmu.constant.ValidateCodeType;
import cn.edu.gzmu.validate.exception.ValidateCodeException;
import cn.edu.gzmu.validate.impl.AbstractValidateCodeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author echo
 * @version 1.0
 * @date 19-5-22 9:02
 */
@Component("emailValidateCodeRepository")
public class EmailCodeRepository extends AbstractValidateCodeRepository {
    @Override
    protected String buildKey(ServletWebRequest request, ValidateCodeType type) {
        String email = request.getHeader(SecurityConstants.PARAMETER_EMAIL);
        if (StringUtils.isBlank(email)) {
            throw new ValidateCodeException("请求中不存在邮箱号");
        }
        return "code:" + type.toString().toLowerCase() + ":" + email;
    }
}
