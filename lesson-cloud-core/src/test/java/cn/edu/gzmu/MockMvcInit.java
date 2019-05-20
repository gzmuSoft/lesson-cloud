package cn.edu.gzmu;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.Before;
import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;


/**
 * @author echo
 * @version 1.0
 * @date 19-4-13 12:53
 */
public abstract class MockMvcInit {

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Autowired
    private WebApplicationContext ctx;

    protected MockMvc mockMvc;
    protected ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .apply(documentationConfiguration(this.restDocumentation))
                .build();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }

    /**
     * 获取一个资源
     *
     * @throws Exception 异常
     */
    public abstract void testGetResources() throws Exception;

    /**
     * 获取已经存在的资源
     *
     * @throws Exception 异常
     */
    public abstract void testGetExistResources() throws Exception;

    /**
     * 获取一个资源
     *
     * @throws Exception 异常
     */
    public abstract void testGetResource() throws Exception;

    /**
     * 添加一个资源
     *
     * @throws Exception 异常
     */
    public abstract void testPostResource() throws Exception;

    /**
     * 更新一个资源
     *
     * @throws Exception 异常
     */
    public abstract void testPutResource() throws Exception;

    /**
     * 更新一个资源
     *
     * @throws Exception 异常
     */
    public abstract void testPatchResource() throws Exception;

    /**
     * 删除资源
     *
     * @throws Exception 异常
     */
    public abstract void testDeleteExistResource() throws Exception;

}
