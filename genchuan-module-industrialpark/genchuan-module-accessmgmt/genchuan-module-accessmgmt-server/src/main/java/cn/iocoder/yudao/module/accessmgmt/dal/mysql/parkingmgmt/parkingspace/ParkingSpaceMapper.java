package cn.iocoder.yudao.module.accessmgmt.dal.mysql.parkingmgmt.parkingspace;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingspace.vo.ParkingSpaceChartRespVO;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.parkingspace.vo.ParkingSpacePageReqVO;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.parkingmgmt.parkingspace.ParkingSpaceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 车位信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ParkingSpaceMapper extends BaseMapperX<ParkingSpaceDO> {

    /**
     * 分页查询车位信息，支持按车位编号(模糊)/停车场名称(模糊)/车位类型(精确)/车位状态(精确)筛选，按主键倒序
     */
    default PageResult<ParkingSpaceDO> selectPage(ParkingSpacePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkingSpaceDO>()
                .likeIfPresent(ParkingSpaceDO::getSpaceCode, reqVO.getSpaceCode())
                .likeIfPresent(ParkingSpaceDO::getParkName, reqVO.getParkName())
                .eqIfPresent(ParkingSpaceDO::getSpaceType, reqVO.getSpaceType())
                .eqIfPresent(ParkingSpaceDO::getSpaceStatus, reqVO.getSpaceStatus())
                .orderByDesc(ParkingSpaceDO::getId));
    }

    /**
     * 各停车场经纬度分布（用于地图打点）
     */
    List<ParkingSpaceChartRespVO.ParkMapItem> selectParkMapList(@Param("parkName") String parkName);

    /**
     * 各车位经纬度分布（用于地图打点）
     */
    List<ParkingSpaceChartRespVO.SpaceMapItem> selectSpaceMapList(@Param("parkName") String parkName);

    /**
     * 统计总车位/空闲/占用/预约数量（聚合查询）
     */
    ParkingSpaceChartRespVO selectChartStats(@Param("parkName") String parkName);

    /**
     * 按时间段统计车位使用率趋势
     */
    List<ParkingSpaceChartRespVO.UseRateItem> selectUseRateList(@Param("parkName") String parkName);

    /**
     * 按车位类型统计占比分布
     */
    List<ParkingSpaceChartRespVO.TypeRateItem> selectTypeRateList(@Param("parkName") String parkName);

}
