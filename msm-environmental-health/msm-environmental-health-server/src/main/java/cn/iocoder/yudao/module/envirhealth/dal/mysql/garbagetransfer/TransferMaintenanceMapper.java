package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transfermaintenance.TransferMaintenancePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferMaintenanceDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.detail.TransferMaintenanceDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设备维护 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TransferMaintenanceMapper extends BaseMapperX<TransferMaintenanceDO> {

    default PageResult<TransferMaintenanceDO> selectPage(TransferMaintenancePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TransferMaintenanceDO>()
                .eqIfPresent(TransferMaintenanceDO::getMaintenanceId, reqVO.getMaintenanceId())
                .eqIfPresent(TransferMaintenanceDO::getTransferId, reqVO.getTransferId())
                .eqIfPresent(TransferMaintenanceDO::getEquipmentId, reqVO.getEquipmentId())
                .eqIfPresent(TransferMaintenanceDO::getMaintenanceCycle, reqVO.getMaintenanceCycle())
                .betweenIfPresent(TransferMaintenanceDO::getLastMaintenanceTime, reqVO.getLastMaintenanceTime())
                .eqIfPresent(TransferMaintenanceDO::getMaintenanceContent, reqVO.getMaintenanceContent())
                .eqIfPresent(TransferMaintenanceDO::getHandleBy, reqVO.getHandleBy())
                .eqIfPresent(TransferMaintenanceDO::getMaintenanceStatus, reqVO.getMaintenanceStatus())
                .betweenIfPresent(TransferMaintenanceDO::getExpectedCompleteTime, reqVO.getExpectedCompleteTime())
                .eqIfPresent(TransferMaintenanceDO::getAbnormalIsTimeout, reqVO.getAbnormalIsTimeout())
                .betweenIfPresent(TransferMaintenanceDO::getMaintenanceTime, reqVO.getMaintenanceTime())
                .eqIfPresent(TransferMaintenanceDO::getReplaceParts, reqVO.getReplaceParts())
                .eqIfPresent(TransferMaintenanceDO::getMaintenanceCost, reqVO.getMaintenanceCost())
                .eqIfPresent(TransferMaintenanceDO::getMaintenancePhoto, reqVO.getMaintenancePhoto())
                .eqIfPresent(TransferMaintenanceDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(TransferMaintenanceDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(TransferMaintenanceDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(TransferMaintenanceDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(TransferMaintenanceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransferMaintenanceDO::getId));
    }

    List<TransferMaintenanceDetailDO> selectDetailPage(@Param("reqVO") TransferMaintenancePageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") TransferMaintenancePageReqVO pageReqVO);
}