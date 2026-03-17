package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.EvalObjectOverviewVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.ObjectPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.ObjectRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.ObjectSaveReqVO;
import cn.iocoder.yudao.module.evaluate.controller.common.vo.SelectOptionRespVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.object.ObjectDO;
import cn.iocoder.yudao.module.evaluate.service.area.AreaService;
import cn.iocoder.yudao.module.evaluate.service.baseinfo.relatedobject.RelatedObjectService;
import cn.iocoder.yudao.module.evaluate.service.object.ObjectService;
import cn.iocoder.yudao.module.evaluate.service.objecttype.ObjectTypeService;
import cn.iocoder.yudao.module.evaluate.service.user.UserService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.nacos.api.model.v2.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "评价体系管理 - 评价对象***")
@RestController
@RequestMapping("/evaluate/object")
@Validated
@Slf4j
public class ObjectController {

    @Resource
    private ObjectService objectService;
    @Resource
    private AreaService areaService;
    @Resource
    private ObjectTypeService objectTypeService;
    @Resource
    private UserService userService;
    @Resource
    private RelatedObjectService relatedObjectService;
    @PostMapping("/create")
    @Operation(summary = "创建评价对象")
    @PreAuthorize("@ss.hasPermission('evaluate:object:create')")
    public CommonResult<Long> createObject(@Valid @RequestBody ObjectSaveReqVO createReqVO) {
        return success(objectService.createObject(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评价对象")
    @PreAuthorize("@ss.hasPermission('evaluate:object:update')")
    public CommonResult<Boolean> updateObject(@Valid @RequestBody ObjectSaveReqVO updateReqVO) {
        objectService.updateObject(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评价对象")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:object:delete')")
    public CommonResult<Boolean> deleteObject(@RequestParam("id") Long id) {
        objectService.deleteObject(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评价对象")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:object:query')")
    public CommonResult<ObjectRespVO> getObject(@RequestParam("id") Long id) {
        ObjectDO object = objectService.getObject(id);
        return success(BeanUtils.toBean(object, ObjectRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评价对象分页")
    @PreAuthorize("@ss.hasPermission('evaluate:object:query')")
    public CommonResult<PageResult<ObjectRespVO>> getObjectPage(@Valid ObjectPageReqVO pageReqVO) {
        PageResult<ObjectDO> pageResult = objectService.getObjectPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ObjectRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评价对象 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:object:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportObjectExcel(@Valid ObjectPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ObjectDO> list = objectService.getObjectPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评价对象.xls", "数据", ObjectRespVO.class,
                        BeanUtils.toBean(list, ObjectRespVO.class));
    }
    @PostMapping("/import")
    @Operation(summary = "批量导入评价对象")
    public CommonResult<Integer> importObjects(@RequestParam("file") MultipartFile file) throws IOException {
        // ===== 1. 读取原始单元格数据（不依赖VO映射）=====
        List<Map<Integer, String>> rawRows;
        try (InputStream is = file.getInputStream()) {
            rawRows = EasyExcel.read(is)
                    .headRowNumber(1)   // 第一行为表头，跳过
                    .sheet()
                    .doReadSync();
        }
        log.info("原始数据行数：{}", rawRows.size());

        // ===== 2. 构建名称->ID/编码的映射（保持不变）=====
        List<SelectOptionRespVO> userOptions = userService.getUserSimpleList();
        Map<String, String> userName2IdMap = userOptions.stream()
                .collect(Collectors.toMap(SelectOptionRespVO::getLabel,
                        option -> option.getValue().toString(), (oldVal, newVal) -> oldVal));

//        Map<String, String> userName1IdMap = userOptions.stream()
//                .collect(Collectors.toMap(SelectOptionRespVO::getLabel,
//                        option -> option.getValue().toString(), (oldVal, newVal) -> oldVal));


        List<SelectOptionRespVO> areaOptions = areaService.getAreaSimpleList();
        Map<String, String> areaName2CodeMap = areaOptions.stream()
                .collect(Collectors.toMap(SelectOptionRespVO::getLabel,
                        option -> String.valueOf(option.getValue()), (oldVal, newVal) -> oldVal));

        List<SelectOptionRespVO> typeOptions = objectTypeService.getObjectTypeSimpleList();
        Map<String, String> typeName2IdMap = typeOptions.stream()
                .collect(Collectors.toMap(SelectOptionRespVO::getLabel,
                        option -> String.valueOf(option.getValue()), (oldVal, newVal) -> oldVal));

        List<SelectOptionRespVO> relatedOptions = relatedObjectService.getRelatedObjectSimpleList();
        Map<String, String> relatedName2IdMap = relatedOptions.stream()
                .collect(Collectors.toMap(SelectOptionRespVO::getLabel,
                        option -> String.valueOf(option.getValue()), (oldVal, newVal) -> oldVal));

        // ===== 3. 逐行手动构建VO =====
        List<ObjectSaveReqVO> validVOList = new ArrayList<>();
        for (int i = 0; i < rawRows.size(); i++) {
            Map<Integer, String> row = rawRows.get(i);
            int rowNum = i + 2; // 数据从第2行开始

            // 获取各列数据（根据列索引）
            String name = row.get(0);
            String code = row.get(1);
            String areaName = row.get(2);
            String objectTypeName = row.get(3);
            String managerName = row.get(4);
            String managerPhone = row.get(5);
            String relatedName = row.get(6);
            String statusId = row.get(7);
            //String createUserName = row.get(8);
            // 过滤空行
            if (StringUtils.isBlank(name) || StringUtils.isBlank(code)) {
                log.warn("第{}行：对象名称/编码为空，跳过", rowNum);
                continue;
            }

            // 打印调试
            log.info("第{}行原始数据：name={}, code={}, areaName={}, managerName={}",
                    rowNum, name, code, areaName, managerName);

            try {
                // 负责人名称 -> ID
                String managerId = userName2IdMap.get(managerName);
                if (managerId == null) {
                    throw new ServiceException(400, "未找到【启用状态】的负责人：" + managerName);
                }

                // 所属区域名称 -> 编码
                String areaCode = areaName2CodeMap.get(areaName);
                if (areaCode == null) {
                    throw new ServiceException(400, "未找到所属区域：" + areaName);
                }

                // 对象类型名称 -> ID
                String typeId = typeName2IdMap.get(objectTypeName);
                if (typeId == null) {
                    throw new ServiceException(400, "未找到【启用状态】的对象类型：" + objectTypeName);
                }

                // 关联网格名称 -> ID
                String relatedId = relatedName2IdMap.get(relatedName);
                if (relatedId == null) {
                    throw new ServiceException(400, "未找到【启用状态】的关联网格类型：" + relatedName);
                }
//                String createUserId = userName2IdMap.get(createUserName);
//                if (createUserId == null) {
//                    throw new ServiceException(400, "未找到【启用状态】的创建人：" + createUserName);
//                }
                // 手动构建VO
                ObjectSaveReqVO vo = new ObjectSaveReqVO();
                vo.setName(name);
                vo.setCode(code);
                vo.setAreaName(areaName);
                vo.setObjectTypeName(objectTypeName);
                vo.setManagerName(managerName);
                vo.setManagerPhone(managerPhone);
                vo.setRelatedName(relatedName);
                vo.setStatusId(statusId);
                //vo.setCreateUserName(createUserName);
                // 设置映射后的ID
                vo.setManagerId(managerId);
                vo.setAreaCode(areaCode);
                vo.setObjectTypeId(typeId);
                vo.setRelatedId(relatedId);
                //vo.setCreateBy(createUserId);

                validVOList.add(vo);
            } catch (Exception e) {
                throw new ServiceException(400, "第" + rowNum + "行导入失败：" + e.getMessage());
            }
        }

        if (validVOList.isEmpty()) {
            return success(0);
        }

        // 4. 批量导入
        objectService.importObjects(validVOList);
        return success(validVOList.size());
    }


    @GetMapping("/evalpage")
    @Operation(summary = "分页查询对象列表", description = "支持分页查询对象信息，包含关联数据")
    public PageResult<ObjectRespVO> pageJoinQuery(@Valid ObjectPageReqVO pageReqVO) {
        return objectService.pageJoinQuery(pageReqVO);
    }

    @GetMapping("/{objectId}")
    @Operation(summary = "根据ID获取对象详情", description = "获取指定对象的完整详细信息")
    public ObjectRespVO getDetailById(
            @Parameter(description = "对象ID", required = true)
            @PathVariable String objectId) {
        return objectService.getDetailById(objectId);
    }

    @GetMapping("/validate/name-unique")
    @Operation(summary = "验证名称唯一性", description = "检查指定区域内对象名称是否唯一")
    public void validateNameUnique(
            @Parameter(description = "对象名称", required = true)
            @RequestParam @NotBlank String name,

            @Parameter(description = "区域编码", required = true)
            @RequestParam @NotBlank String areaCode,

            @Parameter(description = "排除的对象ID（用于更新时排除自身）")
            @RequestParam(required = false) String excludeObjectId) {

        objectService.validateNameUnique(name, areaCode, excludeObjectId);
    }
//新改mpl


    @GetMapping("/allpage")
    @Operation(summary = "评价对象全量联表查询*")
    public CommonResult<PageResult<ObjectRespVO>> getAllObjectPage(ObjectPageReqVO pageParam) {
        PageResult<ObjectRespVO> pageResult = objectService.getAllObjectPage(pageParam);
        return success(pageResult);
    }
    /**
     * 获取status_id统计数据（过滤deleted=1）
     * 接口地址：GET /admin-api/evaluate/object/status-count
     * @param statusId 可选参数，前端不传则查全部状态
     */
    @GetMapping("/status-count")
    @Operation(summary = "获取status_id统计数据*")
    public Result<ObjectRespVO> getStatusCount(
            @RequestParam(required = false) Integer statusId) {
            ObjectRespVO respVO = objectService.getStatusCount(statusId);
            return Result.success(respVO);
    }
    /**
     * 评价对象全局概览
     */
    @GetMapping("/overview")
    @Operation(summary = "评价对象全局概览(图表测试)***")
    public CommonResult<EvalObjectOverviewVO> getOverview() {
        EvalObjectOverviewVO overview = objectService.getOverview();
        return CommonResult.success(overview);
    }
}