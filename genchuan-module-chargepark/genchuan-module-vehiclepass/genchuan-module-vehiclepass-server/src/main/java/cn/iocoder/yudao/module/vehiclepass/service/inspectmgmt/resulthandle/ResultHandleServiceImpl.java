package cn.iocoder.yudao.module.vehiclepass.service.inspectmgmt.resulthandle;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo.ResultHandlePageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo.ResultHandleRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo.ResultHandleSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo.ResultHandleBatchHandleReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo.ResultHandleApproveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo.ResultHandleRejectReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inspectmgmt.resulthandle.vo.ResultHandleExecuteReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inspectmgmt.resulthandle.ResultHandleDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.inspectmgmt.resulthandle.ResultHandleMapper;
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
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.HANDLE_NOT_EXISTS;

/**
 * 结果处置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ResultHandleServiceImpl implements ResultHandleService {

    @Resource
    private ResultHandleMapper handleMapper;

    @Override
    public Long createHandle(ResultHandleSaveReqVO createReqVO) {
        // 插入
        ResultHandleDO handle = BeanUtils.toBean(createReqVO, ResultHandleDO.class);
        handleMapper.insert(handle);

        // 返回
        return handle.getId();
    }

    @Override
    public void updateHandle(ResultHandleSaveReqVO updateReqVO) {
        // 校验存在
        validateHandleExists(updateReqVO.getId());
        // 更新
        ResultHandleDO updateObj = BeanUtils.toBean(updateReqVO, ResultHandleDO.class);
        handleMapper.updateById(updateObj);
    }

    @Override
    public void deleteHandle(Long id) {
        // 校验存在
        validateHandleExists(id);
        // 删除
        handleMapper.deleteById(id);
    }

    @Override
    public void deleteHandleListByIds(List<Long> ids) {
        // 删除
        handleMapper.deleteByIds(ids);
    }


    private void validateHandleExists(Long id) {
        if (handleMapper.selectById(id) == null) {
            throw exception(HANDLE_NOT_EXISTS);
        }
    }

    @Override
    public ResultHandleDO getHandle(Long id) {
        return handleMapper.selectById(id);
    }

    @Override
    public PageResult<ResultHandleDO> getHandlePage(ResultHandlePageReqVO pageReqVO) {
        return handleMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<ResultHandleRespVO> getHandlePageWithJoin(ResultHandlePageReqVO pageReqVO) {
        // 构建分页参数
        Page<ResultHandleRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        // 调用JOIN查询
        com.baomidou.mybatisplus.core.metadata.IPage<ResultHandleRespVO> pageResult = handleMapper.selectPageJoin(page, pageReqVO);
        // 转换为PageResult
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    public void batchHandle(ResultHandleBatchHandleReqVO reqVO) {
        for (Long id : reqVO.getIds()) {
            ResultHandleDO updateObj = new ResultHandleDO();
            updateObj.setId(id);
            updateObj.setHandleType(reqVO.getHandleType());
            updateObj.setStatus("待处置");
            handleMapper.updateById(updateObj);
        }
    }

    @Override
    public void approve(Long id) {
        // 校验存在
        validateHandleExists(id);
        // 更新为已通过
        ResultHandleDO updateObj = new ResultHandleDO();
        updateObj.setId(id);
        updateObj.setStatus("已完成");
        handleMapper.updateById(updateObj);
    }

    @Override
    public void reject(ResultHandleRejectReqVO reqVO) {
        // 校验存在
        validateHandleExists(reqVO.getId());
        // 更新为已驳回
        ResultHandleDO updateObj = new ResultHandleDO();
        updateObj.setId(reqVO.getId());
        updateObj.setStatus("已驳回");
        updateObj.setRejectReason(reqVO.getRejectReason());
        handleMapper.updateById(updateObj);
    }

    @Override
    public void execute(ResultHandleExecuteReqVO reqVO) {
        // 校验存在
        validateHandleExists(reqVO.getId());
        // 更新整改状态
        ResultHandleDO updateObj = new ResultHandleDO();
        updateObj.setId(reqVO.getId());
        updateObj.setRectifyStatus(reqVO.getRectifyStatus());
        handleMapper.updateById(updateObj);
    }

}