package cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.membergroup;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membergroup.MemberGroupDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membergroup.vo.*;

/**
 * 会员分组 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MemberGroupMapper extends BaseMapperX<MemberGroupDO> {

    default PageResult<MemberGroupDO> selectPage(MemberGroupPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MemberGroupDO>()
                .likeIfPresent(MemberGroupDO::getName, reqVO.getName())
                .eqIfPresent(MemberGroupDO::getDescription, reqVO.getDescription())
                .eqIfPresent(MemberGroupDO::getRule, reqVO.getRule())
                .eqIfPresent(MemberGroupDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(MemberGroupDO::getEffectiveTime, reqVO.getEffectiveTime())
                .eqIfPresent(MemberGroupDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MemberGroupDO::getCreator, reqVO.getCreator())
                .betweenIfPresent(MemberGroupDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MemberGroupDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(MemberGroupDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(MemberGroupDO::getId));
    }

}