package cn.iocoder.yudao.module.ordertrade.rpc.stationresource;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class StationNameHelper {

    @Resource
    private StationInfoFeignClient stationInfoFeignClient;

    /**
     * 批量填充场站名称
     *
     * @param items        需要填充的对象列表
     * @param idGetter     获取 stationId 的函数
     * @param nameSetter   设置 stationName 的函数
     */
    public <T> void fillStationNames(List<T> items,
                                     Function<T, Long> idGetter,
                                     BiConsumer<T, String> nameSetter) {
        if (items == null || items.isEmpty()) {
            return;
        }
        // 收集所有唯一的 stationId
        Collection<Long> ids = items.stream()
                .map(idGetter)
                .filter(id -> id != null)
                .collect(Collectors.toSet());
        if (ids.isEmpty()) {
            return;
        }
        // 逐个查询（stationresource 目前只提供单条查询接口）
        Map<Long, String> idToName = ids.stream()
                .collect(Collectors.toMap(
                        id -> id,
                        id -> {
                            try {
                                CommonResult<StationInfoDTO> result = stationInfoFeignClient.getStationInfo(id);
                                if (result != null && result.getData() != null) {
                                    return result.getData().getName();
                                }
                            } catch (Exception e) {
                                log.warn("[fillStationNames] 查询场站名称失败, stationId={}", id, e);
                            }
                            return "";
                        }
                ));
        // 回填到列表
        items.forEach(item -> {
            Long id = idGetter.apply(item);
            if (id != null) {
                nameSetter.accept(item, idToName.getOrDefault(id, ""));
            }
        });
    }

    /**
     * 填充单个对象的场站名称
     */
    public <T> void fillStationName(T item,
                                    Function<T, Long> idGetter,
                                    BiConsumer<T, String> nameSetter) {
        if (item == null) {
            return;
        }
        fillStationNames(Collections.singletonList(item), idGetter, nameSetter);
    }

}
