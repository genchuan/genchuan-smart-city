package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transfermaintenance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transfermaintenance.TransferMaintenanceDashboardRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transfermaintenance.TransferMaintenancePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transfermaintenance.TransferMaintenanceSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferMaintenanceDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferMaintenanceDetailDO;
import jakarta.validation.Valid;

/**
 * 设备维护 Service 接口
 *
 * @author 芋道源码
 */
public interface TransferMaintenanceService {

    /**
     * 创建设备维护
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTransferMaintenance(@Valid TransferMaintenanceSaveReqVO createReqVO);

    /**
     * 更新设备维护
     *
     * @param updateReqVO 更新信息
     */
    void updateTransferMaintenance(@Valid TransferMaintenanceSaveReqVO updateReqVO);

    /**
     * 删除设备维护
     *
     * @param id 编号
     */
    void deleteTransferMaintenance(Long id);

    /**
     * 获得设备维护
     *
     * @param id 编号
     * @return 设备维护
     */
    TransferMaintenanceDO getTransferMaintenance(Long id);

    /**
     * 获得设备维护分页
     *
     * @param pageReqVO 分页查询
     * @return 设备维护分页
     */
    PageResult<TransferMaintenanceDO> getTransferMaintenancePage(TransferMaintenancePageReqVO pageReqVO);

    PageResult<TransferMaintenanceDetailDO> getTransferMaintenanceDetailPage(TransferMaintenancePageReqVO pageReqVO);

    /**
     * 获取设备维护看板统计数据
     */
    TransferMaintenanceDashboardRespVO getMaintenanceDashboard();

    /**
     * 审核/验收设备维护
     *
     * @param maintenanceId 维护单ID
     * @param result 验收结果：合格/不合格
     */
    void reviewTransferMaintenance(Long maintenanceId, String result);
}