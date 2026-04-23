package cn.iocoder.yudao.module.vehiclepass.service.leavemgmt.abnormalleave;

import java.util.*;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeavePageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveBatchHandleReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveCheckReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveIgnoreReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveUpdateProgressReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.leavemgmt.abnormalleave.AbnormalLeaveDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 异常离场 Service 接口
 *
 * @author 亘川智城
 */
public interface AbnormalLeaveService {

    /**
     * 创建异常离场
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createLeave(@Valid AbnormalLeaveSaveReqVO createReqVO);

    /**
     * 更新异常离场
     *
     * @param updateReqVO 更新信息
     */
    void updateLeave(@Valid AbnormalLeaveSaveReqVO updateReqVO);

    /**
     * 删除异常离场
     *
     * @param id 编号
     */
    void deleteLeave(Long id);

    /**
     * 批量删除异常离场
     *
     * @param ids 编号
     */
    void deleteLeaveListByIds(List<Long> ids);

    /**
     * 获得异常离场
     *
     * @param id 编号
     * @return 异常离场
     */
    AbnormalLeaveDO getLeave(Long id);

    /**
     * 获得异常离场分页
     *
     * @param pageReqVO 分页查询
     * @return 异常离场分页
     */
    PageResult<AbnormalLeaveDO> getLeavePage(AbnormalLeavePageReqVO pageReqVO);

    /**
     * 获得异常离场分页（使用JOIN查询）
     *
     * @param pageReqVO 分页查询
     * @return 异常离场分页（含关联表字段）
     */
    PageResult<AbnormalLeaveRespVO> getLeavePageWithJoin(AbnormalLeavePageReqVO pageReqVO);

    /**
     * 批量处置异常离场
     *
     * @param reqVO 批量处置请求
     */
    void batchHandle(AbnormalLeaveBatchHandleReqVO reqVO);

    /**
     * 核查异常离场
     *
     * @param reqVO 核查请求
     */
    void checkLeave(AbnormalLeaveCheckReqVO reqVO);

    /**
     * 忽略异常离场
     *
     * @param reqVO 忽略请求
     */
    void ignoreLeave(AbnormalLeaveIgnoreReqVO reqVO);

    /**
     * 更新处置进度
     *
     * @param reqVO 更新进度请求
     */
    void updateProgress(AbnormalLeaveUpdateProgressReqVO reqVO);

    /**
     * 获取异常离场统计
     *
     * @param reqVO 统计请求
     * @return 统计结果
     */
    AbnormalLeaveChartRespVO getChart(AbnormalLeaveChartReqVO reqVO);

}