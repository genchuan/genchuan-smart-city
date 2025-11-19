package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.heart.inspectsupvtask;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtask.vo.InspectSupvTaskPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.heart.inspectsupvtask.InspectSupvTaskDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检重点督办任务 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectSupvTaskMapper extends BaseMapperX<InspectSupvTaskDO> {

    default PageResult<InspectSupvTaskDO> selectPage(InspectSupvTaskPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectSupvTaskDO>()
                .eqIfPresent(InspectSupvTaskDO::getSupvTaskId, reqVO.getSupvTaskId())
                .eqIfPresent(InspectSupvTaskDO::getSupvTaskCode, reqVO.getSupvTaskCode())
                .eqIfPresent(InspectSupvTaskDO::getRectifyTaskId, reqVO.getRectifyTaskId())
                .eqIfPresent(InspectSupvTaskDO::getRectifyTaskCode, reqVO.getRectifyTaskCode())
                .eqIfPresent(InspectSupvTaskDO::getProblemId, reqVO.getProblemId())
                .likeIfPresent(InspectSupvTaskDO::getProblemName, reqVO.getProblemName())
                .eqIfPresent(InspectSupvTaskDO::getSupvUserId, reqVO.getSupvUserId())
                .likeIfPresent(InspectSupvTaskDO::getSupvUserName, reqVO.getSupvUserName())
                .eqIfPresent(InspectSupvTaskDO::getSupvFrequency, reqVO.getSupvFrequency())
                .betweenIfPresent(InspectSupvTaskDO::getSupvDeadlineTime, reqVO.getSupvDeadlineTime())
                .eqIfPresent(InspectSupvTaskDO::getSupvRequirement, reqVO.getSupvRequirement())
                .eqIfPresent(InspectSupvTaskDO::getSupvStatus, reqVO.getSupvStatus())
                .eqIfPresent(InspectSupvTaskDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectSupvTaskDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectSupvTaskDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectSupvTaskDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectSupvTaskDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectSupvTaskDO::getId));
    }

}