package cn.iocoder.yudao.module.industry.service.park.user.parkuser;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkuser.vo.ParkUserPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkuser.vo.ParkUserSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkuser.ParkUserDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.user.parkuser.ParkUserMapper;
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
 * 停车系统用户 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkUserServiceImpl implements ParkUserService {

    @Resource
    private ParkUserMapper parkUserMapper;

    @Override
    public Long createParkUser(ParkUserSaveReqVO createReqVO) {
        // 插入
        ParkUserDO parkUser = BeanUtils.toBean(createReqVO, ParkUserDO.class);
        parkUserMapper.insert(parkUser);
        // 返回
        return parkUser.getId();
    }

    @Override
    public void updateParkUser(ParkUserSaveReqVO updateReqVO) {
        // 校验存在
        validateParkUserExists(updateReqVO.getId());
        // 更新
        ParkUserDO updateObj = BeanUtils.toBean(updateReqVO, ParkUserDO.class);
        parkUserMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkUser(Long id) {
        // 校验存在
        validateParkUserExists(id);
        // 删除
        parkUserMapper.deleteById(id);
    }

    private void validateParkUserExists(Long id) {
        if (parkUserMapper.selectById(id) == null) {
            throw exception(PARK_USER_NOT_EXISTS);
        }
    }

    @Override
    public ParkUserDO getParkUser(Long id) {
        return parkUserMapper.selectById(id);
    }

    @Override
    public PageResult<ParkUserDO> getParkUserPage(ParkUserPageReqVO pageReqVO) {
        return parkUserMapper.selectPage(pageReqVO);
    }

}
