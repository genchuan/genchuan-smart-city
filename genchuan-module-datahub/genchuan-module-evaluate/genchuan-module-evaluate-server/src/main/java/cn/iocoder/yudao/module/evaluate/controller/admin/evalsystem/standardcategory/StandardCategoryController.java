package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standardcategory;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standardcategory.vo.StandardCategoryPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standardcategory.vo.StandardCategoryRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.standardcategory.vo.StandardCategorySaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.standardcategory.StandardCategoryDO;
import cn.iocoder.yudao.module.evaluate.service.standardcategory.StandardCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "评价体系管理 - 评价标准管理")
@Slf4j
@RestController
@RequestMapping("/evaluate/standard-category")
@Validated
public class StandardCategoryController {

    @Resource
    private StandardCategoryService standardCategoryService;

    @PostMapping("/create")
    @Operation(summary = "创建标准分类")
    @PreAuthorize("@ss.hasPermission('evaluate:standard-category:create')")
    public CommonResult<Long> createStandardCategory(@Valid @RequestBody StandardCategorySaveReqVO createReqVO) {
        return success(standardCategoryService.createStandardCategory(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新标准分类")
    @PreAuthorize("@ss.hasPermission('evaluate:standard-category:update')")
    public CommonResult<Boolean> updateStandardCategory(@Valid @RequestBody StandardCategorySaveReqVO updateReqVO) {
        standardCategoryService.updateStandardCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除标准分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:standard-category:delete')")
    public CommonResult<Boolean> deleteStandardCategory(@RequestParam("id") Long id) {
        standardCategoryService.deleteStandardCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得标准分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:standard-category:query')")
    public CommonResult<StandardCategoryRespVO> getStandardCategory(@RequestParam("id") Long id) {
        StandardCategoryDO standardCategory = standardCategoryService.getStandardCategory(id);
        return success(BeanUtils.toBean(standardCategory, StandardCategoryRespVO.class));
    }

    @GetMapping("/pageList")
    @Operation(summary = "获得标准分类分页")
    @PreAuthorize("@ss.hasPermission('evaluate:standard-category:query')")
    public CommonResult<PageResult<StandardCategoryRespVO>> getStandardCategoryPageList(@Valid StandardCategoryPageReqVO pageReqVO) {
        PageResult<StandardCategoryDO> pageResult = standardCategoryService.getStandardCategoryPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StandardCategoryRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出标准分类 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:standard-category:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStandardCategoryExcel(@Valid StandardCategoryPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StandardCategoryDO> list = standardCategoryService.getStandardCategoryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "标准分类.xls", "数据", StandardCategoryRespVO.class,
                        BeanUtils.toBean(list, StandardCategoryRespVO.class));
    }
    /**
     * 标准分类联表分页查询（支持钻取筛选）
     * @param reqVO 分页查询参数（含基础筛选、钻取条件）
     * @return 分页结果（包含所有联表展示字段）
     */
    @Operation(summary = "标准分类联表分页查询", description = "支持标准分类名称模糊查询、同指标体系/同状态钻取筛选")
    @GetMapping("/page")
    public CommonResult<PageResult<StandardCategoryRespVO>> getStandardCategoryPage(
            @Validated StandardCategoryPageReqVO reqVO // 自动校验参数合法性
    ) {
        log.info("开始查询标准分类联表数据，参数：{}", reqVO);
        // 调用Service层获取分页结果
        PageResult<StandardCategoryRespVO> pageResult = standardCategoryService.getStandardCategoryJoinPage(reqVO);
        log.info("标准分类联表数据查询完成，页码：{}，总条数：{}", reqVO.getPageNo(), pageResult.getTotal());
        return CommonResult.success(pageResult);
    }

    /**
     * 钻取查询 - 筛选同指标体系的标准分类（简化接口，可选）
     * @param systemId 指标体系ID（钻取核心参数）
     * @param pageNo 页码
     * @param pageSize 页大小
     * @return 分页结果
     */
    @Operation(summary = "钻取查询 - 同指标体系标准分类", description = "点击「适用指标体系」触发，筛选同一体系下的所有标准分类")
    @GetMapping("/drill/system")
    public CommonResult<PageResult<StandardCategoryRespVO>> drillBySystemId(
            @Parameter(description = "指标体系ID", required = true, example = "26662")
            @RequestParam String systemId,

            @Parameter(description = "页码", example = "1")
            @RequestParam(defaultValue = "1") Integer pageNo,

            @Parameter(description = "页大小", example = "10")
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        // 构建分页查询参数
        StandardCategoryPageReqVO reqVO = new StandardCategoryPageReqVO();
        reqVO.setSystemId(systemId);
        reqVO.setPageNo(pageNo);
        reqVO.setPageSize(pageSize);
        // 调用通用分页查询方法
        PageResult<StandardCategoryRespVO> pageResult = standardCategoryService.getStandardCategoryJoinPage(reqVO);
        return CommonResult.success(pageResult);
    }

    /**
     * 钻取查询 - 筛选同状态的标准分类（简化接口，可选）
     * @param statusId 状态ID（钻取核心参数）
     * @param pageNo 页码
     * @param pageSize 页大小
     * @return 分页结果
     */
    @Operation(summary = "钻取查询 - 同状态标准分类", description = "点击「状态」触发，筛选同一状态下的所有标准分类")
    @GetMapping("/drill/status")
    public CommonResult<PageResult<StandardCategoryRespVO>> drillByStatusId(
            @Parameter(description = "状态ID", required = true, example = "7106")
            @RequestParam Integer statusId,

            @Parameter(description = "页码", example = "1")
            @RequestParam(defaultValue = "1") Integer pageNo,

            @Parameter(description = "页大小", example = "10")
            @RequestParam(defaultValue = "10") Integer pageSize
    ) {
        // 构建分页查询参数
        StandardCategoryPageReqVO reqVO = new StandardCategoryPageReqVO();
        reqVO.setStatusId(statusId);
        reqVO.setPageNo(pageNo);
        reqVO.setPageSize(pageSize);
        // 调用通用分页查询方法
        return CommonResult.success(standardCategoryService.getStandardCategoryJoinPage(reqVO));
    }

}