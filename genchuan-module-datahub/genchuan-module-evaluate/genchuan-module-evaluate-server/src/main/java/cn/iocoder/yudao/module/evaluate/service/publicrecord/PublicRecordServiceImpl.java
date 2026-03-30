package cn.iocoder.yudao.module.evaluate.service.publicrecord;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.publicrecord.vo.PublicRecordPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.publicrecord.vo.PublicRecordSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.publicrecord.PublicRecordDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.publicrecord.PublicRecordMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.PUBLIC_RECORD_NOT_EXISTS;

/**
 * 评价结果公示 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PublicRecordServiceImpl implements PublicRecordService {

    @Resource
    private PublicRecordMapper publicRecordMapper;

    @Override
    public Long createPublicRecord(PublicRecordSaveReqVO createReqVO) {
        // 插入
        PublicRecordDO publicRecord = BeanUtils.toBean(createReqVO, PublicRecordDO.class);
        publicRecordMapper.insert(publicRecord);
        // 返回
        return publicRecord.getId();
    }

    @Override
    public void updatePublicRecord(PublicRecordSaveReqVO updateReqVO) {
        // 校验存在
        validatePublicRecordExists(updateReqVO.getId());
        // 更新
        PublicRecordDO updateObj = BeanUtils.toBean(updateReqVO, PublicRecordDO.class);
        publicRecordMapper.updateById(updateObj);
    }

    @Override
    public void deletePublicRecord(Long id) {
        // 校验存在
        validatePublicRecordExists(id);
        // 删除
        publicRecordMapper.deleteById(id);
    }

    private void validatePublicRecordExists(Long id) {
        if (publicRecordMapper.selectById(id) == null) {
            throw exception(PUBLIC_RECORD_NOT_EXISTS);
        }
    }

    @Override
    public PublicRecordDO getPublicRecord(Long id) {
        return publicRecordMapper.selectById(id);
    }

    @Override
    public PageResult<PublicRecordDO> getPublicRecordPage(PublicRecordPageReqVO pageReqVO) {
        return publicRecordMapper.selectPage(pageReqVO);
    }

}