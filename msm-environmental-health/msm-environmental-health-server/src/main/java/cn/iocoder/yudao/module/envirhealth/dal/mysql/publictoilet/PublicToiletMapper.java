package cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet.PublicToiletPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.PublicToiletDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.PublicToiletDetailDO;
import cn.iocoder.yudao.module.envirhealth.util.options.vo.OptionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 公厕 Mapper
 */
@Mapper
public interface PublicToiletMapper extends BaseMapperX<PublicToiletDO> {

    default PageResult<PublicToiletDO> selectPage(PublicToiletPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PublicToiletDO>()
                .eqIfPresent(PublicToiletDO::getToiletId, reqVO.getToiletId())
                .likeIfPresent(PublicToiletDO::getName, reqVO.getName())
                .likeIfPresent(PublicToiletDO::getLocation, reqVO.getLocation())
                .eqIfPresent(PublicToiletDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(PublicToiletDO::getOpenHours, reqVO.getOpenHours())
                .betweenIfPresent(PublicToiletDO::getStallCount, reqVO.getStallCount())
                .eqIfPresent(PublicToiletDO::getOperationStatusId, reqVO.getOperationStatusId())
                .eqIfPresent(PublicToiletDO::getManagerId, reqVO.getManagerId())
                .betweenIfPresent(PublicToiletDO::getCleaningRate, reqVO.getCleaningRate())
                .betweenIfPresent(PublicToiletDO::getComplaintRate, reqVO.getComplaintRate())
                .betweenIfPresent(PublicToiletDO::getFacilityRate, reqVO.getFacilityRate())
                .geIfPresent(PublicToiletDO::getWarningCount, reqVO.getWarningCountMin())
                .betweenIfPresent(PublicToiletDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(PublicToiletDO::getUpdateTime, reqVO.getUpdateTime())
                .orderByDesc(PublicToiletDO::getId));
    }

    /**
     * 查询全局最大序号（用于toilet_id）
     */
    @Select("SELECT IFNULL(MAX(CAST(SUBSTRING_INDEX(toilet_id, '-', -1) AS UNSIGNED)), 0) FROM public_toilet")
    Integer selectMaxSeq();

    // 详情分页
    List<PublicToiletDetailDO> selectDetailPage(@Param("reqVO") PublicToiletPageReqVO pageReqVO);

    // 查询总数
    Long selectCount(@Param("reqVO") PublicToiletPageReqVO pageReqVO);

    /**
     * 获取公厕总数量
     */
    @Select("SELECT COUNT(*) FROM public_toilet WHERE deleted = 0")
    Long selectTotalCount();

    /**
     * 获取公厕名称下拉选项
     */
    @Select("SELECT name AS label, toilet_id AS value " +
            "FROM public_toilet " +
            "WHERE deleted = 0 " +
            "ORDER BY id DESC")
    List<OptionVO> selectToiletOptions();
}