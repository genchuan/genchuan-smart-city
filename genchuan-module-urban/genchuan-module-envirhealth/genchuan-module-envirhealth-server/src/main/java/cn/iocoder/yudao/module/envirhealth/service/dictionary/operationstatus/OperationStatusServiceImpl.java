package cn.iocoder.yudao.module.envirhealth.service.dictionary.operationstatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.operationstatus.vo.OperationStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.operationstatus.vo.OperationStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.OperationStatusDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.dictionary.OperationStatusMapper;
import cn.iocoder.yudao.module.envirhealth.util.vo.OptionVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.OPERATION_STATUS_NOT_EXISTS;

/**
 * 运营状态字典 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class OperationStatusServiceImpl implements OperationStatusService {

    @Resource
    private OperationStatusMapper operationStatusMapper;

    @Override
    public Long createOperationStatus(OperationStatusSaveReqVO createReqVO) {
        // 插入
        OperationStatusDO operationStatus = BeanUtils.toBean(createReqVO, OperationStatusDO.class);
        operationStatusMapper.insert(operationStatus);
        // 返回
        return operationStatus.getId();
    }

    @Override
    public void updateOperationStatus(OperationStatusSaveReqVO updateReqVO) {
        // 校验存在
        validateOperationStatusExists(updateReqVO.getId());
        // 更新
        OperationStatusDO updateObj = BeanUtils.toBean(updateReqVO, OperationStatusDO.class);
        operationStatusMapper.updateById(updateObj);
    }

    @Override
    public void deleteOperationStatus(Long id) {
        // 校验存在
        validateOperationStatusExists(id);
        // 删除
        operationStatusMapper.deleteById(id);
    }

    private void validateOperationStatusExists(Long id) {
        if (operationStatusMapper.selectById(id) == null) {
            throw exception(OPERATION_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public OperationStatusDO getOperationStatus(Long id) {
        return operationStatusMapper.selectById(id);
    }

    @Override
    public PageResult<OperationStatusDO> getOperationStatusPage(OperationStatusPageReqVO pageReqVO) {
        return operationStatusMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OptionVO> getOperationStatusOptions() {

        List<OperationStatusDO> list;
        list = operationStatusMapper.selectList(
                new LambdaQueryWrapperX<OperationStatusDO>()
                        .eq(OperationStatusDO::getDeleted, 0)
                        .orderByDesc(OperationStatusDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, operationStatusDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(operationStatusDO.getName());
            vo.setValue(operationStatusDO.getSysOperationStatusId());
            return vo;
        });
    }
}