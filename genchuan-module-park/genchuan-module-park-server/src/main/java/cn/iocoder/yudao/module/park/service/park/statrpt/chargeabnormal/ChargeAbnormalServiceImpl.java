package cn.iocoder.yudao.module.park.service.park.statrpt.chargeabnormal;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.statrpt.chargeabnormal.vo.*;
import cn.iocoder.yudao.module.park.dal.dataobject.park.statrpt.chargeabnormal.ChargeAbnormalDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.statrpt.chargeabnormal.ChargeAbnormalMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.CHARGE_ABNORMAL_NOT_EXISTS;

/**
 * 收费异常 Service 实现类
 *
 * 职责说明：
 * - 负责收费异常的基础 CRUD 操作
 * - 负责收费异常统计报表与趋势分析的业务计算
 * - SQL 负责数据聚合，Service 负责业务含义解释与结果组装
 *
 * 设计原则：
 * - 统计类接口尽量无副作用
 * - 趋势数据由 Service 层补齐，保证前端图表连续性
 *
 * @author lxs
 */
@Service
@Validated
public class ChargeAbnormalServiceImpl implements ChargeAbnormalService {

    @Resource
    private ChargeAbnormalMapper chargeAbnormalMapper;

    /**
     * 新增收费异常记录
     *
     * 说明：
     * - 仅负责数据落库，不包含复杂业务规则
     * - 使用 BeanUtils 完成 VO -> DO 转换，降低样板代码
     *
     * @param createReqVO 新增请求参数
     * @return 新生成的异常记录主键 ID
     */
    @Override
    public Long createChargeAbnormal(ChargeAbnormalSaveReqVO createReqVO) {
        ChargeAbnormalDO chargeAbnormal = BeanUtils.toBean(createReqVO, ChargeAbnormalDO.class);
        chargeAbnormalMapper.insert(chargeAbnormal);
        return chargeAbnormal.getId();
    }

    /**
     * 更新收费异常记录
     *
     * 说明：
     * - 更新前必须校验记录是否存在，避免“静默失败”
     * - 采用 updateById，防止误更新多条数据
     *
     * @param updateReqVO 更新请求参数
     */
    @Override
    public void updateChargeAbnormal(ChargeAbnormalSaveReqVO updateReqVO) {
        validateChargeAbnormalExists(updateReqVO.getId());
        ChargeAbnormalDO updateObj = BeanUtils.toBean(updateReqVO, ChargeAbnormalDO.class);
        chargeAbnormalMapper.updateById(updateObj);
    }

    /**
     * 删除收费异常记录
     *
     * 说明：
     * - 删除前校验记录是否存在
     * - 具体删除方式由 Mapper 决定（逻辑删 / 物理删）
     *
     * @param id 异常记录主键
     */
    @Override
    public void deleteChargeAbnormal(Long id) {
        validateChargeAbnormalExists(id);
        chargeAbnormalMapper.deleteById(id);
    }

    /**
     * 校验收费异常记录是否存在
     *
     * 说明：
     * - 抽取为统一方法，避免重复代码
     * - 不存在时直接抛出业务异常
     *
     * @param id 异常记录主键
     */
    private void validateChargeAbnormalExists(Long id) {
        if (chargeAbnormalMapper.selectById(id) == null) {
            throw exception(CHARGE_ABNORMAL_NOT_EXISTS);
        }
    }

    /**
     * 根据 ID 获取收费异常详情
     *
     * @param id 异常记录主键
     * @return 异常记录 DO
     */
    @Override
    public ChargeAbnormalDO getChargeAbnormal(Long id) {
        return chargeAbnormalMapper.selectById(id);
    }

    /**
     * 分页查询收费异常列表
     *
     * @param pageReqVO 分页查询参数
     * @return 分页结果
     */
    @Override
    public PageResult<ChargeAbnormalDO> getChargeAbnormalPage(ChargeAbnormalPageReqVO pageReqVO) {
        return chargeAbnormalMapper.selectPage(pageReqVO);
    }

