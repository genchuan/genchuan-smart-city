package cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo;

import lombok.Data;

import java.util.List;

// 单条台账及证据返回VO
@Data
public class RectifyEvidenceVO {
    private String ledgerCode;
    private List<EvidenceItem> evidenceList;
}
