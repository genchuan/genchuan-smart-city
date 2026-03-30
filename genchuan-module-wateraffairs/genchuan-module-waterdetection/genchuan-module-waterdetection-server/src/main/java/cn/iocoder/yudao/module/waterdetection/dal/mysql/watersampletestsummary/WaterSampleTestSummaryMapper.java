package cn.iocoder.yudao.module.waterdetection.dal.mysql.watersampletestsummary;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Function;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersampletestsummary.WaterSampleTestSummaryDO;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.watersampletestsummary.vo.*;

/**
 * 外检统计水质检测结果汇总 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface WaterSampleTestSummaryMapper extends BaseMapperX<WaterSampleTestSummaryDO> {

    default PageResult<WaterSampleTestSummaryDO> selectPage(WaterSampleTestSummaryPageReqVO reqVO) {
        LambdaQueryWrapperX<WaterSampleTestSummaryDO> wrapper = new LambdaQueryWrapperX<WaterSampleTestSummaryDO>()
                .likeIfPresent(WaterSampleTestSummaryDO::getClientName, reqVO.getClientName())
                .likeIfPresent(WaterSampleTestSummaryDO::getReceiveDate, reqVO.getReceiveDate())
                .likeIfPresent(WaterSampleTestSummaryDO::getSampleNo, reqVO.getSampleNo())
                .likeIfPresent(WaterSampleTestSummaryDO::getSampleName, reqVO.getSampleName())
                .likeIfPresent(WaterSampleTestSummaryDO::getSamplingLocation, reqVO.getSamplingLocation())
                .betweenIfPresent(WaterSampleTestSummaryDO::getCreateTime, reqVO.getCreateTime());

        // 动态排序处理
        if (StringUtils.isNotBlank(reqVO.getSortField())) {
            // 对于数值字段，使用自定义排序SQL
            if (isNumericField(reqVO.getSortField())) {
                String orderByClause = buildDmNumericOrderByClause(reqVO.getSortField(), reqVO.getSortOrder());
                wrapper.last(orderByClause);
            } else {
                // 对于普通字段，使用原来的排序方式
                SFunction<WaterSampleTestSummaryDO, ?> sortField = getSimpleSortField(reqVO.getSortField());
                if ("ASC".equalsIgnoreCase(reqVO.getSortOrder())) {
                    wrapper.orderByAsc(sortField);
                } else {
                    wrapper.orderByDesc(sortField);
                }
            }
        } else {
            // 默认排序
            wrapper.orderByDesc(WaterSampleTestSummaryDO::getId);
        }

        return selectPage(reqVO, wrapper);
    }

    // 判断是否是数值字段
    default boolean isNumericField(String fieldName) {
        Set<String> numericFields = new HashSet<>(Arrays.asList(
                "phValue", "ammoniaN", "dichlorobromomethane", "dichloroaceticAcid",
                "chlorineDioxide", "fluoride", "permanganateIndex", "cadmium",
                "chromium", "mercury", "turbidity", "sulfate", "aluminum",
                "chloride", "chlorate", "manganese", "lead", "cyanide",
                "dissolvedSolids", "chloroform", "trichloroaceticAcid",
                "bromoform", "colorDegree", "arsenic", "iron", "copper",
                "nitrateN", "zinc", "chlorite", "dibromochloromethane",
                "totalAlphaRadioactivity", "totalBetaRadioactivity",
                "totalHardness","longitude", "latitude"
        ));
        return numericFields.contains(fieldName);
    }

    // 达梦数据库专用的数值排序SQL构建方法（全大写列名）
    default String buildDmNumericOrderByClause(String fieldName, String sortOrder) {
        // 将字段名转换为大写形式
        String columnName = convertToDmColumnName(fieldName);

        return "ORDER BY " +
                "CASE " +
                // 处理带±的值（提取±前的数字）
                "WHEN " + columnName + " LIKE '%±%' THEN " +
                "TO_NUMBER(SUBSTR(" + columnName + ", 1, INSTR(" + columnName + ", '±') - 1)) " +
                // 处理其他符号（<＞等）
                "WHEN REGEXP_LIKE(" + columnName + ", '^[<＜>＞=＝]?[0-9]+(\\.[0-9]+)?$') THEN " +
                "TO_NUMBER(REGEXP_REPLACE(" + columnName + ", '[<＜>＞=＝]', '')) " +
                "ELSE NULL END " +
                (StringUtils.equalsIgnoreCase(sortOrder, "ASC") ? "ASC" : "DESC") +
                " NULLS LAST";
    }

    // 将Java字段名转换为达梦数据库列名（全大写）
    default String convertToDmColumnName(String fieldName) {
        // 特殊字段名映射（Java属性名 -> 达梦数据库列名）
        Map<String, String> specialMappings = new HashMap<>();
        // 基本信息字段
        specialMappings.put("clientName", "CLIENT_NAME");
        specialMappings.put("receiveDate", "RECEIVE_DATE");
        specialMappings.put("sampleNo", "SAMPLE_NO");
        specialMappings.put("sampleName", "SAMPLE_NAME");
        specialMappings.put("samplingLocation", "SAMPLING_LOCATION");

        // 物理指标字段
        specialMappings.put("phValue", "PH_VALUE");
        specialMappings.put("odourTaste", "ODOUR_TASTE");
        specialMappings.put("turbidity", "TURBIDITY");
        specialMappings.put("visibleObject", "VISIBLE_OBJECT");
        specialMappings.put("colorDegree", "COLOR_DEGREE");
        specialMappings.put("dissolvedSolids", "DISSOLVED_SOLIDS");
        specialMappings.put("totalHardness", "TOTAL_HARDNESS");

        // 化学指标字段
        specialMappings.put("ammoniaN", "AMMONIA_N");
        specialMappings.put("permanganateIndex", "PERMANGANATE_INDEX");
        specialMappings.put("fluoride", "FLUORIDE");
        specialMappings.put("chloride", "CHLORIDE");
        specialMappings.put("sulfate", "SULFATE");
        specialMappings.put("nitrateN", "NITRATE_N");
        specialMappings.put("chlorate", "CHLORATE");
        specialMappings.put("chlorite", "CHLORITE");

        // 重金属指标字段
        specialMappings.put("aluminum", "ALUMINUM");
        specialMappings.put("iron", "IRON");
        specialMappings.put("manganese", "MANGANESE");
        specialMappings.put("copper", "COPPER");
        specialMappings.put("zinc", "ZINC");
        specialMappings.put("lead", "LEAD");
        specialMappings.put("cadmium", "CADMIUM");
        specialMappings.put("chromium", "CHROMIUM");
        specialMappings.put("arsenic", "ARSENIC");
        specialMappings.put("mercury", "MERCURY");
        specialMappings.put("cyanide", "CYANIDE");

        // 消毒副产物字段
        specialMappings.put("chlorineDioxide", "CHLORINE_DIOXIDE");
        specialMappings.put("trihalomethanes", "TRIHALOMETHANES");
        specialMappings.put("chloroform", "CHLOROFORM");
        specialMappings.put("dichlorobromomethane", "DICHLOROBROMOMETHANE");
        specialMappings.put("dibromochloromethane", "DIBROMOCHLOROMETHANE");
        specialMappings.put("bromoform", "BROMOFORM");
        specialMappings.put("dichloroaceticAcid", "DICHLOROACETIC_ACID");
        specialMappings.put("trichloroaceticAcid", "TRICHLOROACETIC_ACID");

        // 微生物指标字段
        specialMappings.put("totalColiform", "TOTAL_COLIFORM");
        specialMappings.put("escherichiaColi", "ESCHERICHIA_COLI");
        specialMappings.put("totalBacteriaCount", "TOTAL_BACTERIA_COUNT");

        // 放射性指标字段
        specialMappings.put("totalAlphaRadioactivity", "TOTAL_ALPHA_RADIOACTIVITY");
        specialMappings.put("totalBetaRadioactivity", "TOTAL_BETA_RADIOACTIVITY");
        specialMappings.put("longitude", "LONGITUDE");
        specialMappings.put("latitude", "LATITUDE");
        if (specialMappings.containsKey(fieldName)) {
            return specialMappings.get(fieldName);
        }

        // 通用转换：驼峰转大写加下划线
        return fieldName.replaceAll("([a-z])([A-Z])", "$1_$2").toUpperCase();
    }

    // 获取普通字段的排序方式
    default SFunction<WaterSampleTestSummaryDO, ?> getSimpleSortField(String fieldName) {
        switch (fieldName) {
            case "id": return WaterSampleTestSummaryDO::getId;
            case "clientName": return WaterSampleTestSummaryDO::getClientName;
            case "receiveDate": return WaterSampleTestSummaryDO::getReceiveDate;
            case "sampleNo": return WaterSampleTestSummaryDO::getSampleNo;
            case "sampleName": return WaterSampleTestSummaryDO::getSampleName;
            case "samplingLocation": return WaterSampleTestSummaryDO::getSamplingLocation;
            case "createTime": return WaterSampleTestSummaryDO::getCreateTime;
            case "updateTime": return WaterSampleTestSummaryDO::getUpdateTime;
            case "longitude": return WaterSampleTestSummaryDO::getLongitude;
            case "latitude": return WaterSampleTestSummaryDO::getLatitude;
            default: return WaterSampleTestSummaryDO::getId;
        }
    }
    default List<WaterSampleTestSummaryDO> selectListBySampleName(String sampleName) {
        return selectList(new LambdaQueryWrapperX<WaterSampleTestSummaryDO>().like(WaterSampleTestSummaryDO::getSampleName, sampleName));
    }

}