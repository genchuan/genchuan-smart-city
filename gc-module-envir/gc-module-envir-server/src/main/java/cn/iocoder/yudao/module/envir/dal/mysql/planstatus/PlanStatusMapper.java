package cn.iocoder.yudao.module.envir.dal.mysql.planstatus;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envir.dal.dataobject.planstatus.PlanStatusDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.envir.controller.admin.planstatus.vo.*;

/**
 * 计划状态字典 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PlanStatusMapper extends BaseMapperX<PlanStatusDO> {

    default PageResult<PlanStatusDO> selectPage(PlanStatusPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PlanStatusDO>()
                .eqIfPresent(PlanStatusDO::getSysPlanStatusId, reqVO.getSysPlanStatusId())
                .likeIfPresent(PlanStatusDO::getName, reqVO.getName())
                .eqIfPresent(PlanStatusDO::getCode, reqVO.getCode())
                .eqIfPresent(PlanStatusDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PlanStatusDO::getSort, reqVO.getSort())
                .eqIfPresent(PlanStatusDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PlanStatusDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PlanStatusDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(PlanStatusDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(PlanStatusDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PlanStatusDO::getId));
    }

}