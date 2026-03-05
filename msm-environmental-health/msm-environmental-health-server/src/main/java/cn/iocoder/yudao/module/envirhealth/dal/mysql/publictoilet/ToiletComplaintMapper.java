package cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet.PublicToiletPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcomplaint.ToiletComplaintPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletComplaintDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.PublicToiletDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.ToiletComplaintDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 公厕投诉 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ToiletComplaintMapper extends BaseMapperX<ToiletComplaintDO> {

    default PageResult<ToiletComplaintDO> selectPage(ToiletComplaintPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ToiletComplaintDO>()
                .eqIfPresent(ToiletComplaintDO::getComplaintId, reqVO.getComplaintId())
                .eqIfPresent(ToiletComplaintDO::getToiletId, reqVO.getToiletId())
                .eqIfPresent(ToiletComplaintDO::getComplaintTypeId, reqVO.getComplaintTypeId())
                .eqIfPresent(ToiletComplaintDO::getContent, reqVO.getContent())
                .likeIfPresent(ToiletComplaintDO::getComplaintName, reqVO.getComplaintName())
                .eqIfPresent(ToiletComplaintDO::getPhone, reqVO.getPhone())
                .betweenIfPresent(ToiletComplaintDO::getComplaintTime, reqVO.getComplaintTime())
                .eqIfPresent(ToiletComplaintDO::getDispatchStatus, reqVO.getDispatchStatus())
                .eqIfPresent(ToiletComplaintDO::getHandlerId, reqVO.getHandlerId())
                .eqIfPresent(ToiletComplaintDO::getIsTimeout, reqVO.getIsTimeout())
                .eqIfPresent(ToiletComplaintDO::getHandleMeasure, reqVO.getHandleMeasure())
                .eqIfPresent(ToiletComplaintDO::getHandleResult, reqVO.getHandleResult())
                .eqIfPresent(ToiletComplaintDO::getReformPhoto, reqVO.getReformPhoto())
                .eqIfPresent(ToiletComplaintDO::getFeedbackContent, reqVO.getFeedbackContent())
                .betweenIfPresent(ToiletComplaintDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ToiletComplaintDO::getId));
    }

    /**
     * 查询全局最大序号（用于complaint_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(complaint_id, '-', -1)), 0) FROM public_toilet_complaint")
    Integer selectMaxSeq();

    List<ToiletComplaintDetailDO> selectDetailPage(@Param("reqVO") ToiletComplaintPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") ToiletComplaintPageReqVO pageReqVO);

    /**
     * 统计待处置的投诉数量
     * 待处置：dispatch_status IN ('待派单', '已派单')
     */
    @Select("SELECT COUNT(*) FROM public_toilet_complaint " +
            "WHERE deleted = 0 " +
            "AND dispatch_status IN ('待派单', '已派单')")
    Long countPendingDisposal();
}