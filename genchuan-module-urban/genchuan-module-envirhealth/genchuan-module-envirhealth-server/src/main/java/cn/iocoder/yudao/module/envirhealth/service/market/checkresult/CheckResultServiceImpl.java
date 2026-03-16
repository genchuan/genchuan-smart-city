package cn.iocoder.yudao.module.envirhealth.service.market.checkresult;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.market.vo.checkresult.CheckResultPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.market.vo.checkresult.CheckResultSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.market.CheckResultDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.market.CheckResultMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.CHECK_RESULT_NOT_EXISTS;

/**
 * 核查结果字典表 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CheckResultServiceImpl implements CheckResultService {

    @Resource
    private CheckResultMapper checkResultMapper;

    @Override
    public Long createCheckResult(CheckResultSaveReqVO createReqVO) {
        // 插入
        CheckResultDO checkResult = BeanUtils.toBean(createReqVO, CheckResultDO.class);
        checkResultMapper.insert(checkResult);
        // 返回
        return checkResult.getId();
    }

    @Override
    public void updateCheckResult(CheckResultSaveReqVO updateReqVO) {
        // 校验存在
        validateCheckResultExists(updateReqVO.getId());
        // 更新
        CheckResultDO updateObj = BeanUtils.toBean(updateReqVO, CheckResultDO.class);
        checkResultMapper.updateById(updateObj);
    }

    @Override
    public void deleteCheckResult(Long id) {
        // 校验存在
        validateCheckResultExists(id);
        // 删除
        checkResultMapper.deleteById(id);
    }

    private void validateCheckResultExists(Long id) {
        if (checkResultMapper.selectById(id) == null) {
            throw exception(CHECK_RESULT_NOT_EXISTS);
        }
    }

    @Override
    public CheckResultDO getCheckResult(Long id) {
        return checkResultMapper.selectById(id);
    }

    @Override
    public PageResult<CheckResultDO> getCheckResultPage(CheckResultPageReqVO pageReqVO) {
        return checkResultMapper.selectPage(pageReqVO);
    }

}