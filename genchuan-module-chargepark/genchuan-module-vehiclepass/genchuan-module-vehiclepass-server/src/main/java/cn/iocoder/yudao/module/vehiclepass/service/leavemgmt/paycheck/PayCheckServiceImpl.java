package cn.iocoder.yudao.module.vehiclepass.service.leavemgmt.paycheck;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo.PayCheckPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo.PayCheckSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.leavemgmt.paycheck.vo.PayCheckRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.leavemgmt.paycheck.PayCheckDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.leavemgmt.paycheck.PayCheckMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.CHECK_NOT_EXISTS;

/**
 * 缴费核验 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PayCheckServiceImpl implements PayCheckService {

    @Resource
    private PayCheckMapper checkMapper;

    @Override
    public Long createCheck(PayCheckSaveReqVO createReqVO) {
        // 插入
        PayCheckDO check = BeanUtils.toBean(createReqVO, PayCheckDO.class);
        checkMapper.insert(check);

        // 返回
        return check.getId();
    }

    @Override
    public void updateCheck(PayCheckSaveReqVO updateReqVO) {
        // 校验存在
        validateCheckExists(updateReqVO.getId());
        // 更新
        PayCheckDO updateObj = BeanUtils.toBean(updateReqVO, PayCheckDO.class);
        checkMapper.updateById(updateObj);
    }

    @Override
    public void deleteCheck(Long id) {
        // 校验存在
        validateCheckExists(id);
        // 删除
        checkMapper.deleteById(id);
    }

    @Override
    public void deleteCheckListByIds(List<Long> ids) {
        // 删除
        checkMapper.deleteByIds(ids);
    }


    private void validateCheckExists(Long id) {
        if (checkMapper.selectById(id) == null) {
            throw exception(CHECK_NOT_EXISTS);
        }
    }

    @Override
    public PayCheckDO getCheck(Long id) {
        return checkMapper.selectById(id);
    }

    @Override
    public PageResult<PayCheckDO> getCheckPage(PayCheckPageReqVO pageReqVO) {
        return checkMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<PayCheckRespVO> getCheckPageWithJoin(PayCheckPageReqVO pageReqVO) {
        // 构建分页参数
        Page<PayCheckRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        // 调用JOIN查询
        com.baomidou.mybatisplus.core.metadata.IPage<PayCheckRespVO> pageResult = checkMapper.selectPageJoin(page, pageReqVO);
        // 转换为PageResult
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

}