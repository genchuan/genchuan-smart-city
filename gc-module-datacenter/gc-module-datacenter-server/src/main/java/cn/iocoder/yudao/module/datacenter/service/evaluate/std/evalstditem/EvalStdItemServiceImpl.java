package cn.iocoder.yudao.module.datacenter.service.evaluate.std.evalstditem;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.std.evalstditem.vo.EvalStdItemPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.std.evalstditem.vo.EvalStdItemSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.std.evalstditem.EvalStdItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.std.evalstditem.EvalStdItemMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 标准项管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalStdItemServiceImpl implements EvalStdItemService {

    @Resource
    private EvalStdItemMapper evalStdItemMapper;

    @Override
    public Long createEvalStdItem(EvalStdItemSaveReqVO createReqVO) {
        // 插入
        EvalStdItemDO evalStdItem = BeanUtils.toBean(createReqVO, EvalStdItemDO.class);
        evalStdItemMapper.insert(evalStdItem);
        // 返回
        return evalStdItem.getId();
    }

    @Override
    public void updateEvalStdItem(EvalStdItemSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalStdItemExists(updateReqVO.getId());
        // 更新
        EvalStdItemDO updateObj = BeanUtils.toBean(updateReqVO, EvalStdItemDO.class);
        evalStdItemMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalStdItem(Long id) {
        // 校验存在
        validateEvalStdItemExists(id);
        // 删除
        evalStdItemMapper.deleteById(id);
    }

    private void validateEvalStdItemExists(Long id) {
        if (evalStdItemMapper.selectById(id) == null) {
            throw exception(EVAL_STD_ITEM_NOT_EXISTS);
        }
    }

    @Override
    public EvalStdItemDO getEvalStdItem(Long id) {
        return evalStdItemMapper.selectById(id);
    }

    @Override
    public PageResult<EvalStdItemDO> getEvalStdItemPage(EvalStdItemPageReqVO pageReqVO) {
        return evalStdItemMapper.selectPage(pageReqVO);
    }

}