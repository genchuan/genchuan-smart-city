package cn.iocoder.yudao.module.smartcity.dal.mysql.developmentinspectionplan;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.developmentinspectionplan.DevelopmentInspectionPlanDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.developmentinspectionplan.vo.*;

/**
 * 巡查计划管理 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface DevelopmentInspectionPlanMapper extends BaseMapperX<DevelopmentInspectionPlanDO> {

    default PageResult<DevelopmentInspectionPlanDO> selectPage(DevelopmentInspectionPlanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DevelopmentInspectionPlanDO>()
                .betweenIfPresent(DevelopmentInspectionPlanDO::getPatrolTime, reqVO.getPatrolTime())
                .eqIfPresent(DevelopmentInspectionPlanDO::getRegion, reqVO.getRegion())
                .eqIfPresent(DevelopmentInspectionPlanDO::getStaff, reqVO.getStaff())
                .betweenIfPresent(DevelopmentInspectionPlanDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DevelopmentInspectionPlanDO::getId));
    }

}