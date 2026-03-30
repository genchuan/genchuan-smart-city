package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transferoperation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationCompletedDashboardVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationDashboardVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferOperationDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferOperationDetailDO;
import jakarta.validation.Valid;

/**
 * 转运作业 Service 接口
 *
 * @author 芋道源码
 */
public interface TransferOperationService {

    /**
     * 创建转运作业
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTransferOperation(@Valid TransferOperationSaveReqVO createReqVO);

    /**
     * 更新转运作业
     *
     * @param updateReqVO 更新信息
     */
    void updateTransferOperation(@Valid TransferOperationSaveReqVO updateReqVO);

    /**
     * 删除转运作业
     *
     * @param id 编号
     */
    void deleteTransferOperation(Long id);

    /**
     * 获得转运作业
     *
     * @param id 编号
     * @return 转运作业
     */
    TransferOperationDO getTransferOperation(Long id);

    /**
     * 获得转运作业分页
     *
     * @param pageReqVO 分页查询
     * @return 转运作业分页
     */
    PageResult<TransferOperationDO> getTransferOperationPage(TransferOperationPageReqVO pageReqVO);

    /**
     * 获得转运作业详情
     *
     * @param pageReqVO 分页查询
     * @return 转运作业分页
     */
    PageResult<TransferOperationDetailDO> getTransferOperationDetailPage(TransferOperationPageReqVO pageReqVO);

    /**
     * 获取转运作业仪表盘统计数据
     *
     * @return 仪表盘统计数据
     */
    TransferOperationDashboardVO getDashboardStats();

    /**
     * 获取转运作业仪表盘统计数据(已完成)
     *
     * @return 仪表盘统计数据
     */
    TransferOperationCompletedDashboardVO getTransferOperationDashboard(String timeDimension);

    /**
     * 暂停转运作业
     *
     * @param operationId 转运作业ID
     * @param pauseStatusId 暂停状态ID（uuid-plan-status-004）
     */
    void pauseTransferOperation(Long operationId, String pauseStatusId);
}