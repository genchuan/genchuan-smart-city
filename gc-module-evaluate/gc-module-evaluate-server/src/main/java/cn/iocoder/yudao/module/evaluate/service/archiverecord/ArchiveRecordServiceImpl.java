package cn.iocoder.yudao.module.evaluate.service.archiverecord;

import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.archiverecord.vo.ArchiveRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.archiverecord.vo.ArchiveRecordSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.archiverecord.ArchiveRecordDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.archiverecord.ArchiveRecordMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

/**
 * 评价结果存档 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ArchiveRecordServiceImpl implements ArchiveRecordService {

    @Resource
    private ArchiveRecordMapper archiveRecordMapper;

    @Override
    public Long createArchiveRecord(ArchiveRecordSaveReqVO createReqVO) {
        // 插入
        ArchiveRecordDO archiveRecord = BeanUtils.toBean(createReqVO, ArchiveRecordDO.class);
        archiveRecordMapper.insert(archiveRecord);
        // 返回
        return archiveRecord.getId();
    }

    @Override
    public void updateArchiveRecord(ArchiveRecordSaveReqVO updateReqVO) {
        // 校验存在
        validateArchiveRecordExists(updateReqVO.getId());
        // 更新
        ArchiveRecordDO updateObj = BeanUtils.toBean(updateReqVO, ArchiveRecordDO.class);
        archiveRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteArchiveRecord(Long id) {
        // 校验存在
        validateArchiveRecordExists(id);
        // 删除
        archiveRecordMapper.deleteById(id);
    }

    private void validateArchiveRecordExists(Long id) {
        if (archiveRecordMapper.selectById(id) == null) {
            throw exception(ARCHIVE_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public ArchiveRecordDO getArchiveRecord(Long id) {
        return archiveRecordMapper.selectById(id);
    }

    @Override
    public PageResult<ArchiveRecordDO> getArchiveRecordPage(ArchiveRecordPageReqVO pageReqVO) {
        return archiveRecordMapper.selectPage(pageReqVO);
    }

}