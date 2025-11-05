package cn.iocoder.yudao.module.datacenter.service.evtmanager.evtclassifyallocate;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtclassifyallocate.vo.EvtClassifyAllocatePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtclassifyallocate.vo.EvtClassifyAllocateSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtclassifyallocate.EvtClassifyAllocateDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;


/**
 * 事件分级分拨 Service 接口
 *
 * @author 亘川智城
 */
public interface EvtClassifyAllocateService {

    /**
     * 创建事件分级分拨
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvtClassifyAllocate(@Valid EvtClassifyAllocateSaveReqVO createReqVO);

    /**
     * 更新事件分级分拨
     *
     * @param updateReqVO 更新信息
     */
    void updateEvtClassifyAllocate(@Valid EvtClassifyAllocateSaveReqVO updateReqVO);

    /**
     * 删除事件分级分拨
     *
     * @param id 编号
     */
    void deleteEvtClassifyAllocate(Long id);

    /**
     * 获得事件分级分拨
     *
     * @param id 编号
     * @return 事件分级分拨
     */
    EvtClassifyAllocateDO getEvtClassifyAllocate(Long id);

    /**
     * 获得事件分级分拨分页
     *
     * @param pageReqVO 分页查询
     * @return 事件分级分拨分页
     */
    PageResult<EvtClassifyAllocateDO> getEvtClassifyAllocatePage(EvtClassifyAllocatePageReqVO pageReqVO);

}