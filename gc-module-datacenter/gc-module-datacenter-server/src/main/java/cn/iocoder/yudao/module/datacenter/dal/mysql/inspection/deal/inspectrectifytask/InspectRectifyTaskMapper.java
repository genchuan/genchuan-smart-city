package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.deal.inspectrectifytask;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytask.vo.InspectRectifyTaskPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.deal.inspectrectifytask.InspectRectifyTaskDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检整改任务派发 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectRectifyTaskMapper extends BaseMapperX<InspectRectifyTaskDO> {

    default PageResult<InspectRectifyTaskDO> selectPage(InspectRectifyTaskPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectRectifyTaskDO>()
                .eqIfPresent(InspectRectifyTaskDO::getRectifyTaskId, reqVO.getRectifyTaskId())
                .eqIfPresent(InspectRectifyTaskDO::getRectifyTaskCode, reqVO.getRectifyTaskCode())
                .eqIfPresent(InspectRectifyTaskDO::getProblemId, reqVO.getProblemId())
                .eqIfPresent(InspectRectifyTaskDO::getProblemCode, reqVO.getProblemCode())
                .likeIfPresent(InspectRectifyTaskDO::getProblemName, reqVO.getProblemName())
                .eqIfPresent(InspectRectifyTaskDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(InspectRectifyTaskDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(InspectRectifyTaskDO::getLeaderUserId, reqVO.getLeaderUserId())
                .likeIfPresent(InspectRectifyTaskDO::getLeaderUserName, reqVO.getLeaderUserName())
                .eqIfPresent(InspectRectifyTaskDO::getRectifyDeadlineHour, reqVO.getRectifyDeadlineHour())
                .betweenIfPresent(InspectRectifyTaskDO::getRectifyDeadlineTime, reqVO.getRectifyDeadlineTime())
                .eqIfPresent(InspectRectifyTaskDO::getDispatchDesc, reqVO.getDispatchDesc())
                .eqIfPresent(InspectRectifyTaskDO::getRectifyStatus, reqVO.getRectifyStatus())
                .eqIfPresent(InspectRectifyTaskDO::getDispatchUser, reqVO.getDispatchUser())
                .betweenIfPresent(InspectRectifyTaskDO::getDispatchTime, reqVO.getDispatchTime())
                .betweenIfPresent(InspectRectifyTaskDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(InspectRectifyTaskDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(InspectRectifyTaskDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectRectifyTaskDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectRectifyTaskDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectRectifyTaskDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectRectifyTaskDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectRectifyTaskDO::getId));
    }

}