package cn.edu.gzmu.generate;

import cn.edu.gzmu.generate.config.GenConfig;
import cn.edu.gzmu.generate.config.GenControllerConfig;
import cn.edu.gzmu.generate.config.GenEntityConfig;
import cn.edu.gzmu.generate.config.GenRepositoryConfig;
import cn.edu.gzmu.generate.util.GenDatabaseUtil;
import cn.edu.gzmu.generate.util.GenUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller资源生成
 *
 * @author echo
 * @version 1.0
 * @date 19-4-9 21:05
 */
@Slf4j
@Order(3)
@Component
public class GenController implements ApplicationRunner {
    private final GenDatabaseUtil genDatabaseUtil;
    private final FreeMarkerConfigurationFactory freeMarkerConfigurationFactory;
    private final GenControllerConfig genControllerConfig;
    private final GenRepositoryConfig genRepositoryConfig;
    private final GenEntityConfig genEntityConfig;
    private final GenConfig genConfig;

    @Autowired
    public GenController(GenDatabaseUtil genDatabaseUtil, FreeMarkerConfigurationFactory freeMarkerConfigurationFactory,
                         GenControllerConfig genControllerConfig, GenRepositoryConfig genRepositoryConfig, GenEntityConfig genEntityConfig,
                         GenConfig genConfig) {
        this.genDatabaseUtil = genDatabaseUtil;
        this.freeMarkerConfigurationFactory = freeMarkerConfigurationFactory;
        this.genControllerConfig = genControllerConfig;
        this.genRepositoryConfig = genRepositoryConfig;
        this.genEntityConfig = genEntityConfig;
        this.genConfig = genConfig;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void run(ApplicationArguments args) throws Exception {
        Configuration configuration = freeMarkerConfigurationFactory.createConfiguration();
        Template entityTemplate = configuration.getTemplate("Controller.ftl");
        List<String> tables = genDatabaseUtil.getTables();
        Map<String, Object> data = new HashMap<>(6);
        data.put("package_name", genControllerConfig.getPackageName());
        data.put("base_controller", genControllerConfig.getBaseController());
        data.put("now_version", genConfig.getVersion());
        GenUtil.createDir(GenUtil.generateDir(genControllerConfig.getModuleName(), genControllerConfig.getPackageName()));
        FileWriter fileWriter;
        for (String table : tables) {
            String className = GenUtil.underlineToHump(table, true);
            data.put("entity_path", genEntityConfig.getPackageName() + "." + className);
            data.put("repository_class", genRepositoryConfig.getPackageName() + "." + className);
            data.put("rest_path", GenUtil.toPlural(table));
            data.put("class_name", className);
            fileWriter = new FileWriter(GenUtil.generateDir(genControllerConfig.getModuleName(), genControllerConfig.getPackageName())
                    + className + "Controller" + GenUtil.SUFFIX);
            entityTemplate.process(data, fileWriter);
            log.info("Table {} Controller generate succeed!", table);
        }
        log.info("Controller generate succeed!");
    }


}
