package cn.iocoder.yudao.module.envirhealth.service.river.monitorstatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.monitorstatus.MonitorStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.monitorstatus.MonitorStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.MonitorStatusDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 监测状态字典表【通用复用】 Service 接口
 *
 * @author 芋道源码
 */
public interface MonitorStatusService {

    /**
     * 创建监测状态字典表【通用复用】
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMonitorStatus(@Valid MonitorStatusSaveReqVO createReqVO);

    /**
     * 更新监测状态字典表【通用复用】
     *
     * @param updateReqVO 更新信息
     */
    void updateMonitorStatus(@Valid MonitorStatusSaveReqVO updateReqVO);

    /**
     * 删除监测状态字典表【通用复用】
     *
     * @param id 编号
     */
    void deleteMonitorStatus(Long id);

    /**
     * 获得监测状态字典表【通用复用】
     *
     * @param id 编号
     * @return 监测状态字典表【通用复用】
     */
    MonitorStatusDO getMonitorStatus(Long id);

    /**
     * 获得监测状态字典表【通用复用】分页
     *
     * @param pageReqVO 分页查询
     * @return 监测状态字典表【通用复用】分页
     */
    PageResult<MonitorStatusDO> getMonitorStatusPage(MonitorStatusPageReqVO pageReqVO);

    /**
     * 获得监测状态下拉框选项
     * @return 下拉框选项列表
     */
    List<OptionVO> getMonitorStatusOptions();
}