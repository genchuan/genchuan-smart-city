package cn.iocoder.yudao.module.studentmgmt.controller.admin.moralresource.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 资源类型 / 学习完成率统计 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ChartResourceCountRespVO {

    @Schema(description = "活动类型列表")
    private List<JSONObject> typeList;

    @Schema(description = "对应类型的活动数量列表")
    private List<JSONObject> resourceCountList;

    @Schema(description = "对应类型的参与人数列表")
    private List<JSONObject> learnRateList ;


}
