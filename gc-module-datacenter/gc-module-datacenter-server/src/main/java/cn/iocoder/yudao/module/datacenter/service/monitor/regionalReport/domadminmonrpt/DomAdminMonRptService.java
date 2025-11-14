package cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.domadminmonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domadminmonrpt.vo.DomAdminMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domadminmonrpt.vo.DomAdminMonRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domadminmonrpt.DomAdminMonRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 行政区划分域监测报表 Service 接口
 *
 * @author zcq
 */
public interface DomAdminMonRptService {

    /**
     * 创建行政区划分域监测报表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDomAdminMonRpt(@Valid DomAdminMonRptSaveReqVO createReqVO);

    /**
     * 更新行政区划分域监测报表
     *
     * @param updateReqVO 更新信息
     */
    void updateDomAdminMonRpt(@Valid DomAdminMonRptSaveReqVO updateReqVO);

    /**
     * 删除行政区划分域监测报表
     *
     * @param id 编号
     */
    void deleteDomAdminMonRpt(Long id);

    /**
     * 获得行政区划分域监测报表
     *
     * @param id 编号
     * @return 行政区划分域监测报表
     */
    DomAdminMonRptDO getDomAdminMonRpt(Long id);

    /**
     * 获得行政区划分域监测报表分页
     *
     * @param pageReqVO 分页查询
     * @return 行政区划分域监测报表分页
     */
    PageResult<DomAdminMonRptDO> getDomAdminMonRptPage(DomAdminMonRptPageReqVO pageReqVO);

}