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
    public List<Object> getScenePointDataBySceneId(String sceneCode) {
        if (sceneCode == null) {
            throw exception(new ErrorCode(400, "场景ID不能为空"));
        }

        // 1. 查询场景信息
        UniversalSceneDO scene = universalSceneMapper.selectOne(
                new LambdaQueryWrapper<UniversalSceneDO>()
                        .eq(UniversalSceneDO::getSceneCode, sceneCode)
        );

        if (scene == null) {
            throw exception(new ErrorCode(400, "该场景不存在"));
        }

// 2. 根据场景类型分发到对应 Service
        if ("1".equals(sceneCode)) {
            return listEmergRiskViewDemo(sceneCode);
        } else if ("2".equals(sceneCode)) {
            return getFactoryPoints(sceneCode);
        } else if ("3".equals(sceneCode)) {
            return getSchoolPoints(sceneCode);
        } else if ("4".equals(sceneCode)) {
            return getHospitalPoints(sceneCode);
        }
        return getGenericPoints(sceneCode);
    }

    // ----------------- 各个场景获取方法 -----------------

    private List<Object> getFactoryPoints(String sceneCode) {
        // TODO: 调用 factoryPointService 获取点位数据
        return List.of(); // 示例空数据
    }

    private List<Object> getSchoolPoints(String sceneCode) {
        // TODO: 调用 schoolPointService 获取点位数据
        return List.of(); // 示例空数据
    }

    private List<Object> getHospitalPoints(String sceneCode) {
        // TODO: 调用 hospitalPointService 获取点位数据
        return List.of(); // 示例空数据
    }

    private List<Object> listEmergRiskViewDemo(String sceneCode) {
        // 示例演示数据
        Map<String, Object> point1 = new HashMap<>();
        point1.put("lat", 26.855237);
        point1.put("lng", 118.001000);
        point1.put("pointName", "市政路灯001");
        point1.put("facilityType", "路灯");
        point1.put("maintainCycle", 90);
        point1.put("deviceStatus", "正常");

        Map<String, Object> point2 = new HashMap<>();
        point2.put("lat", 26.856123);
        point2.put("lng", 118.002345);
        point2.put("pointName", "市政路灯002");
        point2.put("facilityType", "路灯");
        point2.put("maintainCycle", 90);
        point2.put("deviceStatus", "异常");

        Map<String, Object> point3 = new HashMap<>();
        point3.put("lat", 26.857890);
        point3.put("lng", 118.003210);
        point3.put("pointName", "市政路灯003");
        point3.put("facilityType", "路灯");
        point3.put("maintainCycle", 90);
        point3.put("deviceStatus", "维修中");

        Map<String, Object> point4 = new HashMap<>();
        point4.put("lat", 26.858456);
        point4.put("lng", 118.004567);
        point4.put("pointName", "市政路灯004");
        point4.put("facilityType", "路灯");
        point4.put("maintainCycle", 90);
        point4.put("deviceStatus", "正常");

        Map<String, Object> point5 = new HashMap<>();
        point5.put("lat", 26.859321);
        point5.put("lng", 118.005678);
        point5.put("pointName", "市政路灯005");
        point5.put("facilityType", "路灯");
        point5.put("maintainCycle", 90);
        point5.put("deviceStatus", "异常");

        return List.of(point1, point2, point3, point4, point5);
    }

    private List<Object> getGenericPoints(String sceneCode) {
        // TODO: 通用场景点位获取逻辑
        return List.of(); // 示例空数据
    }

}
