package cn.iocoder.yudao.module.envirhealth.service.user.user;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.user.UserOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.user.UserPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.user.UserSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.UserDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.UserDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.user.UserMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.user.UserCodeGenerator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.USER_NOT_EXISTS;

/**
 * 系统用户 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserCodeGenerator codeGenerator;

    @Override
    public Long createUser(UserSaveReqVO createReqVO) {
        // 插入
        UserDO user = BeanUtils.toBean(createReqVO, UserDO.class);

        user.setId(null);
        user.setUserId(codeGenerator.generateUserId());

        userMapper.insert(user);
        // 返回
        return user.getId();
    }

    @Override
    public void updateUser(UserSaveReqVO updateReqVO) {
        // 校验存在
        validateUserExists(updateReqVO.getId());
        // 更新
        UserDO updateObj = BeanUtils.toBean(updateReqVO, UserDO.class);
        userMapper.updateById(updateObj);
    }

    @Override
    public void deleteUser(Long id) {
        // 校验存在
        validateUserExists(id);
        // 删除
        userMapper.deleteById(id);
    }

    private void validateUserExists(Long id) {
        if (userMapper.selectById(id) == null) {
            throw exception(USER_NOT_EXISTS);
        }
    }

    @Override
    public UserDO getUser(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public PageResult<UserDO> getUserPage(UserPageReqVO pageReqVO) {
        return userMapper.selectPage(pageReqVO);
    }

    @Override
    public List<UserOptionVO> getUserOptions() {

        List<UserDO> list;
        list = userMapper.selectList(
                new LambdaQueryWrapperX<UserDO>()
                        .eq(UserDO::getDeleted, 0)
                        .orderByDesc(UserDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, userDO -> {
            UserOptionVO vo = new UserOptionVO();
            vo.setLabel(userDO.getUserName());
            vo.setValue(userDO.getUserId());
            return vo;
        });
    }

    @Override
    public PageResult<UserDetailDO> getUserDetailPage(UserPageReqVO pageReqVO) {

        Long total = userMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<UserDetailDO> list = userMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }
}