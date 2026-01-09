package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.point;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.point.vo.ScenePointQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.point.vo.ScenePointRespVO;

import cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.point.ScenePointService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 场景大屏3-场景点位数据")
@RestController
@RequestMapping("/industry/scene_point")
@Validated
public class ScenePointController {
    @Resource
    private ScenePointService scenePointService;

//    @GetMapping("/get")
//    @Operation(summary = "获得场景点位数据")
//    @PreAuthorize("@ss.hasPermission('industry:scene_point:query')")
//    public CommonResult<ScenePointRespVO> getScenePoint(
//            @Valid ScenePointQueryReqVO scenePointQueryReqVO
//    ) {
//        ScenePointRespVO scenePointRespVO = scenePointService.getScenePoint(scenePointQueryReqVO);
//        return success(scenePointRespVO);
//    }

    @GetMapping("/list")
    @Operation(summary = "获得场景点位数据列表-通过场景id")
    @PreAuthorize("@ss.hasPermission('industry:scene_point_list_scene:list')")
    public CommonResult<List<ScenePointRespVO>> listScenePointBySceneId(
            @Valid ScenePointQueryReqVO scenePointQueryReqVO
    ) {
        List<ScenePointRespVO> scenePointRespVOList = scenePointService.listScenePointBySceneId(scenePointQueryReqVO);
        return success(scenePointRespVOList);
    }

//    @GetMapping("/map/{sceneKey}")
//    @Operation(summary = "获取场景地图点位数据")
//    @PreAuthorize("@ss.hasPermission('industry:scene_point_list_scene:list')")
//    public CommonResult<Map<String, Object>> getSceneMapData(
//            @PathVariable("sceneKey") String sceneKey) {
//
//        ScenePointQueryReqVO queryVO = new ScenePointQueryReqVO();
//        queryVO.setSceneCode(sceneKey);
//
//        List<ScenePointRespVO> points = scenePointService.listScenePointBySceneId(queryVO);
//
//        Map<String, Object> data = new HashMap<>();
//        data.put("list", points);
//
//        return success(data);
//
//
//    }


    @GetMapping("/map/{sceneKey}")
    @Operation(summary = "3.获取场景地图数据接口")
    @PreAuthorize("@ss.hasPermission('industry:scene_point_list_scene:list')")
    public CommonResult<Map<String, Object>> getSceneMapDataMap(
            @PathVariable("sceneKey") String sceneKey) {

        ScenePointQueryReqVO queryVO = new ScenePointQueryReqVO();
        queryVO.setSceneCode(sceneKey);

        List<Map<String, Object>> points = scenePointService.listScenePointBySceneIdMap(queryVO);

        Map<String, Object> data = new HashMap<>();
        data.put("list", points);

        return success(data);
    }

}
