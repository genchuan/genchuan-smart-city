package cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet.PublicToiletPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.PublicToiletDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.PublicToiletDetailDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    /**
     * 统计正常运营的公厕数量
     */
    @Select("SELECT COUNT(*) FROM public_toilet WHERE deleted = 0 AND operation_status_id = 'uuid-op-status-001'")
    Integer countNormalOperation();

    /**
     * 统计保洁达标数量（保洁达标率≥95%）
     */
    @Select("SELECT COUNT(*) FROM public_toilet WHERE deleted = 0 AND cleaning_rate >= 95.00")
    Integer countCleaningQualified();

    /**
     * 获取公厕环状图统计(全部)
     */
    @Select("""
            SELECT
            COALESCE(s.name, '未知') AS name,
            COUNT(1) AS value
            FROM public_toilet t
            LEFT JOIN sys_operation_status s ON s.sys_operation_status_id = t.operation_status_id
            WHERE t.deleted = 0
            GROUP BY COALESCE(s.name, '未知')
            ORDER BY value DESC""")
    List<PieItemVO> selectOperationStatusPie();

    /**
     * 获取公厕环状图统计(全部)
     */
    @Select("""
            SELECT
              COALESCE(a.area_name, '未知') AS name,
              COUNT(1) AS value
            FROM public_toilet t
            LEFT JOIN sys_area a ON a.area_code = t.area_code
            WHERE t.deleted = 0
            GROUP BY COALESCE(a.area_name, '未知')
            ORDER BY value DESC
            """)
    List<PieItemVO> selectAreaDistributionPie();

    /**
     * 获取公厕柱状图统计(全部)
     */
    @Select("""
            SELECT
              COALESCE(NULLIF(TRIM(a.area_name), ''), '未知') AS name,
              ROUND(
                100 * AVG(CASE WHEN t.cleaning_rate IS NOT NULL AND t.cleaning_rate >= 95 THEN 1 ELSE 0 END),
                2
              ) AS value
            FROM public_toilet t
            LEFT JOIN sys_area a ON a.area_code = t.area_code
            WHERE t.deleted = b'0'
            GROUP BY COALESCE(NULLIF(TRIM(a.area_name), ''), '未知')
            ORDER BY value DESC
            """)
    List<BarItemVO> selectCleaningQualifiedRateByArea();
}