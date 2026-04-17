package cn.iocoder.yudao.module.chargepark.carservice.service.rescue;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoBatchDispatchReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoDispatchReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoEvaluateReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoTransferReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoUpdateProgressReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.rescue.RescueInfoDO;

import java.util.List;

/**
 * 救援信息 Service 接口
 *
 * @author carservice
 */
public interface RescueInfoService {

    Long createRescueInfo(RescueInfoSaveReqVO createReqVO);

    void updateRescueInfo(RescueInfoSaveReqVO updateReqVO);

    void deleteRescueInfo(Long id);

    void deleteRescueInfoListByIds(List<Long> ids);

    RescueInfoDO getRescueInfo(Long id);

    PageResult<RescueInfoDO> getRescueInfoPage(RescueInfoPageReqVO pageReqVO);

    // ========== 业务操作（状态机） ==========

    /** 派发：待派发 → 待认领 */
    void dispatchRescueInfo(RescueInfoDispatchReqVO reqVO);

    /** 批量派发：待派发 → 待认领（多条） */
    void batchDispatchRescueInfo(RescueInfoBatchDispatchReqVO reqVO);

    /** 认领：待认领 → 处理中（自动设 rescueUserId 为当前登录用户） */
    void claimRescueInfo(Long id);

    /** 更新进度：处理中状态下更新 progress / photo（不切状态） */
    void updateRescueInfoProgress(RescueInfoUpdateProgressReqVO reqVO);

    /** 转派：处理中 → 处理中（更换 rescueUserId） */
    void transferRescueInfo(RescueInfoTransferReqVO reqVO);

    /** 完成救援：处理中 → 已完成（自动设 finishTime / handleDuration） */
    void completeRescueInfo(Long id);

    /** 评价：已完成状态下设置 score / evaluateContent */
    void evaluateRescueInfo(RescueInfoEvaluateReqVO reqVO);

    /** 归档：已完成状态下设置 archiveStatus = 已归档 */
    void archiveRescueInfo(Long id);

}
