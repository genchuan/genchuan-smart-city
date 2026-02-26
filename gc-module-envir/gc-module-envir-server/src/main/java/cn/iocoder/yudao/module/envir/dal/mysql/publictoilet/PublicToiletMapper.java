package cn.iocoder.yudao.module.envir.dal.mysql.publictoilet;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envir.dal.dataobject.facility.FacilityDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.publictoilet.PublicToiletDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.publictoilet.PublicToiletDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.area.AreaDO;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.envir.controller.admin.publictoilet.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 公厕 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PublicToiletMapper extends BaseMapperX<PublicToiletDO> {

    default PageResult<PublicToiletDO> selectPage(PublicToiletPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PublicToiletDO>()
                .eqIfPresent(PublicToiletDO::getPublicToiletId, reqVO.getPublicToiletId())
                .likeIfPresent(PublicToiletDO::getName, reqVO.getName())
                .eqIfPresent(PublicToiletDO::getLocation, reqVO.getLocation())
                .eqIfPresent(PublicToiletDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(PublicToiletDO::getOpenHours, reqVO.getOpenHours())
                .eqIfPresent(PublicToiletDO::getStallCount, reqVO.getStallCount())
                .eqIfPresent(PublicToiletDO::getFacilityIds, reqVO.getFacilityIds())
                .eqIfPresent(PublicToiletDO::getConsumableIds, reqVO.getConsumableIds())
                .eqIfPresent(PublicToiletDO::getOperationStatusId, reqVO.getOperationStatusId())
                .eqIfPresent(PublicToiletDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(PublicToiletDO::getAbnormalCreateBy, reqVO.getAbnormalCreateBy())
                .betweenIfPresent(PublicToiletDO::getAbnormalCreateTime, reqVO.getAbnormalCreateTime())
                .betweenIfPresent(PublicToiletDO::getAbnormalUpdateTime, reqVO.getAbnormalUpdateTime())
                .eqIfPresent(PublicToiletDO::getCleaningRate, reqVO.getCleaningRate())
                .eqIfPresent(PublicToiletDO::getWarningCount, reqVO.getWarningCount())
                .eqIfPresent(PublicToiletDO::getComplaintRate, reqVO.getComplaintRate())
                .eqIfPresent(PublicToiletDO::getPhotoUrl, reqVO.getPhotoUrl())
                .eqIfPresent(PublicToiletDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PublicToiletDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PublicToiletDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(PublicToiletDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(PublicToiletDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PublicToiletDO::getId));
    }

    /**
     * 查询公厕详情列表
     * @return 公厕详情列表
     */
    List<PublicToiletDetailDO> selectListDetail();

    /**
     * 分页查询公厕详情列表
     */
    List<PublicToiletDetailDO> selectDetailPage(@Param("reqVO") PublicToiletPageReqVO reqVO);

    /**
     * 查询总数
     */
    Long selectCount(@Param("reqVO") PublicToiletPageReqVO reqVO);
}