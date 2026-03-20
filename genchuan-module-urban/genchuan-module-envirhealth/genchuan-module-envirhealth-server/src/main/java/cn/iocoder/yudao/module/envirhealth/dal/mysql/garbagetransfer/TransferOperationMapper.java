package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferOperationDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferOperationDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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

    /**
     * 统计当前作业总数
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer_operation WHERE deleted = 0")
    Long selectTotalCount();

    /**
     * 统计正常运行数
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer_operation WHERE deleted = 0 AND abnormal_is_abnormal = '否'")
    Long selectNormalCount();

    /**
     * 统计异常标记数
     */
    @Select("SELECT COUNT(*) FROM garbage_transfer_operation WHERE deleted = 0 AND abnormal_is_abnormal = '是'")
    Long selectAbnormalCount();

    /**
     * 查询已完成转运作业数量（返回List<Map>格式）
     *
     * @return 包含已完成数量的List<Map>
     */
    @Select("SELECT " +
            "    '已完成' as status_name, " +
            "    COUNT(DISTINCT gto.id) as count " +
            "FROM garbage_transfer_operation gto " +
            "INNER JOIN garbage_collection gc ON gto.plan_id = gc.collection_id " +
            "WHERE gto.deleted = 0 " +
            "  AND gc.deleted = 0 " +
            "  AND gc.plan_status_id = 'uuid-plan-status-003' " +
            "GROUP BY '已完成'")
    List<Map<String, Object>> selectCompletedCountAsList();
}