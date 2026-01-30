package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.gridmanage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 网格管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridManageRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2398")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "行政区划节点ID", example = "16590")
    @ExcelProperty("行政区划节点ID")
    private String treeNodeId;

    @Schema(description = "行政区划节点编码")
    @ExcelProperty("行政区划节点编码")
    private String nodeCode;

    @Schema(description = "行政区划节点名称", example = "李四")
    @ExcelProperty("行政区划节点名称")
    private String nodeName;

    @Schema(description = "网格类型", example = "2")
    @ExcelProperty("网格类型")
    private String gridType;

    @Schema(description = "网格名称", example = "张三")
    @ExcelProperty("网格名称")
    private String gridName;

    @Schema(description = "唯一网格编码")
    @ExcelProperty("唯一网格编码")
    private String gridCode;

    @Schema(description = "边界坐标")
    @ExcelProperty("边界坐标")
    private String boundaryCoords;

    @Schema(description = "面积（m²）")
    @ExcelProperty("面积（m²）")
    private BigDecimal gridArea;

    @Schema(description = "状态", example = "2")
    @ExcelProperty("状态")
    private String gridStatus;

    @Schema(description = "网格员ID", example = "25073")
    @ExcelProperty("网格员ID")
    private Long gridUserId;

    @Schema(description = "网格员姓名", example = "李四")
    @ExcelProperty("网格员姓名")
    private String gridUserName;

    @Schema(description = "划分时间")
    @ExcelProperty("划分时间")
    private LocalDateTime divTime;

    @Schema(description = "业务备注", example = "你说的对")
    @ExcelProperty("业务备注")
    private String gridRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}