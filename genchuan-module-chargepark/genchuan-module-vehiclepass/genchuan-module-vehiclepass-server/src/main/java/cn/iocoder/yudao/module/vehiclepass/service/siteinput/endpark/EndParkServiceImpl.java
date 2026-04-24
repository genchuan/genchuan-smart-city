package cn.iocoder.yudao.module.vehiclepass.service.siteinput.endpark;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo.EndParkPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo.EndParkRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo.EndParkSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo.EndParkPayReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo.EndParkConfirmReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.endpark.vo.EndParkCancelReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.siteinput.endpark.EndParkDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.siteinput.endpark.EndParkMapper;
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
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.PARK_NOT_EXISTS;


/**
 * 结束停车 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EndParkServiceImpl implements EndParkService {

    @Resource
    private EndParkMapper parkMapper;

    @Override
    public Long createPark(EndParkSaveReqVO createReqVO) {
        // 插入
        EndParkDO park = BeanUtils.toBean(createReqVO, EndParkDO.class);
        parkMapper.insert(park);

        // 返回
        return park.getId();
    }

    @Override
    public void updatePark(EndParkSaveReqVO updateReqVO) {
        // 校验存在
        validateParkExists(updateReqVO.getId());
        // 更新
        EndParkDO updateObj = BeanUtils.toBean(updateReqVO, EndParkDO.class);
        parkMapper.updateById(updateObj);
    }

    @Override
    public void deletePark(Long id) {
        // 校验存在
        validateParkExists(id);
        // 删除
        parkMapper.deleteById(id);
    }

    @Override
    public void deleteParkListByIds(List<Long> ids) {
        // 删除
        parkMapper.deleteByIds(ids);
    }

    @Override
    public EndParkDO getPark(Long id) {
        return parkMapper.selectById(id);
    }

    @Override
    public PageResult<EndParkDO> getParkPage(EndParkPageReqVO pageReqVO) {
        return parkMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<EndParkRespVO> getParkPageWithJoin(EndParkPageReqVO pageReqVO) {
        Page<EndParkRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        com.baomidou.mybatisplus.core.metadata.IPage<EndParkRespVO> pageResult = parkMapper.selectPageJoin(page, pageReqVO);
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    public void pay(EndParkPayReqVO payReqVO) {
        EndParkDO park = validateParkExists(payReqVO.getId());
        park.setStatus("已支付");
        parkMapper.updateById(park);
    }

    @Override
    public void confirm(EndParkConfirmReqVO confirmReqVO) {
        EndParkDO park = validateParkExists(confirmReqVO.getId());
        park.setStatus("已确认");
        parkMapper.updateById(park);
    }

    @Override
    public void cancel(EndParkCancelReqVO cancelReqVO) {
        EndParkDO park = validateParkExists(cancelReqVO.getId());
        park.setStatus("已取消");
        park.setRemark(cancelReqVO.getCancelReason());
        parkMapper.updateById(park);
    }

    private EndParkDO validateParkExists(Long id) {
        EndParkDO park = parkMapper.selectById(id);
        if (park == null) {
            throw exception(PARK_NOT_EXISTS);
        }
        return park;
    }

}