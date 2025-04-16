package cn.iocoder.yudao.module.smartcity.dal.mysql.inspectioninspectionplan;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectioninspectionplan.InspectionInspectionPlanDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectioninspectionplan.vo.*;

/**
 * 巡查计划 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface InspectionInspectionPlanMapper extends BaseMapperX<InspectionInspectionPlanDO> {

    default PageResult<InspectionInspectionPlanDO> selectPage(InspectionInspectionPlanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectionInspectionPlanDO>()
                .eqIfPresent(InspectionInspectionPlanDO::getInspectionProject, reqVO.getInspectionProject())
                .eqIfPresent(InspectionInspectionPlanDO::getPatrolLocation, reqVO.getPatrolLocation())
                .betweenIfPresent(InspectionInspectionPlanDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectionInspectionPlanDO::getId));
    }

}