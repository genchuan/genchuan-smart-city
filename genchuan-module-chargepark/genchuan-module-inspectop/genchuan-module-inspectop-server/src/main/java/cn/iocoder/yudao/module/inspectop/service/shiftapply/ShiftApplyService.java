package cn.iocoder.yudao.module.inspectop.service.shiftapply;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.shiftapply.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.shiftapply.ShiftApplyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 换班申请 Service 接口
 *
 * @author zhucongquan
 */
public interface ShiftApplyService {

    /**
     * 创建换班申请
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createShiftApply(@Valid ShiftApplySaveReqVO createReqVO);

    /**
     * 更新换班申请
     *
     * @param updateReqVO 更新信息
     */
    void updateShiftApply(@Valid ShiftApplySaveReqVO updateReqVO);

    /**
     * 删除换班申请
     *
     * @param id 编号
     */
    void deleteShiftApply(Long id);

    /**
    * 批量删除换班申请
    *
    * @param ids 编号
    */
    void deleteShiftApplyListByIds(List<Long> ids);

    /**
     * 获得换班申请
     *
     * @param id 编号
     * @return 换班申请
     */
    ShiftApplyRespVO getShiftApply(Long id);

    /**
     * 获得换班申请分页
     *
     * @param pageReqVO 分页查询
     * @return 换班申请分页
     */
    PageResult<ShiftApplyRespVO> getShiftApplyPage(ShiftApplyPageReqVO pageReqVO);

    /**
     * 批量审核换班申请
     *
     * @param reqVO 审核信息
     * @return 审核结果
     */
    Boolean batchAuditShiftApply(@Valid ShiftApplyBatchAuditReqVO reqVO);

    /**
     * 通过换班申请
     *
     * @param reqVO 通过信息
     * @return 操作结果
     */
    Boolean approveShiftApply(@Valid ShiftApplyApproveReqVO reqVO);

    /**
     * 驳回换班申请
     *
     * @param reqVO 驳回信息
     * @return 操作结果
     */
    Boolean rejectShiftApply(@Valid ShiftApplyRejectReqVO reqVO);

    /**
     * 确认换班申请
     *
     * @param reqVO 确认信息
     * @return 操作结果
     */
    Boolean confirmShiftApply(@Valid ShiftApplyConfirmReqVO reqVO);

    /**
     * 重新申请换班
     *
     * @param reqVO 重新申请信息
     * @return 操作结果
     */
    Boolean reapplyShiftApply(@Valid ShiftApplyReapplyReqVO reqVO);

    /**
     * 获取换班申请统计图表数据
     *
     * @param reqVO 查询参数
     * @return 图表数据
     */
    ShiftApplyChartRespVO getShiftApplyChart(ShiftApplyChartReqVO reqVO);
}