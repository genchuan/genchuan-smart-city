package cn.iocoder.yudao.module.smartcity.dal.mysql.patroltaskmanagement;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.patroltaskmanagement.PatrolTaskManagementDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.patroltaskmanagement.vo.*;

/**
 * 巡査任务管理 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface PatrolTaskManagementMapper extends BaseMapperX<PatrolTaskManagementDO> {

    default PageResult<PatrolTaskManagementDO> selectPage(PatrolTaskManagementPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PatrolTaskManagementDO>()
                .eqIfPresent(PatrolTaskManagementDO::getTask, reqVO.getTask())
                .eqIfPresent(PatrolTaskManagementDO::getBelongingPlan, reqVO.getBelongingPlan())
                .eqIfPresent(PatrolTaskManagementDO::getExecutive, reqVO.getExecutive())
                .betweenIfPresent(PatrolTaskManagementDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PatrolTaskManagementDO::getId));
    }

}