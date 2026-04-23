package cn.iocoder.yudao.module.vehiclepass.service.specialpass.gateopen;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.specialpass.gateopen.vo.GateOpenSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.specialpass.gateopen.GateOpenDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.specialpass.gateopen.GateOpenMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.OPEN_NOT_EXISTS;


/**
 * 开闸管理 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class GateOpenServiceImpl implements GateOpenService {

    @Resource
    private GateOpenMapper openMapper;

    @Override
    public Long createOpen(GateOpenSaveReqVO createReqVO) {
        // 插入
        GateOpenDO open = BeanUtils.toBean(createReqVO, GateOpenDO.class);
        openMapper.insert(open);

        // 返回
        return open.getId();
    }

    @Override
    public void updateOpen(GateOpenSaveReqVO updateReqVO) {
        // 校验存在
        validateOpenExists(updateReqVO.getId());
        // 更新
        GateOpenDO updateObj = BeanUtils.toBean(updateReqVO, GateOpenDO.class);
        openMapper.updateById(updateObj);
    }

    @Override
    public void deleteOpen(Long id) {
        // 校验存在
        validateOpenExists(id);
        // 删除
        openMapper.deleteById(id);
    }

    @Override
    public void deleteOpenListByIds(List<Long> ids) {
        // 删除
        openMapper.deleteByIds(ids);
    }


    private void validateOpenExists(Long id) {
        if (openMapper.selectById(id) == null) {
            throw exception(OPEN_NOT_EXISTS);
        }
    }

    @Override
    public GateOpenDO getOpen(Long id) {
        return openMapper.selectById(id);
    }

    @Override
    public PageResult<GateOpenDO> getOpenPage(GateOpenPageReqVO pageReqVO) {
        return openMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<GateOpenRespVO> getOpenPageWithJoin(GateOpenPageReqVO pageReqVO) {
        Page<GateOpenRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        IPage<GateOpenRespVO> pageResult = openMapper.selectPageJoin(page, pageReqVO);
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

}