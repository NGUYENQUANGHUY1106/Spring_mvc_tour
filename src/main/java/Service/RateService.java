package Service;


import Dto.Request.RateRequest;
import Dto.Response.RateResponse;
import Entity.Customer;
import Entity.Hotel;
import Entity.Rate;
import MapperData.RateMapper;
import Repository.CustomerRepository;
import Repository.HotelRepository;
import Repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RateService {
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private RateMapper rateMapper;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private HotelRepository hotelRepository;

    public RateResponse addRate(RateRequest rateRequest){
        Customer customer = customerRepository.findByUserId(rateRequest.getIdUser());
        Hotel hotel = hotelRepository.findById(rateRequest.getIdHotel()).get();

        Rate rate = rateMapper.toRate(rateRequest);
        rate.setCustomer(customer);
        rate.setHotel(hotel);
        return  rateMapper.toRateResponse(rateRepository.save(rate));
    }
    public List<RateResponse> getAll(Long idHotel){
        List<Rate> rates = rateRepository.findAllByHotelId(idHotel);
        return  rates.stream().map(rateMapper::toRateResponse).collect(Collectors.toList());
    }
}
