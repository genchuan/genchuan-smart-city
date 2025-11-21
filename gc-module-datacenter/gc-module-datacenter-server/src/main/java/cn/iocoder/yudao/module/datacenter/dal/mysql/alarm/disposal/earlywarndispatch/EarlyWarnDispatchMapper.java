package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.disposal.earlywarndispatch;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarndispatch.vo.EarlyWarnDispatchPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarndispatch.EarlyWarnDispatchDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警告警派单 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnDispatchMapper extends BaseMapperX<EarlyWarnDispatchDO> {

    default PageResult<EarlyWarnDispatchDO> selectPage(EarlyWarnDispatchPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnDispatchDO>()
                .eqIfPresent(EarlyWarnDispatchDO::getDispatchId, reqVO.getDispatchId())
                .eqIfPresent(EarlyWarnDispatchDO::getDispatchNo, reqVO.getDispatchNo())
                .eqIfPresent(EarlyWarnDispatchDO::getEarlyWarnId, reqVO.getEarlyWarnId())
                .likeIfPresent(EarlyWarnDispatchDO::getEarlyWarnName, reqVO.getEarlyWarnName())
                .eqIfPresent(EarlyWarnDispatchDO::getDispatchType, reqVO.getDispatchType())
                .eqIfPresent(EarlyWarnDispatchDO::getReceiveDeptId, reqVO.getReceiveDeptId())
                .likeIfPresent(EarlyWarnDispatchDO::getReceiveDeptName, reqVO.getReceiveDeptName())
                .eqIfPresent(EarlyWarnDispatchDO::getHandleUserId, reqVO.getHandleUserId())
                .likeIfPresent(EarlyWarnDispatchDO::getHandleUserName, reqVO.getHandleUserName())
                .eqIfPresent(EarlyWarnDispatchDO::getDispatchStatus, reqVO.getDispatchStatus())
                .eqIfPresent(EarlyWarnDispatchDO::getHandleLimit, reqVO.getHandleLimit())
                .betweenIfPresent(EarlyWarnDispatchDO::getDispatchTime, reqVO.getDispatchTime())
                .betweenIfPresent(EarlyWarnDispatchDO::getReceiveTime, reqVO.getReceiveTime())
                .eqIfPresent(EarlyWarnDispatchDO::getRejectReason, reqVO.getRejectReason())
                .eqIfPresent(EarlyWarnDispatchDO::getDispatchUserId, reqVO.getDispatchUserId())
                .likeIfPresent(EarlyWarnDispatchDO::getDispatchUserName, reqVO.getDispatchUserName())
                .eqIfPresent(EarlyWarnDispatchDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnDispatchDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnDispatchDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnDispatchDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnDispatchDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnDispatchDO::getId));
    }

}