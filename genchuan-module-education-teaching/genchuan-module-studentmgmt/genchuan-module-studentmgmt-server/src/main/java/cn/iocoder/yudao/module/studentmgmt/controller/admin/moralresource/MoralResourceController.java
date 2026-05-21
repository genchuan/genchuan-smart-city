package cn.iocoder.yudao.module.studentmgmt.controller.admin.moralresource;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.biz.system.dict.dto.DictDataRespDTO;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.moralresource.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.moralresource.MoralResourceDO;
import cn.iocoder.yudao.module.studentmgmt.enums.StudentMgmtDictTypeEnum;
import cn.iocoder.yudao.module.studentmgmt.service.moralresource.MoralResourceService;
import cn.iocoder.yudao.module.system.api.dict.DictDataApi;
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

@Tag(name = "学生管理后台 - 德育资源")
@RestController
@RequestMapping("/studentmgmt/moral-resource")
@Validated
public class MoralResourceController {

    @Resource
    private MoralResourceService moralResourceService;
    @Resource
    private DictDataApi dictDataApi;
    @PostMapping("/create")
    @Operation(summary = "创建德育资源")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:create')")
    public CommonResult<Long> createMoralResource(@Valid @RequestBody MoralResourceSaveReqVO createReqVO) {
        return success(moralResourceService.createMoralResource(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新德育资源")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:update')")
    public CommonResult<Boolean> updateMoralResource(@Valid @RequestBody MoralResourceSaveReqVO updateReqVO) {
        moralResourceService.updateMoralResource(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除德育资源")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:delete')")
    public CommonResult<Boolean> deleteMoralResource(@RequestParam("id") Long id) {
        moralResourceService.deleteMoralResource(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除德育资源")
                @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:delete')")
    public CommonResult<Boolean> deleteMoralResourceList(@RequestParam("ids") List<Long> ids) {
        moralResourceService.deleteMoralResourceListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得德育资源")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:query')")
    public CommonResult<MoralResourceRespVO> getMoralResource(@RequestParam("id") Long id) {
        MoralResourceDO moralResource = moralResourceService.getMoralResource(id);
        return success(BeanUtils.toBean(moralResource, MoralResourceRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得德育资源分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:query')")
    public CommonResult<PageResult<MoralResourceRespVO>> getMoralResourcePage(@Valid MoralResourcePageReqVO pageReqVO) {
        PageResult<MoralResourceDO> pageResult = moralResourceService.getMoralResourcePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MoralResourceRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出德育资源 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMoralResourceExcel(@Valid MoralResourcePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MoralResourceDO> list = moralResourceService.getMoralResourcePage(pageReqVO).getList();
        CommonResult<List<DictDataRespDTO>> typeDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.MORAL_RESOURCE_RESOURCE_TYPE.getType());
        CommonResult<List<DictDataRespDTO>> statusDictDataList = dictDataApi.getDictDataList(StudentMgmtDictTypeEnum.MORAL_RESOURCE_STATUS.getType());
        list = list.stream().map(item -> {
            String resourceType = item.getResourceType();
            if (typeDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : typeDictDataList.getData()) {
                    if (dictData.getValue().equals(resourceType)) {
                        resourceType = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setResourceType(resourceType);
            String status = item.getStatus();
            if (statusDictDataList.getData() != null) {
                for (DictDataRespDTO dictData : statusDictDataList.getData()) {
                    if (dictData.getValue().equals(status)) {
                        status = dictData.getLabel();
                        break;
                    }
                }
            }
            item.setStatus(status);
            return item;
        }).toList();
        // 导出 Excel
        ExcelUtils.write(response, "德育资源.xls", "数据", MoralResourceRespVO.class,
                        BeanUtils.toBean(list, MoralResourceRespVO.class));
    }

    @PutMapping("/online")
    @Operation(summary = "上架")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:online')")
    public CommonResult<Boolean> online(@Valid @RequestBody MoralResourceOnlineReqVO reqVO) {
        boolean isSuccess = moralResourceService.online(reqVO);
        return success(isSuccess);
    }

    @PutMapping("/offline")
    @Operation(summary = "下架")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:offline')")
    public CommonResult<Boolean> offline(@Valid @RequestBody MoralResourceOnlineReqVO reqVO) {
        boolean isSuccess = moralResourceService.offline(reqVO);
        return success(isSuccess);
    }

    @GetMapping("/chart")
    @Operation(summary = "德育资源学习看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:query')")
    public CommonResult<MoralResourceChartRespVO> chart(@Valid MoralResourceChartReqVO reqVO) {
        MoralResourceChartRespVO vo = moralResourceService.chart(reqVO);
        return success(vo);
    }

    @GetMapping("/chart/resourceCount")
    @Operation(summary = "资源类型 / 学习完成率统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-resource:query')")
    public CommonResult<ChartResourceCountRespVO> resourceCount(@Valid MoralResourceChartReqVO reqVO) {
        ChartResourceCountRespVO vo = moralResourceService.resourceCount(reqVO);
        return success(vo);
    }


}