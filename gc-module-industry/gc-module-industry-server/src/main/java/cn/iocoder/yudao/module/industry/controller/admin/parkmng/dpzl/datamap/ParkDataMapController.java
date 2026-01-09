package cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.datamap;

import cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.datamap.vo.ParkDataMapQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.datamap.vo.ParkDataMapRespVO;
import cn.iocoder.yudao.module.industry.service.parkmng.dpzl.datamap.ParkDataMapService;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 停车管理数据地图")
@RestController
@RequestMapping("/industry/park-mng-data-map")
@Validated
public class ParkDataMapController {

    @Resource
    private ParkDataMapService dataMapService;

    @GetMapping("/list")
    @Operation(summary = "查询停车数据地图列表")
    public CommonResult<List<ParkDataMapRespVO>> getDataMapList(@Valid ParkDataMapQueryReqVO queryReqVO) {
        List<ParkDataMapRespVO> list = dataMapService.getDataMapList(queryReqVO);
        return success(list);
    }

    @GetMapping("/get")
    @Operation(summary = "根据ID查询停车数据地图详情")
    public CommonResult<ParkDataMapRespVO> getDataMapById(@RequestParam("id") Long id) {
        ParkDataMapRespVO dataMap = dataMapService.getDataMapById(id);
        return success(dataMap);
    }

}