package cn.iocoder.yudao.module.datacenter.service.evtmanager.evtrptreg;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtrptreg.vo.EvtRptRegPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtrptreg.vo.EvtRptRegSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtrptreg.EvtRptRegDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 事件接报登记 Service 接口
 *
 * @author 亘川智城
 */
public interface EvtRptRegService {

    /**
     * 创建事件接报登记
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvtRptReg(@Valid EvtRptRegSaveReqVO createReqVO);

    /**
     * 更新事件接报登记
     *
     * @param updateReqVO 更新信息
     */
    void updateEvtRptReg(@Valid EvtRptRegSaveReqVO updateReqVO);

    /**
     * 删除事件接报登记
     *
     * @param id 编号
     */
    void deleteEvtRptReg(Long id);

    /**
     * 获得事件接报登记
     *
     * @param id 编号
     * @return 事件接报登记
     */
    EvtRptRegDO getEvtRptReg(Long id);

    /**
     * 获得事件接报登记分页
     *
     * @param pageReqVO 分页查询
     * @return 事件接报登记分页
     */
    PageResult<EvtRptRegDO> getEvtRptRegPage(EvtRptRegPageReqVO pageReqVO);

}