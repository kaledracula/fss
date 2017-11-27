package com.cony.projects.fss.dictionary.controller;

import com.cony.projects.fss.dictionary.common.DicConstants;
import com.cony.projects.fss.dictionary.entity.Dictionary;
import com.cony.projects.fss.dictionary.entity.DictionaryItem;
import com.cony.projects.fss.dictionary.service.IDictionaryItemService;
import com.cony.projects.fss.dictionary.service.IDictionaryService;
import com.cony.web.common.result.DefaultResult;
import com.cony.web.common.result.Result;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 描述：Dictionary控制层
*/
@RestController
@RequestMapping("/dictionary")
@Api(value = "DictionaryController", description = "Dictionary API")
public class DictionaryController  {

    @Autowired
    private IDictionaryItemService dictionaryItemService;

    /**
     * 描述：获取
     */
    public Result getItemsByDicCode() {
        Map<String,Object> map = new HashMap<>();
        map.put("dictCode", DicConstants.DEPARTMENT_TYPE_CODE);
        return new DefaultResult<List<DictionaryItem>>().setData(dictionaryItemService.query(map));
    }
}
