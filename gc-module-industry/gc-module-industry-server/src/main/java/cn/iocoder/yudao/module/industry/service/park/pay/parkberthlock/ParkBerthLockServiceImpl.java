package cn.iocoder.yudao.module.industry.service.park.pay.parkberthlock;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkberthlock.vo.ParkBerthLockPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkberthlock.vo.ParkBerthLockSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkberthlock.ParkBerthLockDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.pay.parkberthlock.ParkBerthLockMapper;
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
 * 泊位锁定记录 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkBerthLockServiceImpl implements ParkBerthLockService {

    @Resource
    private ParkBerthLockMapper parkBerthLockMapper;

    @Override
    public Long createParkBerthLock(ParkBerthLockSaveReqVO createReqVO) {
        // 插入
        ParkBerthLockDO parkBerthLock = BeanUtils.toBean(createReqVO, ParkBerthLockDO.class);
        parkBerthLockMapper.insert(parkBerthLock);
        // 返回
        return parkBerthLock.getId();
    }

    @Override
    public void updateParkBerthLock(ParkBerthLockSaveReqVO updateReqVO) {
        // 校验存在
        validateParkBerthLockExists(updateReqVO.getId());
        // 更新
        ParkBerthLockDO updateObj = BeanUtils.toBean(updateReqVO, ParkBerthLockDO.class);
        parkBerthLockMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkBerthLock(Long id) {
        // 校验存在
        validateParkBerthLockExists(id);
        // 删除
        parkBerthLockMapper.deleteById(id);
    }

    private void validateParkBerthLockExists(Long id) {
        if (parkBerthLockMapper.selectById(id) == null) {
            throw exception(PARK_BERTH_LOCK_NOT_EXISTS);
        }
    }

    @Override
    public ParkBerthLockDO getParkBerthLock(Long id) {
        return parkBerthLockMapper.selectById(id);
    }

    @Override
    public PageResult<ParkBerthLockDO> getParkBerthLockPage(ParkBerthLockPageReqVO pageReqVO) {
        return parkBerthLockMapper.selectPage(pageReqVO);
    }

}
