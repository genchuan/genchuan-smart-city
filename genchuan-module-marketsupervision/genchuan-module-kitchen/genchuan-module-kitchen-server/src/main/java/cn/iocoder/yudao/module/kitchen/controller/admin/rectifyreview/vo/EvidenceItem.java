package cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo;

import lombok.Data;

// 单条证据信息
@Data
public class EvidenceItem {
    private String url;
    private String name;
    private String type; // image/pdf/file
}
