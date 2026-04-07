package cn.iocoder.yudao.module.vehiclecharging.dal.mysql.chargingstation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.ChargingStationAreaCountRespVO;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.ChargingStationPageReqVO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.chargingstation.ChargingStationDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChargingStationMapper extends BaseMapperX<ChargingStationDO> {
    default PageResult<ChargingStationDO> selectPage(ChargingStationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ChargingStationDO>()
                .likeIfPresent(ChargingStationDO::getStationCode, reqVO.getStationCode())
                .likeIfPresent(ChargingStationDO::getStationName, reqVO.getStationName())
                .likeIfPresent(ChargingStationDO::getAddress, reqVO.getAddress())
                .eqIfPresent(ChargingStationDO::getCoopMode, reqVO.getCoopMode())
                .eqIfPresent(ChargingStationDO::getStationStatus, reqVO.getStationStatus())
                .eqIfPresent(ChargingStationDO::getAreaId, reqVO.getAreaId())
                .orderByDesc(ChargingStationDO::getId));
    }

    /**
     * 统计各区域充电场站数量
     *
     * @return 统计列表
     */
    List<ChargingStationAreaCountRespVO> selectAreaStationCount(@Param("id") Long id);
}
