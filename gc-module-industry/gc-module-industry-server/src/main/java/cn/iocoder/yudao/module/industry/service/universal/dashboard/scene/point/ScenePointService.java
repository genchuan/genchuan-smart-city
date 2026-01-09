package cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.point;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.point.vo.ScenePointQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.point.vo.ScenePointRespVO;

import java.util.List;
import java.util.Map;

/**
 * 场景点位数据 Service 接口
 *
 */
public interface ScenePointService {

    /**
     * 查询场景点位数据
     *
     * @param scenePointQueryReqVO 查询条件
     * @return 查询结果
     */
//    ScenePointRespVO getScenePoint(ScenePointQueryReqVO scenePointQueryReqVO);

    List<ScenePointRespVO> listScenePointBySceneId(ScenePointQueryReqVO scenePointQueryReqVO);

    List<Map<String, Object>> listScenePointBySceneIdMap(ScenePointQueryReqVO queryVO);
}
