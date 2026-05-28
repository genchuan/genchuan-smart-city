package cn.iocoder.yudao.module.ordertrade.framework.utils;

import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class UserNameInjector {

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
            return;
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
