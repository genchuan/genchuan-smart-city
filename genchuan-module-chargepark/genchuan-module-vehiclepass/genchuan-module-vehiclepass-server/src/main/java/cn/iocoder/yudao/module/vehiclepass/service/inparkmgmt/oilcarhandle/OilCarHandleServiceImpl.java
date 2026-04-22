package cn.iocoder.yudao.module.vehiclepass.service.inparkmgmt.oilcarhandle;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandleBatchHandleReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandleHandleReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandleIgnoreReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandleUpdateProgressReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandlePageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandleRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo.OilCarHandleSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inparkmgmt.oilcarhandle.OilCarHandleDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.inparkmgmt.oilcarhandle.OilCarHandleMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.time.LocalDateTime;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.CAR_HANDLE_NOT_EXISTS;

/**
 * 油车占位处置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class OilCarHandleServiceImpl implements OilCarHandleService {

    @Resource
    private OilCarHandleMapper carHandleMapper;

    @Override
    public Long createCarHandle(OilCarHandleSaveReqVO createReqVO) {
        // 插入
        OilCarHandleDO carHandle = BeanUtils.toBean(createReqVO, OilCarHandleDO.class);
        carHandleMapper.insert(carHandle);

        // 返回
        return carHandle.getId();
    }

    @Override
    public void updateCarHandle(OilCarHandleSaveReqVO updateReqVO) {
        // 校验存在
        validateCarHandleExists(updateReqVO.getId());
        // 更新
        OilCarHandleDO updateObj = BeanUtils.toBean(updateReqVO, OilCarHandleDO.class);
        carHandleMapper.updateById(updateObj);
    }

    @Override
    public void deleteCarHandle(Long id) {
        // 校验存在
        validateCarHandleExists(id);
        // 删除
        carHandleMapper.deleteById(id);
    }

    @Override
    public void deleteCarHandleListByIds(List<Long> ids) {
        // 删除
        carHandleMapper.deleteByIds(ids);
    }


    private void validateCarHandleExists(Long id) {
        if (carHandleMapper.selectById(id) == null) {
            throw exception(CAR_HANDLE_NOT_EXISTS);
        }
    }

    @Override
    public OilCarHandleDO getCarHandle(Long id) {
        return carHandleMapper.selectById(id);
    }

    @Override
    public PageResult<OilCarHandleDO> getCarHandlePage(OilCarHandlePageReqVO pageReqVO) {
        return carHandleMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<OilCarHandleRespVO> getCarHandlePageWithJoin(OilCarHandlePageReqVO pageReqVO) {
        Page<OilCarHandleRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        IPage<OilCarHandleRespVO> pageResult = carHandleMapper.selectPageJoin(page, pageReqVO);
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    public void batchHandle(OilCarHandleBatchHandleReqVO reqVO) {
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();

        for (Long id : reqVO.getIds()) {
            OilCarHandleDO carHandle = carHandleMapper.selectById(id);
            if (carHandle == null) {
                continue;
            }

            OilCarHandleDO updateObj = new OilCarHandleDO();
            updateObj.setId(id);
            updateObj.setHandleUserId(currentUserId);
            updateObj.setHandleTime(LocalDateTime.now());

            String handleType = reqVO.getHandleType();
            if ("处置".equals(handleType)) {
                updateObj.setStatus("处理中");
                updateObj.setHandleMethod("已处置");
                updateObj.setHandleType("处置");
            } else if ("忽略".equals(handleType)) {
                updateObj.setStatus("已关闭");
                updateObj.setIgnoreReason("批量忽略");
                updateObj.setHandleType("忽略");
            }

            carHandleMapper.updateById(updateObj);
        }
    }

    @Override
    public void handle(OilCarHandleHandleReqVO reqVO) {
        OilCarHandleDO carHandle = carHandleMapper.selectById(reqVO.getId());
        if (carHandle == null) {
            throw exception(CAR_HANDLE_NOT_EXISTS);
        }

        OilCarHandleDO updateObj = new OilCarHandleDO();
        updateObj.setId(reqVO.getId());
        updateObj.setHandleUserId(SecurityFrameworkUtils.getLoginUserId());
        updateObj.setHandleTime(LocalDateTime.now());
        updateObj.setStatus("处理中");
        updateObj.setHandleMethod(reqVO.getHandleMethod());
        updateObj.setHandleType("处置");
        carHandleMapper.updateById(updateObj);
    }

    @Override
    public void ignore(OilCarHandleIgnoreReqVO reqVO) {
        OilCarHandleDO carHandle = carHandleMapper.selectById(reqVO.getId());
        if (carHandle == null) {
            throw exception(CAR_HANDLE_NOT_EXISTS);
        }

        OilCarHandleDO updateObj = new OilCarHandleDO();
        updateObj.setId(reqVO.getId());
        updateObj.setHandleUserId(SecurityFrameworkUtils.getLoginUserId());
        updateObj.setHandleTime(LocalDateTime.now());
        updateObj.setStatus("已关闭");
        updateObj.setHandleType("忽略");
        updateObj.setIgnoreReason(reqVO.getIgnoreReason());
        carHandleMapper.updateById(updateObj);
    }

    @Override
    public void updateProgress(OilCarHandleUpdateProgressReqVO reqVO) {
        OilCarHandleDO carHandle = carHandleMapper.selectById(reqVO.getId());
        if (carHandle == null) {
            throw exception(CAR_HANDLE_NOT_EXISTS);
        }

        OilCarHandleDO updateObj = new OilCarHandleDO();
        updateObj.setId(reqVO.getId());
        updateObj.setHandleProgress(reqVO.getHandleProgress());
        carHandleMapper.updateById(updateObj);
    }

}