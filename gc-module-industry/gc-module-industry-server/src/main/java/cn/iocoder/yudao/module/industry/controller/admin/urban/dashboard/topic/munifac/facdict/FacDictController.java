package cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.facdict;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;

import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.facdict.vo.FacDictQueryReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.urban.dashboard.topic.munifac.facdict.vo.FacDictRespVO;

import cn.iocoder.yudao.module.industry.service.urban.dashboard.topic.munifac.facdict.FacDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 市政设施专题-设施类型字典")
@RestController
@RequestMapping("/industry/fac-dict")
@Validated
public class FacDictController {
    @Resource
    private FacDictService facDictService;

    @GetMapping("/get")
    @Operation(summary = "获得市政设施专题-设施类型字典")
    @PreAuthorize("@ss.hasPermission('industry:fac-dict:query')")
    public CommonResult<FacDictRespVO> getFacDict(
            @Valid FacDictQueryReqVO facDictQueryReqVO
    ) {
        FacDictRespVO facDictRespVO = facDictService.getFacDict(facDictQueryReqVO);
        return success(facDictRespVO);
    }
}
