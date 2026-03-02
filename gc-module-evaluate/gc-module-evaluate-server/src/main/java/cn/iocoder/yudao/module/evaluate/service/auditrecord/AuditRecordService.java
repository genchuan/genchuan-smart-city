package cn.iocoder.yudao.module.evaluate.service.auditrecord;

import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.auditrecord.vo.AuditRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.auditrecord.vo.AuditRecordSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.auditrecord.AuditRecordDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 评价结果审核 Service 接口
 *
 * @author 亘川智城
 */
public interface AuditRecordService {

    /**
     * 创建评价结果审核
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAuditRecord(@Valid AuditRecordSaveReqVO createReqVO);

    /**
     * 更新评价结果审核
     *
     * @param updateReqVO 更新信息
     */
    void updateAuditRecord(@Valid AuditRecordSaveReqVO updateReqVO);

    /**
     * 删除评价结果审核
     *
     * @param id 编号
     */
    void deleteAuditRecord(Long id);

    /**
     * 获得评价结果审核
     *
     * @param id 编号
     * @return 评价结果审核
     */
    AuditRecordDO getAuditRecord(Long id);

    /**
     * 获得评价结果审核分页
     *
     * @param pageReqVO 分页查询
     * @return 评价结果审核分页
     */
    PageResult<AuditRecordDO> getAuditRecordPage(AuditRecordPageReqVO pageReqVO);

}