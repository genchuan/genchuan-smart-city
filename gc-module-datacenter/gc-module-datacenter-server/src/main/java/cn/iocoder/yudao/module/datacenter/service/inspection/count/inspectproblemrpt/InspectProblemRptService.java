package cn.iocoder.yudao.module.datacenter.service.inspection.count.inspectproblemrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectproblemrpt.vo.InspectProblemRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.count.inspectproblemrpt.vo.InspectProblemRptSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.count.inspectproblemrpt.InspectProblemRptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检问题统计 Service 接口
 *
 * @author zcq
 */
public interface InspectProblemRptService {

    /**
     * 创建巡查巡检问题统计
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectProblemRpt(@Valid InspectProblemRptSaveReqVO createReqVO);

    /**
     * 更新巡查巡检问题统计
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectProblemRpt(@Valid InspectProblemRptSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检问题统计
     *
     * @param id 编号
     */
    void deleteInspectProblemRpt(Long id);

    /**
     * 获得巡查巡检问题统计
     *
     * @param id 编号
     * @return 巡查巡检问题统计
     */
    InspectProblemRptDO getInspectProblemRpt(Long id);

    /**
     * 获得巡查巡检问题统计分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检问题统计分页
     */
    PageResult<InspectProblemRptDO> getInspectProblemRptPage(InspectProblemRptPageReqVO pageReqVO);

}