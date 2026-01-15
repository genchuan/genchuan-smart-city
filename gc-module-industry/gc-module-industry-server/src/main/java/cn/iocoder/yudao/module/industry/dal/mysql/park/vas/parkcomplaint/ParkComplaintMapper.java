package cn.iocoder.yudao.module.industry.dal.mysql.park.vas.parkcomplaint;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkcomplaint.vo.ParkComplaintPageReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkcomplaint.ParkComplaintDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 投诉记录 Mapper
 *
 * @author lxs
 */
@Mapper
public interface ParkComplaintMapper extends BaseMapperX<ParkComplaintDO> {

    default PageResult<ParkComplaintDO> selectPage(ParkComplaintPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ParkComplaintDO>()
                .eqIfPresent(ParkComplaintDO::getComplaintNo, reqVO.getComplaintNo())
                .eqIfPresent(ParkComplaintDO::getComplainantId, reqVO.getComplainantId())
                .eqIfPresent(ParkComplaintDO::getComplainantPhone, reqVO.getComplainantPhone())
                .eqIfPresent(ParkComplaintDO::getComplaintType, reqVO.getComplaintType())
                .eqIfPresent(ParkComplaintDO::getRelatedAssetId, reqVO.getRelatedAssetId())
                .eqIfPresent(ParkComplaintDO::getRelatedOrderId, reqVO.getRelatedOrderId())
                .eqIfPresent(ParkComplaintDO::getComplaintContent, reqVO.getComplaintContent())
                .betweenIfPresent(ParkComplaintDO::getComplaintTime, reqVO.getComplaintTime())
                .eqIfPresent(ParkComplaintDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ParkComplaintDO::getProcessContent, reqVO.getProcessContent())
                .eqIfPresent(ParkComplaintDO::getProcessBy, reqVO.getProcessBy())
                .betweenIfPresent(ParkComplaintDO::getProcessTime, reqVO.getProcessTime())
                .eqIfPresent(ParkComplaintDO::getSatisfaction, reqVO.getSatisfaction())
                .betweenIfPresent(ParkComplaintDO::getFeedbackTime, reqVO.getFeedbackTime())
                .betweenIfPresent(ParkComplaintDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(ParkComplaintDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ParkComplaintDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ParkComplaintDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(ParkComplaintDO::getExtCommon4, reqVO.getExtCommon4())
                .eqIfPresent(ParkComplaintDO::getRemark, reqVO.getRemark())
                .orderByDesc(ParkComplaintDO::getId));
    }

}
