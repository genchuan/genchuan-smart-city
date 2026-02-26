package cn.iocoder.yudao.module.envir.dal.mysql.garbagecollection;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envir.dal.dataobject.garbagecollection.*;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.envir.controller.admin.garbagecollection.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 收运计划 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface GarbageCollectionMapper extends BaseMapperX<GarbageCollectionDO> {

    default PageResult<GarbageCollectionDO> selectPage(GarbageCollectionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GarbageCollectionDO>()
                .eqIfPresent(GarbageCollectionDO::getGarbageCollectionId, reqVO.getGarbageCollectionId())
                .eqIfPresent(GarbageCollectionDO::getPlanNo, reqVO.getPlanNo())
                .eqIfPresent(GarbageCollectionDO::getGarbageTypeId, reqVO.getGarbageTypeId())
                .eqIfPresent(GarbageCollectionDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(GarbageCollectionDO::getPointIds, reqVO.getPointIds())
                .eqIfPresent(GarbageCollectionDO::getFrequency, reqVO.getFrequency())
                .eqIfPresent(GarbageCollectionDO::getTimePeriod, reqVO.getTimePeriod())
                .eqIfPresent(GarbageCollectionDO::getVehicleId, reqVO.getVehicleId())
                .eqIfPresent(GarbageCollectionDO::getStaffIds, reqVO.getStaffIds())
                .eqIfPresent(GarbageCollectionDO::getPlanStatusId, reqVO.getPlanStatusId())
                .eqIfPresent(GarbageCollectionDO::getAbnormalCreateBy, reqVO.getAbnormalCreateBy())
                .betweenIfPresent(GarbageCollectionDO::getAbnormalCreateTime, reqVO.getAbnormalCreateTime())
                .betweenIfPresent(GarbageCollectionDO::getAbnormalUpdateTime, reqVO.getAbnormalUpdateTime())
                .eqIfPresent(GarbageCollectionDO::getCompletionRate, reqVO.getCompletionRate())
                .eqIfPresent(GarbageCollectionDO::getAbnormalCount, reqVO.getAbnormalCount())
                .eqIfPresent(GarbageCollectionDO::getAbnormalDetailIds, reqVO.getAbnormalDetailIds())
                .eqIfPresent(GarbageCollectionDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(GarbageCollectionDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(GarbageCollectionDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(GarbageCollectionDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(GarbageCollectionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GarbageCollectionDO::getId));
    }

    /**
     * 联表查询垃圾收运详情列表
     * @return 详情列表
     */
    List<GarbageCollectionDetailDO> selectListDetail();

    /**
     * 联表查询垃圾收运详情列表(分页)
     * @param pageReqVO 分页查询参数
     * @return 详情列表
     */
    List<GarbageCollectionDetailDO> selectDetailPage(@Param("reqVO") GarbageCollectionPageReqVO pageReqVO);

    /**
     * 查询主表总数(分页)- 只查主表，性能更好
     * @param pageReqVO 查询参数
     * @return 总数
     */
    Long selectCount(@Param("reqVO") GarbageCollectionPageReqVO pageReqVO);
}