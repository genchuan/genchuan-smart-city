package cn.iocoder.yudao.module.industry.service.park.pay.statparkpay;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.statparkpay.vo.StatParkPayGenReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.statparkpay.vo.StatParkPayPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.statparkpay.vo.StatParkPaySaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.statparkpay.StatParkPayDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.pay.parkpay.ParkPayMapper;
import cn.iocoder.yudao.module.industry.dal.mysql.park.pay.statparkpay.StatParkPayMapper;
import cn.iocoder.yudao.module.industry.framework.util.lxs.RegionUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;



import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 缴费统计 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class StatParkPayServiceImpl implements StatParkPayService {

    @Resource
    private StatParkPayMapper statParkPayMapper;

    @Resource
    private ParkPayMapper parkPayMapper;



    /**
     * 生成缴费统计数据
     *
     * 处理逻辑说明：
     * 1. 根据统计周期（day / week / month / year）计算统计起止时间；
     * 2. 按统计条件汇总主指标数据（订单数、缴费金额、成功率等）；
     * 3. 按区域维度统计下一级区域的缴费占比：
     *    - 若已到最下级区域（社区级），则仅返回自身，占比为 100%；
     *    - 否则统计下级区域数据并生成占比 JSON；
     * 4. 对主指标、区域名称、区域占比列表中的空值进行兜底处理；
     * 5. 生成统计编码、统计时间等固定字段；
     * 6. 将统计结果序列化后存入统计表，并返回生成的主键 ID。
     *
     * @param createReqVO 统计生成请求参数
     * @return 新增统计记录的主键 ID
     */
    @Override
    public Long createStatParkPay(StatParkPayGenReqVO createReqVO) {
        //1.对stat_cycle进行分解
        String statCycle=createReqVO.getStatCycle();
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime startTime;
        LocalDateTime endTime;

        if (StringUtils.hasText(statCycle)){
            switch (statCycle) {
                case "day":
                    // 当天 00:00:00 - 23:59:59
                    startTime = now.toLocalDate().atStartOfDay();
                    endTime = startTime.plusDays(1).minusSeconds(1);
                    break;

                case "week":
                    // 最近 7 天（含今天）
                    endTime = now;
                    startTime = now.minusDays(6).toLocalDate().atStartOfDay();
                    break;

                case "month":
                    // 当月
                    startTime = now.withDayOfMonth(1).toLocalDate().atStartOfDay();
                    endTime = startTime.plusMonths(1).minusSeconds(1);
                    break;

                case "year":
                    // 当年
                    startTime = now.withDayOfYear(1).toLocalDate().atStartOfDay();
                    endTime = startTime.plusYears(1).minusSeconds(1);
                    break;

                default:
                    throw new IllegalArgumentException("不支持的统计周期 statCycle=" + statCycle);
            }
        }else {
            //如果前端没有传入筛选周期，就用当月的
            startTime = now.withDayOfYear(1).toLocalDate().atStartOfDay();
            endTime = startTime.plusYears(1).minusSeconds(1);
        }

        //回填到 VO（供 Mapper XML 使用）
        createReqVO.setStartTime(startTime);
        createReqVO.setEndTime(endTime);

        // 2.调用 statParkPayMapper 的统计接口,统计主指标
        StatParkPayDO statParkPayDO = statParkPayMapper.stat(createReqVO);

        // 3. 统计该筛选区域 的 下一级各区域 缴费占比（JSON）
        String regionCode = createReqVO.getRegionFullCode();

        createReqVO.setRegionCodeChildLevel(String.valueOf(RegionUtils.getRegionLevel(regionCode)+1));
        createReqVO.setRegionCodeChildEffectiveLength(
                String.valueOf(RegionUtils.getEffectiveLengthByLevel(Integer.valueOf(createReqVO.getRegionCodeChildLevel()))));

        String regionPayRatioJson = statParkPayMapper.getRegionPayRatio(createReqVO);

        //3.1.1 填充 区域占比，情况1：统计地区到第五级
        //如果统计地区到了社区级别（5），那么它的下一级地区会变成6，但是6不存在，所以社区的下级区域统计是只有自己的社区
        if (Integer.parseInt(createReqVO.getRegionCodeChildLevel())>5) {
            // 已经是最下级区域，直接返回自身
            StatParkPayDO.RegionPayRatioItem item = new StatParkPayDO.RegionPayRatioItem();
            item.setRegionFullCode(statParkPayDO.getRegionFullCode());
            item.setRegionName(statParkPayDO.getRegionName());
            item.setPayAmount(statParkPayDO.getTotalPayAmount());
            item.setRatio(BigDecimal.valueOf(100.0));

            statParkPayDO.setRegionPayRatioList(Collections.singletonList(item));
            // 同时生成 JSON 字符串
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                statParkPayDO.setRegionPayRatio(objectMapper.writeValueAsString(
                        Collections.singletonList(item)
                ));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                // 出错时直接用空数组或者手动生成字符串，保证不抛异常
                statParkPayDO.setRegionPayRatio("[]");
            }
        }else {

            //3.1.2 填充 区域占比，情况2：统计地区未到第五级
            if (regionPayRatioJson != null && !regionPayRatioJson.isEmpty()) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    List<StatParkPayDO.RegionPayRatioItem> regionPayRatioList = objectMapper.readValue(
                            regionPayRatioJson,
                            new TypeReference<List<StatParkPayDO.RegionPayRatioItem>>() {}
                    );
                    statParkPayDO.setRegionPayRatioList(regionPayRatioList);
                    statParkPayDO.setRegionPayRatio(regionPayRatioJson);
                } catch (JsonProcessingException e) {
                    e.printStackTrace(); // 或者记录日志
                    statParkPayDO.setRegionPayRatioList(Collections.emptyList());
                }

            }
        }
        // 4. 补充统计表固定字段
        statParkPayDO.setStatCode(UUID.randomUUID().toString().replace("-", ""));
        statParkPayDO.setStatCycle(statCycle);
        statParkPayDO.setStatTime(endTime);
        // 统计周期名称（展示用）
        statParkPayDO.setStatCycleName(buildStatCycleName(statCycle, startTime, endTime));
        // 统计口径起止时间（防回溯污染，钻取必用）
        statParkPayDO.setStatStartTime(startTime);
        statParkPayDO.setStatEndTime(endTime);


        //5.null 兜底处理
        // 主指标 null 兜底
        statParkPayDO.setTotalOrderCount(statParkPayDO.getTotalOrderCount() == null ? 0 : statParkPayDO.getTotalOrderCount());
        statParkPayDO.setTotalPayAmount(statParkPayDO.getTotalPayAmount() == null ? BigDecimal.ZERO : statParkPayDO.getTotalPayAmount());
        statParkPayDO.setPaySuccessRate(statParkPayDO.getPaySuccessRate() == null ? BigDecimal.ZERO : statParkPayDO.getPaySuccessRate());
        statParkPayDO.setWechatRatio(statParkPayDO.getWechatRatio() == null ? BigDecimal.ZERO : statParkPayDO.getWechatRatio());
        statParkPayDO.setAlipayRatio(statParkPayDO.getAlipayRatio() == null ? BigDecimal.ZERO : statParkPayDO.getAlipayRatio());
        statParkPayDO.setCardRatio(statParkPayDO.getCardRatio() == null ? BigDecimal.ZERO : statParkPayDO.getCardRatio());
        // 区域名称兜底
        String regionName = statParkPayMapper.selectAreaByFullCode(createReqVO.getRegionFullCode());
        if (regionName==null){
            regionName = "未知地区";
        }
        if (statParkPayDO.getRegionName()==null){
            statParkPayDO.setRegionName(regionName);
        }
        // 下级区域列表 null 兜底
        boolean changeRegionPayRatioListFlag=false;
        for (StatParkPayDO.RegionPayRatioItem item:statParkPayDO.getRegionPayRatioList()){

            if (item.getRegionName()==null){
                item.setRegionName(regionName);
                changeRegionPayRatioListFlag=true;
            }
            if (item.getPayAmount()==null){
                item.setPayAmount(BigDecimal.ZERO);
                changeRegionPayRatioListFlag=true;
            }
        }
        if (changeRegionPayRatioListFlag){
            // 同时生成 JSON 字符串
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                statParkPayDO.setRegionPayRatio(objectMapper.writeValueAsString(
                        statParkPayDO.getRegionPayRatioList()
                ));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                // 出错时直接用空数组或者手动生成字符串，保证不抛异常
                statParkPayDO.setRegionPayRatio("[]");
            }
        }

        //6.展示到控制台上（方便调试）
        System.out.println("cs2025-12-24 16:05:38:"+statParkPayDO);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // 支持 LocalDateTime
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // 美化输出

        try {
            String json = objectMapper.writeValueAsString(statParkPayDO);
            System.out.println("cs2025-12-24 16:05:38:\n" + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //7. 存入数据库
        // 保存到数据库
        statParkPayMapper.insert(statParkPayDO);

        // 返回数据库生成的 ID（假设 MyBatis 配置了 useGeneratedKeys=true）
        return statParkPayDO.getId();

    }

    /**
     * 构建统计周期名称（用于展示）
     */
    private String buildStatCycleName(String statCycle,
                                      LocalDateTime startTime,
                                      LocalDateTime endTime) {

        // 中文日期格式
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("yyyy年MM月");
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yyyy年");

        switch (statCycle) {
            case "day":
                // 2025年12月24日
                return startTime.format(dayFormatter);

            case "week":
                // 2025年12月18日-2025年12月24日
                return startTime.format(dayFormatter)
                        + "-"
                        + endTime.format(dayFormatter);

            case "month":
                // 2025年12月
                return startTime.format(monthFormatter);

            case "year":
                // 2025年
                return startTime.format(yearFormatter);

            default:
                // 兜底：中文起止时间
                return startTime.format(dayFormatter)
                        + "-"
                        + endTime.format(dayFormatter);
        }
    }


    @Override
    public void updateStatParkPay(StatParkPaySaveReqVO updateReqVO) {
        // 校验存在
        validateStatParkPayExists(updateReqVO.getId());
        // 更新
        StatParkPayDO updateObj = BeanUtils.toBean(updateReqVO, StatParkPayDO.class);
        statParkPayMapper.updateById(updateObj);
    }

    @Override
    public void deleteStatParkPay(Long id) {
        // 校验存在
        validateStatParkPayExists(id);
        // 删除
        statParkPayMapper.deleteById(id);
    }

    private void validateStatParkPayExists(Long id) {
        if (statParkPayMapper.selectById(id) == null) {
            throw exception(STAT_PARK_PAY_NOT_EXISTS);
        }
    }

    @Override
    public StatParkPayDO getStatParkPay(Long id) {
        return statParkPayMapper.selectById(id);
    }

    /**
     * 分页查询缴费统计数据
     *
     * 处理逻辑说明：
     * 1. 根据分页条件从统计表中分页查询缴费统计记录；
     * 2. 对每条记录中的区域缴费占比字段（region_pay_ratio）进行反序列化，
     *    将 JSON 字符串转换为对象列表，便于前端直接使用；
     * 3. 当占比数据为空或解析失败时，统一返回空列表，避免空指针问题。
     *
     * @param pageReqVO 分页查询参数
     * @return 缴费统计分页结果
     */
    @Override
    public PageResult<StatParkPayDO> getStatParkPayPage(StatParkPayPageReqVO pageReqVO) {
        // 1. 分页查询
        PageResult<StatParkPayDO> pageResult = statParkPayMapper.selectPage(pageReqVO);

        // 2. 遍历每一条记录，将 regionPayRatio JSON 映射为对象列表
        ObjectMapper objectMapper = new ObjectMapper();
        for (StatParkPayDO stat : pageResult.getList()) {
            String regionPayRatioJson = stat.getRegionPayRatio();
            if (regionPayRatioJson != null && !regionPayRatioJson.isEmpty()) {
                try {
                    List<StatParkPayDO.RegionPayRatioItem> list =
                            objectMapper.readValue(regionPayRatioJson,
                                    new TypeReference<List<StatParkPayDO.RegionPayRatioItem>>() {});
                    stat.setRegionPayRatioList(list);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    stat.setRegionPayRatioList(Collections.emptyList());
                }
            } else {
                stat.setRegionPayRatioList(Collections.emptyList());
            }
        }

        return pageResult;
    }

}
