package com.cony.projects.fss.basic.controller;

import com.cony.data.common.mapper.MapUtils;
import com.cony.projects.fss.basic.entity.SalesmanDto;
import com.cony.projects.fss.basic.service.ISalesmanService;
import com.cony.projects.fss.basic.entity.Salesman;
import com.cony.web.common.result.DefaultResult;
import com.cony.web.common.result.Result;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* 描述：Salesman控制层
*/
@RestController
@RequestMapping("/api/basic/salesman")
@Api(value = "SalesmanController", description = "Salesman API")
public class SalesmanController extends AbstractSpringController<Salesman,ISalesmanService> {


    @RequestMapping(value = "/getSalesmanDtoList", method = RequestMethod.POST)
    public Result getSalesmanDtoList(@Valid @RequestBody(required = false) Map<String, ? extends Object> params) {
        List<Salesman> list = getService().query(params);
        List<SalesmanDto> dtoList = new ArrayList<>();
        for(Salesman salesman : list) {
            SalesmanDto salesmanDto = new SalesmanDto();
            salesmanDto.setId(salesman.getId());
            salesmanDto.setName(salesman.getName());
            salesmanDto.setMobilePhone(salesman.getMobilePhone());
            salesmanDto.setMarketGroupName(salesman.getMarketGroup().getName());
            salesmanDto.setDistributionLocationLocation(salesman.getMarketGroup().getDistributionLocation().getLocation());
            dtoList.add(salesmanDto);
        }
        return new DefaultResult<List<SalesmanDto>>().setData(dtoList);
    }

}
