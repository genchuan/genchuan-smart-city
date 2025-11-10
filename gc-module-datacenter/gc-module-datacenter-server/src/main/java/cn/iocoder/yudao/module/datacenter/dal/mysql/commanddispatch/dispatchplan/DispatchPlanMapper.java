package cn.iocoder.yudao.module.datacenter.dal.mysql.commanddispatch.dispatchplan;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchplan.vo.DispatchPlanPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.dispatchplan.DispatchPlanDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 调度方案 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface DispatchPlanMapper extends BaseMapperX<DispatchPlanDO> {

    default PageResult<DispatchPlanDO> selectPage(DispatchPlanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DispatchPlanDO>()
                .eqIfPresent(DispatchPlanDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(DispatchPlanDO::getPlanNo, reqVO.getPlanNo())
                .eqIfPresent(DispatchPlanDO::getRespId, reqVO.getRespId())
                .eqIfPresent(DispatchPlanDO::getRespNo, reqVO.getRespNo())
                .likeIfPresent(DispatchPlanDO::getPlanName, reqVO.getPlanName())
                .eqIfPresent(DispatchPlanDO::getDemandDesc, reqVO.getDemandDesc())
                .eqIfPresent(DispatchPlanDO::getResType, reqVO.getResType())
                .eqIfPresent(DispatchPlanDO::getResId, reqVO.getResId())
                .likeIfPresent(DispatchPlanDO::getResName, reqVO.getResName())
                .eqIfPresent(DispatchPlanDO::getDispatchQuantity, reqVO.getDispatchQuantity())
                .eqIfPresent(DispatchPlanDO::getDispatchDest, reqVO.getDispatchDest())
                .eqIfPresent(DispatchPlanDO::getDestCoordX, reqVO.getDestCoordX())
                .eqIfPresent(DispatchPlanDO::getDestCoordY, reqVO.getDestCoordY())
                .eqIfPresent(DispatchPlanDO::getChargerId, reqVO.getChargerId())
                .likeIfPresent(DispatchPlanDO::getChargerName, reqVO.getChargerName())
                .betweenIfPresent(DispatchPlanDO::getRequireCompleteTime, reqVO.getRequireCompleteTime())
                .eqIfPresent(DispatchPlanDO::getPlanStatus, reqVO.getPlanStatus())
                .eqIfPresent(DispatchPlanDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(DispatchPlanDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(DispatchPlanDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DispatchPlanDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DispatchPlanDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DispatchPlanDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(DispatchPlanDO::getId));
    }

}