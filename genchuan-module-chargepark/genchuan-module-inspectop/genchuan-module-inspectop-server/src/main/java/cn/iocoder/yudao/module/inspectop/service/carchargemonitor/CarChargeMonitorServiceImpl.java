package cn.iocoder.yudao.module.inspectop.service.carchargemonitor;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.carchargemonitor.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.carchargemonitor.CarChargeMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.carchargemonitor.CarChargeMonitorMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 汽车充电监测 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class CarChargeMonitorServiceImpl implements CarChargeMonitorService {

    @Resource
    private CarChargeMonitorMapper carChargeMonitorMapper;

    @Override
    public Long createCarChargeMonitor(CarChargeMonitorSaveReqVO createReqVO) {
        // 插入
        CarChargeMonitorDO carChargeMonitor = BeanUtils.toBean(createReqVO, CarChargeMonitorDO.class);
        carChargeMonitorMapper.insert(carChargeMonitor);

        // 返回
        return carChargeMonitor.getId();
    }

    @Override
    public void updateCarChargeMonitor(CarChargeMonitorSaveReqVO updateReqVO) {
        // 校验存在
        validateCarChargeMonitorExists(updateReqVO.getId());
        // 更新
        CarChargeMonitorDO updateObj = BeanUtils.toBean(updateReqVO, CarChargeMonitorDO.class);
        carChargeMonitorMapper.updateById(updateObj);
    }

    @Override
    public void deleteCarChargeMonitor(Long id) {
        // 校验存在
        validateCarChargeMonitorExists(id);
        // 删除
        carChargeMonitorMapper.deleteById(id);
    }

    @Override
        public void deleteCarChargeMonitorListByIds(List<Long> ids) {
        // 删除
        carChargeMonitorMapper.deleteByIds(ids);
        }


    private void validateCarChargeMonitorExists(Long id) {
        if (carChargeMonitorMapper.selectById(id) == null) {
            throw exception(CAR_CHARGE_MONITOR_NOT_EXISTS);
        }
    }

    @Override
    public CarChargeMonitorDO getCarChargeMonitor(Long id) {
        return carChargeMonitorMapper.selectById(id);
    }

    @Override
    public PageResult<CarChargeMonitorDO> getCarChargeMonitorPage(CarChargeMonitorPageReqVO pageReqVO) {
        return carChargeMonitorMapper.selectPage(pageReqVO);
    }

}