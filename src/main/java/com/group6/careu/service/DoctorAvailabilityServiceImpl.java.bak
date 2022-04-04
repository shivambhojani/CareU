package com.group6.careu.service;

import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.DoctorAvailability;
import com.group6.careu.model.DoctorAvailabilityModel;
import com.group6.careu.repository.DoctorAvailabilityRepository;
import com.group6.careu.security.CareuUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DoctorAvailabilityServiceImpl implements DoctorAvailabilityService{

    @Autowired
    DoctorAvailabilityRepository doctorAvailabilityRepository;

    @Override
    public List<DoctorAvailability> getAllTimes(Integer doctor_id) {
        return doctorAvailabilityRepository.getAvailableTimes(doctor_id);
    }

    @Override
    public void saveAvailability(DoctorAvailabilityModel model){
        CareuUserDetails userDetails= (CareuUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.setDoctorId(userDetails.getId());
        System.out.println(userDetails.getId());
        List<DoctorAvailability> list=getEntites(model);
        System.out.println("In line 25");
        doctorAvailabilityRepository.saveAll(list);
    }

    @Override
    public List<DoctorAvailability> getEntites(DoctorAvailabilityModel model) {
        List<DoctorAvailability> list = new ArrayList<DoctorAvailability>();
        System.out.println("In line 30");
        for (int i=0; i<model.getAvailableDate().size(); i++) {
            DoctorAvailability entity = new DoctorAvailability();
            System.out.println("In line 34");
            Doctor d=new Doctor();
            d.setDoctor_id(model.getDoctorId());

            entity.setDoctor(d);
            entity.setAvailableDate(model.getAvailableDate().get(i).toString());
            entity.setStartTime(model.getStartTime().get(i).toString());
            entity.setEndTime(model.getEndTime().get(i).toString());
            list.add(entity);
            System.out.println("In line 43");
            System.out.println("Availability Date:"+model.getAvailableDate().get(i));
            System.out.println("Start Time:"+model.getStartTime().get(i));
            System.out.println("End Time:"+model.getEndTime().get(i));
        }

        return list;
    }
}
