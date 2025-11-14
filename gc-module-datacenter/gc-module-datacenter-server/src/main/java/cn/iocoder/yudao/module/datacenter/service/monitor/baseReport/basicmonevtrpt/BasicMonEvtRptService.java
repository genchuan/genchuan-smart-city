package cn.iocoder.yudao.module.datacenter.service.monitor.baseReport.basicmonevtrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonevtrpt.vo.BasicMonEvtRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonevtrpt.vo.BasicMonEvtRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.baseReport.basicmonevtrpt.BasicMonEvtRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 基础监测事件报 Service 接口
 *
 * @author zhucongquan
 */
public interface BasicMonEvtRptService {

    /**
     * 创建基础监测事件报
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBasicMonEvtRpt(@Valid BasicMonEvtRptSaveReqVO createReqVO);

    /**
     * 更新基础监测事件报
     *
     * @param updateReqVO 更新信息
     */
    void updateBasicMonEvtRpt(@Valid BasicMonEvtRptSaveReqVO updateReqVO);

    /**
     * 删除基础监测事件报
     *
     * @param id 编号
     */
    void deleteBasicMonEvtRpt(Long id);

    /**
     * 获得基础监测事件报
     *
     * @param id 编号
     * @return 基础监测事件报
     */
    BasicMonEvtRptDO getBasicMonEvtRpt(Long id);

    /**
     * 获得基础监测事件报分页
     *
     * @param pageReqVO 分页查询
     * @return 基础监测事件报分页
     */
    PageResult<BasicMonEvtRptDO> getBasicMonEvtRptPage(BasicMonEvtRptPageReqVO pageReqVO);

}