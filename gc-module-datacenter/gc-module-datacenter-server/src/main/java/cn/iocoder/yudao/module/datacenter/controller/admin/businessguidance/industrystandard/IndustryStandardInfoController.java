package cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.industrystandard;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.industrystandard.IndustryStandardInfoDO;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.industrystandard.vo.*;
import cn.iocoder.yudao.module.datacenter.service.businessguidance.industrystandard.IndustryStandardInfoService;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 管理后台 - 行业规范信息 Controller
 */
@Tag(name = "管理后台 - 行业规范信息")
@RestController
@RequestMapping("/datacenter/industry-standard-info")
public class IndustryStandardInfoController {

    @Resource
    private IndustryStandardInfoService industryStandardInfoService;

    @PostMapping("/create")
    @Operation(summary = "创建行业规范信息")
    public CommonResult<Long> createIndustryStandardInfo(@Valid @RequestBody IndustryStandardInfoCreateReqVO createReqVO) {
        return success(industryStandardInfoService.createIndustryStandardInfo(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新行业规范信息")
    public CommonResult<Boolean> updateIndustryStandardInfo(@Valid @RequestBody IndustryStandardInfoUpdateReqVO updateReqVO) {
        industryStandardInfoService.updateIndustryStandardInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除行业规范信息")
    public CommonResult<Boolean> deleteIndustryStandardInfo(@RequestParam("id") Long id) {
        industryStandardInfoService.deleteIndustryStandardInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得行业规范信息")
    public CommonResult<IndustryStandardInfoRespVO> getIndustryStandardInfo(@RequestParam("id") Long id) {
        return success(convert(industryStandardInfoService.getIndustryStandardInfo(id)));
    }

    @GetMapping("/page")
    @Operation(summary = "获得行业规范信息分页")
    public CommonResult<PageResult<IndustryStandardInfoRespVO>> getIndustryStandardInfoPage(@Valid IndustryStandardInfoPageReqVO pageReqVO) {
        PageResult<IndustryStandardInfoDO> pageResult = industryStandardInfoService.getIndustryStandardInfoPage(pageReqVO);
        return success(new PageResult<>(convertList(pageResult.getList()), pageResult.getTotal()));
    }

    @GetMapping("/list")
    @Operation(summary = "获得行业规范信息列表")
    public CommonResult<List<IndustryStandardInfoRespVO>> getIndustryStandardInfoList() {
        List<IndustryStandardInfoDO> list = industryStandardInfoService.getIndustryStandardInfoList();
        return success(convertList(list));
    }

    // 转换方法
    private IndustryStandardInfoRespVO convert(IndustryStandardInfoDO industryStandardInfo) {
        if (industryStandardInfo == null) return null;
        IndustryStandardInfoRespVO respVO = new IndustryStandardInfoRespVO();
        // 字段赋值...
        return respVO;
    }

    private List<IndustryStandardInfoRespVO> convertList(List<IndustryStandardInfoDO> list) {
        return list.stream().map(this::convert).collect(java.util.stream.Collectors.toList());
    }
}