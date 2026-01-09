package cn.iocoder.yudao.module.smartcity.dal.mysql.patrolpersonnel;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.patrolpersonnel.PatrolPersonnelDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.patrolpersonnel.vo.*;

/**
 * 巡查人员 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface PatrolPersonnelMapper extends BaseMapperX<PatrolPersonnelDO> {

    default PageResult<PatrolPersonnelDO> selectPage(PatrolPersonnelPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PatrolPersonnelDO>()
                .eqIfPresent(PatrolPersonnelDO::getNumber, reqVO.getNumber())
                .likeIfPresent(PatrolPersonnelDO::getFullName, reqVO.getFullName())
                .betweenIfPresent(PatrolPersonnelDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PatrolPersonnelDO::getId));
    }

}