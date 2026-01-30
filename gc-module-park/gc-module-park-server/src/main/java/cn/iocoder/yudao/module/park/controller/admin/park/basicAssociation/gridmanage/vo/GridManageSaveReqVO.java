package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.gridmanage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 网格管理新增/修改 Request VO")
@Data
public class GridManageSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2398")
    private Long id;

    @Schema(description = "行政区划节点ID", example = "16590")
    private String treeNodeId;

    @Schema(description = "行政区划节点编码")
    private String nodeCode;

    @Schema(description = "行政区划节点名称", example = "李四")
    private String nodeName;

    @Schema(description = "网格类型", example = "2")
    private String gridType;

    @Schema(description = "网格名称", example = "张三")
    private String gridName;

    @Schema(description = "唯一网格编码")
    private String gridCode;

    @Schema(description = "边界坐标")
    private String boundaryCoords;

    @Schema(description = "面积（m²）")
    private BigDecimal gridArea;

    @Schema(description = "状态", example = "2")
    private String gridStatus;

    @Schema(description = "网格员ID", example = "25073")
    private Long gridUserId;

    @Schema(description = "网格员姓名", example = "李四")
    private String gridUserName;

    @Schema(description = "划分时间")
    private LocalDateTime divTime;

    @Schema(description = "业务备注", example = "你说的对")
    private String gridRemark;

}