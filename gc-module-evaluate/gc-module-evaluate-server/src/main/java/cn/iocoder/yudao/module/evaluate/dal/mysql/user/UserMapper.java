package cn.iocoder.yudao.module.evaluate.dal.mysql.user;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.user.vo.UserPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.user.UserDO;
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
                .eqIfPresent(UserDO::getUserId, reqVO.getUserId())
                .likeIfPresent(UserDO::getUserName, reqVO.getUserName())
                .eqIfPresent(UserDO::getUserPhone, reqVO.getUserPhone())
                .likeIfPresent(UserDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(UserDO::getRoleId, reqVO.getRoleId())
                .eqIfPresent(UserDO::getStatusId, reqVO.getStatusId())
                .eqIfPresent(UserDO::getCreateBy, reqVO.getCreateBy())
                .eqIfPresent(UserDO::getUpdateBy, reqVO.getUpdateBy())
                .betweenIfPresent(UserDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(UserDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(UserDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(UserDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(UserDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(UserDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(UserDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserDO::getId));
    }

}