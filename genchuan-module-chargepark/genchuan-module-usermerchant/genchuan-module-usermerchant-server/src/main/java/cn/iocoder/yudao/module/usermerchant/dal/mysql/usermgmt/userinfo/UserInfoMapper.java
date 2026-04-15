package cn.iocoder.yudao.module.usermerchant.dal.mysql.usermgmt.userinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.usermgmt.userinfo.UserInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.usermerchant.controller.admin.usermgmt.userinfo.vo.*;

/**
 * 用户信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface UserInfoMapper extends BaseMapperX<UserInfoDO> {

    default PageResult<UserInfoDO> selectPage(UserInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserInfoDO>()
                .likeIfPresent(UserInfoDO::getNickname, reqVO.getNickname())
                .eqIfPresent(UserInfoDO::getPhone, reqVO.getPhone())
                .eqIfPresent(UserInfoDO::getUserType, reqVO.getUserType())
                .eqIfPresent(UserInfoDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(UserInfoDO::getRegisterTime, reqVO.getRegisterTime())
                .betweenIfPresent(UserInfoDO::getLoginTime, reqVO.getLoginTime())
                .eqIfPresent(UserInfoDO::getWalletBalance, reqVO.getWalletBalance())
                .eqIfPresent(UserInfoDO::getCarCount, reqVO.getCarCount())
                .eqIfPresent(UserInfoDO::getRemark, reqVO.getRemark())
                .orderByDesc(UserInfoDO::getId));
    }

}