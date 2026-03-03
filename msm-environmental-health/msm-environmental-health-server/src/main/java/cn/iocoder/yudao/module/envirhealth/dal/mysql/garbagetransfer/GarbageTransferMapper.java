package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.garbagetransfer.GarbageTransferPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.GarbageTransferDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.detail.GarbageTransferDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 垃圾转运站 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface GarbageTransferMapper extends BaseMapperX<GarbageTransferDO> {

    default PageResult<GarbageTransferDO> selectPage(GarbageTransferPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GarbageTransferDO>()
                .eqIfPresent(GarbageTransferDO::getTransferId, reqVO.getTransferId())
                .likeIfPresent(GarbageTransferDO::getName, reqVO.getName())
                .eqIfPresent(GarbageTransferDO::getLocation, reqVO.getLocation())
                .eqIfPresent(GarbageTransferDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(GarbageTransferDO::getEquipmentIds, reqVO.getEquipmentIds())
                .eqIfPresent(GarbageTransferDO::getOperationStatusId, reqVO.getOperationStatusId())
                .eqIfPresent(GarbageTransferDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(GarbageTransferDO::getDailyTransferVolume, reqVO.getDailyTransferVolume())
                .eqIfPresent(GarbageTransferDO::getEquipmentRate, reqVO.getEquipmentRate())
                .eqIfPresent(GarbageTransferDO::getEnvironmentRate, reqVO.getEnvironmentRate())
                .eqIfPresent(GarbageTransferDO::getUnhandledAlarmCount, reqVO.getUnhandledAlarmCount())
                .eqIfPresent(GarbageTransferDO::getPendingMaintenanceCount, reqVO.getPendingMaintenanceCount())
                .eqIfPresent(GarbageTransferDO::getEnvironmentData, reqVO.getEnvironmentData())
                .eqIfPresent(GarbageTransferDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(GarbageTransferDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(GarbageTransferDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(GarbageTransferDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(GarbageTransferDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GarbageTransferDO::getId));
    }

    List<GarbageTransferDetailDO> selectDetailPage(@Param("reqVO") GarbageTransferPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") GarbageTransferPageReqVO pageReqVO);
}