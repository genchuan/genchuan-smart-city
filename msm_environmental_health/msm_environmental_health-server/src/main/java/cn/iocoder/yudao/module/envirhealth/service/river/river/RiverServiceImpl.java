package cn.iocoder.yudao.module.envirhealth.service.river.river;

import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.river.RiverPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.river.vo.river.RiverSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.detail.RiverDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.river.RiverDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.river.RiverMapper;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 河道 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class RiverServiceImpl implements RiverService {

    @Resource
    private RiverMapper riverMapper;

    @Override
    public Long createRiver(RiverSaveReqVO createReqVO) {
        // 插入
        RiverDO river = BeanUtils.toBean(createReqVO, RiverDO.class);
        riverMapper.insert(river);
        // 返回
        return river.getId();
    }

    @Override
    public void updateRiver(RiverSaveReqVO updateReqVO) {
        // 校验存在
        validateRiverExists(updateReqVO.getId());
        // 更新
        RiverDO updateObj = BeanUtils.toBean(updateReqVO, RiverDO.class);
        riverMapper.updateById(updateObj);
    }

    @Override
    public void deleteRiver(Long id) {
        // 校验存在
        validateRiverExists(id);
        // 删除
        riverMapper.deleteById(id);
    }

    private void validateRiverExists(Long id) {
        if (riverMapper.selectById(id) == null) {
            throw exception(RIVER_NOT_EXISTS);
        }
    }

    @Override
    public RiverDO getRiver(Long id) {
        return riverMapper.selectById(id);
    }

    @Override
    public PageResult<RiverDO> getRiverPage(RiverPageReqVO pageReqVO) {
        return riverMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<RiverDetailDO> getRiverDetailPage(RiverPageReqVO pageReqVO) {
        Long total = riverMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<RiverDetailDO> list = riverMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }
}