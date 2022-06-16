package com.istianfengl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.istianfengl.common.Result;
import com.istianfengl.entity.Detail;
import com.istianfengl.mapper.DetailMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/details")
public class DetailController {

    @Resource
    private DetailMapper detailMapper;

    @GetMapping
    public Result<?> getDetail(@RequestParam("id") Integer id) {
        QueryWrapper<Detail> detailQueryWrapper = new QueryWrapper<>();
        detailQueryWrapper.eq("emp_id", id);
        List<Detail> details = detailMapper.selectList(detailQueryWrapper);

        return Result.success(details);
    }
}
