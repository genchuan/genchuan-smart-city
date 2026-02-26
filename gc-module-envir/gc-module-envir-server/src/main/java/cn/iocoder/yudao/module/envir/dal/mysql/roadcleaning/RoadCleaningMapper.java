package cn.iocoder.yudao.module.envir.dal.mysql.roadcleaning;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envir.controller.admin.publictoilet.vo.PublicToiletPageReqVO;
import cn.iocoder.yudao.module.envir.dal.dataobject.publictoilet.PublicToiletDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.roadcleaning.RoadCleaningDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.roadcleaning.RoadCleaningDetailDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.envir.controller.admin.roadcleaning.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 道路清扫计划 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface RoadCleaningMapper extends BaseMapperX<RoadCleaningDO> {

    default PageResult<RoadCleaningDO> selectPage(RoadCleaningPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoadCleaningDO>()
                .eqIfPresent(RoadCleaningDO::getRoadCleaningId, reqVO.getRoadCleaningId())
                .eqIfPresent(RoadCleaningDO::getPlanNo, reqVO.getPlanNo())
                .eqIfPresent(RoadCleaningDO::getRoadId, reqVO.getRoadId())
                .eqIfPresent(RoadCleaningDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(RoadCleaningDO::getFrequency, reqVO.getFrequency())
                .eqIfPresent(RoadCleaningDO::getTimePeriod, reqVO.getTimePeriod())
                .eqIfPresent(RoadCleaningDO::getStaffIds, reqVO.getStaffIds())
                .eqIfPresent(RoadCleaningDO::getToolIds, reqVO.getToolIds())
                .eqIfPresent(RoadCleaningDO::getPlanStatusId, reqVO.getPlanStatusId())
                .eqIfPresent(RoadCleaningDO::getAbnormalCreateBy, reqVO.getAbnormalCreateBy())
                .betweenIfPresent(RoadCleaningDO::getAbnormalCreateTime, reqVO.getAbnormalCreateTime())
                .betweenIfPresent(RoadCleaningDO::getAbnormalUpdateTime, reqVO.getAbnormalUpdateTime())
                .eqIfPresent(RoadCleaningDO::getQualityRate, reqVO.getQualityRate())
                .eqIfPresent(RoadCleaningDO::getProblemCount, reqVO.getProblemCount())
                .eqIfPresent(RoadCleaningDO::getAttendanceRate, reqVO.getAttendanceRate())
                .eqIfPresent(RoadCleaningDO::getCheckPhotoUrl, reqVO.getCheckPhotoUrl())
                .eqIfPresent(RoadCleaningDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RoadCleaningDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RoadCleaningDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RoadCleaningDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(RoadCleaningDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RoadCleaningDO::getId));
    }

    /**
     * 查询道路清扫详情列表
     * @return 道路清扫详情列表
     */
    List<RoadCleaningDetailDO> selectListDetail();

    /**
     * 分页查询道路清扫详情列表
     */
    List<RoadCleaningDetailDO> selectDetailPage(@Param("reqVO") RoadCleaningPageReqVO reqVO);

    /**
     * 查询总数
     */
    Long selectCount(@Param("reqVO") RoadCleaningPageReqVO reqVO);
}