package cn.iocoder.yudao.module.smartcity.dal.mysql.patrolresources;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.patrolresources.PatrolResourcesDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.patrolresources.vo.*;

/**
 * 巡查资源 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface PatrolResourcesMapper extends BaseMapperX<PatrolResourcesDO> {

    default PageResult<PatrolResourcesDO> selectPage(PatrolResourcesPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PatrolResourcesDO>()
                .likeIfPresent(PatrolResourcesDO::getResourceName, reqVO.getResourceName())
                .eqIfPresent(PatrolResourcesDO::getResourceNumber, reqVO.getResourceNumber())
                .betweenIfPresent(PatrolResourcesDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PatrolResourcesDO::getId));
    }

}