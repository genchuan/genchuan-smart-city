package cn.iocoder.yudao.module.waterdetection.dal.mysql.meteruserrelation;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.meteruserrelation.MeterUserRelationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.meteruserrelation.vo.*;

/**
 * 户表关联及变更管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface MeterUserRelationMapper extends BaseMapperX<MeterUserRelationDO> {

    default PageResult<MeterUserRelationDO> selectPage(MeterUserRelationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MeterUserRelationDO>()
                .eqIfPresent(MeterUserRelationDO::getMeterCode, reqVO.getMeterCode())
                .eqIfPresent(MeterUserRelationDO::getOldUserCode, reqVO.getOldUserCode())
                .eqIfPresent(MeterUserRelationDO::getNewUserCode, reqVO.getNewUserCode())
                .eqIfPresent(MeterUserRelationDO::getChangeReason, reqVO.getChangeReason())
                .betweenIfPresent(MeterUserRelationDO::getChangeTime, reqVO.getChangeTime())
                .eqIfPresent(MeterUserRelationDO::getOperator, reqVO.getOperator())
                .betweenIfPresent(MeterUserRelationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MeterUserRelationDO::getId));
    }

}