package cn.iocoder.yudao.module.evaluate.service.record;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.inspection.record.vo.RecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.inspection.record.vo.RecordSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.record.RecordDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.record.RecordMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.RECORD_NOT_EXISTS;

/**
 * 考察记录 Service 实现类
 *
 * @author 亘川智城
 */

@Validated
@Service("inspectionRecordService")
public class RecordServiceImpl implements RecordService {

    @Resource
    private RecordMapper recordMapper;

    @Override
    public Long createRecord(RecordSaveReqVO createReqVO) {
        // 插入
        RecordDO record = BeanUtils.toBean(createReqVO, RecordDO.class);
        recordMapper.insert(record);
        // 返回
        return record.getId();
    }

    @Override
    public void updateRecord(RecordSaveReqVO updateReqVO) {
        // 校验存在
        validateRecordExists(updateReqVO.getId());
        // 更新
        RecordDO updateObj = BeanUtils.toBean(updateReqVO, RecordDO.class);
        recordMapper.updateById(updateObj);
    }

    @Override
    public void deleteRecord(Long id) {
        // 校验存在
        validateRecordExists(id);
        // 删除
        recordMapper.deleteById(id);
    }

    private void validateRecordExists(Long id) {
        if (recordMapper.selectById(id) == null) {
            throw exception(RECORD_NOT_EXISTS);
        }
    }

    @Override
    public RecordDO getRecord(Long id) {
        return recordMapper.selectById(id);
    }

    @Override
    public PageResult<RecordDO> getRecordPage(RecordPageReqVO pageReqVO) {
        return recordMapper.selectPage(pageReqVO);
    }

}