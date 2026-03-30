package cn.iocoder.yudao.module.kitchen.controller.admin.rectifyreview.vo;

import cn.iocoder.yudao.module.kitchen.framework.lxsutils.procom.aop.sysope.BatchIdField;
import lombok.Data;

import java.util.List;

// 批量查询证据请求
@Data
public class BatchEvidenceRequestVO {
    private List<Long> ledgerIdList;
    private Long pageNo = 1L;
    private Long pageSize = 10L;
}
