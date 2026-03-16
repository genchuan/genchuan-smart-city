package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve.TransferReservePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferReserveDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.detail.TransferReserveDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 进站预约 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TransferReserveMapper extends BaseMapperX<TransferReserveDO> {

    default PageResult<TransferReserveDO> selectPage(TransferReservePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TransferReserveDO>()
                .eqIfPresent(TransferReserveDO::getReserveId, reqVO.getReserveId())
                .eqIfPresent(TransferReserveDO::getVehicleId, reqVO.getVehicleId())
                .eqIfPresent(TransferReserveDO::getGarbageTypeId, reqVO.getGarbageTypeId())
                .betweenIfPresent(TransferReserveDO::getExpectedTime, reqVO.getExpectedTime())
                .eqIfPresent(TransferReserveDO::getGarbageWeight, reqVO.getGarbageWeight())
                .eqIfPresent(TransferReserveDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(TransferReserveDO::getReserveStatus, reqVO.getReserveStatus())
                .eqIfPresent(TransferReserveDO::getSortNo, reqVO.getSortNo())
                .betweenIfPresent(TransferReserveDO::getAbnormalCreateTime, reqVO.getAbnormalCreateTime())
                .eqIfPresent(TransferReserveDO::getHandleBy, reqVO.getHandleBy())
                .betweenIfPresent(TransferReserveDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TransferReserveDO::getId));
    }

    /**
     * 查询全局最大序号（用于reserve_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(reserve_id, '-', -1)), 0) FROM garbage_transfer_reserve")
    Integer selectMaxSeq();

    /**
     * 查询已经排序的最大值
     */
    @Select("SELECT COALESCE(MAX(sort_no), 0) FROM garbage_transfer_reserve WHERE deleted = 0 AND reserve_status = '已排序' ")
    Integer selectMaxSortNo();

    List<TransferReserveDetailDO> selectDetailPage(@Param("reqVO") TransferReservePageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") TransferReservePageReqVO pageReqVO);
}