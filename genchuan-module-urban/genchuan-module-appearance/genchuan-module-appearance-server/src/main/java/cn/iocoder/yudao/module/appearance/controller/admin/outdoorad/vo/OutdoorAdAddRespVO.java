package cn.iocoder.yudao.module.appearance.controller.admin.outdoorad.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "管理后台 - 户外广告 Add Response VO")
@ExcelIgnoreUnannotated
public class OutdoorAdAddRespVO {
    @Schema(description = "新增广告主键ID（UUID）")
    @ExcelProperty("广告主键ID")
    private String outdoorAdId;

    @Schema(description = "广告编码，唯一")
    @ExcelProperty("广告编码")
    private String adCode;

    @Schema(description = "区块链存证哈希值")
    @ExcelProperty("区块链存证哈希值")
    private String chainHash;

    @Schema(description = "创建时间，格式yyyy-MM-dd HH:mm:ss")
    @ExcelProperty("创建时间")
    private String createTime;
}