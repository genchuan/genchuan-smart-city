package cn.iocoder.yudao.module.studentmgmt.service.repairmgmt;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.repairmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.repairmgmt.RepairMgmtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 报修管理 Service 接口
 *
 * @author 芋道源码
 */
public interface RepairMgmtService {

    /**
     * 创建报修管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRepairMgmt(@Valid RepairMgmtSaveReqVO createReqVO);

    /**
     * 更新报修管理
     *
     * @param updateReqVO 更新信息
     */
    void updateRepairMgmt(@Valid RepairMgmtSaveReqVO updateReqVO);

    /**
     * 删除报修管理
     *
     * @param id 编号
     */
    void deleteRepairMgmt(Long id);

    /**
    * 批量删除报修管理
    *
    * @param ids 编号
    */
    void deleteRepairMgmtListByIds(List<Long> ids);

    /**
     * 获得报修管理
     *
     * @param id 编号
     * @return 报修管理
     */
    RepairMgmtDO getRepairMgmt(Long id);

    /**
     * 获得报修管理分页
     *
     * @param pageReqVO 分页查询
     * @return 报修管理分页
     */
    PageResult<RepairMgmtDO> getRepairMgmtPage(RepairMgmtPageReqVO pageReqVO);

    Boolean assign(@Valid RepairMgmtAssignReqVO reqVO);

    Boolean feedback(@Valid RepairMgmtFeedbackReqVO reqVO);

    RepairMgmtChartRespVO chart(@Valid RepairMgmtChartReqVO reqVO);

    RepairMgmtCountRespVO repairCount(@Valid RepairMgmtCountReqVO reqVO);
}