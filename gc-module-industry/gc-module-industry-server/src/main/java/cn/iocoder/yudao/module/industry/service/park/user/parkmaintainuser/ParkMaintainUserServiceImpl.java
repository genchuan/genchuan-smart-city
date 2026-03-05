package cn.iocoder.yudao.module.industry.service.park.user.parkmaintainuser;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainuser.vo.ParkMaintainUserPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainuser.vo.ParkMaintainUserSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmaintainuser.ParkMaintainUserDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.user.parkmaintainuser.ParkMaintainUserMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 运维人员 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkMaintainUserServiceImpl implements ParkMaintainUserService {

    @Resource
    private ParkMaintainUserMapper parkMaintainUserMapper;

    @Override
    public Long createParkMaintainUser(ParkMaintainUserSaveReqVO createReqVO) {
        // 插入
        ParkMaintainUserDO parkMaintainUser = BeanUtils.toBean(createReqVO, ParkMaintainUserDO.class);
        parkMaintainUserMapper.insert(parkMaintainUser);
        // 返回
        return parkMaintainUser.getId();
    }

    @Override
    public void updateParkMaintainUser(ParkMaintainUserSaveReqVO updateReqVO) {
        // 校验存在
        validateParkMaintainUserExists(updateReqVO.getId());
        // 更新
        ParkMaintainUserDO updateObj = BeanUtils.toBean(updateReqVO, ParkMaintainUserDO.class);
        parkMaintainUserMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkMaintainUser(Long id) {
        // 校验存在
        validateParkMaintainUserExists(id);
        // 删除
        parkMaintainUserMapper.deleteById(id);
    }

    private void validateParkMaintainUserExists(Long id) {
        if (parkMaintainUserMapper.selectById(id) == null) {
            throw exception(PARK_MAINTAIN_USER_NOT_EXISTS);
        }
    }

    @Override
    public ParkMaintainUserDO getParkMaintainUser(Long id) {
        return parkMaintainUserMapper.selectById(id);
    }

    @Override
    public PageResult<ParkMaintainUserDO> getParkMaintainUserPage(ParkMaintainUserPageReqVO pageReqVO) {
        return parkMaintainUserMapper.selectPage(pageReqVO);
    }

}
