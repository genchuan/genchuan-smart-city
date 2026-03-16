package cn.iocoder.yudao.module.kitchen.service.dictionary.cancelreasondict;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.cancelreasondict.vo.CancelReasonDictPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.dictionary.cancelreasondict.vo.CancelReasonDictSaveReqVO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.dictionary.cancelreasondict.CancelReasonDictDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.dictionary.cancelreasondict.CancelReasonDictMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.kitchen.enums.ErrorCodeConstants.CANCEL_REASON_DICT_NOT_EXISTS;

/**
 * 撤销原因字典 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CancelReasonDictServiceImpl implements CancelReasonDictService {

    @Resource
    private CancelReasonDictMapper cancelReasonDictMapper;

    @Override
    public Long createCancelReasonDict(CancelReasonDictSaveReqVO createReqVO) {
        // 插入
        CancelReasonDictDO cancelReasonDict = BeanUtils.toBean(createReqVO, CancelReasonDictDO.class);
        cancelReasonDictMapper.insert(cancelReasonDict);
        // 返回
        return cancelReasonDict.getId();
    }

    @Override
    public void updateCancelReasonDict(CancelReasonDictSaveReqVO updateReqVO) {
        // 校验存在
        validateCancelReasonDictExists(updateReqVO.getId());
        // 更新
        CancelReasonDictDO updateObj = BeanUtils.toBean(updateReqVO, CancelReasonDictDO.class);
        cancelReasonDictMapper.updateById(updateObj);
    }

    @Override
    public void deleteCancelReasonDict(Long id) {
        // 校验存在
        validateCancelReasonDictExists(id);
        // 删除
        cancelReasonDictMapper.deleteById(id);
    }

    private void validateCancelReasonDictExists(Long id) {
        if (cancelReasonDictMapper.selectById(id) == null) {
            throw exception(CANCEL_REASON_DICT_NOT_EXISTS);
        }
    }

    @Override
    public CancelReasonDictDO getCancelReasonDict(Long id) {
        return cancelReasonDictMapper.selectById(id);
    }

    @Override
    public PageResult<CancelReasonDictDO> getCancelReasonDictPage(CancelReasonDictPageReqVO pageReqVO) {
        return cancelReasonDictMapper.selectPage(pageReqVO);
    }

}
