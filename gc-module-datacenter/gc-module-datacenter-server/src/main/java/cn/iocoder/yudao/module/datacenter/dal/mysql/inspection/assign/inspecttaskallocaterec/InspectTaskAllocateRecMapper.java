package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.assign.inspecttaskallocaterec;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttaskallocaterec.vo.InspectTaskAllocateRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.assign.inspecttaskallocaterec.InspectTaskAllocateRecDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检任务分配记录 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectTaskAllocateRecMapper extends BaseMapperX<InspectTaskAllocateRecDO> {

    default PageResult<InspectTaskAllocateRecDO> selectPage(InspectTaskAllocateRecPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectTaskAllocateRecDO>()
                .eqIfPresent(InspectTaskAllocateRecDO::getAllocateId, reqVO.getAllocateId())
                .eqIfPresent(InspectTaskAllocateRecDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(InspectTaskAllocateRecDO::getTaskCode, reqVO.getTaskCode())
                .eqIfPresent(InspectTaskAllocateRecDO::getOldPersonId, reqVO.getOldPersonId())
                .likeIfPresent(InspectTaskAllocateRecDO::getOldPersonName, reqVO.getOldPersonName())
                .eqIfPresent(InspectTaskAllocateRecDO::getNewPersonId, reqVO.getNewPersonId())
                .likeIfPresent(InspectTaskAllocateRecDO::getNewPersonName, reqVO.getNewPersonName())
                .eqIfPresent(InspectTaskAllocateRecDO::getAllocateReason, reqVO.getAllocateReason())
                .betweenIfPresent(InspectTaskAllocateRecDO::getAllocateTime, reqVO.getAllocateTime())
                .eqIfPresent(InspectTaskAllocateRecDO::getAllocateUser, reqVO.getAllocateUser())
                .eqIfPresent(InspectTaskAllocateRecDO::getTaskStatusChange, reqVO.getTaskStatusChange())
                .eqIfPresent(InspectTaskAllocateRecDO::getTaskType, reqVO.getTaskType())
                .eqIfPresent(InspectTaskAllocateRecDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectTaskAllocateRecDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectTaskAllocateRecDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectTaskAllocateRecDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectTaskAllocateRecDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectTaskAllocateRecDO::getId));
    }

}