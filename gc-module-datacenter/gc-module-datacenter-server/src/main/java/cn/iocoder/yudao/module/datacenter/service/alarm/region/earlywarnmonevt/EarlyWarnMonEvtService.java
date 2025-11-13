package cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnmonevt;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmonevt.vo.EarlyWarnMonEvtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmonevt.vo.EarlyWarnMonEvtSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmonevt.EarlyWarnMonEvtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 按监测事件预警告警统计 Service 接口
 *
 * @author zcq
 */
public interface EarlyWarnMonEvtService {

    /**
     * 创建按监测事件预警告警统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEarlyWarnMonEvt(@Valid EarlyWarnMonEvtSaveReqVO createReqVO);

    /**
     * 更新按监测事件预警告警统计
     *
     * @param updateReqVO 更新信息
     */
    void updateEarlyWarnMonEvt(@Valid EarlyWarnMonEvtSaveReqVO updateReqVO);

    /**
     * 删除按监测事件预警告警统计
     *
     * @param id 编号
     */
    void deleteEarlyWarnMonEvt(Long id);

    /**
     * 获得按监测事件预警告警统计
     *
     * @param id 编号
     * @return 按监测事件预警告警统计
     */
    EarlyWarnMonEvtDO getEarlyWarnMonEvt(Long id);

    /**
     * 获得按监测事件预警告警统计分页
     *
     * @param pageReqVO 分页查询
     * @return 按监测事件预警告警统计分页
     */
    PageResult<EarlyWarnMonEvtDO> getEarlyWarnMonEvtPage(EarlyWarnMonEvtPageReqVO pageReqVO);

}