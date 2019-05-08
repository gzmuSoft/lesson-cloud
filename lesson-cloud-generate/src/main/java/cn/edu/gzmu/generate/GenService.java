package cn.edu.gzmu.generate;

import cn.edu.gzmu.generate.config.*;
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
 * Service资源生成
 *
 * @author echo
 * @version 1.0
 * @date 19-4-9 21:05
 */
@Slf4j
@Order(4)
@Component
public class GenService implements ApplicationRunner {
    private final GenDatabaseUtil genDatabaseUtil;
    private final FreeMarkerConfigurationFactory freeMarkerConfigurationFactory;
    private final GenServiceConfig genServiceConfig;
    private final GenRepositoryConfig genRepositoryConfig;
    private final GenEntityConfig genEntityConfig;
    private final GenConfig genConfig;

    @Autowired
    public GenService(GenDatabaseUtil genDatabaseUtil, FreeMarkerConfigurationFactory freeMarkerConfigurationFactory,
                      GenServiceConfig genServiceConfig, GenRepositoryConfig genRepositoryConfig, GenEntityConfig genEntityConfig,
                      GenConfig genConfig) {
        this.genDatabaseUtil = genDatabaseUtil;
        this.freeMarkerConfigurationFactory = freeMarkerConfigurationFactory;
        this.genServiceConfig = genServiceConfig;
        this.genRepositoryConfig = genRepositoryConfig;
        this.genEntityConfig = genEntityConfig;
        this.genConfig = genConfig;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void run(ApplicationArguments args) throws Exception {
        if (!genConfig.isGenService()) {
            log.info("ServiceImpl don't generate!");
            return;
        }
        Configuration configuration = freeMarkerConfigurationFactory.createConfiguration();
        Template serviceImplTemplate = configuration.getTemplate("ServiceImpl.ftl");
        Template serviceTemplate = configuration.getTemplate("Service.ftl");
        List<String> tables = genDatabaseUtil.getTables();
        Map<String, Object> data = new HashMap<>(6);
        data.put("package_name", genServiceConfig.getPackageName());
        data.put("base_service", genServiceConfig.getBaseService());
        data.put("now_version", genConfig.getVersion());
        GenUtil.createDir(GenUtil.generateDir(genServiceConfig.getModuleName(), genServiceConfig.getPackageName()));
        FileWriter fileWriter;
        for (String table : tables) {
            String className = GenUtil.underlineToHump(table, true);
            data.put("entity_path", genEntityConfig.getPackageName() + "." + className);
            data.put("repository_class", genRepositoryConfig.getPackageName() + "." + className);
            data.put("class_name", className);
            File serviceImpl = new File(GenUtil.generateDir(genServiceConfig.getModuleName(), genServiceConfig.getPackageName(), "impl")
                    + className + "ServiceImpl" + GenUtil.SUFFIX);
            if (serviceImpl.exists() && !genServiceConfig.isOverwrite()) {
                log.info("Table {} ServiceImpl is existed, do nothing!", table);
                continue;
            }
            fileWriter = new FileWriter(serviceImpl);
            serviceImplTemplate.process(data, fileWriter);
            log.info("Table {} ServiceImpl generate succeed!", table);
            File service = new File(GenUtil.generateDir(genServiceConfig.getModuleName(), genServiceConfig.getPackageName())
                    + className + "Service" + GenUtil.SUFFIX);
            if (service.exists() && !genServiceConfig.isOverwrite()) {
                log.info("Table {} Service is existed, do nothing!", table);
                continue;
            }
            fileWriter = new FileWriter(service);
            serviceTemplate.process(data, fileWriter);
            log.info("Table {} Service generate succeed!", table);
        }
        log.info("ServiceImpl and Service generate succeed!");
    }
}
