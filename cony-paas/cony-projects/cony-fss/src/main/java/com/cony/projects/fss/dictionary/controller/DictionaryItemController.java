package com.cony.projects.fss.dictionary.controller;

import com.cony.projects.fss.dictionary.common.DicConstants;
import com.cony.projects.fss.dictionary.entity.DictionaryItem;
import com.cony.projects.fss.dictionary.service.IDictionaryItemService;
import com.cony.web.common.result.DefaultResult;
import com.cony.web.common.result.Result;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 描述：DictionaryItem控制层
*/
@RestController
@RequestMapping("/dictionaryItem")
@Api(value = "DictionaryItemController", description = "DictionaryItem API")
public class DictionaryItemController extends AbstractSpringController<DictionaryItem,IDictionaryItemService> {


}
