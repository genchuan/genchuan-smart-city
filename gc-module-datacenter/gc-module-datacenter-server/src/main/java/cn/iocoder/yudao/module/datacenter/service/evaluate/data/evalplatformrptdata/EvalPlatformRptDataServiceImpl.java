package cn.iocoder.yudao.module.datacenter.service.evaluate.data.evalplatformrptdata;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalplatformrptdata.vo.EvalPlatformRptDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalplatformrptdata.vo.EvalPlatformRptDataSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.data.evalplatformrptdata.EvalPlatformRptDataDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.data.evalplatformrptdata.EvalPlatformRptDataMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 平台上报数据 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EvalPlatformRptDataServiceImpl implements EvalPlatformRptDataService {

    @Resource
    private EvalPlatformRptDataMapper evalPlatformRptDataMapper;

    @Override
    public Long createEvalPlatformRptData(EvalPlatformRptDataSaveReqVO createReqVO) {
        // 插入
        EvalPlatformRptDataDO evalPlatformRptData = BeanUtils.toBean(createReqVO, EvalPlatformRptDataDO.class);
        evalPlatformRptDataMapper.insert(evalPlatformRptData);
        // 返回
        return evalPlatformRptData.getId();
    }

    @Override
    public void updateEvalPlatformRptData(EvalPlatformRptDataSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalPlatformRptDataExists(updateReqVO.getId());
        // 更新
        EvalPlatformRptDataDO updateObj = BeanUtils.toBean(updateReqVO, EvalPlatformRptDataDO.class);
        evalPlatformRptDataMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalPlatformRptData(Long id) {
        // 校验存在
        validateEvalPlatformRptDataExists(id);
        // 删除
        evalPlatformRptDataMapper.deleteById(id);
    }

    private void validateEvalPlatformRptDataExists(Long id) {
        if (evalPlatformRptDataMapper.selectById(id) == null) {
            throw exception(EVAL_PLATFORM_RPT_DATA_NOT_EXISTS);
        }
    }

    @Override
    public EvalPlatformRptDataDO getEvalPlatformRptData(Long id) {
        return evalPlatformRptDataMapper.selectById(id);
    }

    @Override
    public PageResult<EvalPlatformRptDataDO> getEvalPlatformRptDataPage(EvalPlatformRptDataPageReqVO pageReqVO) {
        return evalPlatformRptDataMapper.selectPage(pageReqVO);
    }

}