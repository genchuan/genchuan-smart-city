package cn.iocoder.yudao.module.industry.framework.util.lxs.stat;

import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkorderescape.ParkOrderEscapeDO;
import com.baomidou.mybatisplus.annotation.TableName;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class StatUtils {
    //对传入的数据列表进行统计,默认数据列表的全部项类型都是一致的
    public static <T> Map<String, Object> statList( List<T> dataList){
        if (dataList == null || dataList.isEmpty()) {
            return Collections.emptyMap();
        }
        Class<T> clazz = (Class<T>) dataList.get(0).getClass();
        return statList(clazz, dataList);
    }
    //对传入的数据列表进行统计：

    /**
     * 对指定类的对象列表进行数字字段统计。
     * <p>
     * 统计内容包括：
     * <ul>
     *     <li>sum：每个数字字段的总和</li>
     *     <li>avg：每个数字字段的平均值</li>
     * </ul>
     * 支持的数字类型：{@link Integer}, {@link Long}, {@link Short}, {@link Byte},
     * {@link Float}, {@link Double}, {@link BigDecimal} 及对应的基本类型。
     * <p>
     * 特别说明：
     * <ul>
     *     <li>字段名为 "id"、以 "Id" 结尾或以 "_id" 结尾的字段将被自动忽略</li>
     *     <li>返回的 Map 结构如下：
     *         <pre>
     * {
     *     "sum": { "field1": value1, "field2": value2, ... },
     *     "avg": { "field1": value1, "field2": value2, ... }
     * }
     *         </pre>
     *     </li>
     *     <li>平均值保留 6 位小数，四舍五入</li>
     * </ul>
     *
     * @param clazz    需要统计的类对象
     * @param dataList 需要统计的数据列表
     * @param <T>      泛型类型
     * @return Map<String, Object> 包含 "sum" 和 "avg" 两个子 Map
     * @throws RuntimeException 如果无法访问对象字段
     */

    public static <T> Map<String, Object> statList(Class<T> clazz, List<T> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            return Collections.emptyMap();
        }

        try {

            // 构造返回map
            Map<String, Object> statMap = new LinkedHashMap<>();

            // 构造 sumMap
            Map<String, BigDecimal> sumMap = new LinkedHashMap<>();
            // 构造 avgMap
            Map<String, BigDecimal> avgMap = new LinkedHashMap<>();

            // 获取类的所有字段
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);

                String fieldName = field.getName();
                // 屏蔽 id 类字段
                if ("id".equalsIgnoreCase(fieldName)
                        || fieldName.endsWith("Id")
                        || fieldName.endsWith("_id")) {
                    continue; // 跳过统计
                }

                Class<?> fieldType = field.getType();

                if (Number.class.isAssignableFrom(fieldType) ||
                        fieldType == int.class || fieldType == long.class ||
                        fieldType == float.class || fieldType == double.class ||
                        fieldType == short.class )
                    // 只统计数字类型的字段
                {


                    long sumInt = 0;
                    double sumDouble = 0;
                    BigDecimal sumBigDecimal = BigDecimal.ZERO;
                    //遍历数据列表，对每个对象的某个数字字段求总和
                    for (Object item : dataList) {

                        Object value = field.get(item);
                        if (value != null) {
                            if (value instanceof Integer || value instanceof Long || value instanceof Short || value instanceof Byte) {
                                sumInt += ((Number) value).longValue();
                            } else if (value instanceof Float || value instanceof Double) {
                                sumDouble += ((Number) value).doubleValue();
                            } else if (value instanceof BigDecimal) {
                                sumBigDecimal = sumBigDecimal.add((BigDecimal) value); // 直接相加
                            }
                        }
                    }

                    // 最后统一转 BigDecimal
                    BigDecimal sum = sumBigDecimal.add(BigDecimal.valueOf(sumDouble + sumInt));
                    sumMap.put(field.getName(), sum);

                    // 计算平均值
                    BigDecimal avg = sum.divide(BigDecimal.valueOf(dataList.size()), 6, RoundingMode.HALF_UP);
                    avgMap.put(fieldName, avg);
                }
            }

            //整合sum统计到总统计map
            statMap.put("sum",sumMap);
            //整合avg统计到总统计map
            statMap.put("avg",avgMap);
            return statMap;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("无法访问类字段", e);
        }
    }


    /**
     * 将对象转换为 BigDecimal
     * 支持 BigDecimal、Integer、Float、Double、Long、Short、Byte
     */
    private static BigDecimal toBigDecimal(Object value) {
        if (value instanceof Number) {
            return BigDecimal.valueOf(((Number) value).doubleValue());
        }
        throw new IllegalArgumentException("无法转换为 BigDecimal 的类型: " + value.getClass());
    }


    public static void main(String[] args) {
        // 示例测试
        List<ParkOrderEscapeDO> list = new ArrayList<>();
        ParkOrderEscapeDO item1 = new ParkOrderEscapeDO();
        item1.setId(1L);
        item1.setEscapeAmount(BigDecimal.valueOf(100.5));
        ParkOrderEscapeDO item2 = new ParkOrderEscapeDO();
        item2.setId(2L);
        item2.setEscapeAmount(BigDecimal.valueOf(200.5));
        list.add(item1);
        list.add(item2);

        Map<String, Object> result = statList(ParkOrderEscapeDO.class, list);
        System.out.println(result); // 输出示例: {sum={escapeAmount=301.0, traceTimes=0.0}, avg={escapeAmount=150.500000, traceTimes=0.000000}}
    }
}
