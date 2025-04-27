package Controller10;

import Dto.Request.RateRequest;
import Dto.Response.RateResponse;
import Service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rate")
public class git giutRateController {


    @Autowired
    private RateService rateService;

    @PostMapping
    public RateResponse addRate(@RequestBody RateRequest rateRequest){
        return rateService.addRate(rateRequest);
    }
    @GetMapping("/{idHotel}")
    public List<RateResponse> getAll(@PathVariable Long idHotel){
        return rateService.getAll(idHotel);
    }

}
