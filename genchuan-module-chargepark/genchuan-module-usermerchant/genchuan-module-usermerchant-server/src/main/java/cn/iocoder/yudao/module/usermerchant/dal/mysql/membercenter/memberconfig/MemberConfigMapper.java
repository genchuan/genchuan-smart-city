package cn.iocoder.yudao.module.usermerchant.dal.mysql.membercenter.memberconfig;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.membercenter.memberconfig.MemberConfigDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo.*;

/**
 * 会员配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MemberConfigMapper extends BaseMapperX<MemberConfigDO> {

    default PageResult<MemberConfigDO> selectPage(MemberConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MemberConfigDO>()
                .eqIfPresent(MemberConfigDO::getConfigType, reqVO.getConfigType())
                .eqIfPresent(MemberConfigDO::getContent, reqVO.getContent())
                .eqIfPresent(MemberConfigDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MemberConfigDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(MemberConfigDO::getEffectiveTime, reqVO.getEffectiveTime())
                .eqIfPresent(MemberConfigDO::getCreator, reqVO.getCreator())
                .betweenIfPresent(MemberConfigDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MemberConfigDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(MemberConfigDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(MemberConfigDO::getId));
    }

}