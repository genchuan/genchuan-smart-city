package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transferreserve;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferReserveDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferReserveDetailDO;
import jakarta.validation.Valid;

/**
 * 进站预约 Service 接口
 *
 * @author 芋道源码
 */
public interface TransferReserveService {

    /**
     * 创建进站预约
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTransferReserve(@Valid TransferReserveSaveReqVO createReqVO);

    /**
     * 更新进站预约
     *
     * @param updateReqVO 更新信息
     */
    void updateTransferReserve(@Valid TransferReserveSaveReqVO updateReqVO);

    /**
     * 删除进站预约
     *
     * @param id 编号
     */
    void deleteTransferReserve(Long id);

    /**
     * 获得进站预约
     *
     * @param id 编号
     * @return 进站预约
     */
    TransferReserveDO getTransferReserve(Long id);

    /**
     * 获得进站预约分页
     *
     * @param pageReqVO 分页查询
     * @return 进站预约分页
     */
    PageResult<TransferReserveDO> getTransferReservePage(TransferReservePageReqVO pageReqVO);

    /**
     * 获得进站预约分页详情
     */
    PageResult<TransferReserveDetailDO> getTransferReserveDetailPage(TransferReservePageReqVO pageReqVO);

    /**
     * 批量排序
     */
    void batchSortTransferReserve(TransferReserveBatchSortReqVO reqVO);

    /**
     * 获取进站预约看板统计数据
     *
     * @return 看板统计数据
     */
    TransferReserveDashboardRespVO getDashboardStats();

    /**
     * 预约排号
     */
    void sortTransferReserve(TransferReserveSortReqVO reqVO);
}