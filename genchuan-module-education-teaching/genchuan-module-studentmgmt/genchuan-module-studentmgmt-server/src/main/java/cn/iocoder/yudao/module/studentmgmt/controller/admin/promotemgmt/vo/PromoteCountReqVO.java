package cn.iocoder.yudao.module.studentmgmt.controller.admin.promotemgmt.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 各站点宣传人数统计 Request VO")
@Data
public class PromoteCountReqVO {

    @Schema(description = "宣传站点列表")
    private List<String> siteList;
    @Schema(description = "各站点宣传人数列表")
    private List<Integer> promoteNumList;
    @Schema(description = "各站点意向学生数列表")
    private List<Integer> intentNumList;



}