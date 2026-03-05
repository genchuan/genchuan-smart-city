package cn.iocoder.yudao.module.industry.dal.mysql.park.marketing.parkpointsrecord;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrecord.vo.ParkPointsRecordPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parkpointsrecord.ParkPointsRecordDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 积分变动记录 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkPointsRecordMapper extends BaseMapperX<ParkPointsRecordDO> {

    default PageResult<ParkPointsRecordDO> selectPage(ParkPointsRecordPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkPointsRecordDO>()
                .eqIfPresent(ParkPointsRecordDO::getUserId, reqVO.getUserId())
                .eqIfPresent(ParkPointsRecordDO::getPointsType, reqVO.getPointsType())
                .eqIfPresent(ParkPointsRecordDO::getPointsAmount, reqVO.getPointsAmount())
                .eqIfPresent(ParkPointsRecordDO::getTriggerSource, reqVO.getTriggerSource())
                .eqIfPresent(ParkPointsRecordDO::getRelatedId, reqVO.getRelatedId())
                .betweenIfPresent(ParkPointsRecordDO::getChangeTime, reqVO.getChangeTime())
                .eqIfPresent(ParkPointsRecordDO::getBalanceAfter, reqVO.getBalanceAfter())
                .betweenIfPresent(ParkPointsRecordDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkPointsRecordDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ParkPointsRecordDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkPointsRecordDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkPointsRecordDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkPointsRecordDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(ParkPointsRecordDO::getId));
    }

}
