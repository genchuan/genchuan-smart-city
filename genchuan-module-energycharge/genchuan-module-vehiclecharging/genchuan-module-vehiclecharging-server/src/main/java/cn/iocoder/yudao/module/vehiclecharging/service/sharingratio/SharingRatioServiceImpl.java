package cn.iocoder.yudao.module.vehiclecharging.service.sharingratio;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingratio.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sharingratio.SharingRatioDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.sharingratio.SharingRatioMapper;

/**
 * 分账比例 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class SharingRatioServiceImpl implements SharingRatioService {

    @Resource
    private SharingRatioMapper sharingRatioMapper;

    @Override
    public Long createSharingRatio(SharingRatioCreateReqVO createReqVO) {
        // 插入
        SharingRatioDO sharingRatio = BeanUtils.toBean(createReqVO, SharingRatioDO.class);
        String sharingCode = generateSharingCode();
        sharingRatio.setSharingCode(sharingCode);
        sharingRatio.setSharingStatus("未生效");
        sharingRatioMapper.insert(sharingRatio);

        // 返回
        return sharingRatio.getId();
    }

    @Override
    public void updateSharingRatio(SharingRatioUpdateReqVO updateReqVO) {
        // 校验存在
        validateSharingRatioExists(updateReqVO.getId());
        // 更新
        SharingRatioDO updateObj = BeanUtils.toBean(updateReqVO, SharingRatioDO.class);
        sharingRatioMapper.updateById(updateObj);
    }

    private void validateSharingRatioExists(Long id) {
        if (sharingRatioMapper.selectById(id) == null) {
//            throw exception(SHARING_RATIO_NOT_EXISTS);
            throw new RuntimeException("500 记录不存在，id=" + id);
        }
    }

    @Override
    public SharingRatioDO getSharingRatio(Long id) {
        return sharingRatioMapper.selectById(id);
    }

    @Override
    public PageResult<SharingRatioDO> getSharingRatioPage(SharingRatioPageReqVO pageReqVO) {
        return sharingRatioMapper.selectPage(pageReqVO);
    }

    @Override
    public void updateSharingStatus(Long id, int i) {
        // 校验存在
        validateSharingRatioExists(id);
        // 更新
        SharingRatioDO updateObj = new SharingRatioDO();
        updateObj.setId(id);
        if(i == 0) {
            updateObj.setSharingStatus("已生效");
        } else if(i == 1) {
            updateObj.setSharingStatus("已失效");
        } else {
            throw new RuntimeException("500 错误的状态");
        }
        int rows = sharingRatioMapper.updateById(updateObj);
        if (rows <= 0) {
            throw new RuntimeException("500 更新失败");
        }
    }

    @Override
    public Long copySharingRatio(Long sourceId) {
        // 1. 查询源数据（需未删除）
        SharingRatioDO source = sharingRatioMapper.selectById(sourceId);
        if (source == null || source.getDeleted()) {
            throw new RuntimeException("500 源分账方案不存在或已删除");
        }

        // 2. 复制新对象
        SharingRatioDO copy = new SharingRatioDO();
        BeanUtils.copyProperties(source, copy);

        // 3. 生成新的唯一方案编号
        copy.setSharingCode(generateSharingCode());

        // 4. 重置审计字段（createTime/updateTime由数据库自动填充，creator/updater由框架自动填充，此处不手动设置）
        // 注意：如果租户ID需要继承原租户，则保留source.getTenantId()，否则由框架自动填充当前租户

        // 5. 插入数据库
        int rows = sharingRatioMapper.insert(copy);
        if (rows <= 0) {
            throw new RuntimeException("500 复制分账方案失败");
        }

        return copy.getId();
    }

    /**
     * 生成唯一方案编号
     * 示例：SR-20250403-001
     */
    private String generateSharingCode() {
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        // 查询当日已存在的最大序号，简单起见可以使用UUID或雪花算法，这里提供序号生成示例
        String prefix = "SR-" + datePart + "-";
        LambdaQueryWrapper<SharingRatioDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(SharingRatioDO::getSharingCode)
                .like(SharingRatioDO::getSharingCode, prefix)
                .orderByDesc(SharingRatioDO::getSharingCode)
                .last("LIMIT 1");
        SharingRatioDO last = sharingRatioMapper.selectOne(wrapper);
        int seq = 1;
        if (last != null && last.getSharingCode() != null) {
            String code = last.getSharingCode();
            String seqStr = code.substring(prefix.length());
            try {
                seq = Integer.parseInt(seqStr) + 1;
            } catch (NumberFormatException e) {
                seq = 1;
            }
        }
        return prefix + String.format("%03d", seq);
    }

    @Override
    public SharingRatioSummaryRespVO getChartSummary(SharingRatioChartReqVO reqVO) {
        // 1. 卡片统计：总方案数、各状态方案数（根据时间范围筛选）
        SharingRatioSummaryRespVO respVO = sharingRatioMapper.selectSummaryStats(reqVO.getTimeRangeStart(), reqVO.getTimeRangeEnd());

        // 2. 饼图数据：按分账类型分组统计方案数量
        List<SharingRatioSummaryRespVO.PieData> pieData = sharingRatioMapper.selectPieData(reqVO.getTimeRangeStart(), reqVO.getTimeRangeEnd());
        respVO.setPieData(pieData);

        // 3. 柱状图数据：按合作方分组统计方案数量（取前 N 个，或全部）
        List<SharingRatioSummaryRespVO.BarData> barData = sharingRatioMapper.selectBarData(reqVO.getTimeRangeStart(), reqVO.getTimeRangeEnd());
        respVO.setBarData(barData);

        return respVO;
    }

    @Override
    public SharingRatioCooperatorRatioRespVO getCooperatorRatio(SharingRatioChartReqVO reqVO) {
        Long startTime = reqVO.getTimeRangeStart();
        Long endTime = reqVO.getTimeRangeEnd();

        // 1. 查询各合作方方案数量
        List<Map<String, Object>> cooperatorCounts = sharingRatioMapper.selectCooperatorCounts(startTime, endTime);
        if (cooperatorCounts == null || cooperatorCounts.isEmpty()) {
            return new SharingRatioCooperatorRatioRespVO();
        }

        // 2. 计算总方案数
        int total = 0;
        for (Map<String, Object> map : cooperatorCounts) {
            total += ((Number) map.get("count")).intValue();
        }

        // 3. 计算每个合作方的占比（保留整数，四舍五入）
        List<SharingRatioCooperatorRatioRespVO.CooperatorRatio> list = new ArrayList<>();
        for (Map<String, Object> map : cooperatorCounts) {
            String name = (String) map.get("cooperator");
            int count = ((Number) map.get("count")).intValue();
            int ratio = (int) Math.round((double) count / total * 100);
            SharingRatioCooperatorRatioRespVO.CooperatorRatio item = new SharingRatioCooperatorRatioRespVO.CooperatorRatio();
            item.setName(name);
            item.setValue(ratio);
            list.add(item);
        }

        // 4. 组装返回
        SharingRatioCooperatorRatioRespVO respVO = new SharingRatioCooperatorRatioRespVO();
        respVO.setList(list);
        return respVO;
    }

    @Override
    public SharingRatioSchemeCompareRespVO schemeCompare(List<Long> schemeIds) {
        if (schemeIds == null || schemeIds.isEmpty()) {
            return new SharingRatioSchemeCompareRespVO();
        }
        // 查询方案（自动过滤已删除和租户隔离）
        List<SharingRatioDO> list = sharingRatioMapper.selectList(new LambdaQueryWrapper<SharingRatioDO>()
                .in(SharingRatioDO::getId, schemeIds)
                .eq(SharingRatioDO::getDeleted, false));
        // 转换为响应 VO
        List<SharingRatioSchemeCompareRespVO.SchemeCompareItem> items = list.stream()
                .map(doItem -> {
                    SharingRatioSchemeCompareRespVO.SchemeCompareItem item = new SharingRatioSchemeCompareRespVO.SchemeCompareItem();
                    item.setName(doItem.getSharingName());
                    item.setRatio(doItem.getSharingRatio());
                    item.setCooperator(doItem.getCooperator());
                    return item;
                })
                .collect(Collectors.toList());
        SharingRatioSchemeCompareRespVO respVO = new SharingRatioSchemeCompareRespVO();
        respVO.setList(items);
        return respVO;
    }

    @Override
    public SharingRatioStatusCountRespVO getStatusCount(SharingRatioChartReqVO reqVO) {
        Long startTime = reqVO.getTimeRangeStart();
        Long endTime = reqVO.getTimeRangeEnd();

        // 查询各状态数量（直接使用之前统计查询的方法，但只取状态部分）
        SharingRatioSummaryRespVO summary = sharingRatioMapper.selectSummaryStats(startTime, endTime);
        if (summary == null) {
            summary = new SharingRatioSummaryRespVO();
            summary.setValidCount(0);
            summary.setInvalidCount(0);
            summary.setPendingCount(0);
        }

        SharingRatioStatusCountRespVO respVO = new SharingRatioStatusCountRespVO();
        SharingRatioStatusCountRespVO.StatusCount statusCount = new SharingRatioStatusCountRespVO.StatusCount();
        statusCount.setValid(summary.getValidCount());
        statusCount.setInvalid(summary.getInvalidCount());
        statusCount.setPending(summary.getPendingCount());
        respVO.setStatusCount(statusCount);
        return respVO;
    }

}