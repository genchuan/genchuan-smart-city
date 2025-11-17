package cn.iocoder.yudao.module.datacenter.service.inspection.report.inspectproblemlevelrec;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemlevelrec.vo.InspectProblemLevelRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemlevelrec.vo.InspectProblemLevelRecSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.report.inspectproblemlevelrec.InspectProblemLevelRecDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 巡查巡检问题分级记录 Service 接口
 *
 * @author zcq
 */
public interface InspectProblemLevelRecService {

    /**
     * 创建巡查巡检问题分级记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createInspectProblemLevelRec(@Valid InspectProblemLevelRecSaveReqVO createReqVO);

    /**
     * 更新巡查巡检问题分级记录
     *
     * @param updateReqVO 更新信息
     */
    void updateInspectProblemLevelRec(@Valid InspectProblemLevelRecSaveReqVO updateReqVO);

    /**
     * 删除巡查巡检问题分级记录
     *
     * @param id 编号
     */
    void deleteInspectProblemLevelRec(Long id);

    /**
     * 获得巡查巡检问题分级记录
     *
     * @param id 编号
     * @return 巡查巡检问题分级记录
     */
    InspectProblemLevelRecDO getInspectProblemLevelRec(Long id);

    /**
     * 获得巡查巡检问题分级记录分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查巡检问题分级记录分页
     */
    PageResult<InspectProblemLevelRecDO> getInspectProblemLevelRecPage(InspectProblemLevelRecPageReqVO pageReqVO);

}