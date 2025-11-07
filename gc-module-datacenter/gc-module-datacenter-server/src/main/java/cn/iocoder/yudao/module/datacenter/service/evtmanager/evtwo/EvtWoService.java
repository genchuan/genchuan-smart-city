package cn.iocoder.yudao.module.datacenter.service.evtmanager.evtwo;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtwo.vo.EvtWoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtwo.vo.EvtWoSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtwo.EvtWoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 事件工单 Service 接口
 *
 * @author 亘川智城
 */
public interface EvtWoService {

    /**
     * 创建事件工单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvtWo(@Valid EvtWoSaveReqVO createReqVO);

    /**
     * 更新事件工单
     *
     * @param updateReqVO 更新信息
     */
    void updateEvtWo(@Valid EvtWoSaveReqVO updateReqVO);

    /**
     * 删除事件工单
     *
     * @param id 编号
     */
    void deleteEvtWo(Long id);

    /**
     * 获得事件工单
     *
     * @param id 编号
     * @return 事件工单
     */
    EvtWoDO getEvtWo(Long id);

    /**
     * 获得事件工单分页
     *
     * @param pageReqVO 分页查询
     * @return 事件工单分页
     */
    PageResult<EvtWoDO> getEvtWoPage(EvtWoPageReqVO pageReqVO);

}