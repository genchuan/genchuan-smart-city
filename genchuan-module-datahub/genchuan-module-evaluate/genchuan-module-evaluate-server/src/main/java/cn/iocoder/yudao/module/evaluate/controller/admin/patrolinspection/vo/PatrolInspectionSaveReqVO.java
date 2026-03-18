package cn.iocoder.yudao.module.evaluate.controller.admin.patrolinspection.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Schema(description = "管理后台 - 巡查巡检新增/修改 Request VO")
@Data
public class PatrolInspectionSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "32747")
    private Long id;

    @Schema(description = "巡检人ID(关联sys_user.id)", example = "7549")
    private Long userId;

    @Schema(description = "体系ID (关联eval_index_system.id)", example = "14827")
    private Long systemId;

    @Schema(description = "评价对象ID (关联eval_object.id)", example = "27902")
    private Long objectId;

    @Schema(description = "指标项ID(关联eval_index_item.id)", example = "6841")
    private Long itemId;

    @Schema(description = "规则分类ID(关联eval_index_category.id)", example = "27032")
    private Long categoryId;

    @Schema(description = "评价说明")
    private String details;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "状态: 1：待审核中，2：审核通过，3：不用审核", example = "2")
    private String status;

    @Schema(description = "图片文件")
    private MultipartFile[] files;

    @Schema(description = "图片URL")
    private String imageUrl;

    @Schema(description = "地址编码")
    private String addressCoding;

}