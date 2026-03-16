package cn.iocoder.yudao.module.kitchen.service.entrectifyrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.EntRectifyRecordPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.entrectifyrecord.vo.EntRectifyRecordSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.entrectifyrecord.EntRectifyRecordDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.entrectifyrecord.EntRectifyRecordMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.kitchen.enums.ErrorCodeConstants.ENT_RECTIFY_RECORD_NOT_EXISTS;

/**
 * 企业整改记录 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EntRectifyRecordServiceImpl implements EntRectifyRecordService {

    @Resource
    private EntRectifyRecordMapper entRectifyRecordMapper;

    @Override
    public Long createEntRectifyRecord(EntRectifyRecordSaveReqVO createReqVO) {
        // 插入
        EntRectifyRecordDO entRectifyRecord = BeanUtils.toBean(createReqVO, EntRectifyRecordDO.class);
        entRectifyRecordMapper.insert(entRectifyRecord);
        // 返回
        return entRectifyRecord.getId();
    }

    @Override
    public void updateEntRectifyRecord(EntRectifyRecordSaveReqVO updateReqVO) {
        // 校验存在
        validateEntRectifyRecordExists(updateReqVO.getId());
        // 更新
        EntRectifyRecordDO updateObj = BeanUtils.toBean(updateReqVO, EntRectifyRecordDO.class);
        entRectifyRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteEntRectifyRecord(Long id) {
        // 校验存在
        validateEntRectifyRecordExists(id);
        // 删除
        entRectifyRecordMapper.deleteById(id);
    }

    private void validateEntRectifyRecordExists(Long id) {
        if (entRectifyRecordMapper.selectById(id) == null) {
            throw exception(ENT_RECTIFY_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public EntRectifyRecordDO getEntRectifyRecord(Long id) {
        return entRectifyRecordMapper.selectById(id);
    }

    @Override
    public PageResult<EntRectifyRecordDO> getEntRectifyRecordPage(EntRectifyRecordPageReqVO pageReqVO) {
        return entRectifyRecordMapper.selectPage(pageReqVO);
    }

}
