package cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivmonevt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmonevt.vo.DomDivMonEvtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmonevt.vo.DomDivMonEvtSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivmonevt.DomDivMonEvtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 监测事件分域 Service 接口
 *
 * @author zcq
 */
public interface DomDivMonEvtService {

    /**
     * 创建监测事件分域
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDomDivMonEvt(@Valid DomDivMonEvtSaveReqVO createReqVO);

    /**
     * 更新监测事件分域
     *
     * @param updateReqVO 更新信息
     */
    void updateDomDivMonEvt(@Valid DomDivMonEvtSaveReqVO updateReqVO);

    /**
     * 删除监测事件分域
     *
     * @param id 编号
     */
    void deleteDomDivMonEvt(Long id);

    /**
     * 获得监测事件分域
     *
     * @param id 编号
     * @return 监测事件分域
     */
    DomDivMonEvtDO getDomDivMonEvt(Long id);

    /**
     * 获得监测事件分域分页
     *
     * @param pageReqVO 分页查询
     * @return 监测事件分域分页
     */
    PageResult<DomDivMonEvtDO> getDomDivMonEvtPage(DomDivMonEvtPageReqVO pageReqVO);

}