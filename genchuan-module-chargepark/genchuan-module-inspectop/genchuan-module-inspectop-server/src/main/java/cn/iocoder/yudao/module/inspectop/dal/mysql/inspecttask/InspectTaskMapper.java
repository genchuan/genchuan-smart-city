package cn.iocoder.yudao.module.inspectop.dal.mysql.inspecttask;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspecttask.InspectTaskDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.inspectop.controller.admin.inspecttask.vo.*;

/**
 * 巡检任务 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface InspectTaskMapper extends BaseMapperX<InspectTaskDO> {

    default PageResult<InspectTaskDO> selectPage(InspectTaskPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectTaskDO>()
                .eqIfPresent(InspectTaskDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(InspectTaskDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(InspectTaskDO::getDispatchTime, reqVO.getDispatchTime())
                .betweenIfPresent(InspectTaskDO::getClaimTime, reqVO.getClaimTime())
                .eqIfPresent(InspectTaskDO::getStatus, reqVO.getStatus())
                .eqIfPresent(InspectTaskDO::getProgress, reqVO.getProgress())
                .eqIfPresent(InspectTaskDO::getIsArchive, reqVO.getIsArchive())
                .eqIfPresent(InspectTaskDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(InspectTaskDO::getReserve2, reqVO.getReserve2())
                .eqIfPresent(InspectTaskDO::getCreator, reqVO.getCreator())
                .eqIfPresent(InspectTaskDO::getUpdater, reqVO.getUpdater())
                .betweenIfPresent(InspectTaskDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(InspectTaskDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(InspectTaskDO::getId));
    }

}