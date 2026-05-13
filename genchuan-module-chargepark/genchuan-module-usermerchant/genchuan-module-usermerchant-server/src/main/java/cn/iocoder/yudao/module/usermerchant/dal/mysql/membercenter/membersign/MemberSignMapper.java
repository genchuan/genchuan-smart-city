package cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.membersign;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membersign.MemberSignDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membersign.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 会员签到 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MemberSignMapper extends BaseMapperX<MemberSignDO> {

    default PageResult<MemberSignDO> selectPage(MemberSignPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MemberSignDO>()
                .eqIfPresent(MemberSignDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(MemberSignDO::getSignDate, reqVO.getSignDate())
                .eqIfPresent(MemberSignDO::getContinuousDays, reqVO.getContinuousDays())
                .eqIfPresent(MemberSignDO::getPoint, reqVO.getPoint())
                .eqIfPresent(MemberSignDO::getExperience, reqVO.getExperience())
                .eqIfPresent(MemberSignDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MemberSignDO::getCreator, reqVO.getCreator())
                .betweenIfPresent(MemberSignDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MemberSignDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(MemberSignDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(MemberSignDO::getId));
    }

    List<MemberSignChartRespVO.SignTrendVO> selectSignTrend(@Param("start") LocalDateTime start,
                                                            @Param("end") LocalDateTime end,
                                                            @Param("granularity") String granularity);

    List<MemberSignChartRespVO.SignUserDistributionVO> selectSignUserDistribution(@Param("start") LocalDateTime start,
                                                                                  @Param("end") LocalDateTime end);

    Long selectTodaySignCount();

    Long selectTotalMemberCount();

}