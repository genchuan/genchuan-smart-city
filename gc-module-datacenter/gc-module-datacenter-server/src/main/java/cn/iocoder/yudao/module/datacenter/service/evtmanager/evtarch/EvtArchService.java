package cn.iocoder.yudao.module.datacenter.service.evtmanager.evtarch;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtarch.vo.EvtArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtarch.vo.EvtArchSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtarch.EvtArchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 事件办结归档 Service 接口
 *
 * @author 亘川智城
 */
public interface EvtArchService {

    /**
     * 创建事件办结归档
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvtArch(@Valid EvtArchSaveReqVO createReqVO);

    /**
     * 更新事件办结归档
     *
     * @param updateReqVO 更新信息
     */
    void updateEvtArch(@Valid EvtArchSaveReqVO updateReqVO);

    /**
     * 删除事件办结归档
     *
     * @param id 编号
     */
    void deleteEvtArch(Long id);

    /**
     * 获得事件办结归档
     *
     * @param id 编号
     * @return 事件办结归档
     */
    EvtArchDO getEvtArch(Long id);

    /**
     * 获得事件办结归档分页
     *
     * @param pageReqVO 分页查询
     * @return 事件办结归档分页
     */
    PageResult<EvtArchDO> getEvtArchPage(EvtArchPageReqVO pageReqVO);

}