    /**
     * 收费异常统计总览
     *
     * 统计指标说明：
     * - totalAbnormalAmount：异常总金额
     * - abnormalOrderCount：异常订单总数
     * - disposalCompletionRate：处置完成率 = 已处置 / 总异常
     * - correctionSuccessRate：纠错成功率 = 已纠错 / 已处置
     *
     * 设计说明：
     * - Mapper 层返回原始统计结果
     * - Service 层负责业务含义解释与比率计算
     * - 所有比率统一转为百分比，保留 2 位小数
     *
     * @param reqVO 统计筛选条件
     * @return 统计结果
     */
    @Override
    public StatReportRespVO statReport(StatReportReqVO reqVO) {
        Map<String, Object> statMap = chargeAbnormalMapper.selectStatReport(reqVO);

        // 兜底处理，防止 SQL 无数据时返回 null
        if (statMap == null) {
            statMap = new HashMap<>();
        }

        BigDecimal totalAmount = (BigDecimal) statMap.getOrDefault(
                "totalAbnormalAmount", BigDecimal.ZERO);

        Long abnormalOrderCount = ((Number) statMap.getOrDefault(
                "abnormalOrderCount", 0)).longValue();

        Long disposedCount = ((Number) statMap.getOrDefault(
                "disposedCount", 0)).longValue();

        Long correctedCount = ((Number) statMap.getOrDefault(
                "correctedCount", 0)).longValue();

        StatReportRespVO respVO = new StatReportRespVO();
        respVO.setTotalAbnormalAmount(totalAmount);
        respVO.setAbnormalOrderCount(abnormalOrderCount);

        // 处置完成率 = 已处置 / 总异常
        if (abnormalOrderCount > 0) {
            respVO.setDisposalCompletionRate(
                    BigDecimal.valueOf(disposedCount)
                            .multiply(BigDecimal.valueOf(100))
                            .divide(BigDecimal.valueOf(abnormalOrderCount), 2, BigDecimal.ROUND_HALF_UP)
            );
        } else {
            respVO.setDisposalCompletionRate(BigDecimal.ZERO);
        }

        // 纠错成功率 = 已纠错 / 已处置
        if (disposedCount > 0) {
            respVO.setCorrectionSuccessRate(
                    BigDecimal.valueOf(correctedCount)
                            .multiply(BigDecimal.valueOf(100))
                            .divide(BigDecimal.valueOf(disposedCount), 2, BigDecimal.ROUND_HALF_UP)
            );
        } else {
            respVO.setCorrectionSuccessRate(BigDecimal.ZERO);
        }

        return respVO;
    }

    /**
     * 收费异常趋势统计（按天）
     *
     * 设计说明：
     * - 强制统计最近一个月，避免前端传任意时间影响性能
     * - 数据库仅返回有数据的日期
     * - Service 层负责补齐缺失日期，保证折线图连续
     *
     * @param reqVO 统计筛选条件（时间会被强制覆盖）
     * @return 趋势统计结果
     */
    @Override
    public TrendRespVO statTrend(StatReportReqVO reqVO) {
        TrendRespVO respVO = new TrendRespVO();

        // 强制统计时间范围：最近一个月
        LocalDateTime now = LocalDateTime.now();
        reqVO.setEndTime(now);
        reqVO.setStartTime(now.minusMonths(1));

        // 查询数据库中已有的趋势点
        List<TrendPointVO> abnormalAmountPointList =
                chargeAbnormalMapper.selectAbnormalAmountTrend(reqVO);
        List<TrendPointVO> abnormalOrderCountPointList =
                chargeAbnormalMapper.selectAbnormalOrderCount(reqVO);

        // 构造完整的日期轴（按天）
        List<String> fullDateList = buildDateRange(reqVO.getStartTime(), reqVO.getEndTime());

        // 补齐缺失点
        respVO.setAbnormalAmountPointList(
                fillMissingPoints(fullDateList, abnormalAmountPointList));

        respVO.setAbnormalOrderCountPointList(
                fillMissingPoints(fullDateList, abnormalOrderCountPointList));

        return respVO;
    }

    /**
     * 异常原因分类统计主流程：
     * 1. 查询数据库，按原因分组统计笔数
     * 2. 统一在 Service 层计算占比，避免 SQL 复杂化
     */
    @Override
    public List<StatDistributionRespVO> sortStat(StatReportReqVO reqVO) {
        //要统计的字段获取
        String statField = reqVO.getStatGroupField();
        //目前支持的字段
        Set<String> supportedStatFieldList=new HashSet<>(
                Arrays.asList("abnormal_type", "disposal_status", "abnormal_reason"));

        if (!supportedStatFieldList.contains(statField)){
            throw exception(new ErrorCode(500,"该统计纬度不存在"));
        }

        List<StatDistributionRespVO> list =
                chargeAbnormalMapper.selectStatBySortField(reqVO);

        fillRatio(list);
        return list;
    }

    /**
     * 各区域收费异常总数统计
     *
     * 根据请求条件统计指定区域下各子区域的异常数量，并计算各区域占比
     *
     * @param reqVO 查询条件，包括时间区间、区域编码等
     * @return 各区域异常统计及占比列表
     */
    @Override
    public List<StatRegionRespVO> statGroupRegion(StatReportReqVO reqVO) {
        // 从数据库查询各区域异常总数
        List<StatRegionRespVO> list = chargeAbnormalMapper.selectStatByRegion(reqVO);

        // 计算每个区域的占比（ratio）
        fillRatio(list, StatRegionRespVO::getValue, StatRegionRespVO::setRatio);

        return list;
    }


    /**
     * 收费异常分页查询
     *
     * 根据请求条件分页查询收费异常记录，并返回分页结果
     * 支持按时间、区域、异常类型等条件筛选
     *
     * @param reqVO 查询条件，包括页码、每页数量及过滤条件
     * @return 分页结果，包含当前页数据和总记录数
     */
    @Override
    public PageResult<ChargeAbnormalDO> statPage(StatReportReqVO reqVO) {
        Integer pageNo = reqVO.getPageNo();
        Integer pageSize = reqVO.getPageSize();
        Long total = 0L; // 总数据量

        // 防御性处理（避免 NPE 或非法页码）
        if (pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }

        // 计算 offset，用于分页查询
        int offset = (pageNo - 1) * pageSize;
        reqVO.setOffset(offset);
        reqVO.setPageSize(pageSize);

        // 查询分页数据
        List<ChargeAbnormalDO> list = chargeAbnormalMapper.selectPageByCondition(reqVO);

        // 查询总记录数
        total = chargeAbnormalMapper.selectCountByCondition(reqVO);

        // 返回分页结果
        return new PageResult<>(list, total);
    }


