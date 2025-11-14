package cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.dommngmatterrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommngmatterrpt.vo.DomMngMatterRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommngmatterrpt.vo.DomMngMatterRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.dommngmatterrpt.DomMngMatterRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 管理事项分域监测报表 Service 接口
 *
 * @author zcq
 */
public interface DomMngMatterRptService {

    /**
     * 创建管理事项分域监测报表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDomMngMatterRpt(@Valid DomMngMatterRptSaveReqVO createReqVO);

    /**
     * 更新管理事项分域监测报表
     *
     * @param updateReqVO 更新信息
     */
    void updateDomMngMatterRpt(@Valid DomMngMatterRptSaveReqVO updateReqVO);

    /**
     * 删除管理事项分域监测报表
     *
     * @param id 编号
     */
    void deleteDomMngMatterRpt(Long id);

    /**
     * 获得管理事项分域监测报表
     *
     * @param id 编号
     * @return 管理事项分域监测报表
     */
    DomMngMatterRptDO getDomMngMatterRpt(Long id);

    /**
     * 获得管理事项分域监测报表分页
     *
     * @param pageReqVO 分页查询
     * @return 管理事项分域监测报表分页
     */
    PageResult<DomMngMatterRptDO> getDomMngMatterRptPage(DomMngMatterRptPageReqVO pageReqVO);

}