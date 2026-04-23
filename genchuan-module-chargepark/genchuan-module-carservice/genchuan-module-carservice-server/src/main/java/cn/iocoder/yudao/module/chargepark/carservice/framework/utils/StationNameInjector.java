package cn.iocoder.yudao.module.chargepark.carservice.framework.utils;

import cn.iocoder.yudao.module.stationresource.api.station.StationInfoApi;
import cn.iocoder.yudao.module.stationresource.api.station.dto.StationInfoRespDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * 通用场站名拼接工具 — 同 {@link UserNameInjector} 思路。
 *
 * 用法:
 * <pre>
 *   StationNameInjector.inject(list, stationInfoApi,
 *       StationNameInjector.field(ReserveListRespVO::getStationId, ReserveListRespVO::setStationName));
 * </pre>
 *
 * 实现:
 * 1. 收集 station_id 去重
 * 2. 单次 RPC 调 StationInfoApi.getStationMap(ids)
 * 3. RPC 失败 try-catch 降级,不阻塞业务
 */
public class StationNameInjector {

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
    public static <T> void inject(List<T> list, StationInfoApi stationInfoApi, Field<T>... fields) {
        if (list == null || list.isEmpty() || fields == null || fields.length == 0) {
            return;
        }
        Set<Long> ids = new HashSet<>();
        for (T item : list) {
            for (Field<T> f : fields) {
                Long id = f.idGetter.apply(item);
                if (id != null) {
                    ids.add(id);
                }
            }
        }
        if (ids.isEmpty()) {
            return;
        }
        Map<Long, StationInfoRespDTO> stationMap;
        try {
            stationMap = stationInfoApi.getStationMap(ids);
        } catch (Exception ex) {
            return;
        }
        if (stationMap == null || stationMap.isEmpty()) {
            return;
        }
        for (T item : list) {
            for (Field<T> f : fields) {
                Long id = f.idGetter.apply(item);
                if (id == null) {
                    continue;
                }
                StationInfoRespDTO s = stationMap.get(id);
                if (s != null) {
                    f.nameSetter.accept(item, s.getName());
                }
            }
        }
    }

    private StationNameInjector() {}

}
