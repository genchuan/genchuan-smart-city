package cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberuser;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberuser.MemberUserDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberuser.vo.*;

/**
 * 会员用户 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MemberUserMapper extends BaseMapperX<MemberUserDO> {

    default PageResult<MemberUserDO> selectPage(MemberUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MemberUserDO>()
                .eqIfPresent(MemberUserDO::getMobile, reqVO.getMobile())
                .eqIfPresent(MemberUserDO::getPassword, reqVO.getPassword())
                .eqIfPresent(MemberUserDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MemberUserDO::getRegisterIp, reqVO.getRegisterIp())
                .eqIfPresent(MemberUserDO::getRegisterTerminal, reqVO.getRegisterTerminal())
                .eqIfPresent(MemberUserDO::getLoginIp, reqVO.getLoginIp())
                .betweenIfPresent(MemberUserDO::getLoginDate, reqVO.getLoginDate())
                .likeIfPresent(MemberUserDO::getNickname, reqVO.getNickname())
                .eqIfPresent(MemberUserDO::getAvatar, reqVO.getAvatar())
                .likeIfPresent(MemberUserDO::getName, reqVO.getName())
                .eqIfPresent(MemberUserDO::getSex, reqVO.getSex())
                .eqIfPresent(MemberUserDO::getAreaId, reqVO.getAreaId())
                .eqIfPresent(MemberUserDO::getBirthday, reqVO.getBirthday())
                .eqIfPresent(MemberUserDO::getMark, reqVO.getMark())
                .eqIfPresent(MemberUserDO::getPoint, reqVO.getPoint())
                .eqIfPresent(MemberUserDO::getTagIds, reqVO.getTagIds())
                .eqIfPresent(MemberUserDO::getLevelId, reqVO.getLevelId())
                .eqIfPresent(MemberUserDO::getExperience, reqVO.getExperience())
                .eqIfPresent(MemberUserDO::getGroupId, reqVO.getGroupId())
                .betweenIfPresent(MemberUserDO::getExpireTime, reqVO.getExpireTime())
                .eqIfPresent(MemberUserDO::getAutoRenew, reqVO.getAutoRenew())
                .eqIfPresent(MemberUserDO::getCreator, reqVO.getCreator())
                .betweenIfPresent(MemberUserDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MemberUserDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(MemberUserDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(MemberUserDO::getId));
    }

}