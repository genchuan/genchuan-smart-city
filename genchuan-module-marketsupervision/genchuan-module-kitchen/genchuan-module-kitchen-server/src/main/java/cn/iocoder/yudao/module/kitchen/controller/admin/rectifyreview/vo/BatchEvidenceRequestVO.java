package cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo;

import lombok.Data;

import java.util.List;

// 批量查询证据请求
@Data
public class BatchEvidenceRequestVO {
    private List<Long> ledgerIdList;
    private Long pageNo;
    private Long pageSize;
}
