package cn.iocoder.yudao.module.vehiclepass.dal.mysql.specialpass.passrecord;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.passrecord.vo.PassRecordPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.specialpass.passrecord.PassRecordDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 放行记录 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PassRecordMapper extends BaseMapperX<PassRecordDO> {

    default PageResult<PassRecordDO> selectPage(PassRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PassRecordDO>()
                .eqIfPresent(PassRecordDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(PassRecordDO::getPassReason, reqVO.getPassReason())
                .betweenIfPresent(PassRecordDO::getPassTime, reqVO.getPassTime())
                .eqIfPresent(PassRecordDO::getImageUrl, reqVO.getImageUrl())
                .eqIfPresent(PassRecordDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PassRecordDO::getStationId, reqVO.getStationId())
                .eqIfPresent(PassRecordDO::getOperatorId, reqVO.getOperatorId())
                .betweenIfPresent(PassRecordDO::getOperatorTime, reqVO.getOperatorTime())
                .eqIfPresent(PassRecordDO::getCheckResult, reqVO.getCheckResult())
                .eqIfPresent(PassRecordDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PassRecordDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(PassRecordDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(PassRecordDO::getCreator, reqVO.getCreator())
                .eqIfPresent(PassRecordDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(PassRecordDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(PassRecordDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(PassRecordDO::getId));
    }

}