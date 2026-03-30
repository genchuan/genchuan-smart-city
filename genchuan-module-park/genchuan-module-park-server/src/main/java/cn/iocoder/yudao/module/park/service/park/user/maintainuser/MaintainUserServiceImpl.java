package cn.iocoder.yudao.module.park.service.park.user.maintainuser;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.maintainuser.vo.MaintainUserPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.maintainuser.vo.MaintainUserSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.maintainuser.MaintainUserDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.user.maintainuser.MaintainUserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.MAINTAIN_USER_NOT_EXISTS;

/**
 * 运维人员 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MaintainUserServiceImpl implements MaintainUserService {

    @Resource
    private MaintainUserMapper maintainUserMapper;

    @Override
    public Long createMaintainUser(MaintainUserSaveReqVO createReqVO) {
        // 插入
        MaintainUserDO maintainUser = BeanUtils.toBean(createReqVO, MaintainUserDO.class);
        maintainUserMapper.insert(maintainUser);
        // 返回
        return maintainUser.getId();
    }

    @Override
    public void updateMaintainUser(MaintainUserSaveReqVO updateReqVO) {
        // 校验存在
        validateMaintainUserExists(updateReqVO.getId());
        // 更新
        MaintainUserDO updateObj = BeanUtils.toBean(updateReqVO, MaintainUserDO.class);
        maintainUserMapper.updateById(updateObj);
    }

    @Override
    public void deleteMaintainUser(Long id) {
        // 校验存在
        validateMaintainUserExists(id);
        // 删除
        maintainUserMapper.deleteById(id);
    }

    private void validateMaintainUserExists(Long id) {
        if (maintainUserMapper.selectById(id) == null) {
            throw exception(MAINTAIN_USER_NOT_EXISTS);
        }
    }

    @Override
    public MaintainUserDO getMaintainUser(Long id) {
        return maintainUserMapper.selectById(id);
    }

    @Override
    public PageResult<MaintainUserDO> getMaintainUserPage(MaintainUserPageReqVO pageReqVO) {
        return maintainUserMapper.selectPage(pageReqVO);
    }

}
