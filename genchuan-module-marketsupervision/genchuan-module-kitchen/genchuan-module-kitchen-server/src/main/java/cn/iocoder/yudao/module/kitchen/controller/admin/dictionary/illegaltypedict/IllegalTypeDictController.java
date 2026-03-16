package cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegaltypedict;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegaltypedict.vo.IllegalTypeDictPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegaltypedict.vo.IllegalTypeDictRespVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.illegaltypedict.vo.IllegalTypeDictSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.illegaltypedict.IllegalTypeDictDO;
import cn.iocoder.yudao.module.kitchen.service.dictionary.illegaltypedict.IllegalTypeDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 违规类型字典")
@RestController
@RequestMapping("/kitchen/illegal-type-dict")
@Validated
public class IllegalTypeDictController {

    @Resource
    private IllegalTypeDictService illegalTypeDictService;

    @PostMapping("/create")
    @Operation(summary = "创建违规类型字典")
    @PreAuthorize("@ss.hasPermission('kitchen:illegal-type-dict:create')")
    public CommonResult<Long> createIllegalTypeDict(@Valid @RequestBody IllegalTypeDictSaveReqVO createReqVO) {
        return success(illegalTypeDictService.createIllegalTypeDict(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新违规类型字典")
    @PreAuthorize("@ss.hasPermission('kitchen:illegal-type-dict:update')")
    public CommonResult<Boolean> updateIllegalTypeDict(@Valid @RequestBody IllegalTypeDictSaveReqVO updateReqVO) {
        illegalTypeDictService.updateIllegalTypeDict(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除违规类型字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('kitchen:illegal-type-dict:delete')")
    public CommonResult<Boolean> deleteIllegalTypeDict(@RequestParam("id") Long id) {
        illegalTypeDictService.deleteIllegalTypeDict(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得违规类型字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('kitchen:illegal-type-dict:query')")
    public CommonResult<IllegalTypeDictRespVO> getIllegalTypeDict(@RequestParam("id") Long id) {
        IllegalTypeDictDO illegalTypeDict = illegalTypeDictService.getIllegalTypeDict(id);
        return success(BeanUtils.toBean(illegalTypeDict, IllegalTypeDictRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得违规类型字典分页")
    @PreAuthorize("@ss.hasPermission('kitchen:illegal-type-dict:query')")
    public CommonResult<PageResult<IllegalTypeDictRespVO>> getIllegalTypeDictPage(@Valid IllegalTypeDictPageReqVO pageReqVO) {
        PageResult<IllegalTypeDictDO> pageResult = illegalTypeDictService.getIllegalTypeDictPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, IllegalTypeDictRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出违规类型字典 Excel")
    @PreAuthorize("@ss.hasPermission('kitchen:illegal-type-dict:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportIllegalTypeDictExcel(@Valid IllegalTypeDictPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<IllegalTypeDictDO> list = illegalTypeDictService.getIllegalTypeDictPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "违规类型字典.xls", "数据", IllegalTypeDictRespVO.class,
                        BeanUtils.toBean(list, IllegalTypeDictRespVO.class));
    }

}
