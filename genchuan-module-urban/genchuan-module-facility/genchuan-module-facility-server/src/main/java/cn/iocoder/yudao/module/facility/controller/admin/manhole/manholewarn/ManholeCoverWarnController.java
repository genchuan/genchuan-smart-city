package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo.ManholeCoverWarnPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo.ManholeCoverWarnPageRespVO;
import cn.iocoder.yudao.module.facility.service.manhole.manholewarn.ManholeCoverWarnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("manhole/warn")
@Tag(name = "窨井盖监测 - 预警处理管理")
public class ManholeCoverWarnController {

    @Resource
    private ManholeCoverWarnService manholeCoverWarnService;

    @GetMapping("/page")
    @Operation(summary = "窨井盖预警数据分页查询", description = "分页查询窨井盖各类预警信息，支持按井盖、区域、类型、状态、时间筛选")
    public CommonResult<PageResult<ManholeCoverWarnPageRespVO>> pageWarn(@Validated ManholeCoverWarnPageReqVO reqVO) {
        return CommonResult.success(manholeCoverWarnService.getWarnPage(reqVO));
    }
}
