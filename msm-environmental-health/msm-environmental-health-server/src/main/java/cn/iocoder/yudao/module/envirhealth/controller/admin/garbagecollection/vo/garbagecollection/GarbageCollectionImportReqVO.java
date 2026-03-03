package cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "环境卫生管理 - 收运计划导入 Req VO")
@Data
public class GarbageCollectionImportReqVO {

    // 主键ID
    @ExcelProperty("id")
    @Nullable
    private Long id;

    // 收运计划主键
    @ExcelProperty("collection_id")
    @Nullable
    private String collectionId;

    // 收运计划单编号
    @ExcelProperty("plan_no")
    @Nullable
    private String planNo;

    // ========== 核心必填字段 ==========
    @ExcelProperty("area_code")
    @NotBlank(message = "区域编码不能为空")
    private String areaCode;

    @ExcelProperty("garbage_type_id")
    @NotBlank(message = "垃圾品类ID不能为空")
    private String garbageTypeId;

    @ExcelProperty("frequency")
    @NotBlank(message = "收运频次不能为空")
    private String frequency;

    @ExcelProperty("time_period")
    @NotBlank(message = "收运时段不能为空")
    private String timePeriod;

    @ExcelProperty("vehicle_id")
    @NotBlank(message = "车辆ID不能为空")
    private String vehicleId;

    // ========== 非必填但建议默认值的字段 ==========
    @ExcelProperty("staff_ids")
    @Nullable
    private String staffIds;

    @ExcelProperty("point_ids")
    @Nullable
    private String pointIds;

    @ExcelProperty("plan_status_id")
    @Nullable
    private String planStatusId; // 导入时默认赋值为"待执行"状态ID

    @ExcelProperty("completion_rate")
    @NumberFormat("#.##")
    @Nullable
    private BigDecimal completionRate = BigDecimal.ZERO; // 默认0

    @ExcelProperty("abnormal_count")
    @Nullable
    private Integer abnormalCount = 0; // 默认0

    // ========== 自动填充字段（无需Excel填写） ==========
    @ExcelProperty("create_time")
    @Nullable
    private LocalDateTime createTime; // 导入时自动填充当前时间

    @ExcelProperty("is_abnormal")
    @Nullable
    private Integer isAbnormal = 0; // 默认无异常（0=否，1=是）

    // 对JSON字段做空值处理
    public void setStaffIds(String staffIds) {
        this.staffIds = (staffIds == null || staffIds.trim().isEmpty()) ? "[]" : staffIds;
    }

    public void setPointIds(String pointIds) {
        this.pointIds = (pointIds == null || pointIds.trim().isEmpty()) ? "[]" : pointIds;
    }
}