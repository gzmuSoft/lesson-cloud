package cn.edu.gzmu.service.impl;

import cn.edu.gzmu.model.entity.Student;
import cn.edu.gzmu.model.entity.SysData;
import cn.edu.gzmu.model.entity.SysUser;
import cn.edu.gzmu.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * SysUser Service Impl
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {

//    @Override
////    public SysUser searchByAll(String user) {
////        return
////                sysUserRepository.findOne((Specification<SysUser>)
////                        (root, query, criteriaBuilder) ->
////                                criteriaBuilder.or(
////                                        criteriaBuilder.equal(root.get("name"), user),
////                                        criteriaBuilder.equal(root.get("email"), user),
////                                        criteriaBuilder.equal(root.get("phone"), user)
////                                )).orElseThrow(() ->
////                        new UserNotFoundException(String.format("用户 %s 不存在", user)));
////    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUser register(SysUser user, Student student, SysData school) {
//        Student exist = studentRepository.findById(student.getId()).orElseThrow(() ->
//                new ResourceNotFoundException("学生资源不存在！"));
//        Assert.isNull(exist.getUserId(), "当前学生已注册！");
//        if (!exist.getSchoolId().equals(school.getId()) || !exist.getNo().equals(student.getNo())
//                || !exist.getIdNumber().equals(student.getIdNumber()) || !exist.getName().equals(student.getName())) {
//            throw new ResourceNotFoundException("学生信息不匹配！");
//        }
//        if (existUser(user)) {
//            throw new ResourceExistException("用户已经存在！");
//        }
//        user.setEntityType(EntityType.STUDENT.value());
//        user.setEntityId(student.getId());
//        user.setPwd(bCryptPasswordEncoder.encode(user.getPwd()));
//        SysUser save = sysUserRepository.save(user);
//        exist.setUserId(save.getId());
//        studentRepository.save(exist);
        return null;
    }
//
//    /**
//     * 是否存在用户
//     *
//     * @param user 用户
//     * @return 结果
//     */
//    private boolean existUser(SysUser user) {
//        Assert.notNull(user.getName(), "用户名不能为空");
//        SysUser sysUser = new SysUser();
//        sysUser.setName(user.getName());
//        if (sysUserRepository.exists(Example.of(sysUser))) {
//            return true;
//        }
//        Assert.notNull(user.getEmail(), "邮箱不能为空");
//        sysUser = new SysUser();
//        sysUser.setEmail(user.getEmail());
//        if (sysUserRepository.exists(Example.of(sysUser))) {
//            return true;
//        }
//        Assert.notNull(user.getPhone(), "手机号不能为空");
//        sysUser = new SysUser();
//        sysUser.setPhone(user.getPhone());
//        return sysUserRepository.exists(Example.of(sysUser));
//    }

}
