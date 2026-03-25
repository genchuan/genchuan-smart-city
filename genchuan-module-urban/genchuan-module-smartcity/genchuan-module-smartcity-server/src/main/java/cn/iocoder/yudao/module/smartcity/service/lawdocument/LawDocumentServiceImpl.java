package cn.iocoder.yudao.module.smartcity.service.lawdocument;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.lawdocument.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.lawdocument.LawDocumentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.lawdocument.LawDocumentMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 执法文书 Service 实现类
 *
 * @author 朱聪权
 */
@Service
@Validated
public class LawDocumentServiceImpl implements LawDocumentService {

    @Resource
    private LawDocumentMapper lawDocumentMapper;

    @Override
    public Long createLawDocument(LawDocumentSaveReqVO createReqVO) {
        // 插入
        LawDocumentDO lawDocument = BeanUtils.toBean(createReqVO, LawDocumentDO.class);
        lawDocumentMapper.insert(lawDocument);
        // 返回
        return lawDocument.getId();
    }

    @Override
    public void updateLawDocument(LawDocumentSaveReqVO updateReqVO) {
        // 校验存在
        validateLawDocumentExists(updateReqVO.getId());
        // 更新
        LawDocumentDO updateObj = BeanUtils.toBean(updateReqVO, LawDocumentDO.class);
        lawDocumentMapper.updateById(updateObj);
    }

    @Override
    public void deleteLawDocument(Long id) {
        // 校验存在
        validateLawDocumentExists(id);
        // 删除
        lawDocumentMapper.deleteById(id);
    }

    private void validateLawDocumentExists(Long id) {
        if (lawDocumentMapper.selectById(id) == null) {
            throw exception(LAW_DOCUMENT_NOT_EXISTS);
        }
    }

    @Override
    public LawDocumentDO getLawDocument(Long id) {
        return lawDocumentMapper.selectById(id);
    }

    @Override
    public PageResult<LawDocumentDO> getLawDocumentPage(LawDocumentPageReqVO pageReqVO) {
        return lawDocumentMapper.selectPage(pageReqVO);
    }

}