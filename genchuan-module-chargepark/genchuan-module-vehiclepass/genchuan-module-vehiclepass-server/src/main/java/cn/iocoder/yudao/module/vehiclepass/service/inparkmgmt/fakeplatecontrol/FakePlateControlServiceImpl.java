package cn.iocoder.yudao.module.vehiclepass.service.inparkmgmt.fakeplatecontrol;

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

}