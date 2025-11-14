package cn.iocoder.yudao.module.industry.controller.admin.emergency.dashboard.global.resoverview.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 应急资源总览 查询 Request VO")
@Data
public class EmergResoverViewQueryReqVO {

        @Schema(description = "查询地区-省市县的 full_code", example = "110101001001")
        private String regionFullCode;

        @Schema(description = "资源类型，如应急物资/救援设备/医疗资源/救援队伍", example = "应急物资")
        private String resType;

        @Schema(description = "库存状态，充足/紧张/短缺", example = "充足")
        private String stockStatus;

        @Schema(description = "关键词搜索：资源名称或存放位置", example = "灭火器")
        private String keyword;
}
