package cn.iocoder.yudao.module.datacenter.service.monitor.regionalReport.domappscenemonrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domappscenemonrpt.vo.DomAppSceneMonRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regionalReport.domappscenemonrpt.vo.DomAppSceneMonRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regionalReport.domappscenemonrpt.DomAppSceneMonRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 应用场景分域监测报表 Service 接口
 *
 * @author zcq
 */
public interface DomAppSceneMonRptService {

    /**
     * 创建应用场景分域监测报表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDomAppSceneMonRpt(@Valid DomAppSceneMonRptSaveReqVO createReqVO);

    /**
     * 更新应用场景分域监测报表
     *
     * @param updateReqVO 更新信息
     */
    void updateDomAppSceneMonRpt(@Valid DomAppSceneMonRptSaveReqVO updateReqVO);

    /**
     * 删除应用场景分域监测报表
     *
     * @param id 编号
     */
    void deleteDomAppSceneMonRpt(Long id);

    /**
     * 获得应用场景分域监测报表
     *
     * @param id 编号
     * @return 应用场景分域监测报表
     */
    DomAppSceneMonRptDO getDomAppSceneMonRpt(Long id);

    /**
     * 获得应用场景分域监测报表分页
     *
     * @param pageReqVO 分页查询
     * @return 应用场景分域监测报表分页
     */
    PageResult<DomAppSceneMonRptDO> getDomAppSceneMonRptPage(DomAppSceneMonRptPageReqVO pageReqVO);

}