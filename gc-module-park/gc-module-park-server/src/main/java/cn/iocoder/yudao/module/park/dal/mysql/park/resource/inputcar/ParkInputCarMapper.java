package cn.iocoder.yudao.module.park.dal.mysql.park.resource.inputcar;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.resource.inputcar.vo.ParkInputCarPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.resource.inputcar.ParkInputCarDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 泊位录入车辆 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface ParkInputCarMapper extends BaseMapperX<ParkInputCarDO> {

    default PageResult<ParkInputCarDO> selectPage(ParkInputCarPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkInputCarDO>()
                .eqIfPresent(ParkInputCarDO::getParkId, reqVO.getParkId())
                .eqIfPresent(ParkInputCarDO::getTargetBerthNo, reqVO.getTargetBerthNo())
                .eqIfPresent(ParkInputCarDO::getCarNumber, reqVO.getCarNumber())
                .eqIfPresent(ParkInputCarDO::getCarType, reqVO.getCarType())
                .eqIfPresent(ParkInputCarDO::getPlateColor, reqVO.getPlateColor())
                .eqIfPresent(ParkInputCarDO::getParkingStatus, reqVO.getParkingStatus())
                .eqIfPresent(ParkInputCarDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkInputCarDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkInputCarDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkInputCarDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(ParkInputCarDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(ParkInputCarDO::getEntryTime, reqVO.getEntryTime())
                .betweenIfPresent(ParkInputCarDO::getExitTime, reqVO.getExitTime())
                .orderByDesc(ParkInputCarDO::getId));
    }

}