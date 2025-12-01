// ParkResDistriController.java
package cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.resourcedistribution;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.resourcedistribution.vo.ParkResDistriQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.parkmng.dpzl.resourcedistribution.vo.ParkResDistriRespVO;
import cn.iocoder.yudao.module.industry.service.parkmng.dpzl.resourcedistribution.ParkResDistriService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import javax.validation.Valid;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 停车资源分布")
@RestController
@RequestMapping("/industry/park-res-distri")
@Validated
public class ParkResDistriController {

    @Resource
    private ParkResDistriService parkResDistriService;

    @GetMapping("/list")
    @Operation(summary = "获取停车资源分布列表")
    public CommonResult<List<ParkResDistriRespVO>> getParkResDistriList(@Valid ParkResDistriQueryReqVO queryReqVO) {
        return success(parkResDistriService.getParkResDistriList(queryReqVO));
    }


}