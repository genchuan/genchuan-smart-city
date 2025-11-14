package cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.domassetmonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domassetmonrpt.vo.DomAssetMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domassetmonrpt.vo.DomAssetMonRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domassetmonrpt.DomAssetMonRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 资产分域监测报表 Service 接口
 *
 * @author zcq
 */
public interface DomAssetMonRptService {

    /**
     * 创建资产分域监测报表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDomAssetMonRpt(@Valid DomAssetMonRptSaveReqVO createReqVO);

    /**
     * 更新资产分域监测报表
     *
     * @param updateReqVO 更新信息
     */
    void updateDomAssetMonRpt(@Valid DomAssetMonRptSaveReqVO updateReqVO);

    /**
     * 删除资产分域监测报表
     *
     * @param id 编号
     */
    void deleteDomAssetMonRpt(Long id);

    /**
     * 获得资产分域监测报表
     *
     * @param id 编号
     * @return 资产分域监测报表
     */
    DomAssetMonRptDO getDomAssetMonRpt(Long id);

    /**
     * 获得资产分域监测报表分页
     *
     * @param pageReqVO 分页查询
     * @return 资产分域监测报表分页
     */
    PageResult<DomAssetMonRptDO> getDomAssetMonRptPage(DomAssetMonRptPageReqVO pageReqVO);

}