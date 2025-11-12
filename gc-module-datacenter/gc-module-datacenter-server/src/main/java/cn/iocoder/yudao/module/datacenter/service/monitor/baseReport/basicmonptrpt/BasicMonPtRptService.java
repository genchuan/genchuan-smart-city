package cn.iocoder.yudao.module.datacenter.service.monitor.baseReport.basicmonptrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonptrpt.vo.BasicMonPtRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonptrpt.vo.BasicMonPtRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.baseReport.basicmonptrpt.BasicMonPtRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 基础监测点位报表 Service 接口
 *
 * @author zcq
 */
public interface BasicMonPtRptService {

    /**
     * 创建基础监测点位报表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBasicMonPtRpt(@Valid BasicMonPtRptSaveReqVO createReqVO);

    /**
     * 更新基础监测点位报表
     *
     * @param updateReqVO 更新信息
     */
    void updateBasicMonPtRpt(@Valid BasicMonPtRptSaveReqVO updateReqVO);

    /**
     * 删除基础监测点位报表
     *
     * @param id 编号
     */
    void deleteBasicMonPtRpt(Long id);

    /**
     * 获得基础监测点位报表
     *
     * @param id 编号
     * @return 基础监测点位报表
     */
    BasicMonPtRptDO getBasicMonPtRpt(Long id);

    /**
     * 获得基础监测点位报表分页
     *
     * @param pageReqVO 分页查询
     * @return 基础监测点位报表分页
     */
    PageResult<BasicMonPtRptDO> getBasicMonPtRptPage(BasicMonPtRptPageReqVO pageReqVO);

}