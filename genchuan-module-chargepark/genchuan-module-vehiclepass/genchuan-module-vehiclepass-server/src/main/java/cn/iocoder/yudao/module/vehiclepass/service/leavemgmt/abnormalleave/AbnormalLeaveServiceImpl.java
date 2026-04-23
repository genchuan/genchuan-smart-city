package cn.iocoder.yudao.module.vehiclepass.service.leavemgmt.abnormalleave;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeavePageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.abnormalleave.vo.AbnormalLeaveRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.leavemgmt.abnormalleave.AbnormalLeaveDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.leavemgmt.abnormalleave.AbnormalLeaveMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.LEAVE_NOT_EXISTS;

/**
 * 异常离场 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AbnormalLeaveServiceImpl implements AbnormalLeaveService {

    @Resource
    private AbnormalLeaveMapper leaveMapper;

    @Override
    public Long createLeave(AbnormalLeaveSaveReqVO createReqVO) {
        // 插入
        AbnormalLeaveDO leave = BeanUtils.toBean(createReqVO, AbnormalLeaveDO.class);
        leaveMapper.insert(leave);

        // 返回
        return leave.getId();
    }

    @Override
    public void updateLeave(AbnormalLeaveSaveReqVO updateReqVO) {
        // 校验存在
        validateLeaveExists(updateReqVO.getId());
        // 更新
        AbnormalLeaveDO updateObj = BeanUtils.toBean(updateReqVO, AbnormalLeaveDO.class);
        leaveMapper.updateById(updateObj);
    }

    @Override
    public void deleteLeave(Long id) {
        // 校验存在
        validateLeaveExists(id);
        // 删除
        leaveMapper.deleteById(id);
    }

    @Override
    public void deleteLeaveListByIds(List<Long> ids) {
        // 删除
        leaveMapper.deleteByIds(ids);
    }


    private void validateLeaveExists(Long id) {
        if (leaveMapper.selectById(id) == null) {
            throw exception(LEAVE_NOT_EXISTS);
        }
    }

    @Override
    public AbnormalLeaveDO getLeave(Long id) {
        return leaveMapper.selectById(id);
    }

    @Override
    public PageResult<AbnormalLeaveDO> getLeavePage(AbnormalLeavePageReqVO pageReqVO) {
        return leaveMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<AbnormalLeaveRespVO> getLeavePageWithJoin(AbnormalLeavePageReqVO pageReqVO) {
        // 构建分页参数
        Page<AbnormalLeaveRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        // 调用JOIN查询
        com.baomidou.mybatisplus.core.metadata.IPage<AbnormalLeaveRespVO> pageResult = leaveMapper.selectPageJoin(page, pageReqVO);
        // 转换为PageResult
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

}