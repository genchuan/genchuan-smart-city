package cn.iocoder.yudao.module.studentmgmt.dal.mysql.moralactivity;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.moralactivity.MoralActivityDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.moralactivity.vo.*;

/**
 * 德育活动 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MoralActivityMapper extends BaseMapperX<MoralActivityDO> {

    default PageResult<MoralActivityDO> selectPage(MoralActivityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MoralActivityDO>()
                .likeIfPresent(MoralActivityDO::getActivityName, reqVO.getActivityName())
                .eqIfPresent(MoralActivityDO::getActivityType, reqVO.getActivityType())
                .eqIfPresent(MoralActivityDO::getHostDept, reqVO.getHostDept())
                .betweenIfPresent(MoralActivityDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(MoralActivityDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(MoralActivityDO::getJoinNum, reqVO.getJoinNum())
                .eqIfPresent(MoralActivityDO::getPhoto, reqVO.getPhoto())
                .eqIfPresent(MoralActivityDO::getContent, reqVO.getContent())
                .betweenIfPresent(MoralActivityDO::getPublishTime, reqVO.getPublishTime())
                .eqIfPresent(MoralActivityDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MoralActivityDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MoralActivityDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(MoralActivityDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(MoralActivityDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MoralActivityDO::getId));
    }

}