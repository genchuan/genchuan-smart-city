package cn.iocoder.yudao.module.envirhealth.service.park.park;

import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.park.ParkPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.park.vo.park.ParkSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.detail.ParkDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.park.ParkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.park.ParkMapper;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 公园 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ParkServiceImpl implements ParkService {

    @Resource
    private ParkMapper parkMapper;

    @Override
    public Long createPark(ParkSaveReqVO createReqVO) {
        // 插入
        ParkDO park = BeanUtils.toBean(createReqVO, ParkDO.class);
        parkMapper.insert(park);
        // 返回
        return park.getId();
    }

    @Override
    public void updatePark(ParkSaveReqVO updateReqVO) {
        // 校验存在
        validateParkExists(updateReqVO.getId());
        // 更新
        ParkDO updateObj = BeanUtils.toBean(updateReqVO, ParkDO.class);
        parkMapper.updateById(updateObj);
    }

    @Override
    public void deletePark(Long id) {
        // 校验存在
        validateParkExists(id);
        // 删除
        parkMapper.deleteById(id);
    }

    private void validateParkExists(Long id) {
        if (parkMapper.selectById(id) == null) {
            throw exception(PARK_NOT_EXISTS);
        }
    }

    @Override
    public ParkDO getPark(Long id) {
        return parkMapper.selectById(id);
    }

    @Override
    public PageResult<ParkDO> getParkPage(ParkPageReqVO pageReqVO) {
        return parkMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<ParkDetailDO> getParkDetailPage(ParkPageReqVO pageReqVO) {
        Long total = parkMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<ParkDetailDO> list = parkMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }
}