package cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberpoint;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberpoint.MemberPointDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberpoint.vo.*;

/**
 * 会员积分 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MemberPointMapper extends BaseMapperX<MemberPointDO> {

    default PageResult<MemberPointDO> selectPage(MemberPointPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MemberPointDO>()
                .eqIfPresent(MemberPointDO::getUserId, reqVO.getUserId())
                .eqIfPresent(MemberPointDO::getChangeAmount, reqVO.getChangeAmount())
                .eqIfPresent(MemberPointDO::getTotalPoint, reqVO.getTotalPoint())
                .eqIfPresent(MemberPointDO::getChangeType, reqVO.getChangeType())
                .eqIfPresent(MemberPointDO::getChangeReason, reqVO.getChangeReason())
                .eqIfPresent(MemberPointDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MemberPointDO::getCheckResult, reqVO.getCheckResult())
                .betweenIfPresent(MemberPointDO::getCheckTime, reqVO.getCheckTime())
                .eqIfPresent(MemberPointDO::getCheckBy, reqVO.getCheckBy())
                .eqIfPresent(MemberPointDO::getBizId, reqVO.getBizId())
                .eqIfPresent(MemberPointDO::getBizType, reqVO.getBizType())
                .eqIfPresent(MemberPointDO::getTitle, reqVO.getTitle())
                .eqIfPresent(MemberPointDO::getDescription, reqVO.getDescription())
                .eqIfPresent(MemberPointDO::getCreator, reqVO.getCreator())
                .betweenIfPresent(MemberPointDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MemberPointDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(MemberPointDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(MemberPointDO::getId));
    }

}