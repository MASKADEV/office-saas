package com.project.office.service;

import com.project.office.entity.Service;
import com.project.office.entity.Team;
import com.project.office.repository.ServiceRepository;
import com.project.office.repository.TeamRepoository;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceSerImpl implements ServiceSer{

    private final ServiceRepository serRepository;
    private final TeamRepoository teamRepoository;

    public ServiceSerImpl(ServiceRepository serRepository, TeamRepoository teamRepoository) {
        this.serRepository = serRepository;
        this.teamRepoository = teamRepoository;
    }

    @Override
    public Service createService(Long team_id,Service ser) {
        Team team = teamRepoository.findById(team_id).orElse(null);
        if(team != null) {
            List<Team> teams= new ArrayList<>();
            teams.add(team);
            ser.setTeams(teams);
            Service results =  serRepository.save(ser);
            if(results != null){
             return results;
            }
        }
        return null;
    }

    @Override
    public Service updateService(Service service) {

        return null;
    }

    @Override
    public List<Service> findAllService(Long team_id) {
        return null;
    }

    @Override
    public Boolean deleteService(Long id) {
        Service ser = serRepository.findById(id).orElse(null);
        if(ser != null){
            serRepository.delete(ser);
        }
        return null;
    }
}
