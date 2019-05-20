package cn.edu.gzmu.controller;

import cn.edu.gzmu.MockMvcInit;
import cn.edu.gzmu.model.entity.SysLog;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * @author echo
 * @version 1.0
 * @date 19-4-13 11:31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class SysLogControllerTest extends MockMvcInit {

    private SysLog sysLog;

    @Test
    @Override
    public void testGetResources() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/sysLogs").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links").exists())
                .andExpect(jsonPath("$._embedded").exists())
                .andExpect(jsonPath("$.page").exists())
                .andDo(document(""))
                .andReturn();
        assertEquals(mapper.readTree(mvcResult.getResponse().getContentAsString())
                        .get("_embedded").get("sysLogs").size(),
                2);
    }

    @Test
    @Override
    public void testGetExistResources() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/sysLogs/search/all").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links").exists())
                .andExpect(jsonPath("$._embedded").exists())
                .andExpect(jsonPath("$.page").exists())
                .andReturn();
        assertEquals(mapper.readTree(mvcResult.getResponse().getContentAsString())
                        .get("_embedded").get("sysLogs").size(),
                3);
    }

    @Test
    @Override
    public void testGetResource() throws Exception {
        getOneResource();
    }

    @Test
    @Override
    public void testPostResource() throws Exception {
        sysLog = getOneResource();
        sysLog.setName("测试添加");
        mockMvc.perform(post("/sysLogs").contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(sysLog)))
                .andExpect(status().isCreated());
    }

    @Test
    @Override
    public void testPutResource() throws Exception {
        sysLog = getOneResource();
        sysLog.setId(1L);
        mockMvc.perform(put("/sysLogs/1")
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(sysLog)))
                    .andExpect(status().isNoContent());
    }

    @Test
    @Override
    public void testPatchResource() throws Exception {
        sysLog = getOneResource();
        sysLog.setId(1L);
        mockMvc.perform(patch("/sysLogs/1")
                .contentType(APPLICATION_JSON)
                .content(mapper.writeValueAsString(sysLog)))
                .andExpect(status().isNoContent());
    }

    @Test
    @Override
    public void testDeleteExistResource() throws Exception {
        mockMvc.perform(delete("/sysLogs/3"))
                .andExpect(status().isNoContent());
    }

    private SysLog getOneResource() throws Exception {
        String content = mockMvc.perform(get("/sysLogs/1").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links").exists())
                .andExpect(jsonPath("$.name").value("测试日志1"))
                .andReturn().getResponse().getContentAsString();
        return mapper.readValue(content, SysLog.class);
    }
}
