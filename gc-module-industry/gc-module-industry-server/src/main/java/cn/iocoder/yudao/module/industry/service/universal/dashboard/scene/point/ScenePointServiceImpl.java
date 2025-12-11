package cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.point;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.field.vo.SceneFieldRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.point.vo.ExclusiveField;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.point.vo.ScenePointQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.point.vo.ScenePointRespVO;

import cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.point.ScenePointMapper;
import cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.field.SceneFieldService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 场景点位数据 Service 实现类
 * <p>
 * 功能说明：
 * 1. 实现 ScenePointService 接口中的业务逻辑方法
 * 2. 调用对应的 Mapper 进行数据库查询
 * 3. 提供统一的 Service 层接口给 Controller 使用
 */
@Service
@Validated
public class ScenePointServiceImpl implements ScenePointService {

    // 注入对应的 Mapper 对象，用于数据库操作
    @Resource
    private ScenePointMapper scenePointMapper;

    @Resource
    private SceneFieldService sceneFieldService;

    @Resource
    private ScenePointDispService scenePointDispService;

    /**
     * 查询场景点位数据数据
     *
     * @param scenePointQueryReqVO 查询条件 VO 对象
     * @return ScenePointRespVO 查询结果 VO 对象
     */
    @Override
    public ScenePointRespVO getScenePoint(ScenePointQueryReqVO scenePointQueryReqVO) {

        Long sceneId = scenePointQueryReqVO.getSceneId();
        if (sceneId == null) {
            throw exception(new ErrorCode(400, "场景ID不能为空"));
        }

        // ================================
        // 1. 获取该场景的“专属字段名称列表”exclusiveFieldNameList
        // ================================

        List<SceneFieldRespVO> SceneFieldList = sceneFieldService.listFiledBySceneId(sceneId);
        // 将 SceneFieldList 中每个对象的 label 字段提取出来，组成新的字符串列表List<String> exclusiveFieldNameList
        List<String> exclusiveFieldNameList = SceneFieldList.stream()
                .map(SceneFieldRespVO::getLabel)  // 提取 label 字段
                .collect(Collectors.toList());     // 收集成 List

        if (exclusiveFieldNameList == null || exclusiveFieldNameList.isEmpty()) {
            exclusiveFieldNameList = Collections.emptyList();
        }


        // ================================
        //2.遍历exclusiveFieldNameList的每个项（专属字段名称），把每个项赋值给 对应的一个 ExclusiveField 的name
        //将最终的 全部 ExclusiveField 收集成 ExclusiveFieldList
        // ================================
        List<ExclusiveField> exclusiveFieldList = new ArrayList<>();
        for (String fieldName : exclusiveFieldNameList) {
            ExclusiveField ef = new ExclusiveField();
            ef.setName(fieldName); // 这里只设置 name
            exclusiveFieldList.add(ef);
        }



        // ================================
        //3.遍历业务数据bizDataList 的 每个项 bizData 中 字段名 和 ExclusiveField 的name相同的 ，
        // 将bizData该字段名的值赋值给 ExclusiveField 的 value
        // ================================
        // 将 mapper 查询结果（Map 结构：字段名 → 字段值）
        List<Object> bizDataList = scenePointDispService.getScenePointDataBySceneId(sceneId);
        ObjectMapper objectMapper = new ObjectMapper();

        // 转成 List<Map<String,Object>>
        List<Map<String, Object>> bizDataMapList = bizDataList.stream()
                .map(obj -> objectMapper.convertValue(obj, new TypeReference<Map<String, Object>>() {}))
                .collect(Collectors.toList());


        if (bizDataMapList == null||bizDataMapList.isEmpty()) {
            throw exception(new ErrorCode(404, "点位数据不存在"));
        }

        // 将 bizData 中的值注入到 ExclusiveField.value
        for (ExclusiveField ef : exclusiveFieldList) {
            String key = ef.getName(); // 专属字段名 = field_id

            if (bizDataMapList.get(0).containsKey(key)) {
                ef.setValue(bizDataMapList.get(0).get(key)); // 给 value 赋实际业务数据值
            } else {
                ef.setValue(null); // 若业务数据中无该字段，设为 null
            }
        }

        // ================================
        // 4. 构造 ScenePointRespVO ，
        // 配置通用字段，然后将 ExclusiveFieldList 赋值给 ScenePointRespVO的 exclusiveFieldList
        // ================================
        ScenePointRespVO respVO = new ScenePointRespVO();

        // 通用字段赋值（lat、lng、compName）
        respVO.setLat(convertDouble(bizDataMapList.get(0).get("lat")));
        respVO.setLng(convertDouble(bizDataMapList.get(0).get("lng")));
        respVO.setCompName((String) bizDataMapList.get(0).get("comp_name"));

        // 设置专属字段列表
        respVO.setExclusiveFieldList(exclusiveFieldList);

        return respVO;
    }

    @Override
    public List<ScenePointRespVO> listScenePointBySceneId(ScenePointQueryReqVO scenePointQueryReqVO) {

        Long sceneId = scenePointQueryReqVO.getSceneId();
        if (sceneId == null) {
            throw exception(new ErrorCode(400, "场景ID不能为空"));
        }

        // ================================
        // 1. 获取该场景的“专属字段名称列表”exclusiveFieldNameList
        // ================================

        List<SceneFieldRespVO> SceneFieldList = sceneFieldService.listFiledBySceneId(sceneId);
        // 将 SceneFieldList 中每个对象的 label 字段提取出来，组成新的字符串列表List<String> exclusiveFieldNameList
        List<String> exclusiveFieldNameList = SceneFieldList.stream()
                .map(SceneFieldRespVO::getLabel)  // 提取 label 字段
                .collect(Collectors.toList());     // 收集成 List

        if (exclusiveFieldNameList == null || exclusiveFieldNameList.isEmpty()) {
            exclusiveFieldNameList = Collections.emptyList();
        }

        // 2. 获取业务点位数据列表List<Object> bizDataList
        List<Object> bizDataList = scenePointDispService.getScenePointDataBySceneId(sceneId);
        if (bizDataList == null || bizDataList.isEmpty()) {
            throw exception(new ErrorCode(404, "点位数据不存在"));
        }

        ObjectMapper objectMapper = new ObjectMapper();

        // 3. 将List<Object> bizDataList 转成 List<Map<String,Object>> 并封装每个点位
        List<ScenePointRespVO> respList = new ArrayList<>();
        for (Object obj : bizDataList) {
            Map<String, Object> bizData = objectMapper.convertValue(obj, new TypeReference<Map<String, Object>>() {});

            // 3.1 构造 ExclusiveFieldList
            List<ExclusiveField> exclusiveFieldList = new ArrayList<>();
            for (String fieldName : exclusiveFieldNameList) {
                ExclusiveField ef = new ExclusiveField();
                ef.setName(fieldName);
                ef.setValue(bizData.getOrDefault(fieldName, null));
                exclusiveFieldList.add(ef);
            }

            // 3.2 构造 ScenePointRespVO
            ScenePointRespVO respVO = new ScenePointRespVO();
            respVO.setLat(convertDouble(bizData.get("lat")));
            respVO.setLng(convertDouble(bizData.get("lng")));
            respVO.setCompName((String) bizData.get("comp_name"));
            respVO.setExclusiveFieldList(exclusiveFieldList);

            respList.add(respVO);
        }

        return respList;
    }

    /**
     * 将 Object 转 Double，确保安全转换
     */
    private Double convertDouble(Object o) {
        if (o == null) return null;
        if (o instanceof Number) return ((Number) o).doubleValue();
        return Double.parseDouble(o.toString());
    }
}
