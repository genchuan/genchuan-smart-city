package cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegalleveldict;

import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegalleveldict.vo.IllegalLevelDictPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegalleveldict.vo.IllegalLevelDictRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegalleveldict.vo.IllegalLevelDictSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.illegalleveldict.IllegalLevelDictDO;
import cn.iocoder.yudao.module.kitchen.service.dictionary.illegalleveldict.IllegalLevelDictService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;


@Tag(name = "管理后台 - 违规等级字典")
@RestController
@RequestMapping("/kitchen/illegal-level-dict")
@Validated
public class IllegalLevelDictController {

    @Resource
    private IllegalLevelDictService illegalLevelDictService;

    @PostMapping("/create")
    @Operation(summary = "创建违规等级字典")
    @PreAuthorize("@ss.hasPermission('kitchen:illegal-level-dict:create')")
    public CommonResult<Long> createIllegalLevelDict(@Valid @RequestBody IllegalLevelDictSaveReqVO createReqVO) {
        return success(illegalLevelDictService.createIllegalLevelDict(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新违规等级字典")
    @PreAuthorize("@ss.hasPermission('kitchen:illegal-level-dict:update')")
    public CommonResult<Boolean> updateIllegalLevelDict(@Valid @RequestBody IllegalLevelDictSaveReqVO updateReqVO) {
        illegalLevelDictService.updateIllegalLevelDict(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除违规等级字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('kitchen:illegal-level-dict:delete')")
    public CommonResult<Boolean> deleteIllegalLevelDict(@RequestParam("id") Long id) {
        illegalLevelDictService.deleteIllegalLevelDict(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得违规等级字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('kitchen:illegal-level-dict:query')")
    public CommonResult<IllegalLevelDictRespVO> getIllegalLevelDict(@RequestParam("id") Long id) {
        IllegalLevelDictDO illegalLevelDict = illegalLevelDictService.getIllegalLevelDict(id);
        return success(BeanUtils.toBean(illegalLevelDict, IllegalLevelDictRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得违规等级字典分页")
    @PreAuthorize("@ss.hasPermission('kitchen:illegal-level-dict:query')")
    public CommonResult<PageResult<IllegalLevelDictRespVO>> getIllegalLevelDictPage(@Valid IllegalLevelDictPageReqVO pageReqVO) {
        PageResult<IllegalLevelDictDO> pageResult = illegalLevelDictService.getIllegalLevelDictPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, IllegalLevelDictRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出违规等级字典 Excel")
    @PreAuthorize("@ss.hasPermission('kitchen:illegal-level-dict:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportIllegalLevelDictExcel(@Valid IllegalLevelDictPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<IllegalLevelDictDO> list = illegalLevelDictService.getIllegalLevelDictPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "违规等级字典.xls", "数据", IllegalLevelDictRespVO.class,
                        BeanUtils.toBean(list, IllegalLevelDictRespVO.class));
    }

}
