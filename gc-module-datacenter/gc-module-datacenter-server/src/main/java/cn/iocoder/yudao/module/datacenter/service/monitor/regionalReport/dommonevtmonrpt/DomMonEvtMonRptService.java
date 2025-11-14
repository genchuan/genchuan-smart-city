package cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.dommonevtmonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommonevtmonrpt.vo.DomMonEvtMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommonevtmonrpt.vo.DomMonEvtMonRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.dommonevtmonrpt.DomMonEvtMonRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 监测事件分域监测报表 Service 接口
 *
 * @author zcq
 */
public interface DomMonEvtMonRptService {

    /**
     * 创建监测事件分域监测报表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDomMonEvtMonRpt(@Valid DomMonEvtMonRptSaveReqVO createReqVO);

    /**
     * 更新监测事件分域监测报表
     *
     * @param updateReqVO 更新信息
     */
    void updateDomMonEvtMonRpt(@Valid DomMonEvtMonRptSaveReqVO updateReqVO);

    /**
     * 删除监测事件分域监测报表
     *
     * @param id 编号
     */
    void deleteDomMonEvtMonRpt(Long id);

    /**
     * 获得监测事件分域监测报表
     *
     * @param id 编号
     * @return 监测事件分域监测报表
     */
    DomMonEvtMonRptDO getDomMonEvtMonRpt(Long id);

    /**
     * 获得监测事件分域监测报表分页
     *
     * @param pageReqVO 分页查询
     * @return 监测事件分域监测报表分页
     */
    PageResult<DomMonEvtMonRptDO> getDomMonEvtMonRptPage(DomMonEvtMonRptPageReqVO pageReqVO);

}