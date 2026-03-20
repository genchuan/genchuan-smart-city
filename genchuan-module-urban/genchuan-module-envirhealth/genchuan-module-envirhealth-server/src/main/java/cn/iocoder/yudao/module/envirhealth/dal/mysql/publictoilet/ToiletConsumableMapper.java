package cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletconsumable.ToiletConsumablePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletConsumableDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletConsumableDetailDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 公厕耗材配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ToiletConsumableMapper extends BaseMapperX<ToiletConsumableDO> {

    default PageResult<ToiletConsumableDO> selectPage(ToiletConsumablePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ToiletConsumableDO>()
                .eqIfPresent(ToiletConsumableDO::getToiletId, reqVO.getToiletId())
                .eqIfPresent(ToiletConsumableDO::getConsumableId, reqVO.getConsumableId())
                .eqIfPresent(ToiletConsumableDO::getConsumableStock, reqVO.getConsumableStock())
                .eqIfPresent(ToiletConsumableDO::getConsumableThreshold, reqVO.getConsumableThreshold())
                .eqIfPresent(ToiletConsumableDO::getConsumableWarning, reqVO.getConsumableWarning())
                .betweenIfPresent(ToiletConsumableDO::getLastSupplyTime, reqVO.getLastSupplyTime())
                .eqIfPresent(ToiletConsumableDO::getSupplyCycle, reqVO.getSupplyCycle())
                .eqIfPresent(ToiletConsumableDO::getConsumableGap, reqVO.getConsumableGap())
                .eqIfPresent(ToiletConsumableDO::getManagerId, reqVO.getManagerId())
                .betweenIfPresent(ToiletConsumableDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ToiletConsumableDO::getId));
    }

    /**
     * 分页查询待补充的耗材配置
     */
    // 详情分页
    List<ToiletConsumableDetailDO> selectDetailPage(@Param("reqVO") ToiletConsumablePageReqVO pageReqVO);

    // 查询总数
    Long selectCount(@Param("reqVO") ToiletConsumablePageReqVO pageReqVO);

    /**
     * 统计物资待补充的数量
     */
    @Select("SELECT COUNT(*) FROM public_toilet_consumable " +
            "WHERE deleted = 0 ")
    Long countConsumable();

    /**
     * 待补充物资数量
     */
    @Select("""
            SELECT COUNT(1)
            FROM public_toilet_consumable c
            WHERE c.deleted = b'0'
              AND IFNULL(c.consumable_gap, 0) > 0
            """)
    Long countPendingTotal();

    /**
     * 严重预警数量
     */
    @Select("""
        SELECT COUNT(1)
        FROM public_toilet_consumable c
        WHERE c.deleted = b'0'
          AND IFNULL(c.consumable_gap, 0) > 0
          AND c.consumable_warning = '严重预警'
        """)
    Long countHighWarningPending();

    /**
     * 各个区域待补充数
     */
    @Select("""
            SELECT COUNT(DISTINCT COALESCE(NULLIF(TRIM(a.area_name), ''), '未知'))
            FROM public_toilet_consumable c
            LEFT JOIN public_toilet pt ON pt.toilet_id = c.toilet_id
            LEFT JOIN sys_area a ON a.area_code = pt.area_code
            WHERE c.deleted = b'0'
              AND IFNULL(c.consumable_gap, 0) > 0
            """)
    Long countPendingAreaDistinct();

    @Select("""
            SELECT
              COALESCE(NULLIF(TRIM(sc.consumable_name), ''), '未知') AS name,
              COUNT(1) AS value
            FROM public_toilet_consumable c
            LEFT JOIN sys_consumable sc ON c.consumable_id = sc.consumable_id
            WHERE c.deleted = b'0'
              AND IFNULL(c.consumable_gap, 0) > 0
            GROUP BY COALESCE(NULLIF(TRIM(sc.consumable_name), ''), '未知')
            ORDER BY value DESC
            """)
    List<PieItemVO> selectTypeDistributionPending();

    @Select("""
            SELECT
              COALESCE(NULLIF(TRIM(c.consumable_warning), ''), '未知') AS name,
              COUNT(1) AS value
            FROM public_toilet_consumable c
            WHERE c.deleted = b'0'
              AND IFNULL(c.consumable_gap, 0) > 0
            GROUP BY COALESCE(NULLIF(TRIM(c.consumable_warning), ''), '未知')
            ORDER BY value DESC
            """)
    List<PieItemVO> selectWarningDistributionPending();

    @Select("""
            SELECT
              COALESCE(NULLIF(TRIM(sc.consumable_name), ''), '未知') AS name,
              CAST(SUM(IFNULL(c.consumable_gap, 0)) AS DECIMAL(10,2)) AS value
            FROM public_toilet_consumable c
            LEFT JOIN sys_consumable sc ON c.consumable_id = sc.consumable_id
            WHERE c.deleted = b'0'
              AND IFNULL(c.consumable_gap, 0) > 0
            GROUP BY COALESCE(NULLIF(TRIM(sc.consumable_name), ''), '未知')
            ORDER BY value DESC
            """)
    List<BarItemVO> selectGapByConsumablePending();
}