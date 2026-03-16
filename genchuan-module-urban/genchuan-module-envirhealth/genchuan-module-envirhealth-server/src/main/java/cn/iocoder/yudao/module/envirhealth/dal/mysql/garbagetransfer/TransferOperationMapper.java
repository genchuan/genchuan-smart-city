package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferOperationDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.detail.TransferOperationDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 转运作业 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TransferOperationMapper extends BaseMapperX<TransferOperationDO> {

    default PageResult<TransferOperationDO> selectPage(TransferOperationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TransferOperationDO>()
                .eqIfPresent(TransferOperationDO::getOperationId, reqVO.getOperationId())
                .eqIfPresent(TransferOperationDO::getVehicleId, reqVO.getVehicleId())
                .eqIfPresent(TransferOperationDO::getGarbageTypeId, reqVO.getGarbageTypeId())
                .betweenIfPresent(TransferOperationDO::getEntryTime, reqVO.getEntryTime())
                .eqIfPresent(TransferOperationDO::getGarbageWeight, reqVO.getGarbageWeight())
                .eqIfPresent(TransferOperationDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(TransferOperationDO::getEquipmentStatus, reqVO.getEquipmentStatus())
                .eqIfPresent(TransferOperationDO::getProgress, reqVO.getProgress())
                .eqIfPresent(TransferOperationDO::getDestination, reqVO.getDestination())
                .eqIfPresent(TransferOperationDO::getAbnormalIsAbnormal, reqVO.getAbnormalIsAbnormal())
                .betweenIfPresent(TransferOperationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransferOperationDO::getId));
    }

    /**
     * 查询全局最大序号（用于operation_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(operation_id, '-', -1)), 0) FROM garbage_transfer_operation")
    Integer selectMaxSeq();

    List<TransferOperationDetailDO> selectDetailPage(@Param("reqVO") TransferOperationPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") TransferOperationPageReqVO pageReqVO);
}