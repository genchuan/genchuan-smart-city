package cn.iocoder.yudao.module.smartcity.dal.mysql.inspectionplanmanagement;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionplanmanagement.InspectionPlanManagementDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionplanmanagement.vo.*;

/**
 * 巡查计划管理 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface InspectionPlanManagementMapper extends BaseMapperX<InspectionPlanManagementDO> {

    default PageResult<InspectionPlanManagementDO> selectPage(InspectionPlanManagementPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectionPlanManagementDO>()
                .likeIfPresent(InspectionPlanManagementDO::getPlanName, reqVO.getPlanName())
                .eqIfPresent(InspectionPlanManagementDO::getPatrolArea, reqVO.getPatrolArea())
                .betweenIfPresent(InspectionPlanManagementDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectionPlanManagementDO::getId));
    }

}