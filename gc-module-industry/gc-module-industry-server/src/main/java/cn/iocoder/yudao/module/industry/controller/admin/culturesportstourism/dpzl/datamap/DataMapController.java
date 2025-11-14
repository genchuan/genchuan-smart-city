// DataMapController.java
package cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap.vo.DataMapQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.culturesportstourism.dpzl.datamap.vo.DataMapRespVO;
import cn.iocoder.yudao.module.industry.service.culturesportstourism.dpzl.datamap.DataMapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Tag(name = "管理后台 - 文旅数据地图")
@RestController
@RequestMapping("/industry/datamap")
@RequiredArgsConstructor
public class DataMapController {

    private final DataMapService dataMapService;

    @GetMapping("/query")
    @Operation(summary = "查询文旅数据地图信息")
    public CommonResult<List<DataMapRespVO>> queryDataMap(DataMapQueryReqVO queryVO) {
        return CommonResult.success(dataMapService.getDataMapInfo(queryVO));
    }
}