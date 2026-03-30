package cn.iocoder.yudao.module.facility.framework.lxsutils.road;

import cn.iocoder.yudao.module.facility.controller.admin.syswarn.vo.SysWarnCandidate;
import cn.iocoder.yudao.module.facility.dal.dataobject.road.roadmonitor.RoadMonitorDO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class RoadWarnUtil {

    private RoadWarnUtil() {
        // 工具类不允许实例化
    }

    /**
     * 根据道路监测数据计算所有超标预警项
     */
    public static List<SysWarnCandidate> calculateCandidates(RoadMonitorDO monitor) {
        List<SysWarnCandidate> candidates = new ArrayList<>();

        // 1. 坑洼数量
        addIfOver(
                candidates,
                "坑洼数量超标",
                "坑洼数量",
                monitor.getPotholeNum(),
                monitor.getPotholeNumThreshold()
        );

        // 2. 裂缝长度
        addIfOver(
                candidates,
                "裂缝长度超标",
                "裂缝长度",
                monitor.getCrackLength(),
                monitor.getCrackLengthThreshold()
        );

        // 3. 路面温度
        addIfOver(
                candidates,
                "路面温度超标",
                "路面温度",
                monitor.getRoadTemp(),
                monitor.getRoadTempThreshold()
        );

        // 4. 交通流量
        addIfOver(
                candidates,
                "交通流量超标",
                "交通流量",
                monitor.getTrafficFlow(),
                monitor.getTrafficFlowThreshold()
        );

        return candidates;
    }

    /**
     * 公共超标判断方法
     *
     * 用途说明：
     *  - 用于对“监测值 vs 阈值”进行统一的超标判断
     *  - 当监测值大于阈值时，生成一条预警候选数据并加入集合
     *  - 适用于多种指标复用（如：位移、沉降、裂缝宽度等）
     *
     * 泛型说明：
     *  - T 必须实现 Comparable，用于支持大小比较
     *  - 实际业务中通常传入 BigDecimal
     *
     * @param candidates 预警候选结果集合（满足超标条件时向其中追加数据）
     * @param warnType   预警类型（如：道路预警、桥梁预警等）
     * @param indexName  指标名称（如：沉降量、裂缝宽度等）
     * @param value      实际监测值
     * @param threshold  超标阈值
     */
    private static <T extends Comparable<T>> void addIfOver(
            List<SysWarnCandidate> candidates,
            String warnType,
            String indexName,
            T value,
            T threshold
    ) {
        // 监测值或阈值为空时，不进行判断，直接返回
        if (value == null || threshold == null) {
            return;
        }

        // compareTo > 0 表示：value > threshold，即发生超标
        if (value.compareTo(threshold) > 0) {
            // 构建超标预警候选对象并加入集合
            candidates.add(new SysWarnCandidate(
                    warnType,                 // 预警类型
                    indexName,                // 指标名称
                    (BigDecimal) value,       // 实际监测值（业务中约定为 BigDecimal）
                    (BigDecimal) threshold    // 阈值
            ));
        }
    }
}
