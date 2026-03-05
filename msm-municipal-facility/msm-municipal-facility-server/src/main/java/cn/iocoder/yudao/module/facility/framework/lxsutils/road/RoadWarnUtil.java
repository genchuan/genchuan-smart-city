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
     * 公共超标判断逻辑
     */
    private static <T extends Comparable<T>> void addIfOver(
            List<SysWarnCandidate> candidates,
            String warnType,
            String indexName,
            T value,
            T threshold
    ) {
        if (value == null || threshold == null) {
            return;
        }

        if (value.compareTo(threshold) > 0) {
            candidates.add(new SysWarnCandidate(
                    warnType,
                    indexName,
                    (BigDecimal) value,
                    (BigDecimal) threshold
            ));
        }
    }
}
