package cn.iocoder.yudao.module.industry.dal.mysql.park.cardriveoutrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.cardriveoutrecord.vo.CarDriveoutRecordPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.cardriveoutrecord.CarDriveoutRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 车辆出场记录 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface CarDriveoutRecordMapper extends BaseMapperX<CarDriveoutRecordDO> {

    default PageResult<CarDriveoutRecordDO> selectPage(CarDriveoutRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CarDriveoutRecordDO>()
                .eqIfPresent(CarDriveoutRecordDO::getRecordId, reqVO.getRecordId())
                .eqIfPresent(CarDriveoutRecordDO::getEntranceNo, reqVO.getEntranceNo())
                .likeIfPresent(CarDriveoutRecordDO::getEntranceName, reqVO.getEntranceName())
                .eqIfPresent(CarDriveoutRecordDO::getPlateType, reqVO.getPlateType())
                .eqIfPresent(CarDriveoutRecordDO::getPlateNumber, reqVO.getPlateNumber())
                .betweenIfPresent(CarDriveoutRecordDO::getDriveInTime, reqVO.getDriveInTime())
                .eqIfPresent(CarDriveoutRecordDO::getDriveInPhoto, reqVO.getDriveInPhoto())
                .eqIfPresent(CarDriveoutRecordDO::getExitNo, reqVO.getExitNo())
                .likeIfPresent(CarDriveoutRecordDO::getExitName, reqVO.getExitName())
                .betweenIfPresent(CarDriveoutRecordDO::getDriveOutTime, reqVO.getDriveOutTime())
                .eqIfPresent(CarDriveoutRecordDO::getDriveOutPhoto, reqVO.getDriveOutPhoto())
                .eqIfPresent(CarDriveoutRecordDO::getEmptyPlot, reqVO.getEmptyPlot())
                .eqIfPresent(CarDriveoutRecordDO::getOperatorId, reqVO.getOperatorId())
                .likeIfPresent(CarDriveoutRecordDO::getOperatorName, reqVO.getOperatorName())
                .eqIfPresent(CarDriveoutRecordDO::getShouldPay, reqVO.getShouldPay())
                .eqIfPresent(CarDriveoutRecordDO::getActualPay, reqVO.getActualPay())
                .eqIfPresent(CarDriveoutRecordDO::getOutType, reqVO.getOutType())
                .eqIfPresent(CarDriveoutRecordDO::getOutRemark, reqVO.getOutRemark())
                .eqIfPresent(CarDriveoutRecordDO::getPayMethod, reqVO.getPayMethod())
                .eqIfPresent(CarDriveoutRecordDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(CarDriveoutRecordDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(CarDriveoutRecordDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(CarDriveoutRecordDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(CarDriveoutRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CarDriveoutRecordDO::getId));
    }

    /**
     * 根据recordId查询出场记录
     */
    @Select("SELECT * FROM park_car_driveout_record WHERE record_id = #{recordId} AND deleted = 0 LIMIT 1")
    CarDriveoutRecordDO selectByRecordId(String recordId);

}