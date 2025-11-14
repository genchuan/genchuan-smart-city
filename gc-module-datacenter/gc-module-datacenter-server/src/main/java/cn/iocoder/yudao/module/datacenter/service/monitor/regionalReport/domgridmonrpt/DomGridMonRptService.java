package cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.domgridmonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domgridmonrpt.vo.DomGridMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domgridmonrpt.vo.DomGridMonRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domgridmonrpt.DomGridMonRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 网格分域监测报表 Service 接口
 *
 * @author zcq
 */
public interface DomGridMonRptService {

    /**
     * 创建网格分域监测报表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDomGridMonRpt(@Valid DomGridMonRptSaveReqVO createReqVO);

    /**
     * 更新网格分域监测报表
     *
     * @param updateReqVO 更新信息
     */
    void updateDomGridMonRpt(@Valid DomGridMonRptSaveReqVO updateReqVO);

    /**
     * 删除网格分域监测报表
     *
     * @param id 编号
     */
    void deleteDomGridMonRpt(Long id);

    /**
     * 获得网格分域监测报表
     *
     * @param id 编号
     * @return 网格分域监测报表
     */
    DomGridMonRptDO getDomGridMonRpt(Long id);

    /**
     * 获得网格分域监测报表分页
     *
     * @param pageReqVO 分页查询
     * @return 网格分域监测报表分页
     */
    PageResult<DomGridMonRptDO> getDomGridMonRptPage(DomGridMonRptPageReqVO pageReqVO);

}