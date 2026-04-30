package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.stationmgmt.stationinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.StationInfoPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo.StationInfoRespVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.stationinfo.StationInfoDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 场站信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface StationInfoMapper extends BaseMapperX<StationInfoDO> {

    default PageResult<StationInfoDO> selectPage(StationInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StationInfoDO>()
                .likeIfPresent(StationInfoDO::getStationNo, reqVO.getStationNo())
                .likeIfPresent(StationInfoDO::getName, reqVO.getName())
                .eqIfPresent(StationInfoDO::getType, reqVO.getType())
                .likeIfPresent(StationInfoDO::getAddress, reqVO.getAddress())
                .eqIfPresent(StationInfoDO::getSpaceTotal, reqVO.getSpaceTotal())
                .eqIfPresent(StationInfoDO::getUserId, reqVO.getUserId())
                .eqIfPresent(StationInfoDO::getFeeStandard, reqVO.getFeeStandard())
                .eqIfPresent(StationInfoDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(StationInfoDO::getOperateType, reqVO.getOperateType())
                .eqIfPresent(StationInfoDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(StationInfoDO::getBindTime, reqVO.getBindTime())
                .eqIfPresent(StationInfoDO::getBindUserId, reqVO.getBindUserId())
                .eqIfPresent(StationInfoDO::getDeviceCount, reqVO.getDeviceCount())
                .eqIfPresent(StationInfoDO::getSpaceCount, reqVO.getSpaceCount())
                .eqIfPresent(StationInfoDO::getRemark, reqVO.getRemark())
                .eqIfPresent(StationInfoDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(StationInfoDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(StationInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(StationInfoDO::getId));
    }

    Page<StationInfoRespVO> getPage(
            Page<StationInfoRespVO> page,
            @Param("pageReqVO") StationInfoPageReqVO pageReqVO
    );
}
