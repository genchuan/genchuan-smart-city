package cn.iocoder.yudao.module.datacenter.service.evaluate.idx.evalidxitem;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxitem.vo.EvalIdxItemPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxitem.vo.EvalIdxItemSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.idx.evalidxitem.EvalIdxItemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.idx.evalidxitem.EvalIdxItemMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 指标项管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalIdxItemServiceImpl implements EvalIdxItemService {

    @Resource
    private EvalIdxItemMapper evalIdxItemMapper;

    @Override
    public Long createEvalIdxItem(EvalIdxItemSaveReqVO createReqVO) {
        // 插入
        EvalIdxItemDO evalIdxItem = BeanUtils.toBean(createReqVO, EvalIdxItemDO.class);
        evalIdxItemMapper.insert(evalIdxItem);
        // 返回
        return evalIdxItem.getId();
    }

    @Override
    public void updateEvalIdxItem(EvalIdxItemSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalIdxItemExists(updateReqVO.getId());
        // 更新
        EvalIdxItemDO updateObj = BeanUtils.toBean(updateReqVO, EvalIdxItemDO.class);
        evalIdxItemMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalIdxItem(Long id) {
        // 校验存在
        validateEvalIdxItemExists(id);
        // 删除
        evalIdxItemMapper.deleteById(id);
    }

    private void validateEvalIdxItemExists(Long id) {
        if (evalIdxItemMapper.selectById(id) == null) {
            throw exception(EVAL_IDX_ITEM_NOT_EXISTS);
        }
    }

    @Override
    public EvalIdxItemDO getEvalIdxItem(Long id) {
        return evalIdxItemMapper.selectById(id);
    }

    @Override
    public PageResult<EvalIdxItemDO> getEvalIdxItemPage(EvalIdxItemPageReqVO pageReqVO) {
        return evalIdxItemMapper.selectPage(pageReqVO);
    }

}