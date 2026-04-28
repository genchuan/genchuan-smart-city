package cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.areamgmt.areainfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.areamgmt.areainfo.vo.AreaInfoPageReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.areamgmt.areainfo.AreaInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 片区信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AreaInfoMapper extends BaseMapperX<AreaInfoDO> {

    default PageResult<AreaInfoDO> selectPage(AreaInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AreaInfoDO>()
                .eqIfPresent(AreaInfoDO::getUserId,reqVO.getUserId())
                .likeIfPresent(AreaInfoDO::getAreaNo, reqVO.getAreaNo())
                .likeIfPresent(AreaInfoDO::getName, reqVO.getName())
//                .eqIfPresent(AreaInfoDO::getParentId, reqVO.getParentId())
//                .eqIfPresent(AreaInfoDO::getProvince, reqVO.getProvince())
//                .eqIfPresent(AreaInfoDO::getCity, reqVO.getCity())
                .likeIfPresent(AreaInfoDO::getDistrict, reqVO.getDistrict())
//                .eqIfPresent(AreaInfoDO::getAddress, reqVO.getAddress())
//                .eqIfPresent(AreaInfoDO::getLeaderId, reqVO.getLeaderId())
                .likeIfPresent(AreaInfoDO::getPhone, reqVO.getPhone())
                .eqIfPresent(AreaInfoDO::getStationCount, reqVO.getStationCount())
                .eqIfPresent(AreaInfoDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AreaInfoDO::getRemark, reqVO.getRemark())
                .eqIfPresent(AreaInfoDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(AreaInfoDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(AreaInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AreaInfoDO::getId));
    }

}
