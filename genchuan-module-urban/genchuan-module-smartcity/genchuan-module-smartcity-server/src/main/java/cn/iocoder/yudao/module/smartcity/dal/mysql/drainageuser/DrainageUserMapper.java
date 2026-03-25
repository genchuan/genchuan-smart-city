package cn.iocoder.yudao.module.smartcity.dal.mysql.drainageuser;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.drainageuser.DrainageUserDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.drainageuser.vo.*;

/**
 * 排水户信息 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface DrainageUserMapper extends BaseMapperX<DrainageUserDO> {

    default PageResult<DrainageUserDO> selectPage(DrainageUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DrainageUserDO>()
                .likeIfPresent(DrainageUserDO::getCreditCode, reqVO.getCreditCode())
                .likeIfPresent(DrainageUserDO::getUserName, reqVO.getUserName())
                .likeIfPresent(DrainageUserDO::getIndustryType, reqVO.getIndustryType())
                .orderByDesc(DrainageUserDO::getId));
    }

}