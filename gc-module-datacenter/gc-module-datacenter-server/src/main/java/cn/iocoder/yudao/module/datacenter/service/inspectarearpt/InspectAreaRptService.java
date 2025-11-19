package cn.iocoder.yudao.module.datacenter.service.inspectarearpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectarearpt.vo.InspectAreaRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectarearpt.vo.InspectAreaRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspectarearpt.InspectAreaRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检区域统计 Service 接口
 *
 * @author zcq
 */
public interface InspectAreaRptService {

    /**
     * 创建巡查巡检区域统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectAreaRpt(@Valid InspectAreaRptSaveReqVO createReqVO);

    /**
     * 更新巡查巡检区域统计
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectAreaRpt(@Valid InspectAreaRptSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检区域统计
     *
     * @param id 编号
     */
    void deleteInspectAreaRpt(Long id);

    /**
     * 获得巡查巡检区域统计
     *
     * @param id 编号
     * @return 巡查巡检区域统计
     */
    InspectAreaRptDO getInspectAreaRpt(Long id);

    /**
     * 获得巡查巡检区域统计分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检区域统计分页
     */
    PageResult<InspectAreaRptDO> getInspectAreaRptPage(InspectAreaRptPageReqVO pageReqVO);

}