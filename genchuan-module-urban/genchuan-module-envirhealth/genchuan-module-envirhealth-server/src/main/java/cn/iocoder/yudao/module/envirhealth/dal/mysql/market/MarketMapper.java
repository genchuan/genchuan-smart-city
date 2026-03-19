package cn.iocoder.yudao.module.envirhealth.dal.mysql.market;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.market.vo.MarketPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.market.MarketDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.MarketDetailDO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 集贸市场 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MarketMapper extends BaseMapperX<MarketDO> {

    default PageResult<MarketDO> selectPage(MarketPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MarketDO>()
                .eqIfPresent(MarketDO::getMarketId, reqVO.getMarketId())
                .likeIfPresent(MarketDO::getName, reqVO.getName())
                .eqIfPresent(MarketDO::getAddress, reqVO.getAddress())
                .eqIfPresent(MarketDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(MarketDO::getStallCount, reqVO.getStallCount())
                .eqIfPresent(MarketDO::getManagerId, reqVO.getManagerId())
                .eqIfPresent(MarketDO::getOperationStatusId, reqVO.getOperationStatusId())
                .eqIfPresent(MarketDO::getHygieneRate, reqVO.getHygieneRate())
                .eqIfPresent(MarketDO::getWasteTransferRate, reqVO.getWasteTransferRate())
                .eqIfPresent(MarketDO::getSewageRate, reqVO.getSewageRate())
                .eqIfPresent(MarketDO::getUnfinishedTaskCount, reqVO.getUnfinishedTaskCount())
                .eqIfPresent(MarketDO::getCleaningFrequency, reqVO.getCleaningFrequency())
                .betweenIfPresent(MarketDO::getCleaningTime, reqVO.getCleaningTime())
                .eqIfPresent(MarketDO::getCleaningArea, reqVO.getCleaningArea())
                .eqIfPresent(MarketDO::getStaffIds, reqVO.getStaffIds())
                .eqIfPresent(MarketDO::getCleaningStandard, reqVO.getCleaningStandard())
                .eqIfPresent(MarketDO::getGarbageTypeIds, reqVO.getGarbageTypeIds())
                .eqIfPresent(MarketDO::getGarbageContainerCount, reqVO.getGarbageContainerCount())
                .eqIfPresent(MarketDO::getWasteTransferInterval, reqVO.getWasteTransferInterval())
                .betweenIfPresent(MarketDO::getWasteTransferTime, reqVO.getWasteTransferTime())
                .eqIfPresent(MarketDO::getVehicleId, reqVO.getVehicleId())
                .eqIfPresent(MarketDO::getSewageDischargeArea, reqVO.getSewageDischargeArea())
                .eqIfPresent(MarketDO::getSewageDisposalWay, reqVO.getSewageDisposalWay())
                .eqIfPresent(MarketDO::getSewageCleaningFrequency, reqVO.getSewageCleaningFrequency())
                .eqIfPresent(MarketDO::getSewageProblemDesc, reqVO.getSewageProblemDesc())
                .betweenIfPresent(MarketDO::getLastSewageCleaningTime, reqVO.getLastSewageCleaningTime())
                .betweenIfPresent(MarketDO::getNextSewageCleaningTime, reqVO.getNextSewageCleaningTime())
                .eqIfPresent(MarketDO::getSewageDisposalLog, reqVO.getSewageDisposalLog())
                .betweenIfPresent(MarketDO::getHygieneCheckTime, reqVO.getHygieneCheckTime())
                .eqIfPresent(MarketDO::getCheckBy, reqVO.getCheckBy())
                .betweenIfPresent(MarketDO::getHygieneCheckDate, reqVO.getHygieneCheckDate())
                .eqIfPresent(MarketDO::getPreviousProblem, reqVO.getPreviousProblem())
                .eqIfPresent(MarketDO::getQualifiedItemCount, reqVO.getQualifiedItemCount())
                .eqIfPresent(MarketDO::getUnqualifiedItemCount, reqVO.getUnqualifiedItemCount())
                .eqIfPresent(MarketDO::getReformRequire, reqVO.getReformRequire())
                .eqIfPresent(MarketDO::getReformDeadline, reqVO.getReformDeadline())
                .betweenIfPresent(MarketDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MarketDO::getId));
    }

    /**
     * 查询全局最大序号（用于market_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(market_id, '-', -1)), 0) FROM market")
    Integer selectMaxSeq();

    List<MarketDetailDO> selectDetailPage(@Param("reqVO") MarketPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") MarketPageReqVO pageReqVO);

    // ========== 仪表盘新增方法 ==========
    /**
     * 统计集贸市场总数
     */
    @Select("SELECT COUNT(*) FROM market")
    Long selectTotalMarketCount();

    /**
     * 统计卫生达标的集贸市场数量
     * 注：hygiene_rate >= 90
     */
    @Select("SELECT COUNT(*) FROM market WHERE hygiene_rate >= 90")
    Long selectHygieneStandardMetCount();

    /**
     * 统计垃圾转运达标的集贸市场数量
     */
    @Select("SELECT COUNT(*) FROM market WHERE waste_transfer_rate >= 90")
    Long selectWasteTransferStandardMetCount();

    /**
     * 统计污水排放达标的集贸市场数量
     */
    @Select("SELECT COUNT(*) FROM market WHERE sewage_rate >= 90")
    Long selectSewageDischargeStandardMetCount();

    /**
     * 查询运营状态分布（饼图）
     */
    @Select("""
            SELECT
                os.name AS name,
                COUNT(*) AS value
            FROM market m
                LEFT JOIN sys_operation_status os ON os.sys_operation_status_id = m.operation_status_id
            GROUP BY m.operation_status_id, m.id ,os.name
            ORDER BY m.id
            """)
    List<PieItemVO> selectOperationStatusDistribution();

    /**
     * 查询区域分布（饼图）
     */
    @Select("""
            SELECT
                a.area_name AS name,
                COUNT(*) AS value
            FROM market m
                LEFT JOIN sys_area a ON a.area_code = m.area_code
            GROUP BY m.area_code, m.id, a.area_name
            ORDER BY m.id
            """)
    List<PieItemVO> selectAreaDistribution();

    /**
     * 查询各市场卫生合规率
     */
    @Select("""
            SELECT
                name AS name,
                hygiene_rate AS value
            FROM market
            WHERE hygiene_rate IS NOT NULL
            """)
    List<BarItemVO> selectHygieneComplianceRateByMarket();
}