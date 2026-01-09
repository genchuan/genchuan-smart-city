// DataMapController.java
package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap.vo.CultureDataMapQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap.vo.CultureDataMapRespVO;
import cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.datamap.CultureDataMapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Tag(name = "管理后台 - 文旅数据地图")
@RestController
@RequestMapping("/industry/culture-tourism-data-map")
@RequiredArgsConstructor
public class CultureDataMapController {

    private final CultureDataMapService cultureDataMapService;

    @GetMapping("/get")
    @Operation(summary = "查询文旅数据地图信息")
    public CommonResult<List<CultureDataMapRespVO>> queryDataMap(CultureDataMapQueryReqVO queryVO) {
        return CommonResult.success(cultureDataMapService.getDataMapInfo(queryVO));
    }
}