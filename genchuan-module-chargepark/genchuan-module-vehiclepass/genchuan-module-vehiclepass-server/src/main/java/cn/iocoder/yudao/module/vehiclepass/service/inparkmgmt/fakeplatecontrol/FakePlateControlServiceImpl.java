package cn.iocoder.yudao.module.vehiclepass.service.inparkmgmt.fakeplatecontrol;

import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlBatchHandleReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.FakePlateControlSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.fakeplatecontrol.vo.MyFakePlateControlRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inparkmgmt.fakeplatecontrol.FakePlateControlDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.inparkmgmt.fakeplatecontrol.FakePlateControlMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.time.LocalDateTime;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.PLATE_CONTROL_NOT_EXISTS;

/**
 * 套牌管控 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class FakePlateControlServiceImpl implements FakePlateControlService {

    @Resource
    private FakePlateControlMapper plateControlMapper;

    @Override
    public Long createPlateControl(FakePlateControlSaveReqVO createReqVO) {
        // 插入
        FakePlateControlDO plateControl = BeanUtils.toBean(createReqVO, FakePlateControlDO.class);
        plateControlMapper.insert(plateControl);

        // 返回
        return plateControl.getId();
    }

    @Override
    public void updatePlateControl(FakePlateControlSaveReqVO updateReqVO) {
        // 校验存在
        validatePlateControlExists(updateReqVO.getId());
        // 更新
        FakePlateControlDO updateObj = BeanUtils.toBean(updateReqVO, FakePlateControlDO.class);
        plateControlMapper.updateById(updateObj);
    }

    @Override
    public void deletePlateControl(Long id) {
        // 校验存在
        validatePlateControlExists(id);
        // 删除
        plateControlMapper.deleteById(id);
    }

    @Override
    public void deletePlateControlListByIds(List<Long> ids) {
        // 删除
        plateControlMapper.deleteByIds(ids);
    }


    private void validatePlateControlExists(Long id) {
        if (plateControlMapper.selectById(id) == null) {
            throw exception(PLATE_CONTROL_NOT_EXISTS);
        }
    }

    @Override
    public FakePlateControlDO getPlateControl(Long id) {
        return plateControlMapper.selectById(id);
    }

    @Override
    public PageResult<FakePlateControlDO> getPlateControlPage(FakePlateControlPageReqVO pageReqVO) {
        return plateControlMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<MyFakePlateControlRespVO> getFakePlateControlPage(FakePlateControlPageReqVO pageReqVO) {
        Page<MyFakePlateControlRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        IPage<MyFakePlateControlRespVO> pageResult = plateControlMapper.selectPageJoinStationUser(page, pageReqVO);
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    public void batchHandle(FakePlateControlBatchHandleReqVO reqVO) {
        // 获取当前登录用户ID（这里暂时使用固定值，实际应从 SecurityUtils 获取）
        Long currentUserId = SecurityFrameworkUtils.getLoginUserId();

        for (Long id : reqVO.getIds()) {
            // 校验记录存在
            FakePlateControlDO plateControl = plateControlMapper.selectById(id);
            if (plateControl == null) {
                continue;
            }

            // 更新记录
            FakePlateControlDO updateObj = new FakePlateControlDO();
            updateObj.setId(id);
            updateObj.setHandleUserId(currentUserId);
            updateObj.setHandleTime(LocalDateTime.now());

            String handleType = reqVO.getHandleType();
            if ("核查".equals(handleType)) {
                // 核查：更新处置状态为处理中，处置进度为"已核查"
                updateObj.setStatus("处理中");
                updateObj.setHandleProgress("已核查");
                updateObj.setHandleType("核查");
            } else if ("忽略".equals(handleType)) {
                // 忽略：更新处置状态为已关闭，忽略理由为"批量忽略"
                updateObj.setStatus("已关闭");
                updateObj.setIgnoreReason("批量忽略");
                updateObj.setHandleType("忽略");
            }

            plateControlMapper.updateById(updateObj);
        }
    }

}