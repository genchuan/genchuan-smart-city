package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transfermaintenance.TransferMaintenancePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferMaintenanceDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferMaintenanceDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
                .betweenIfPresent(TransferMaintenanceDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransferMaintenanceDO::getId));
    }

    /**
     * 查询全局最大序号（用于maintenance_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(maintenance_id, '-', -1)), 0) FROM garbage_transfer_maintenance")
    Integer selectMaxSeq();

    List<TransferMaintenanceDetailDO> selectDetailPage(@Param("reqVO") TransferMaintenancePageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") TransferMaintenancePageReqVO pageReqVO);

    //查询设备维护表的数量
    @Select("SELECT COUNT(*) FROM garbage_transfer_maintenance WHERE deleted = 0")
    Long selectAllCount();
}