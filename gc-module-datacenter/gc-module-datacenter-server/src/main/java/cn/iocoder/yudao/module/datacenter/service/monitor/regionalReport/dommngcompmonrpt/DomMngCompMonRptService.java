package cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.dommngcompmonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommngcompmonrpt.vo.DomMngCompMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommngcompmonrpt.vo.DomMngCompMonRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.dommngcompmonrpt.DomMngCompMonRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 管理部件分域监测报表 Service 接口
 *
 * @author zcq
 */
public interface DomMngCompMonRptService {

    /**
     * 创建管理部件分域监测报表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDomMngCompMonRpt(@Valid DomMngCompMonRptSaveReqVO createReqVO);

    /**
     * 更新管理部件分域监测报表
     *
     * @param updateReqVO 更新信息
     */
    void updateDomMngCompMonRpt(@Valid DomMngCompMonRptSaveReqVO updateReqVO);

    /**
     * 删除管理部件分域监测报表
     *
     * @param id 编号
     */
    void deleteDomMngCompMonRpt(Long id);

    /**
     * 获得管理部件分域监测报表
     *
     * @param id 编号
     * @return 管理部件分域监测报表
     */
    DomMngCompMonRptDO getDomMngCompMonRpt(Long id);

    /**
     * 获得管理部件分域监测报表分页
     *
     * @param pageReqVO 分页查询
     * @return 管理部件分域监测报表分页
     */
    PageResult<DomMngCompMonRptDO> getDomMngCompMonRptPage(DomMngCompMonRptPageReqVO pageReqVO);

}