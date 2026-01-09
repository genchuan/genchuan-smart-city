package cn.iocoder.yudao.module.industry.service.park.pay.parkrealtime;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkrealtime.vo.ParkRealTimePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkrealtime.vo.ParkRealTimeSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkrealtime.ParkRealTimeDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.pay.parkrealtime.ParkRealTimeMapper;
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
 * 停车泊位实时状态 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkRealTimeServiceImpl implements ParkRealTimeService {

    @Resource
    private ParkRealTimeMapper parkRealTimeMapper;

    @Override
    public Long createParkRealTime(ParkRealTimeSaveReqVO createReqVO) {
        // 插入
        ParkRealTimeDO parkRealTime = BeanUtils.toBean(createReqVO, ParkRealTimeDO.class);
        parkRealTimeMapper.insert(parkRealTime);
        // 返回
        return parkRealTime.getId();
    }

    @Override
    public void updateParkRealTime(ParkRealTimeSaveReqVO updateReqVO) {
        // 校验存在
        validateParkRealTimeExists(updateReqVO.getId());
        // 更新
        ParkRealTimeDO updateObj = BeanUtils.toBean(updateReqVO, ParkRealTimeDO.class);
        parkRealTimeMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkRealTime(Long id) {
        // 校验存在
        validateParkRealTimeExists(id);
        // 删除
        parkRealTimeMapper.deleteById(id);
    }

    private void validateParkRealTimeExists(Long id) {
        if (parkRealTimeMapper.selectById(id) == null) {
            throw exception(PARK_REAL_TIME_NOT_EXISTS);
        }
    }

    @Override
    public ParkRealTimeDO getParkRealTime(Long id) {
        return parkRealTimeMapper.selectById(id);
    }

    @Override
    public PageResult<ParkRealTimeDO> getParkRealTimePage(ParkRealTimePageReqVO pageReqVO) {
        return parkRealTimeMapper.selectPage(pageReqVO);
    }

}
