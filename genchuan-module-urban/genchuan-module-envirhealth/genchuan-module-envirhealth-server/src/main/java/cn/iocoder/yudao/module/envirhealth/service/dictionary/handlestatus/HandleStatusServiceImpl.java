package cn.iocoder.yudao.module.envirhealth.service.dictionary.handlestatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.handlestatus.vo.HandleStatusOptionVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.handlestatus.vo.HandleStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.handlestatus.vo.HandleStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.HandleStatusDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.dictionary.HandleStatusMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.HANDLE_STATUS_NOT_EXISTS;

/**
 * 处置状态字典表【通用复用】 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class HandleStatusServiceImpl implements HandleStatusService {

    @Resource
    private HandleStatusMapper handleStatusMapper;

    @Override
    public Long createHandleStatus(HandleStatusSaveReqVO createReqVO) {
        // 插入
        HandleStatusDO handleStatus = BeanUtils.toBean(createReqVO, HandleStatusDO.class);
        handleStatusMapper.insert(handleStatus);
        // 返回
        return handleStatus.getId();
    }

    @Override
    public void updateHandleStatus(HandleStatusSaveReqVO updateReqVO) {
        // 校验存在
        validateHandleStatusExists(updateReqVO.getId());
        // 更新
        HandleStatusDO updateObj = BeanUtils.toBean(updateReqVO, HandleStatusDO.class);
        handleStatusMapper.updateById(updateObj);
    }

    @Override
    public void deleteHandleStatus(Long id) {
        // 校验存在
        validateHandleStatusExists(id);
        // 删除
        handleStatusMapper.deleteById(id);
    }

    private void validateHandleStatusExists(Long id) {
        if (handleStatusMapper.selectById(id) == null) {
            throw exception(HANDLE_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public HandleStatusDO getHandleStatus(Long id) {
        return handleStatusMapper.selectById(id);
    }

    @Override
    public PageResult<HandleStatusDO> getHandleStatusPage(HandleStatusPageReqVO pageReqVO) {
        return handleStatusMapper.selectPage(pageReqVO);
    }

    @Override
    public List<HandleStatusOptionVO> getHandleStatusOptions() {

        List<HandleStatusDO> list;
        list = handleStatusMapper.selectList(
                new LambdaQueryWrapperX<HandleStatusDO>()
                        .eq(HandleStatusDO::getDeleted, 0)
                        .in(HandleStatusDO::getName, "待处置","待复核","已办结")
                        .orderByDesc(HandleStatusDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, handleStatusDO -> {
            HandleStatusOptionVO vo = new HandleStatusOptionVO();
            vo.setLabel(handleStatusDO.getName());
            vo.setValue(handleStatusDO.getSysHandleStatusId());
            return vo;
        });
    }
}