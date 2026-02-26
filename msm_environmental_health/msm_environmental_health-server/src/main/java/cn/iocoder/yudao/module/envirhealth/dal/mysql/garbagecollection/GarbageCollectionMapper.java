package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageCollectionDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageCollectionDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 收运计划 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface GarbageCollectionMapper extends BaseMapperX<GarbageCollectionDO> {

    default PageResult<GarbageCollectionDO> selectPage(GarbageCollectionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GarbageCollectionDO>()
                .eqIfPresent(GarbageCollectionDO::getCollectionId, reqVO.getCollectionId())
                .eqIfPresent(GarbageCollectionDO::getPlanNo, reqVO.getPlanNo())
                .eqIfPresent(GarbageCollectionDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(GarbageCollectionDO::getGarbageTypeId, reqVO.getGarbageTypeId())
                .eqIfPresent(GarbageCollectionDO::getFrequency, reqVO.getFrequency())
                .eqIfPresent(GarbageCollectionDO::getTimePeriod, reqVO.getTimePeriod())
                .eqIfPresent(GarbageCollectionDO::getVehicleId, reqVO.getVehicleId())
                .eqIfPresent(GarbageCollectionDO::getStaffIds, reqVO.getStaffIds())
                .eqIfPresent(GarbageCollectionDO::getPointIds, reqVO.getPointIds())
                .eqIfPresent(GarbageCollectionDO::getPlanStatusId, reqVO.getPlanStatusId())
                .eqIfPresent(GarbageCollectionDO::getCompletionRate, reqVO.getCompletionRate())
                .eqIfPresent(GarbageCollectionDO::getAbnormalCount, reqVO.getAbnormalCount())
                .betweenIfPresent(GarbageCollectionDO::getAbnormalCreateTime, reqVO.getAbnormalCreateTime())
                .betweenIfPresent(GarbageCollectionDO::getAbnormalUpdateTime, reqVO.getAbnormalUpdateTime())
                .eqIfPresent(GarbageCollectionDO::getCreateBy, reqVO.getCreateBy())
                .eqIfPresent(GarbageCollectionDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(GarbageCollectionDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(GarbageCollectionDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(GarbageCollectionDO::getExtCommon4, reqVO.getExtCommon4())
                .eqIfPresent(GarbageCollectionDO::getProgress, reqVO.getProgress())
                .eqIfPresent(GarbageCollectionDO::getCollectedVolume, reqVO.getCollectedVolume())
                .eqIfPresent(GarbageCollectionDO::getCheckinStatus, reqVO.getCheckinStatus())
                .eqIfPresent(GarbageCollectionDO::getTrackCoverage, reqVO.getTrackCoverage())
                .betweenIfPresent(GarbageCollectionDO::getLastReportTime, reqVO.getLastReportTime())
                .eqIfPresent(GarbageCollectionDO::getIsAbnormal, reqVO.getIsAbnormal())
                .betweenIfPresent(GarbageCollectionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GarbageCollectionDO::getId));
    }

    /**
     * 联表查询垃圾收运详情列表(分页)
     * @param pageReqVO 分页查询参数
     * @return 详情列表
     */
    List<GarbageCollectionDetailDO> selectDetailPage(@Param("reqVO") GarbageCollectionPageReqVO pageReqVO);

    /**
     * 查询主表总数(分页)
     * @param pageReqVO 查询参数
     * @return 总数
     */
    Long selectCount(@Param("reqVO") GarbageCollectionPageReqVO pageReqVO);
}