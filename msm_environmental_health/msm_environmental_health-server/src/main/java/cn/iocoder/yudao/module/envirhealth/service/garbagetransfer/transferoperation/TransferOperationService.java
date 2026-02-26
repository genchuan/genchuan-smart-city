package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transferoperation;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.detail.TransferOperationDetailDO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferOperationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

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

    PageResult<TransferOperationDetailDO> getTransferOperationDetailPage(TransferOperationPageReqVO pageReqVO);

}