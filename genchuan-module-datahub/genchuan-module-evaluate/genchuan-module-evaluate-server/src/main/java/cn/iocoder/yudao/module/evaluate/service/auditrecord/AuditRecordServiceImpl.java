package cn.iocoder.yudao.module.evaluate.service.auditrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.auditrecord.vo.AuditRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.auditrecord.vo.AuditRecordSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.auditrecord.AuditRecordDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.auditrecord.AuditRecordMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.AUDIT_RECORD_NOT_EXISTS;

/**
 * 评价结果审核 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AuditRecordServiceImpl implements AuditRecordService {

    @Resource
    private AuditRecordMapper auditRecordMapper;

    @Override
    public Long createAuditRecord(AuditRecordSaveReqVO createReqVO) {
        // 插入
        AuditRecordDO auditRecord = BeanUtils.toBean(createReqVO, AuditRecordDO.class);
        auditRecordMapper.insert(auditRecord);
        // 返回
        return auditRecord.getId();
    }

    @Override
    public void updateAuditRecord(AuditRecordSaveReqVO updateReqVO) {
        // 校验存在
        validateAuditRecordExists(updateReqVO.getId());
        // 更新
        AuditRecordDO updateObj = BeanUtils.toBean(updateReqVO, AuditRecordDO.class);
        auditRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteAuditRecord(Long id) {
        // 校验存在
        validateAuditRecordExists(id);
        // 删除
        auditRecordMapper.deleteById(id);
    }

    private void validateAuditRecordExists(Long id) {
        if (auditRecordMapper.selectById(id) == null) {
            throw exception(AUDIT_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public AuditRecordDO getAuditRecord(Long id) {
        return auditRecordMapper.selectById(id);
    }

    @Override
    public PageResult<AuditRecordDO> getAuditRecordPage(AuditRecordPageReqVO pageReqVO) {
        return auditRecordMapper.selectPage(pageReqVO);
    }

}