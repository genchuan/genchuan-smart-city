package cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberlevel;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberlevel.MemberLevelDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberlevel.vo.*;

/**
 * 会员等级 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MemberLevelMapper extends BaseMapperX<MemberLevelDO> {

    default PageResult<MemberLevelDO> selectPage(MemberLevelPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MemberLevelDO>()
                .likeIfPresent(MemberLevelDO::getName, reqVO.getName())
                .eqIfPresent(MemberLevelDO::getLevelValue, reqVO.getLevelValue())
                .eqIfPresent(MemberLevelDO::getUpgradeCondition, reqVO.getUpgradeCondition())
                .eqIfPresent(MemberLevelDO::getBenefits, reqVO.getBenefits())
                .eqIfPresent(MemberLevelDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(MemberLevelDO::getEffectiveTime, reqVO.getEffectiveTime())
                .eqIfPresent(MemberLevelDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MemberLevelDO::getCreator, reqVO.getCreator())
                .betweenIfPresent(MemberLevelDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MemberLevelDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(MemberLevelDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(MemberLevelDO::getId));
    }

}