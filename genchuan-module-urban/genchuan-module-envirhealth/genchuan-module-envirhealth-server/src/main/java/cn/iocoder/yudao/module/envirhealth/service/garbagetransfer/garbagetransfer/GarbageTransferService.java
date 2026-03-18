package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.garbagetransfer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer.GarbageTransferDashboardRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer.GarbageTransferPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer.GarbageTransferSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.GarbageTransferDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.detail.GarbageTransferDetailDO;
import cn.iocoder.yudao.module.envirhealth.util.vo.StatisticsRespVO;
import jakarta.validation.Valid;

/**
 * 垃圾转运站 Service 接口
 *
 * @author 芋道源码
 */
public interface GarbageTransferService {

    /**
     * 创建垃圾转运站
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGarbageTransfer(@Valid GarbageTransferSaveReqVO createReqVO);

    /**
     * 更新垃圾转运站
     *
     * @param updateReqVO 更新信息
     */
    void updateGarbageTransfer(@Valid GarbageTransferSaveReqVO updateReqVO);

    /**
     * 删除垃圾转运站
     *
     * @param id 编号
     */
    void deleteGarbageTransfer(Long id);

    /**
     * 获得垃圾转运站
     *
     * @param id 编号
     * @return 垃圾转运站
     */
    GarbageTransferDO getGarbageTransfer(Long id);

    /**
     * 获得垃圾转运站分页
     *
     * @param pageReqVO 分页查询
     * @return 垃圾转运站分页
     */
    PageResult<GarbageTransferDO> getGarbageTransferPage(GarbageTransferPageReqVO pageReqVO);

    /**
     * 获得垃圾转运站分页详情
     *
     * @param pageReqVO 分页查询
     * @return 垃圾转运站分页
     */
    PageResult<GarbageTransferDetailDO> getGarbageTransferDetailPage(GarbageTransferPageReqVO pageReqVO);

    /**
     * 获取垃圾转运站看板统计数据
     *
     * @return 看板统计数据
     */
    GarbageTransferDashboardRespVO getDashboardStats();

    /**
     * 获取垃圾转运站统计数据（按状态分组）
     * @return 统计数据
     */
    StatisticsRespVO getGarbageTransferStatistics();
}