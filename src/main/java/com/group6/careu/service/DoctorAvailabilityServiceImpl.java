package com.group6.careu.service;

import com.group6.careu.entity.Doctor;
import com.group6.careu.entity.DoctorAvailability;
import com.group6.careu.entity.User;
import com.group6.careu.model.AppointmentModel;
import com.group6.careu.model.DoctorAvailabilityModel;
import com.group6.careu.repository.DoctorAvailabilityRepository;
import com.group6.careu.repository.DoctorRepository;
import com.group6.careu.repository.UserRepository;
import com.group6.careu.security.CareuUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DoctorAvailabilityServiceImpl implements DoctorAvailabilityService{

    @Autowired
    DoctorAvailabilityRepository doctorAvailabilityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public List<DoctorAvailability> getAllTimes(Integer doctor_id) {
        return doctorAvailabilityRepository.getAvailableTimes(doctor_id);
    }

    @Override
    public void saveAvailability(DoctorAvailabilityModel model){
        CareuUserDetails userDetails= (CareuUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.getUserByDoctorId(userDetails.getId());
        Optional<Doctor> doctorOptional = doctorRepository.findById(user.getDoctor().getDoctor_id());
        model.setDoctorId(doctorOptional.get().getDoctor_id());
        System.out.println(doctorOptional.get().getDoctor_id());
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

            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            java.util.Date date = null;
            try {
                date = format.parse(model.getAvailableDate().get(i));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Date sqlDate = new java.sql.Date(date.getTime());
            entity.setAvailableDate(sqlDate);

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            long ms1 = 0;
            try {
                ms1 = sdf.parse(model.getStartTime().get(i)).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Time t1 = new Time(ms1);
            entity.setStartTime(t1);

            long ms2 = 0;
            try {
                ms2 = sdf.parse(model.getEndTime().get(i)).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Time t2 = new Time(ms2);
            entity.setEndTime(t2);
            /*
            entity.setAvailableDate(model.getAvailableDate().get(i).toString());
            entity.setStartTime(model.getStartTime().get(i).toString());
            entity.setEndTime(model.getEndTime().get(i).toString());
            */
            list.add(entity);
            System.out.println("In line 43");
            System.out.println("Availability Date:"+model.getAvailableDate().get(i));
            System.out.println("Start Time:"+model.getStartTime().get(i));
            System.out.println("End Time:"+model.getEndTime().get(i));
        }

        return list;
    }

    @Override
    public List<DoctorAvailability> getAvailableTimesOfDoctor(DoctorAvailabilityModel doctorAvailability) {
        String available_date = doctorAvailability.getAvailableDate().get(0).replaceFirst("=", "");
        return doctorAvailabilityRepository.getAvailableTimeOfDoctorBasedOnDate(doctorAvailability.getDoctorId(), Date.valueOf(available_date));
    }

    @Override
    public void updateBookedAppointment(AppointmentModel appointmentModel, boolean appointmentBooked) {
        Time startTime = null;
        Time endTime = null;
        if (appointmentModel.getTime().contains("to")) {
            String[] arr = appointmentModel.getTime().split("to");
            startTime = Time.valueOf(arr[0].trim());
            endTime = Time.valueOf(arr[1].trim());
        } else {
            startTime = Time.valueOf(appointmentModel.getTime());
        }
        doctorAvailabilityRepository.updateBookedAppointment(appointmentModel.getDoctor_id(), appointmentModel.getAppointment_date(), startTime, appointmentBooked);
    }
}
