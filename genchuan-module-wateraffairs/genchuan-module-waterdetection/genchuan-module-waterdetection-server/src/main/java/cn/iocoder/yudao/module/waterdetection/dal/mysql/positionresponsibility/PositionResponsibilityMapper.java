package cn.iocoder.yudao.module.waterdetection.dal.mysql.positionresponsibility;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.positionresponsibility.PositionResponsibilityDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.positionresponsibility.vo.*;

/**
 * 岗位职责划分管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface PositionResponsibilityMapper extends BaseMapperX<PositionResponsibilityDO> {

    default PageResult<PositionResponsibilityDO> selectPage(PositionResponsibilityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PositionResponsibilityDO>()
                .likeIfPresent(PositionResponsibilityDO::getPositionName, reqVO.getPositionName())
                .eqIfPresent(PositionResponsibilityDO::getResponsibilityDesc, reqVO.getResponsibilityDesc())
                .eqIfPresent(PositionResponsibilityDO::getQualificationReq, reqVO.getQualificationReq())
                .eqIfPresent(PositionResponsibilityDO::getBelongUnit, reqVO.getBelongUnit())
                .eqIfPresent(PositionResponsibilityDO::getManager, reqVO.getManager())
                .betweenIfPresent(PositionResponsibilityDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PositionResponsibilityDO::getId));
    }

}