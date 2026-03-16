package cn.iocoder.yudao.module.envirhealth.service.river.monitorstatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.monitorstatus.MonitorStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.monitorstatus.MonitorStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.MonitorStatusDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.river.MonitorStatusMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.MONITOR_STATUS_NOT_EXISTS;

/**
 * 监测状态字典表【通用复用】 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MonitorStatusServiceImpl implements MonitorStatusService {

    @Resource
    private MonitorStatusMapper monitorStatusMapper;

    @Override
    public Long createMonitorStatus(MonitorStatusSaveReqVO createReqVO) {
        // 插入
        MonitorStatusDO monitorStatus = BeanUtils.toBean(createReqVO, MonitorStatusDO.class);
        monitorStatusMapper.insert(monitorStatus);
        // 返回
        return monitorStatus.getId();
    }

    @Override
    public void updateMonitorStatus(MonitorStatusSaveReqVO updateReqVO) {
        // 校验存在
        validateMonitorStatusExists(updateReqVO.getId());
        // 更新
        MonitorStatusDO updateObj = BeanUtils.toBean(updateReqVO, MonitorStatusDO.class);
        monitorStatusMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonitorStatus(Long id) {
        // 校验存在
        validateMonitorStatusExists(id);
        // 删除
        monitorStatusMapper.deleteById(id);
    }

    private void validateMonitorStatusExists(Long id) {
        if (monitorStatusMapper.selectById(id) == null) {
            throw exception(MONITOR_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public MonitorStatusDO getMonitorStatus(Long id) {
        return monitorStatusMapper.selectById(id);
    }

    @Override
    public PageResult<MonitorStatusDO> getMonitorStatusPage(MonitorStatusPageReqVO pageReqVO) {
        return monitorStatusMapper.selectPage(pageReqVO);
    }

}