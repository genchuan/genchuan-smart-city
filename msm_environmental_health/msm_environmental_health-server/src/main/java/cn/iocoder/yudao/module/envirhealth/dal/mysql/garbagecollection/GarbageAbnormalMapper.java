package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.GarbageAbnormalPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.area.AreaDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageCollectionDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.UserDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageAbnormalDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageCollectionDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageAbnormalDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.AbnormalTypeDO;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 垃圾异常记录 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface GarbageAbnormalMapper extends BaseMapperX<GarbageAbnormalDO> {

    default PageResult<GarbageAbnormalDO> selectPage(GarbageAbnormalPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GarbageAbnormalDO>()
                .eqIfPresent(GarbageAbnormalDO::getAbnormalId, reqVO.getAbnormalId())
                .eqIfPresent(GarbageAbnormalDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(GarbageAbnormalDO::getAbnormalTypeId, reqVO.getAbnormalTypeId())
                .eqIfPresent(GarbageAbnormalDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(GarbageAbnormalDO::getReportBy, reqVO.getReportBy())
                .betweenIfPresent(GarbageAbnormalDO::getReportTime, reqVO.getReportTime())
                .eqIfPresent(GarbageAbnormalDO::getPriority, reqVO.getPriority())
                .eqIfPresent(GarbageAbnormalDO::getHandlerId, reqVO.getHandlerId())
                .eqIfPresent(GarbageAbnormalDO::getHandleStatus, reqVO.getHandleStatus())
                .eqIfPresent(GarbageAbnormalDO::getIsTimeout, reqVO.getIsTimeout())
                .eqIfPresent(GarbageAbnormalDO::getHandleDesc, reqVO.getHandleDesc())
                .eqIfPresent(GarbageAbnormalDO::getHandlePhotoUrl, reqVO.getHandlePhotoUrl())
                .eqIfPresent(GarbageAbnormalDO::getReviewStatus, reqVO.getReviewStatus())
                .eqIfPresent(GarbageAbnormalDO::getReviewBy, reqVO.getReviewBy())
                .betweenIfPresent(GarbageAbnormalDO::getReviewTime, reqVO.getReviewTime())
                .eqIfPresent(GarbageAbnormalDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(GarbageAbnormalDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(GarbageAbnormalDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(GarbageAbnormalDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(GarbageAbnormalDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GarbageAbnormalDO::getId));
    }

    /*default List<GarbageAbnormalDetailDO> selectDetailPage() {
        return selectJoinList(GarbageAbnormalDetailDO.class, new MPJLambdaWrapper<GarbageAbnormalDO>()
                .selectAll(GarbageAbnormalDO.class)
                .selectAs(GarbageCollectionDO::getPlanNo, GarbageAbnormalDetailDO::getPlanNo)
                .selectAs(AbnormalTypeDO::getAbnormalName, GarbageAbnormalDetailDO::getAbnormalTypeName)
                .selectAs(AreaDO::getAreaName, GarbageAbnormalDetailDO::getAreaName)
                .selectAs(UserDO::getUserName, GarbageAbnormalDetailDO::getReportName)

                .leftJoin(GarbageCollectionDO.class, GarbageCollectionDO::getCollectionId, GarbageAbnormalDO::getPlanId)
                .leftJoin(AbnormalTypeDO.class, AbnormalTypeDO::getAbnormalTypeId, GarbageAbnormalDO::getAbnormalTypeId)
                .leftJoin(AreaDO.class, AreaDO::getAreaCode, GarbageAbnormalDO::getAreaCode)
                .leftJoin(UserDO.class, UserDO::getUserId, GarbageAbnormalDO::getReportBy)

        );
    }*/

    List<GarbageAbnormalDetailDO> selectDetailPage(@Param("reqVO") GarbageAbnormalPageReqVO pageReqVO);


    Long selectCount(@Param("reqVO") GarbageAbnormalPageReqVO pageReqVO);
}