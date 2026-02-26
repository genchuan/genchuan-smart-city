package cn.iocoder.yudao.module.evaluate.service.data;

import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.data.vo.DataPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.datacollect.data.vo.DataSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.data.DataDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.data.DataMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

/**
 * 上报数据 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DataServiceImpl implements DataService {

    @Resource
    private DataMapper dataMapper;

    @Override
    public Long createData(DataSaveReqVO createReqVO) {
        // 插入
        DataDO data = BeanUtils.toBean(createReqVO, DataDO.class);
        dataMapper.insert(data);

        // 返回
        return data.getId();
    }

    @Override
    public void updateData(DataSaveReqVO updateReqVO) {
        // 校验存在
        validateDataExists(updateReqVO.getId());
        // 更新
        DataDO updateObj = BeanUtils.toBean(updateReqVO, DataDO.class);
        dataMapper.updateById(updateObj);
    }

    @Override
    public void deleteData(Long id) {
        // 校验存在
        validateDataExists(id);
        // 删除
        dataMapper.deleteById(id);
    }

    @Override
        public void deleteDataListByIds(List<Long> ids) {
        // 删除
        dataMapper.deleteByIds(ids);
        }


    private void validateDataExists(Long id) {
        if (dataMapper.selectById(id) == null) {
            throw exception(DATA_NOT_EXISTS);
        }
    }

    @Override
    public DataDO getData(Long id) {
        return dataMapper.selectById(id);
    }

    @Override
    public PageResult<DataDO> getDataPage(DataPageReqVO pageReqVO) {
        return dataMapper.selectPage(pageReqVO);
    }

}