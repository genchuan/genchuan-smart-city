package cn.iocoder.yudao.module.datacenter.service.monitor.baseReport.basicmonidxrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonidxrpt.vo.BasicMonIdxRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonidxrpt.vo.BasicMonIdxRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.baseReport.basicmonidxrpt.BasicMonIdxRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 基础监测指标报 Service 接口
 *
 * @author zhucongquan
 */
public interface BasicMonIdxRptService {

    /**
     * 创建基础监测指标报
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBasicMonIdxRpt(@Valid BasicMonIdxRptSaveReqVO createReqVO);

    /**
     * 更新基础监测指标报
     *
     * @param updateReqVO 更新信息
     */
    void updateBasicMonIdxRpt(@Valid BasicMonIdxRptSaveReqVO updateReqVO);

    /**
     * 删除基础监测指标报
     *
     * @param id 编号
     */
    void deleteBasicMonIdxRpt(Long id);

    /**
     * 获得基础监测指标报
     *
     * @param id 编号
     * @return 基础监测指标报
     */
    BasicMonIdxRptDO getBasicMonIdxRpt(Long id);

    /**
     * 获得基础监测指标报分页
     *
     * @param pageReqVO 分页查询
     * @return 基础监测指标报分页
     */
    PageResult<BasicMonIdxRptDO> getBasicMonIdxRptPage(BasicMonIdxRptPageReqVO pageReqVO);

}