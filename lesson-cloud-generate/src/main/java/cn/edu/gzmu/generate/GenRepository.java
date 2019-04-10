package cn.edu.gzmu.generate;

import cn.edu.gzmu.generate.config.GenConfig;
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
 * 资源生成
 *
 * @author echo
 * @version 1.0
 * @date 19-4-9 21:05
 */
@Slf4j
@Order(2)
@Component
public class GenRepository implements ApplicationRunner {
    private final GenDatabaseUtil genDatabaseUtil;
    private final FreeMarkerConfigurationFactory freeMarkerConfigurationFactory;
    private final GenRepositoryConfig genRepositoryConfig;
    private final GenEntityConfig genEntityConfig;
    private final GenConfig genConfig;

    @Autowired
    public GenRepository(GenDatabaseUtil genDatabaseUtil, FreeMarkerConfigurationFactory freeMarkerConfigurationFactory,
                         GenRepositoryConfig genRepositoryConfig, GenEntityConfig genEntityConfig, GenConfig genConfig) {
        this.genDatabaseUtil = genDatabaseUtil;
        this.freeMarkerConfigurationFactory = freeMarkerConfigurationFactory;
        this.genRepositoryConfig = genRepositoryConfig;
        this.genEntityConfig = genEntityConfig;
        this.genConfig = genConfig;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void run(ApplicationArguments args) throws Exception {
        Configuration configuration = freeMarkerConfigurationFactory.createConfiguration();
        Template entityTemplate = configuration.getTemplate("Repository.ftl");
        List<String> tables = genDatabaseUtil.getTables();
        Map<String, Object> data = new HashMap<>(6);
        data.put("package_name", genRepositoryConfig.getPackageName());
        data.put("base_repository", genRepositoryConfig.getBaseRepository());
        data.put("now_version", genConfig.getVersion());
        File dir = new File(GenUtil.generateDir(genRepositoryConfig.getModuleName(), genRepositoryConfig.getPackageName()));
        if (!dir.exists()) {
            log.info("Package not exists.Now start create...The create result is {}", dir.mkdirs());
        }
        FileWriter fileWriter;
        for (String table : tables) {
            data.put("entity_class", genEntityConfig.getPackageName() + "." + GenUtil.underlineToHump(table, true));
            data.put("table_name", table);
            data.put("class_name", GenUtil.underlineToHump(table, true));
            fileWriter = new FileWriter(GenUtil.generateDir(genRepositoryConfig.getModuleName(), genRepositoryConfig.getPackageName())
                    + GenUtil.underlineToHump(table, true) + "Repository" + GenUtil.SUFFIX);
            entityTemplate.process(data, fileWriter);
            log.info("Table {} Repository generate succeed!", table);
        }
        log.info("Repository generate succeed!");
    }


}
