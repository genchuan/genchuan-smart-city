package cn.iocoder.yudao.module.waterdetection.dal.mysql.leakagecontrolplan;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.leakagecontrolplan.LeakageControlPlanDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.leakagecontrolplan.vo.*;

/**
 * 漏损控制方案建议 Mapper
 *
 * @author zcq
 */
@Mapper
public interface LeakageControlPlanMapper extends BaseMapperX<LeakageControlPlanDO> {

    default PageResult<LeakageControlPlanDO> selectPage(LeakageControlPlanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<LeakageControlPlanDO>()
                .eqIfPresent(LeakageControlPlanDO::getPartitionId, reqVO.getPartitionId())
                .eqIfPresent(LeakageControlPlanDO::getExceededLeakageRate, reqVO.getExceededLeakageRate())
                .eqIfPresent(LeakageControlPlanDO::getPressureData, reqVO.getPressureData())
                .eqIfPresent(LeakageControlPlanDO::getPipeAvgAge, reqVO.getPipeAvgAge())
                .eqIfPresent(LeakageControlPlanDO::getSuggestedPlan, reqVO.getSuggestedPlan())
                .betweenIfPresent(LeakageControlPlanDO::getPlanImplementTime, reqVO.getPlanImplementTime())
                .eqIfPresent(LeakageControlPlanDO::getPostImplementRate, reqVO.getPostImplementRate())
                .betweenIfPresent(LeakageControlPlanDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(LeakageControlPlanDO::getId));
    }

}