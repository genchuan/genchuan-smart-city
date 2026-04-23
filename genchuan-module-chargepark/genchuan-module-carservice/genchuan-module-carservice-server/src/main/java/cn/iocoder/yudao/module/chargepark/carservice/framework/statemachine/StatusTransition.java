package cn.iocoder.yudao.module.chargepark.carservice.framework.statemachine;

import java.util.Map;
import java.util.Set;

/**
 * 通用状态机校验工具
 *
 * 用法：
 * <pre>
 * StatusTransition.builder()
 *     .allow("待派发", "待认领")
 *     .allow("待认领", "处理中")
 *     .allow("处理中", "已完成")
 *     .build()
 *     .canTransition(from, to);
 * </pre>
 */
public class StatusTransition {

    private final Map<String, Set<String>> transitions;

    private StatusTransition(Map<String, Set<String>> transitions) {
        this.transitions = transitions;
    }

    public boolean canTransition(String from, String to) {
        if (from == null || to == null) {
            return false;
        }
        Set<String> allowed = transitions.get(from);
        return allowed != null && allowed.contains(to);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final java.util.HashMap<String, Set<String>> map = new java.util.HashMap<>();

        public Builder allow(String from, String to) {
            map.computeIfAbsent(from, k -> new java.util.HashSet<>()).add(to);
            return this;
        }

        public StatusTransition build() {
            return new StatusTransition(java.util.Collections.unmodifiableMap(map));
        }
    }

}
