package cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.point;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.base.UniversalSceneDO;
import cn.iocoder.yudao.module.industry.dal.mysql.universal.dashboard.scene.base.UniversalSceneMapper;
import cn.iocoder.yudao.module.industry.service.emergency.dashboard.global.riskview.EmergRiskViewService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

@Service
public class ScenePointDispService {

    @Resource
    private UniversalSceneMapper universalSceneMapper;

    @Resource
    private EmergRiskViewService emergRiskViewService;

    // TODO: 注入其他场景对应的 Service，例如 factoryPointService、schoolPointService、hospitalPointService

    /**
     * 根据 sceneId 获取点位数据
     */
    public List<Object> getScenePointDataBySceneId(Long sceneId) {
        if (sceneId == null) {
            throw exception(new ErrorCode(400, "场景ID不能为空"));
        }

        // 1. 查询场景信息
        UniversalSceneDO scene = universalSceneMapper.selectOne(
                new LambdaQueryWrapper<UniversalSceneDO>()
                        .eq(UniversalSceneDO::getSceneId, sceneId)
        );

        if (scene == null) {
            throw exception(new ErrorCode(400, "该场景不存在"));
        }

        // 2. 根据场景类型分发到对应 Service
        if (sceneId == 1) {
            return listEmergRiskViewDemo(sceneId);
        } else if (sceneId == 2) {
            return getFactoryPoints(sceneId);
        } else if (sceneId == 3) {
            return getSchoolPoints(sceneId);
        } else if (sceneId == 4) {
            return getHospitalPoints(sceneId);
        }// 通用场景获取逻辑
        return getGenericPoints(sceneId);
    }

    // ----------------- 各个场景获取方法 -----------------

    private List<Object> getFactoryPoints(Long sceneId) {
        // TODO: 调用 factoryPointService 获取点位数据
        return List.of(); // 示例空数据
    }

    private List<Object> getSchoolPoints(Long sceneId) {
        // TODO: 调用 schoolPointService 获取点位数据
        return List.of(); // 示例空数据
    }

    private List<Object> getHospitalPoints(Long sceneId) {
        // TODO: 调用 hospitalPointService 获取点位数据
        return List.of(); // 示例空数据
    }

    private List<Object> listEmergRiskViewDemo(Long sceneId) {
        // 示例演示数据
        Map<String, Object> point1 = new HashMap<>();
        point1.put("resId", "RES20251106001");
        point1.put("resName", "消防泵");
        point1.put("resType", "应急物资");
        point1.put("totalQty", 100);
        point1.put("availableQty", 80);
        point1.put("storageLoc", "仓库A区");
        point1.put("mngrName", "张三");
        point1.put("stockStatus", "充足");
        point1.put("updateTime", "2025-11-19 10:00:00");

        return List.of(point1);
    }

    private List<Object> getGenericPoints(Long sceneId) {
        // TODO: 通用场景点位获取逻辑
        return List.of(); // 示例空数据
    }
}
