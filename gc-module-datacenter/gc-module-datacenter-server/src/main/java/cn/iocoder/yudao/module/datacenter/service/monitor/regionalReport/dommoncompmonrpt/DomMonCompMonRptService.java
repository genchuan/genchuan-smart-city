package cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.dommoncompmonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommoncompmonrpt.vo.DomMonCompMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.dommoncompmonrpt.vo.DomMonCompMonRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.dommoncompmonrpt.DomMonCompMonRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 监测部件分域监测报表 Service 接口
 *
 * @author zcq
 */
public interface DomMonCompMonRptService {

    /**
     * 创建监测部件分域监测报表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDomMonCompMonRpt(@Valid DomMonCompMonRptSaveReqVO createReqVO);

    /**
     * 更新监测部件分域监测报表
     *
     * @param updateReqVO 更新信息
     */
    void updateDomMonCompMonRpt(@Valid DomMonCompMonRptSaveReqVO updateReqVO);

    /**
     * 删除监测部件分域监测报表
     *
     * @param id 编号
     */
    void deleteDomMonCompMonRpt(Long id);

    /**
     * 获得监测部件分域监测报表
     *
     * @param id 编号
     * @return 监测部件分域监测报表
     */
    DomMonCompMonRptDO getDomMonCompMonRpt(Long id);

    /**
     * 获得监测部件分域监测报表分页
     *
     * @param pageReqVO 分页查询
     * @return 监测部件分域监测报表分页
     */
    PageResult<DomMonCompMonRptDO> getDomMonCompMonRptPage(DomMonCompMonRptPageReqVO pageReqVO);

}