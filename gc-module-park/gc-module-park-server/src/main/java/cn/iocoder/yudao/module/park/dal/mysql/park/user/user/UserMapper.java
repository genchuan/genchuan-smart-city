package cn.iocoder.yudao.module.park.dal.mysql.park.user.user;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.park.controller.admin.park.user.user.vo.UserPageReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.user.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface UserMapper extends BaseMapperX<UserDO> {

    default PageResult<UserDO> selectPage(UserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserDO>()
                .likeIfPresent(UserDO::getUserName, reqVO.getUserName())
                .eqIfPresent(UserDO::getPassword, reqVO.getPassword())
                .eqIfPresent(UserDO::getPhone, reqVO.getPhone())
                .eqIfPresent(UserDO::getIdCard, reqVO.getIdCard())
                .eqIfPresent(UserDO::getGender, reqVO.getGender())
                .eqIfPresent(UserDO::getUserType, reqVO.getUserType())
                .eqIfPresent(UserDO::getCertStatus, reqVO.getCertStatus())
                .eqIfPresent(UserDO::getWalletId, reqVO.getWalletId())
                .eqIfPresent(UserDO::getAccountStatus, reqVO.getAccountStatus())
                .betweenIfPresent(UserDO::getRegisterTime, reqVO.getRegisterTime())
                .betweenIfPresent(UserDO::getLastLoginTime, reqVO.getLastLoginTime())
                .betweenIfPresent(UserDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(UserDO::getRemark, reqVO.getRemark())
                .eqIfPresent(UserDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(UserDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(UserDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(UserDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(UserDO::getId));
    }

}
