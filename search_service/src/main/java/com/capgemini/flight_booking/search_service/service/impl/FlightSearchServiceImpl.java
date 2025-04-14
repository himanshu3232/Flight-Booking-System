package com.capgemini.flight_booking.search_service.service.impl;

import com.capgemini.flight_booking.search_service.dto.FlightDto;
import com.capgemini.flight_booking.search_service.dto.mapper.Mapper;
import com.capgemini.flight_booking.search_service.entity.FlightEntity;
import com.capgemini.flight_booking.search_service.exception.InvalidFlightIdException;
import com.capgemini.flight_booking.search_service.repository.FlightRepository;
import com.capgemini.flight_booking.search_service.service.IFlightSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightSearchServiceImpl implements IFlightSearchService {

    private final FlightRepository flightRepository;


    /**
     * @param from onboarding airport
     * @param to destination airport
     * @return list of flights available from onboarding airport to destination on a particular day
     */
    @Override
    public List<FlightDto> getFlights(String from, String to) {
        List<FlightEntity> flightEntityList = flightRepository.getFlightsByFromAndTo(from, to);
        return flightEntityList.stream().map(Mapper::mapToFlightDto).toList();
    }

    /**
     * @return list of all available flights
     */
    @Override
    public List<FlightDto> getAllFlights() {
        return flightRepository.findAll().stream().map(Mapper::mapToFlightDto).toList();
    }

    /**
     * @param flightId unique flightId
     * @return FlightDto of a unique flight
     */
    @Override
    public FlightDto getFlightById(long flightId) {
        return Mapper.mapToFlightDto(flightRepository.findById(flightId).orElseThrow());
    }


    /**
     * @param flightDto with flight id and number of seats previously available
     */
    @Override
    public void updateSeats(FlightDto flightDto) {
        FlightEntity flightEntity = flightRepository
                .findById(flightDto.flightId())
                .orElseThrow(() -> new InvalidFlightIdException("Invalid flight ID: " + flightDto.flightId()));
        flightEntity.setSeatsAvailable(flightDto.seatsAvailable()-1);
        flightRepository.save(flightEntity);
    }
}
