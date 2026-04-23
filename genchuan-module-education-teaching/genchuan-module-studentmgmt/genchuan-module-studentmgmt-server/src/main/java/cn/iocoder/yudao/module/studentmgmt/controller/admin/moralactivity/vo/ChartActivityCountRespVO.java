package cn.iocoder.yudao.module.studentmgmt.controller.admin.moralactivity.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 德育活动态势看板 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ChartActivityCountRespVO {

    @Schema(description = "活动类型列表")
    private List<JSONObject> typeList;

    @Schema(description = "对应类型的活动数量列表")
    private List<JSONObject> activityCountList;

    @Schema(description = "对应类型的参与人数列表")
    private List<JSONObject> joinCountList;


}
