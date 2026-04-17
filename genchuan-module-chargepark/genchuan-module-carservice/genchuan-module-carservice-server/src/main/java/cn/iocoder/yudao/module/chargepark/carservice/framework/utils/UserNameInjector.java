package cn.iocoder.yudao.module.chargepark.carservice.framework.utils;

import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * 通用用户名拼接工具
 *
 * 用法：
 * <pre>
 *   UserNameInjector.inject(list, adminUserApi,
 *       UserNameInjector.field(RescueInfoRespVO::getUserId, RescueInfoRespVO::setUserName),
 *       UserNameInjector.field(RescueInfoRespVO::getRescueUserId, RescueInfoRespVO::setRescueUserName));
 * </pre>
 *
 * 实现:
 * 1. 一次性收集所有 user_id 到 HashSet 去重
 * 2. 单次 RPC 调 AdminUserApi.getUserMap(ids)
 * 3. RPC 失败 try-catch 降级,不阻塞业务
 * 4. 对每个列表项的每个字段,从 userMap 取 nickname 写回
 *
 * 适配场景:N+1 防护,任意张表的 RespVO 列表批量拼接用户名
 */
public class UserNameInjector {

    /** 描述一个用户字段的 (id getter, name setter) 配对 */
    public static class Field<T> {
        final Function<T, Long> idGetter;
        final BiConsumer<T, String> nameSetter;

        Field(Function<T, Long> idGetter, BiConsumer<T, String> nameSetter) {
            this.idGetter = idGetter;
            this.nameSetter = nameSetter;
        }
    }

    public static <T> Field<T> field(Function<T, Long> idGetter, BiConsumer<T, String> nameSetter) {
        return new Field<>(idGetter, nameSetter);
    }

    @SafeVarargs
    public static <T> void inject(List<T> list, AdminUserApi adminUserApi, Field<T>... fields) {
        if (list == null || list.isEmpty() || fields == null || fields.length == 0) {
            return;
        }
        Set<Long> userIds = new HashSet<>();
        for (T item : list) {
            for (Field<T> f : fields) {
                Long id = f.idGetter.apply(item);
                if (id != null) {
                    userIds.add(id);
                }
            }
        }
        if (userIds.isEmpty()) {
            return;
        }
        Map<Long, AdminUserRespDTO> userMap;
        try {
            userMap = adminUserApi.getUserMap(userIds);
        } catch (Exception ex) {
            return; // RPC 失败降级
        }
        if (userMap == null || userMap.isEmpty()) {
            return;
        }
        for (T item : list) {
            for (Field<T> f : fields) {
                Long id = f.idGetter.apply(item);
                if (id == null) {
                    continue;
                }
                AdminUserRespDTO u = userMap.get(id);
                if (u != null) {
                    f.nameSetter.accept(item, u.getNickname());
                }
            }
        }
    }

    private UserNameInjector() {}

}
