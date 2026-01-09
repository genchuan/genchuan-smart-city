package cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.base;

import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.base.vo.UniversalScenePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.base.vo.UniversalSceneRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.universal.dashboard.scene.base.vo.UniversalSceneSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.universal.dashboard.scene.base.UniversalSceneDO;
import cn.iocoder.yudao.module.industry.service.universal.dashboard.scene.base.UniversalSceneService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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



@Tag(name = "管理后台 - 场景大屏1-场景列表")
@RestController
@RequestMapping("/industry/universal-scene")
@Validated
public class UniversalSceneController {

    @Resource
    private UniversalSceneService universalSceneService;

//    @GetMapping("/tree")
//    @Operation(summary = "获取树形通用场景")
//    public CommonResult<List<UniversalSceneRespVO>> getSceneTree() {
//        return CommonResult.success(universalSceneService.listTreeByParentId());
//    }
    @GetMapping("/list")
    @Operation(summary = "1.获取场景列表接口")
    public CommonResult<List<UniversalSceneRespVO>> getSceneList() {
        List<UniversalSceneRespVO> list = universalSceneService.listTreeByParentId();
        return CommonResult.success(list);
    }


    /**
     * 创建通用场景
     *
     * <p>该接口用于创建一个新的通用场景，可以是一级场景或二级子场景。
     * 功能：
     * <ul>
     *     <li>校验父级场景是否存在（parentId 不为 0 时）</li>
     *     <li>校验场景名称和场景值是否重复</li>
     *     <li>生成唯一 sceneId 并保存到数据库</li>
     * </ul>
     *
     * @param createReqVO 请求体，包含待创建场景的信息：
     * <ul>
     *     <li>parentId: 父级ID，0表示一级场景，必填，类型：integer(int64)</li>
     *     <li>level: 场景等级，一级场景为1，二级为2，必填，类型：integer(int32)，示例值：1</li>
     *     <li>description: 场景描述，选填，类型：string，示例值：这是描述</li>
     *     <li>label: 场景名称，如 城管住建/市政设施，必填，类型：string，示例值：城管住建</li>
     *     <li>value: 场景值，用于前端选择，如 urban_admin / urban_admin_facility，必填，类型：string，示例值：urban_admin</li>
     * </ul>
     * @return CommonResult<Long> 返回创建成功的场景 ID
     */
    @PostMapping("/create")
    @Operation(summary = "创建通用场景")
    @PreAuthorize("@ss.hasPermission('industry:universal-scene:create')")
    public CommonResult<Long> createUniversalScene(@Valid @RequestBody UniversalSceneSaveReqVO createReqVO) {
        return success(universalSceneService.createUniversalScene(createReqVO));
    }

    //通过父id获取全部的子场景（父id为0获取全部一级场景）
    @GetMapping("/listByParentId")
    @Operation(summary = "通过父id获取全部的子场景（父id为0获取全部一级场景）")
    @Parameter(name = "parentId", description = "父id", required = true, example = "0")
    @PreAuthorize("@ss.hasPermission('industry:universal-scene-children:list')")
    public CommonResult<List<UniversalSceneRespVO>> listByParentId(@RequestParam Long parentId) {
        List<UniversalSceneRespVO> chilrenList = universalSceneService.listByParentId(parentId);
        return success(BeanUtils.toBean(chilrenList, UniversalSceneRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得通用场景表，一级和二级场景")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:universal-scene:query')")
    public CommonResult<UniversalSceneRespVO> getUniversalScene(@RequestParam("id") Long id) {
        UniversalSceneDO universalScene = universalSceneService.getUniversalScene(id);
        return success(BeanUtils.toBean(universalScene, UniversalSceneRespVO.class));
    }

    @PutMapping("/update")
    @Operation(summary = "更新通用场景表，一级和二级场景")
    @PreAuthorize("@ss.hasPermission('industry:universal-scene:update')")
    public CommonResult<Boolean> updateUniversalScene(@Valid @RequestBody UniversalSceneSaveReqVO updateReqVO) {
        universalSceneService.updateUniversalScene(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除通用场景表，一级和二级场景")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:universal-scene:delete')")
    public CommonResult<Boolean> deleteUniversalScene(@RequestParam("id") Long id) {
        universalSceneService.deleteUniversalScene(id);
        return success(true);
    }



    @GetMapping("/page")
    @Operation(summary = "获得通用场景表，一级和二级场景分页")
    @PreAuthorize("@ss.hasPermission('industry:universal-scene:query')")
    public CommonResult<PageResult<UniversalSceneRespVO>> getUniversalScenePage(@Valid UniversalScenePageReqVO pageReqVO) {
        PageResult<UniversalSceneDO> pageResult = universalSceneService.getUniversalScenePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UniversalSceneRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出通用场景表，一级和二级场景 Excel")
    @PreAuthorize("@ss.hasPermission('industry:universal-scene:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUniversalSceneExcel(@Valid UniversalScenePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UniversalSceneDO> list = universalSceneService.getUniversalScenePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "通用场景表，一级和二级场景.xls", "数据", UniversalSceneRespVO.class,
                        BeanUtils.toBean(list, UniversalSceneRespVO.class));
    }

}
