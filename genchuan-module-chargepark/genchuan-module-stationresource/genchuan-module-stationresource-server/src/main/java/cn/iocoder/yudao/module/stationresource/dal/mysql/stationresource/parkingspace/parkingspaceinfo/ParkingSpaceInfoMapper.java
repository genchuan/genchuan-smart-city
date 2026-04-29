package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.parkingspace.parkingspaceinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ParkingSpaceInfoPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ParkingSpaceInfoRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.statistics.ParkingSpaceChartRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.parkingspace.parkingspaceinfo.ParkingSpaceInfoDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 车位信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ParkingSpaceInfoMapper extends BaseMapperX<ParkingSpaceInfoDO> {

    default PageResult<ParkingSpaceInfoDO> selectPage(ParkingSpaceInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkingSpaceInfoDO>()
                // 模糊查询，改成 like
                .likeIfPresent(ParkingSpaceInfoDO::getSpaceNo, reqVO.getSpaceNo())
                .eqIfPresent(ParkingSpaceInfoDO::getStationId, reqVO.getStationId())
                // 模糊查询，改成 like
                .likeIfPresent(ParkingSpaceInfoDO::getGarage, reqVO.getGarage())
                .eqIfPresent(ParkingSpaceInfoDO::getLocation, reqVO.getLocation())
                .eqIfPresent(ParkingSpaceInfoDO::getType, reqVO.getType())
                .eqIfPresent(ParkingSpaceInfoDO::getDeviceType, reqVO.getDeviceType())
                .eqIfPresent(ParkingSpaceInfoDO::getQrcode, reqVO.getQrcode())
                .eqIfPresent(ParkingSpaceInfoDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ParkingSpaceInfoDO::getRealStatus, reqVO.getRealStatus())
                .betweenIfPresent(ParkingSpaceInfoDO::getBindTime, reqVO.getBindTime())
                .eqIfPresent(ParkingSpaceInfoDO::getBindUserId, reqVO.getBindUserId())
                .eqIfPresent(ParkingSpaceInfoDO::getDeviceId, reqVO.getDeviceId())
                .betweenIfPresent(ParkingSpaceInfoDO::getStatusUpdateTime, reqVO.getStatusUpdateTime())
                .eqIfPresent(ParkingSpaceInfoDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkingSpaceInfoDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(ParkingSpaceInfoDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(ParkingSpaceInfoDO::getCreator, reqVO.getCreator())
                .eqIfPresent(ParkingSpaceInfoDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(ParkingSpaceInfoDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(ParkingSpaceInfoDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(ParkingSpaceInfoDO::getId));
    }


    List<ParkingSpaceChartRespVO.SpaceMapDTO> selectParkingSpaceChartList();

    Long selectTotalSpaceCount();

    Long selectAvailableSpaceCount();

    Page<ParkingSpaceInfoRespVO> getPage(Page<ParkingSpaceInfoRespVO> page, @Param("pageReqVO") ParkingSpaceInfoPageReqVO pageReqVO);
}
