package cn.iocoder.yudao.module.industry.service.park.order.parkorderescape;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderescape.vo.ParkOrderEscapePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderescape.vo.ParkOrderEscapeSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkorderescape.ParkOrderEscapeDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.order.parkorderescape.ParkOrderEscapeMapper;
import cn.iocoder.yudao.module.industry.framework.util.lxs.importer.ImportUtils;
import cn.iocoder.yudao.module.industry.framework.util.lxs.stat.StatUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.executor.BatchResult;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import org.springframework.web.multipart.MultipartFile;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.ORDER_ESCAPE_NOT_EXISTS;


/**
 * 逃费订单 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkOrderEscapeServiceImpl implements ParkOrderEscapeService{

    @Resource
    private ParkOrderEscapeMapper orderEscapeMapper;

    @Override
    public Long createOrderEscape(ParkOrderEscapeSaveReqVO createReqVO) {
        // 插入
        ParkOrderEscapeDO orderEscape = BeanUtils.toBean(createReqVO, ParkOrderEscapeDO.class);
        orderEscapeMapper.insert(orderEscape);
        // 返回
        return orderEscape.getId();
    }

    @Override
    public void updateOrderEscape(ParkOrderEscapeSaveReqVO updateReqVO) {
        // 校验存在
        validateOrderEscapeExists(updateReqVO.getId());
        // 更新
        ParkOrderEscapeDO updateObj = BeanUtils.toBean(updateReqVO, ParkOrderEscapeDO.class);
        orderEscapeMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderEscape(Long id) {
        // 校验存在
        validateOrderEscapeExists(id);
        // 删除
        orderEscapeMapper.deleteById(id);
    }

    private void validateOrderEscapeExists(Long id) {
        if (orderEscapeMapper.selectById(id) == null) {
            throw exception(ORDER_ESCAPE_NOT_EXISTS);
        }
    }

    @Override
    public ParkOrderEscapeDO getOrderEscape(Long id) {
        return orderEscapeMapper.selectById(id);
    }

    @Override
    public PageResult<ParkOrderEscapeDO> getOrderEscapePage(ParkOrderEscapePageReqVO pageReqVO) {
        return orderEscapeMapper.selectPage(pageReqVO);
    }

    @Override
    public int insertBatch(List<ParkOrderEscapeDO> parkOrderEscapeDOList) {


        int successCount=0;
        for(ParkOrderEscapeDO parkOrderEscapeDO:parkOrderEscapeDOList){
            parkOrderEscapeDO.setId(null);
            int count = orderEscapeMapper.insert(parkOrderEscapeDO);
            successCount+=count;
        }

        return successCount;
    }

    @Override
    public Integer importExcel(MultipartFile file) {
        try {
            //1.将Excel数据转化为批量新增的数据
            List<ParkOrderEscapeDO> parkOrderEscapeDOList= ImportUtils.importExcelAndReturnEntity(file,ParkOrderEscapeDO.class.getName());
            if (parkOrderEscapeDOList.isEmpty()) {
                throw exception(new ErrorCode(500, "Excel 中没有数据"));
            }

            //2.批量入库
            int successCount=0;
            for(ParkOrderEscapeDO parkOrderEscapeDO:parkOrderEscapeDOList){
                parkOrderEscapeDO.setId(null);
                int count = orderEscapeMapper.insert(parkOrderEscapeDO);
                successCount+=count;
            }

            //3.返回成功条目
            return successCount;
        }  catch (IOException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                  InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ServiceException e){
            throw exception(new ErrorCode(500,e.getMessage()));
        }

    }



}