    /**
     * 统一填充占比（ratio = value / total * 100）
     *
     * <p>
     * 这是一个【通用统计占比计算方法】，不依赖具体的 VO 类型，
     * 通过函数式参数解耦：
     * <ul>
     *   <li>valueGetter：告诉方法“如何从对象中取统计值”</li>
     *   <li>ratioSetter：告诉方法“如何把计算好的占比设置回对象”</li>
     * </ul>
     *
     * <p>
     * 适用于：
     * <pre>
     *   - 区域统计
     *   - 异常原因统计
     *   - 异常类型统计
     *   - 任意 value + ratio 结构的统计结果
     * </pre>
     *
     * @param list        统计结果列表
     * @param valueGetter 从对象中获取统计值（如 getValue）
     * @param ratioSetter 将占比写回对象（如 setRatio）
     * @param <T>         统计结果对象的类型
     */
    private <T> void fillRatio(List<T> list,
                               Function<T, BigDecimal> valueGetter,
                               BiConsumer<T, BigDecimal> ratioSetter) {

        // 空列表直接返回，避免无意义计算
        if (list == null || list.isEmpty()) {
            return;
        }

        // 计算总数：对所有对象的 value 求和
        BigDecimal total = list.stream()
                // 使用外部传入的方法获取 value，避免写死字段
                .map(valueGetter)
                // 累加所有 value，得到总数
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 如果总数为 0，则所有占比都为 0，避免除零异常
        if (BigDecimal.ZERO.compareTo(total) == 0) {
            list.forEach(item ->
                    // 通过外部传入的方法设置 ratio
                    ratioSetter.accept(item, BigDecimal.ZERO)
            );
            return;
        }

        // 逐条计算占比并写回对象
        list.forEach(item -> {
            // ratio = value / total * 100
            BigDecimal ratio = valueGetter.apply(item)
                    .multiply(BigDecimal.valueOf(100))
                    .divide(total, 2, BigDecimal.ROUND_HALF_UP);

            // 将计算好的占比设置回对象
            ratioSetter.accept(item, ratio);
        });
    }

    /**
     * 统一填充占比（value / total * 100）
     * 所有分类统计通用
     */
    private void fillRatio(List<StatDistributionRespVO> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        BigDecimal total = list.stream()
                .map(StatDistributionRespVO::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (BigDecimal.ZERO.compareTo(total) == 0) {
            list.forEach(item -> item.setRatio(BigDecimal.ZERO));
            return;
        }

        list.forEach(item -> {
            BigDecimal ratio = item.getValue()
                    .multiply(BigDecimal.valueOf(100))
                    .divide(total, 2, BigDecimal.ROUND_HALF_UP);
            item.setRatio(ratio);
        });
    }

    /**
     * 构建完整的日期列表（按天）
     *
     * 说明：
     * - 左闭右闭区间 [startDate, endDate]
     * - 日期格式：yyyy-MM-dd，与 SQL timeKey 对齐
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 日期字符串列表
     */
    private List<String> buildDateRange(LocalDateTime startTime, LocalDateTime endTime) {
        List<String> dateList = new ArrayList<>();

        if (startTime == null || endTime == null) {
            return dateList;
        }

        LocalDate startDate = startTime.toLocalDate();
        LocalDate endDate = endTime.toLocalDate();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            dateList.add(date.toString());
        }

        return dateList;
    }

    /**
     * 补齐趋势缺失点
     *
     * 说明：
     * - 数据库通常只返回有数据的日期
     * - 本方法根据完整时间轴补齐缺失日期
     * - 缺失值默认补 0，保证前端图表连续
     *
     * @param fullDateList 完整日期轴
     * @param originList   原始趋势数据
     * @return 补齐后的趋势点列表
     */
    private List<TrendPointVO> fillMissingPoints(List<String> fullDateList,
                                                 List<TrendPointVO> originList) {

        // timeKey -> TrendPointVO
        Map<String, TrendPointVO> pointMap = originList.stream()
                .collect(Collectors.toMap(
                        TrendPointVO::getTimeKey,
                        Function.identity()
                ));

        List<TrendPointVO> result = new ArrayList<>();

        for (String date : fullDateList) {
            TrendPointVO point = pointMap.get(date);
            if (point != null) {
                result.add(point);
            } else {
                TrendPointVO emptyPoint = new TrendPointVO();
                emptyPoint.setTimeKey(date);
                emptyPoint.setGranularity("DAY");
                emptyPoint.setValue(BigDecimal.ZERO);
                result.add(emptyPoint);
            }
        }

        return result;
    }

}
