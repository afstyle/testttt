package com.huanghao.${module}.controller.admin;

import com.huanghao.server.dto.${Domain}DTO;
import com.huanghao.server.dto.commons.PageDTO;
import com.huanghao.server.service.${Domain}Service;
import com.huanghao.server.util.CommonUtil;
import com.huanghao.server.util.ResultVOUtil;
import com.huanghao.server.util.ValidatorUtil;
import com.huanghao.server.vo.commons.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/${domain}")
public class ${Domain}Controller {


    private static final Logger LOG = LoggerFactory.getLogger(${Domain}Controller.class);
    public static final String BUSINESS_NAME = "${tableNameCn}";


    @Resource
    private ${Domain}Service ${domain}Service;

    /**
     * 列表查询
     */
    @PostMapping("/list${Domain}")
    public ResultVO list${Domain}(@RequestBody PageDTO pageDto) {
        ${domain}Service.list${Domain}(pageDto);
        return ResultVOUtil.success(pageDto);
    }

    /**
     * 保存
     */
    @PostMapping("/save${Domain}")
    public ResultVO save${Domain}(@RequestBody ${Domain}DTO ${domain}Dto) {

        // 校验

        try {
            ${domain}Service.save${Domain}(${domain}Dto);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return ResultVOUtil.error(e.getMessage());
        }
        return ResultVOUtil.success(${domain}Dto);
    }

    /**
     * 单个查询
     */
    @GetMapping("/get${Domain}/{id}")
    public ResultVO get${Domain}(@PathVariable String id) {
        // 校验
        ValidatorUtil.require(id, "id");

        ${Domain}DTO ${domain}DTO = ${domain}Service.get${Domain}(id);
        return ResultVOUtil.success(${domain}DTO);
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete${Domain}/{ids}")
    public ResultVO delete${Domain}(@PathVariable String ids) {
        // 校验
        ValidatorUtil.require(ids, "ids");

        List<String> idList = CommonUtil.splitString2List(ids);
        if (CollectionUtils.isEmpty(idList)) {
            return ResultVOUtil.error(String.format("参数异常，ids = %s", ids));
        }
        idList.forEach(id -> {
            ${domain}Service.delete${Domain}(id);
        });
        return ResultVOUtil.success();
    }

}
