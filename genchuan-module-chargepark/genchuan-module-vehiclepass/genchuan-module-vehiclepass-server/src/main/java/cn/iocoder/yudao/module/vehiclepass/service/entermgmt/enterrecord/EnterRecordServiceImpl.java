package cn.iocoder.yudao.module.vehiclepass.service.entermgmt.enterrecord;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo.EnterRecordPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo.EnterRecordSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo.MyEnterRecordPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.enterrecord.vo.MyEnterRecordRespVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.entermgmt.enterrecord.EnterRecordDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.entermgmt.enterrecord.EnterRecordMapper;
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
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.RECORD_NOT_EXISTS;

/**
 * 入场记录 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EnterRecordServiceImpl implements EnterRecordService {

    @Resource
    private EnterRecordMapper enterRecordMapper;

    @Override
    public Long createRecord(EnterRecordSaveReqVO createReqVO) {
        // 插入
        EnterRecordDO record = BeanUtils.toBean(createReqVO, EnterRecordDO.class);
        enterRecordMapper.insert(record);

        // 返回
        return record.getId();
    }

    @Override
    public void updateRecord(EnterRecordSaveReqVO updateReqVO) {
        // 校验存在
        validateRecordExists(updateReqVO.getId());
        // 更新
        EnterRecordDO updateObj = BeanUtils.toBean(updateReqVO, EnterRecordDO.class);
        enterRecordMapper.updateById(updateObj);
    }

    @Override
    public void deleteRecord(Long id) {
        // 校验存在
        validateRecordExists(id);
        // 删除
        enterRecordMapper.deleteById(id);
    }

    @Override
    public void deleteRecordListByIds(List<Long> ids) {
        // 删除
        enterRecordMapper.deleteByIds(ids);
    }


    private void validateRecordExists(Long id) {
        if (enterRecordMapper.selectById(id) == null) {
            throw exception(RECORD_NOT_EXISTS);
        }
    }

    @Override
    public EnterRecordDO getRecord(Long id) {
        return enterRecordMapper.selectById(id);
    }

    @Override
    public PageResult<EnterRecordDO> getRecordPage(EnterRecordPageReqVO pageReqVO) {
        return enterRecordMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<MyEnterRecordRespVO> getEnterRecordPage(MyEnterRecordPageReqVO reqVO) {
        Page<MyEnterRecordRespVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        IPage<MyEnterRecordRespVO> iPage = enterRecordMapper.selectEnterRecordPage(page, reqVO);
        return new PageResult<>(iPage.getRecords(), iPage.getTotal());
    }


}