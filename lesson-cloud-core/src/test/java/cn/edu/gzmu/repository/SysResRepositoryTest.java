package cn.edu.gzmu.repository;


import cn.edu.gzmu.model.entity.SysRes;
import cn.edu.gzmu.repository.entity.SysResRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SysResRepositoryTest {

    @Resource
    private SysResRepository sysResRepository;

    @Test
    public void testSearchBySysRoleIds() {
        List<SysRes> sysRes = sysResRepository.searchBySysRoleIds(new Long[]{1L, 2L});
        sysRes.forEach(System.out::println);
    }
}
