package cn.iocoder.yudao.module.energymgmt.service.energymonitor.areamonitor;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.energymgmt.controller.admin.energymonitor.areamonitor.vo.*;
import cn.iocoder.yudao.module.energymgmt.dal.dataobject.energymonitor.areamonitor.AreaMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.energymgmt.dal.mysql.energymonitor.areamonitor.AreaMonitorMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.energymgmt.enums.ErrorCodeConstants.*;

/**
 * 分区能耗 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AreaMonitorServiceImpl implements AreaMonitorService {

    @Resource
    private AreaMonitorMapper areaMonitorMapper;

    @Override
    public PageResult<AreaMonitorDO> getAreaMonitorPage(AreaMonitorPageReqVO pageReqVO) {
        return areaMonitorMapper.selectPage(pageReqVO);
    }

    @Override
    public AreaMonitorDO getAreaMonitor(Long id) {
        return areaMonitorMapper.selectById(id);
    }


    private void validateAreaMonitorExists(Long id) {
        if (areaMonitorMapper.selectById(id) == null) {
            throw exception(AREA_MONITOR_NOT_EXISTS);
        }
    }

    //    ———————————————————— 以上是所需实现层 ————————————————————

    @Override
    public Long createAreaMonitor(AreaMonitorSaveReqVO createReqVO) {
        // 插入
        AreaMonitorDO areaMonitor = BeanUtils.toBean(createReqVO, AreaMonitorDO.class);
        areaMonitorMapper.insert(areaMonitor);

        // 返回
        return areaMonitor.getId();
    }

    @Override
    public void updateAreaMonitor(AreaMonitorSaveReqVO updateReqVO) {
        // 校验存在
        validateAreaMonitorExists(updateReqVO.getId());
        // 更新
        AreaMonitorDO updateObj = BeanUtils.toBean(updateReqVO, AreaMonitorDO.class);
        areaMonitorMapper.updateById(updateObj);
    }

    @Override
    public void deleteAreaMonitor(Long id) {
        // 校验存在
        validateAreaMonitorExists(id);
        // 删除
        areaMonitorMapper.deleteById(id);
    }

    @Override
        public void deleteAreaMonitorListByIds(List<Long> ids) {
        // 删除
        areaMonitorMapper.deleteByIds(ids);
        }

}