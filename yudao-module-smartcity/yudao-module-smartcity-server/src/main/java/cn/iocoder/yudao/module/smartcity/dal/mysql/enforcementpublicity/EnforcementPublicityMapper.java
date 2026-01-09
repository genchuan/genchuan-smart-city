package cn.iocoder.yudao.module.smartcity.dal.mysql.enforcementpublicity;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.enforcementpublicity.EnforcementPublicityDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.enforcementpublicity.vo.*;

/**
 * 执法公示 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface EnforcementPublicityMapper extends BaseMapperX<EnforcementPublicityDO> {

    default PageResult<EnforcementPublicityDO> selectPage(EnforcementPublicityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EnforcementPublicityDO>()
                .eqIfPresent(EnforcementPublicityDO::getPublicityNumber, reqVO.getPublicityNumber())
                .eqIfPresent(EnforcementPublicityDO::getPublicityTitle, reqVO.getPublicityTitle())
                .betweenIfPresent(EnforcementPublicityDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EnforcementPublicityDO::getId));
    }

}