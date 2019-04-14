package cn.edu.gzmu.generate.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库配置
 *
 * @author echo
 * @version 1.0
 * @date 19-4-9 21:05
 */
@Data
@Component
@ConfigurationProperties(prefix = "gzmu.generate.database")
public class GenDatabaseConfig {
    /**
     * jdbc url
     */
    private String url;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 驱动
     */
    private String driverClassName;
    /**
     * 表前缀
     */
    private String prefix = "";
    /**
     * 库名
     */
    private String catalog;
    /**
     * 基类字段，将会排除
     */
    private List<String> baseField = new ArrayList<>();
}
