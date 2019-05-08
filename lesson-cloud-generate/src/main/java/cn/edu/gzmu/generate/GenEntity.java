package cn.edu.gzmu.generate;

import cn.edu.gzmu.generate.config.GenConfig;
import cn.edu.gzmu.generate.config.GenEntityConfig;
import cn.edu.gzmu.generate.util.ColumnClass;
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
 * Entity实体类生成
 *
 * @author echo
 * @version 1.0
 * @date 19-4-9 21:05
 */
@Slf4j
@Order(1)
@Component
public class GenEntity implements ApplicationRunner {
    private final GenDatabaseUtil genDatabaseUtil;
    private final FreeMarkerConfigurationFactory freeMarkerConfigurationFactory;
    private final GenEntityConfig genEntityConfig;
    private final GenConfig genConfig;

    @Autowired
    public GenEntity(GenDatabaseUtil genDatabaseUtil, FreeMarkerConfigurationFactory freeMarkerConfigurationFactory,
                     GenEntityConfig genEntityConfig, GenConfig genConfig) {
        this.genDatabaseUtil = genDatabaseUtil;
        this.freeMarkerConfigurationFactory = freeMarkerConfigurationFactory;
        this.genEntityConfig = genEntityConfig;
        this.genConfig = genConfig;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void run(ApplicationArguments args) throws Exception {
        if (!genConfig.isGenEntity()) {
            log.info("Entity don't generate!");
            return;
        }
        Configuration configuration = freeMarkerConfigurationFactory.createConfiguration();
        Template entityTemplate = configuration.getTemplate("Entity.ftl");
        List<String> tables = genDatabaseUtil.getTables();
        Map<String, Object> data = new HashMap<>(6);
        data.put("package_name", genEntityConfig.getPackageName());
        data.put("base_entity", genEntityConfig.getBaseEntity());
        data.put("where_clause", genEntityConfig.getWhereClause());
        data.put("now_version", genConfig.getVersion());
        GenUtil.createDir(GenUtil.generateDir(genEntityConfig.getModuleName(), genEntityConfig.getPackageName()));
        FileWriter fileWriter;
        for (String table : tables) {
            List<ColumnClass> columns = genDatabaseUtil.getColumns(table);
            data.put("table_name", table);
            data.put("class_name", GenUtil.underlineToHump(table, true));
            data.put("columns", columns);
            File file = new File(GenUtil.generateDir(genEntityConfig.getModuleName(), genEntityConfig.getPackageName())
                    + GenUtil.underlineToHump(table, true) + GenUtil.SUFFIX);
            if (file.exists() && !genEntityConfig.isOverwrite()) {
                log.info("Table {} Entity is existed, do nothing!", table);
                continue;
            }
            fileWriter = new FileWriter(file);
            entityTemplate.process(data, fileWriter);
            log.info("Table {} Entity generate succeed!", table);
        }
        log.info("Entity generate succeed!");
    }


}
