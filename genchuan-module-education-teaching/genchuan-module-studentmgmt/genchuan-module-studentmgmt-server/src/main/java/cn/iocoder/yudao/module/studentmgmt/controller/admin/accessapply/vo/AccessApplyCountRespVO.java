package cn.iocoder.yudao.module.studentmgmt.controller.admin.accessapply.vo;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 各班级申请次数 / 类型分布统计 response VO")
@Data
public class AccessApplyCountRespVO {

    private List<JSONObject> classStatistics;


}