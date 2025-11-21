package cn.iocoder.yudao.module.datacenter.service.evaluate.evalobject;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalobject.vo.EvalObjectPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalobject.vo.EvalObjectSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.evalobject.EvalObjectDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.evalobject.EvalObjectMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 评价对象管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalObjectServiceImpl implements EvalObjectService {

    @Resource
    private EvalObjectMapper evalObjectMapper;

    @Override
    public Long createEvalObject(EvalObjectSaveReqVO createReqVO) {
        // 插入
        EvalObjectDO evalObject = BeanUtils.toBean(createReqVO, EvalObjectDO.class);
        evalObjectMapper.insert(evalObject);
        // 返回
        return evalObject.getId();
    }

    @Override
    public void updateEvalObject(EvalObjectSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalObjectExists(updateReqVO.getId());
        // 更新
        EvalObjectDO updateObj = BeanUtils.toBean(updateReqVO, EvalObjectDO.class);
        evalObjectMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalObject(Long id) {
        // 校验存在
        validateEvalObjectExists(id);
        // 删除
        evalObjectMapper.deleteById(id);
    }

    private void validateEvalObjectExists(Long id) {
        if (evalObjectMapper.selectById(id) == null) {
            throw exception(EVAL_OBJECT_NOT_EXISTS);
        }
    }

    @Override
    public EvalObjectDO getEvalObject(Long id) {
        return evalObjectMapper.selectById(id);
    }

    @Override
    public PageResult<EvalObjectDO> getEvalObjectPage(EvalObjectPageReqVO pageReqVO) {
        return evalObjectMapper.selectPage(pageReqVO);
    }

}