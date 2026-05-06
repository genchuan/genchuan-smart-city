package cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.membertag;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.membertag.MemberTagDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.membertag.vo.*;

/**
 * 会员标签 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MemberTagMapper extends BaseMapperX<MemberTagDO> {

    default PageResult<MemberTagDO> selectPage(MemberTagPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MemberTagDO>()
                .likeIfPresent(MemberTagDO::getName, reqVO.getName())
                .eqIfPresent(MemberTagDO::getDescription, reqVO.getDescription())
                .eqIfPresent(MemberTagDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MemberTagDO::getCreator, reqVO.getCreator())
                .betweenIfPresent(MemberTagDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MemberTagDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(MemberTagDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(MemberTagDO::getId));
    }

}