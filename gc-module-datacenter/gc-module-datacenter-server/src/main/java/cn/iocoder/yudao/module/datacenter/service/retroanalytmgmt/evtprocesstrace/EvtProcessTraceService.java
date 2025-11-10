package cn.iocoder.yudao.module.datacenter.service.retroanalytmgmt.evtprocesstrace;

import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.evtprocesstrace.vo.EvtProcessTracePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.evtprocesstrace.vo.EvtProcessTraceSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.retroanalytmgmt.evtprocesstrace.EvtProcessTraceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 事件过程回溯 Service 接口
 *
 * @author 亘川智城
 */
public interface EvtProcessTraceService {

    /**
     * 创建事件过程回溯
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvtProcessTrace(@Valid EvtProcessTraceSaveReqVO createReqVO);

    /**
     * 更新事件过程回溯
     *
     * @param updateReqVO 更新信息
     */
    void updateEvtProcessTrace(@Valid EvtProcessTraceSaveReqVO updateReqVO);

    /**
     * 删除事件过程回溯
     *
     * @param id 编号
     */
    void deleteEvtProcessTrace(Long id);

    /**
     * 获得事件过程回溯
     *
     * @param id 编号
     * @return 事件过程回溯
     */
    EvtProcessTraceDO getEvtProcessTrace(Long id);

    /**
     * 获得事件过程回溯分页
     *
     * @param pageReqVO 分页查询
     * @return 事件过程回溯分页
     */
    PageResult<EvtProcessTraceDO> getEvtProcessTracePage(EvtProcessTracePageReqVO pageReqVO);

}