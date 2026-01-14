package cn.iocoder.yudao.module.industry.dal.mysql.park.cardriveinrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.cardriveinrecord.vo.CarDriveinRecordPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.cardriveinrecord.CarDriveinRecordDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 车辆入场记录 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface CarDriveinRecordMapper extends BaseMapperX<CarDriveinRecordDO> {

    default PageResult<CarDriveinRecordDO> selectPage(CarDriveinRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CarDriveinRecordDO>()
                .eqIfPresent(CarDriveinRecordDO::getRecordId, reqVO.getRecordId())
                .eqIfPresent(CarDriveinRecordDO::getEntranceNo, reqVO.getEntranceNo())
                .likeIfPresent(CarDriveinRecordDO::getEntranceName, reqVO.getEntranceName())
                .eqIfPresent(CarDriveinRecordDO::getPlateType, reqVO.getPlateType())
                .eqIfPresent(CarDriveinRecordDO::getPlateNumber, reqVO.getPlateNumber())
                .betweenIfPresent(CarDriveinRecordDO::getDriveInTime, reqVO.getDriveInTime())
                .eqIfPresent(CarDriveinRecordDO::getDriveInPhoto, reqVO.getDriveInPhoto())
                .eqIfPresent(CarDriveinRecordDO::getEmptyPlot, reqVO.getEmptyPlot())
                .eqIfPresent(CarDriveinRecordDO::getOperatorId, reqVO.getOperatorId())
                .likeIfPresent(CarDriveinRecordDO::getOperatorName, reqVO.getOperatorName())
                .eqIfPresent(CarDriveinRecordDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(CarDriveinRecordDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(CarDriveinRecordDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(CarDriveinRecordDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(CarDriveinRecordDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CarDriveinRecordDO::getId));
    }

    /**
     * 根据recordId查询入场记录
     */
    @Select("SELECT * FROM park_car_drivein_record WHERE record_id = #{recordId} AND deleted = 0 LIMIT 1")
    CarDriveinRecordDO selectByRecordId(String recordId);

}