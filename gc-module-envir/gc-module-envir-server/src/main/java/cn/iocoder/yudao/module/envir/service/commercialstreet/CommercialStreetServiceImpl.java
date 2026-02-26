package cn.iocoder.yudao.module.envir.service.commercialstreet;

import cn.iocoder.yudao.module.envir.dal.dataobject.commercialstreet.CommercialStreetDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.commercialstreet.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.commercialstreet.CommercialStreetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.commercialstreet.CommercialStreetMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

/**
 * 商业街 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CommercialStreetServiceImpl implements CommercialStreetService {

    @Resource
    private CommercialStreetMapper commercialStreetMapper;

    @Override
    public Long createCommercialStreet(CommercialStreetSaveReqVO createReqVO) {
        // 插入
        CommercialStreetDO commercialStreet = BeanUtils.toBean(createReqVO, CommercialStreetDO.class);
        commercialStreetMapper.insert(commercialStreet);
        // 返回
        return commercialStreet.getId();
    }

    @Override
    public void updateCommercialStreet(CommercialStreetSaveReqVO updateReqVO) {
        // 校验存在
        validateCommercialStreetExists(updateReqVO.getId());
        // 更新
        CommercialStreetDO updateObj = BeanUtils.toBean(updateReqVO, CommercialStreetDO.class);
        commercialStreetMapper.updateById(updateObj);
    }

    @Override
    public void deleteCommercialStreet(Long id) {
        // 校验存在
        validateCommercialStreetExists(id);
        // 删除
        commercialStreetMapper.deleteById(id);
    }

    private void validateCommercialStreetExists(Long id) {
        if (commercialStreetMapper.selectById(id) == null) {
            throw exception(COMMERCIAL_STREET_NOT_EXISTS);
        }
    }

    @Override
    public CommercialStreetDO getCommercialStreet(Long id) {
        return commercialStreetMapper.selectById(id);
    }

    @Override
    public PageResult<CommercialStreetDO> getCommercialStreetPage(CommercialStreetPageReqVO pageReqVO) {
        return commercialStreetMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CommercialStreetDetailDO> getCommercialStreetListDetail() {
        return commercialStreetMapper.selectListDetail();
    }
}