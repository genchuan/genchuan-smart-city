package cn.iocoder.yudao.module.vehiclepass.service.entermgmt.unplateenter;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.util.string.StrUtils;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo.UnplateEnterVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.entermgmt.unplateenter.UnplateEnterDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.entermgmt.unplateenter.UnplateEnterMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.ENTER_NOT_EXISTS;

/**
 * 无牌入场 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class UnplateEnterServiceImpl implements UnplateEnterService {

    @Resource
    private UnplateEnterMapper enterMapper;

    @Override
    public Long createEnter(UnplateEnterSaveReqVO createReqVO) {
        // 插入
        UnplateEnterDO enter = BeanUtils.toBean(createReqVO, UnplateEnterDO.class);
        enterMapper.insert(enter);

        // 返回
        return enter.getId();
    }

    @Override
    public void updateEnter(UnplateEnterSaveReqVO updateReqVO) {
        // 校验存在
        validateEnterExists(updateReqVO.getId());
        // 更新
        UnplateEnterDO updateObj = BeanUtils.toBean(updateReqVO, UnplateEnterDO.class);
        enterMapper.updateById(updateObj);
    }

    @Override
    public void deleteEnter(Long id) {
        // 校验存在
        validateEnterExists(id);
        // 删除
        enterMapper.deleteById(id);
    }

    @Override
    public void deleteEnterListByIds(List<Long> ids) {
        // 删除
        enterMapper.deleteByIds(ids);
    }


    private void validateEnterExists(Long id) {
        if (enterMapper.selectById(id) == null) {
            throw exception(ENTER_NOT_EXISTS);
        }
    }

    @Override
    public UnplateEnterDO getEnter(Long id) {
        return enterMapper.selectById(id);
    }

    @Override
    public PageResult<UnplateEnterDO> getEnterPage(UnplateEnterPageReqVO pageReqVO) {
        return enterMapper.selectPage(pageReqVO);
    }
    @Override
    public PageResult<UnplateEnterRespVO> getUnplateEnterPage(UnplateEnterPageReqVO reqVO) {
        // 1. 构建分页对象
        Page<UnplateEnterRespVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());

        // 2. 查询（使用 IPage 参数）
        IPage<UnplateEnterRespVO> pageResult = enterMapper.selectPageJoinStation(page, reqVO);

        // 3. 脱敏处理（安全处理）
        List<UnplateEnterRespVO> list = pageResult.getRecords().stream()
                .peek(vo -> {
                    String phone = vo.getPhone();
                    if (phone != null && phone.length() == 11) {
                        vo.setPhone(phone.substring(0, 3) + "****" + phone.substring(7));
                    }
                })
                .collect(Collectors.toList());

        // 4. 返回最终结果
        return new PageResult<>(list, pageResult.getTotal());
    }

}