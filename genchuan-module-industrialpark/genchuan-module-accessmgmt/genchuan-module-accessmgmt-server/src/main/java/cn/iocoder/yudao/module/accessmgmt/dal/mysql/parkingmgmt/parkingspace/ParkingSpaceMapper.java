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

    default PageResult<ParkingSpaceDO> selectPage(ParkingSpacePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkingSpaceDO>()
                .likeIfPresent(ParkingSpaceDO::getSpaceCode, reqVO.getSpaceCode())
                .likeIfPresent(ParkingSpaceDO::getParkName, reqVO.getParkName())
                .eqIfPresent(ParkingSpaceDO::getSpaceType, reqVO.getSpaceType())
                .eqIfPresent(ParkingSpaceDO::getSpaceStatus, reqVO.getSpaceStatus())
                .orderByDesc(ParkingSpaceDO::getId));
    }

    /**
     * 查询停车场经纬度分布
     */
    List<ParkingSpaceChartRespVO.ParkMapItem> selectParkMapList(@Param("parkName") String parkName);

    /**
     * 查询车位经纬度分布
     */
    List<ParkingSpaceChartRespVO.SpaceMapItem> selectSpaceMapList(@Param("parkName") String parkName);

    /**
     * 统计各状态车位数量
     */
    ParkingSpaceChartRespVO selectChartStats(@Param("parkName") String parkName);

    /**
     * 统计各时段使用率
     */
    List<ParkingSpaceChartRespVO.UseRateItem> selectUseRateList(@Param("parkName") String parkName);

    /**
     * 统计各类型占比
     */
    List<ParkingSpaceChartRespVO.TypeRateItem> selectTypeRateList(@Param("parkName") String parkName);

}
