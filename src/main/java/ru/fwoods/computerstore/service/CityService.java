package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.City;
import ru.fwoods.computerstore.repository.CityRepository;
import ru.fwoods.computerstore.repository.CountryRepository;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    public void save(ru.fwoods.computerstore.model.City city) {
        City cityDomain = new City();
        cityDomain.setName(city.getName());
        cityDomain.setCountry(countryRepository.getOne(city.getCountry()));
        cityRepository.save(cityDomain);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCity(Long id) {
        return cityRepository.getOne(id);
    }

    public void deleteCity(Long id) {
        City city = cityRepository.getOne(id);
        city.setCountry(null);
        cityRepository.delete(city);
    }

    public Page<City> getPageCities(Pageable pageable) {
        return cityRepository.findAll(pageable);
    }

    public City getCityByName(String name) {
        return cityRepository.getCityByName(name);
    }

    public void update(ru.fwoods.computerstore.model.City city) {
        City cityDomain = cityRepository.getOne(city.getId());
        cityDomain.setName(city.getName());
        cityDomain.setCountry(countryRepository.getOne(city.getCountry()));
        cityRepository.save(cityDomain);
    }
}
